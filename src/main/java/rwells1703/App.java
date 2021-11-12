package rwells1703;

import org.openimaj.image.DisplayUtilities;
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
    	// Load an images from a file
        MBFImage lowImage = ImageUtilities.readMBF(new File("images\\stop.png"));
        MBFImage highImage = ImageUtilities.readMBF(new File("images\\lolly.png"));

        // Create a hybrid image
        MBFImage hybridImage = MyHybridImages.makeHybrid(lowImage, 5f, highImage, 5f);

        // Create a display window
        JFrame window = DisplayUtilities.createNamedWindow("main", "OpenIMAJ-Tutorial", true);

        //Display the image
        DisplayUtilities.display(hybridImage, window);
    }
}
