package net.agilhard.gui.framework.swing.app;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.InputStream;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class AppIcon.
 */
public final class AppIcon {

    /**
     * Private constructor for utility class.
     */
    private AppIcon() {
        // .
    }

    /**
     * Gets the icon.
     *
     * @param name
     *            the name
     * @return the icon
     */
    public static ImageIcon getIcon(final String name) {
        ImageIcon icon = null;
        final ClassLoader cl = AppIcon.class.getClassLoader();
        final URL url = cl.getResource("net/agilhard/gui/framework/swing/icons/" + name);

        if (url == null) {
            final Logger log = LoggerFactory.getLogger(AppIcon.class);
            log.error("icon resource '" + name + "' not found");
        } else {
            icon = new ImageIcon(url);
        }
        return icon;
    }

    /**
     * Gets the icon.
     *
     * @param name
     *            the name
     * @return the icon
     */
    public static InputStream getIconAsStream(final String name) {
        final ClassLoader cl = AppIcon.class.getClassLoader();
        final InputStream in = cl.getResourceAsStream("net/agilhard/gui/framework/swing/icons/" + name);
        return in;
    }

    /**
     * Gets the image.
     *
     * @param name
     *            the name
     * @return the icon
     */
    public static Image getImage(final String name) {
        Image image = null;
        final ClassLoader cl = AppIcon.class.getClassLoader();
        final URL url = cl.getResource("net/agilhard/gui/framework/swing/icons/" + name);

        if (url == null) {
            final Logger log = LoggerFactory.getLogger(AppIcon.class);
            log.error("icon resource '" + name + "' not found");
        } else {
            image = Toolkit.getDefaultToolkit().getImage(url);

            final JFrame frame = new JFrame();
            frame.setVisible(false);
            final MediaTracker mediaTracker = new MediaTracker(frame);

            // add your image to the tracker with an arbitrary id
            final int id = 0;
            mediaTracker.addImage(image, id);

            // try to wait for image to be loaded
            // catch if loading was interrupted
            try {
                mediaTracker.waitForID(id);
            }
            catch (final InterruptedException e) {
                final Logger log = LoggerFactory.getLogger(AppIcon.class);
                log.error("image loading interrupted", e);
            }

        }
        return image;
    }
}
// CHECKSTYLE:ON
