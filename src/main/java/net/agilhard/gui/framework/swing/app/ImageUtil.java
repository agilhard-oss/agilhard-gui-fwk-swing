package net.agilhard.gui.framework.swing.app;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * The class ImageUtil.
 *
 * @author bei
 */
public final class ImageUtil {

    /**
     * Private constructor for utility class.
     */
    private ImageUtil() {
        // .
    }

    /**
     * Creates the resized copy.
     *
     * @param originalImage
     *            the original image
     * @param scaledWidth
     *            the scaled width
     * @param scaledHeight
     *            the scaled height
     * @return the buffered image
     */
    public static BufferedImage createResizedCopy(final Image originalImage, final int scaledWidth,
        final int scaledHeight) {
        final int imageType = BufferedImage.TYPE_INT_ARGB;
        final BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        final Graphics2D g = scaledBI.createGraphics();
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }

}
