package imagecompression;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

/**
 * Main class for image compression
 *
 * @author Pranav
 * @since 2019-05-06
 */
public class ImageCompression {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        File input = new File("D:\\uncompressed.jpg");
      BufferedImage image = ImageIO.read(input);

      //File compressedImageFile = new File("D:\\compressed.jpg");
      OutputStream os =new ByteArrayOutputStream();

      Iterator<ImageWriter>writers =  ImageIO.getImageWritersByFormatName("jpg");
      ImageWriter writer = (ImageWriter) writers.next();

      ImageOutputStream ios = ImageIO.createImageOutputStream(os);
      writer.setOutput(ios);

      ImageWriteParam param = writer.getDefaultWriteParam();
      
      param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
      param.setCompressionQuality(0.5f);
      writer.write(null, new IIOImage(image, null, null), param);
      
      os.close();
      ios.close();
      writer.dispose();
    }

}
