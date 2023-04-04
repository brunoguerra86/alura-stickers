import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        String imdbKey = System.getenv("IMDB_API_KEY");

        // Buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        //String url = "https://imdb.api.com/en/API/Top250Movies/" + imdbKey;

        // Buscar imagens da Nasa
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";
        //String url = "http://api.nasa.gov/planetary/apod?api_key=DEMO_KEY?start_date=2023-04-01&end_date=2023-04-03";

        // fazer uma conexão HTTP
        ClienteHttp http = new ClienteHttp();
        String json = http.buscaDados(url);

        // extrair só os dados que interessam (titulo, poster, classificação)
        //ExtratorDeConteudoDaNasa extrator = new ExtratorDeConteudoDaNasa();
        ExtratorDeConteudoDoIMDB extrator = new ExtratorDeConteudoDoIMDB();
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        GeradoraDeFigurinhas geradoraDeFigurinhas = new GeradoraDeFigurinhas();

        // exibir e manipular os dados
        for (int i = 0; i < 3; i++) {
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo().replaceAll(":","") + ".png";

            geradoraDeFigurinhas.cria(inputStream, nomeArquivo);

            System.out.println("\u001b[1mTítulo : \u001b[m" + conteudo.getTitulo());
            System.out.println("\u001b[1mImagem : \u001b[m" + conteudo.getUrlImagem());
            System.out.println();

        }
    }
}
