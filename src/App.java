import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        String imdbKey = System.getenv("IMDB_API_KEY");

        API api = API.NASA;
        String url = api.getUrl();
        ExtratorDeConteudo extrator = api.getExtrator();

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
