package rwells1703;

import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.image.processing.algorithm.FourierTransform;
import org.openimaj.image.FImage;
import org.openimaj.image.processor.SinglebandImageProcessor;
import org.openimaj.math.geometry.shape.Rectangle;

import javax.swing.*;

public class MyConvolution implements SinglebandImageProcessor<Float, FImage> {
    private float[][] kernel;

    public MyConvolution(float[][] kernel) {
        //note that like the image pixels kernel is indexed by [row][column]
        this.kernel = kernel;
    }

    @Override
    public void processImageFourier(FImage image) {
        // convolve image with kernel and store result back in image
        //
        // hint: use FImage#internalAssign(FImage) to set the contents
        // of your temporary buffer image to the image.

        // Get image of the kernel
        FImage template = image.clone();
        template.fill(0f);
        template.drawShapeFilled(new Rectangle(0f, 0f, 30f, 30f), RGBColour.WHITE[0]);

        // Fourier transform the image and its template
        FourierTransform ftImage = new FourierTransform(image, true);
        FourierTransform ftTemplate = new FourierTransform(template, true);

        // Multiply the two together
        FImage magnitude = new FImage(image.getWidth(), image.getHeight());
        FImage phase = new FImage(image.getWidth(), image.getHeight());

        image.internalAssign(magnitude);

        magnitude.addInplace(ftImage.getMagnitude().multiplyInplace(ftTemplate.getMagnitude()));
        magnitude.subtractInplace(ftImage.getMagnitude().multiplyInplace(ftTemplate.getMagnitude()));

        phase.addInplace(ftImage.getMagnitude().multiplyInplace(ftTemplate.getPhase()));
        phase.addInplace(ftTemplate.getMagnitude().multiplyInplace(ftTemplate.getMagnitude()));

        FourierTransform ftConvolutedImage = new FourierTransform(magnitude, phase, false);

        // Inverse the product to get the convoluted image
        FImage inverseFourier = ftConvolutedImage.inverse();

        // Overwrite the original image with the new convoluted image
        //image.internalAssign(inverseFourier);
    }
}