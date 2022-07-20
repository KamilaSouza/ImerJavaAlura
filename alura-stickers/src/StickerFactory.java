
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class StickerFactory {


    public void create(InputStream inputStream, String fileName) throws Exception {

        // read image
        //   InputStream inputStream = new FileInputStream(new File("input/Movie.jpg"));
        //   InputStream inputStream =
        //   new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_1.jpg").openStream();
        BufferedImage originalImage = ImageIO.read(inputStream);


        // create new image resized
        int width = originalImage.getWidth(); // largura
        int height = originalImage.getHeight(); // altura
        int newHeight = height + height/6;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);


        // copy the original image for an image in memory
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);


        // font configuration
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.PINK);
        graphics.setFont(font);


        // write text on image
        String text = "TOPZERA";
        int stringWidthLength = (int) graphics.getFontMetrics().getStringBounds(text, graphics).getWidth();
      //  int stringHeightLength = (int) graphics.getFontMetrics().getStringBounds(comment, graphics).getHeight();

        int horizontalCenter = newImage.getWidth() / 2 - stringWidthLength / 2;
      //  int verticalCenter = newImage.getHeight() / 2 - stringHeightLength / 2;

        graphics.drawString(text,horizontalCenter , (newHeight - newHeight/14));


        // put an image in the file
        ImageIO.write(newImage, "png", new File("output/" + fileName));

    }
}
