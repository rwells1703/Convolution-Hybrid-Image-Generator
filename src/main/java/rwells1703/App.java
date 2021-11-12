package rwells1703;

import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.FImage;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * OpenIMAJ Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws IOException {
    	// Load an image from a file
        MBFImage image = ImageUtilities.readMBF(new File("images\\eye_orig.jpg"));

        // Create the image kernel
        float[][] kernel = {{1f,1f,1f},{1f,1f,1f},{1f,1f,1f}};

        // Process the image
        MBFImage processedImage = image.process(new MyConvolution(kernel));

        // Create a display window
        JFrame window = DisplayUtilities.createNamedWindow("main", "OpenIMAJ-Tutorial", true);

        //Display the image
        DisplayUtilities.display(processedImage, window);
    }
}
