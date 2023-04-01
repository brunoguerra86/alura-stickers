import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        //String url = "https://imdb.api.com/en/API/Top250Movies/api-key";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        //System.out.println(body);

        // extrair só os dados que interessam (titulo, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        //System.out.println(listaDeFilmes.size());
        //System.out.println(listaDeFilmes.get(0));


        // exibir e manipular os dados
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println("\u001b[1mTítulo   : \u001b[m" + filme.get("title"));
            System.out.println("\u001b[1mPoster   : \u001b[m" + filme.get("image"));
            System.out.println("\u001b[1mAvaliação: \u001b[m" + filme.get("imDbRating"));

            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numeroEstrelas = (int) classificacao;

            for (int n = 0; n < numeroEstrelas; n++)
                System.out.print("⭐");
            System.out.println("\n");
        }

    }
}
