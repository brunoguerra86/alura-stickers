import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        String imdbKey = System.getenv("IMDB_API_KEY");

        // Buscar os top 250 filmes
        //String url = "https://imdb.api.com/en/API/Top250Movies/" + imdbKey;
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        // Buscar imagens da Nasa
        //String url = "http://api.nasa.gov/planetary/apod?api_key=DEMO_KEY?start_date=2023-04-01&end_date=2023-04-03";
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        // fazer uma conexão HTTP
        ClienteHttp http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        GeradoraDeFigurinhas geradoraDeFigurinhas = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++) {
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = conteudo.titulo().replaceAll(":","") + ".png";

            geradoraDeFigurinhas.cria(inputStream, nomeArquivo);

            System.out.println("\u001b[1mTítulo : \u001b[m" + conteudo.titulo());
            System.out.println("\u001b[1mImagem : \u001b[m" + conteudo.urlImagem());
            System.out.println();

        }
    }
}
