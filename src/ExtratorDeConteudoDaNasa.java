import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDaNasa implements ExtratorDeConteudo {
    public List<Conteudo> extraiConteudos(String json) {

        // extrair só os dados que interessam (titulo, imagem)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        // Stream() para transformar uma lista com varios atributos numa lista com apenas os atributos que interessam
        return listaDeAtributos.stream()
                .map(atributos -> new Conteudo(atributos.get("title"), atributos.get("url")))
                .toList();
    }
}
