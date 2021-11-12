package rwells1703;

import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.processing.resize.ResizeProcessor;

import java.io.File;
import java.io.IOException;

public class App {
    public static void main( String[] args ) throws IOException {
        makeHybrid();
    }

    public static void makeHybrid() throws IOException {
        // Load an images from a file
        MBFImage lowImage = ImageUtilities.readMBF(new File("images\\data\\bird.bmp"));
        MBFImage highImage = ImageUtilities.readMBF(new File("images\\data\\plane.bmp"));

        // Create a hybrid image
        MBFImage hybridImage = MyHybridImages.makeHybrid(lowImage, 5f, highImage, 3f);

        // Save the image to a file
        //ImageUtilities.write(hybridImage, new File("images\\outputs\\hybrid.jpg"));

        //Display the image
        DisplayUtilities.display(createImageCascade(hybridImage, 4, 20));
    }

    private static MBFImage createImageCascade(MBFImage image, int copies, int xPadding) {
        int displayX = 0;
        int displayY = 0;

        Coordinate[] displayCoordinates = new Coordinate[copies];

        for (int i = 0; i < copies; i++) {
            displayCoordinates[i] = new Coordinate(displayX, displayY);

            displayX += Math.pow(0.5,i)*image.getWidth() + xPadding;
            displayY += Math.pow(0.5,i+1)*image.getHeight();
        }
        displayY += Math.pow(0.5, copies-1)*image.getHeight();

        MBFImage cascade = new MBFImage(displayX, displayY);

        for (int i = 0; i < copies; i++) {
            MBFImage resizedImage = image.clone();
            for (int x = 0; x < i; x++) {
                resizedImage = ResizeProcessor.halfSize(resizedImage);
            }

            cascade.drawImage(resizedImage, displayCoordinates[i].x, displayCoordinates[i].y);
        }

        return cascade;
    }

    private static class Coordinate {
        public int x;
        public int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}