package rwells1703;

import org.openimaj.image.colour.RGBColour;
import org.openimaj.image.FImage;
import org.openimaj.image.processor.SinglebandImageProcessor;
import org.openimaj.math.geometry.shape.Rectangle;

public class MyConvolution implements SinglebandImageProcessor<Float, FImage> {
    private float[][] kernel;
    private int templateWidth;
    private int templateHeight;

    public MyConvolution(float[][] kernel) {
        //note that like the image pixels kernel is indexed by [row][column]
        this.kernel = kernel;
        this.templateWidth = kernel[0].length;
        this.templateHeight = kernel.length;
    }

    @Override
    public void processImage(FImage image) {
        // convolve image with kernel and store result back in image
        //
        // hint: use FImage#internalAssign(FImage) to set the contents
        // of your temporary buffer image to the image.

        // Get image of the kernel
        //FImage template = image.clone();
        //template.fill(0f);
        //template.drawShapeFilled(new Rectangle(0f, 0f, templateWidth, templateHeight), RGBColour.WHITE[0]);

        // Calculate zero border sizes
        int horizontalBorderSize = (int) Math.ceil((double)(templateWidth - 1) / 2);
        int verticalBorderSize = (int) Math.ceil((double)(templateHeight - 1) / 2);

        // Add border to the image
        FImage borderedImage = new FImage(image.getWidth()+2*horizontalBorderSize, image.getHeight()+2*verticalBorderSize);
        borderedImage.overlayInplace(image, horizontalBorderSize, verticalBorderSize);

        FImage convolutedImage = new FImage(borderedImage.getWidth(), borderedImage.getHeight());

        // Loop through all pixels in the bordered image
        for (int x = templateWidth; x < borderedImage.getWidth() - templateWidth; x++) {
            for (int y = templateHeight; y < borderedImage.getHeight() - templateHeight; y++) {
                float newIntensity = 0;

                // Loop through all pixels in the kernel/template
                for (int kernelX = 0; kernelX < templateWidth; kernelX++) {
                    for (int kernelY = 0; kernelY < templateHeight; kernelY++) {
                        // Sum the products of the kernel and corresponding image pixels
                        newIntensity += borderedImage.getPixel(x + kernelX, y + kernelY) * kernel[kernelX][kernelY];
                    }
                }

                // Add the new pixel to the convoluted image
                convolutedImage.setPixel(x, y, newIntensity);
            }
        }

        // Divide the convoluted image by the total amount of pixels in the template
        // This normalises the brightness
        convolutedImage.divideInplace((float) (templateWidth*templateHeight));

        // Output the convoluted image
        image.internalAssign(convolutedImage);
    }
}