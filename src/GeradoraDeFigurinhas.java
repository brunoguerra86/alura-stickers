import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class GeradoraDeFigurinhas {

    void cria(InputStream inputStream, String nomeArquivo) throws Exception {

        // leitura da imagem
        //InputStream inputStream = new FileInputStream("entrada/filme.jpg");
        //InputStream inputStream = new FileInputStream(url).openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem com transparência e com tamanho novo (em memória)
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        Font fonte = new Font("Impact", Font.BOLD, 96);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        // escrever uma frase na nova imagem
        String texto = "TOPZERA";
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
        int larguraTexto = (int) retangulo.getWidth();
        int margemX = (largura - larguraTexto) / 2;
        int margemY = novaAltura - 100;
        graphics.drawString(texto, margemX, margemY);

        // cria borda na fonte
        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        TextLayout textLayout = new TextLayout(texto, fonte, fontRenderContext);

        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(margemX, margemY);
        graphics.setTransform(transform);

        BasicStroke outlineStroke = new BasicStroke(largura * 0.004f);
        graphics.setStroke(outlineStroke);

        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);

        // cria diretório de saída
        File diretorio = new File("saida/");
        diretorio.mkdir();

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File("saida/" + nomeArquivo));
    }
}
