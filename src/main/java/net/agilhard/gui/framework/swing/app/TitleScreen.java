// CHECKSTYLE:OFF
package net.agilhard.gui.framework.swing.app;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class TitleScreen.
 */
public class TitleScreen extends JPanel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 4033061307128294005L;

    /** The Logger. */
    private final Logger log = LoggerFactory.getLogger(TitleScreen.class);

    /** The resource img. */
    private BufferedImage resourceImg;

    /** The img. */
    private BufferedImage img;

    /** The resource filler img. */
    private BufferedImage resourceFillerImg;

    /** The filler img. */
    private BufferedImage fillerImg;

    /** The title. */
    private final String title;

    /** The title. */
    private final String mainTitle;

    /** The resw. */
    private int resw;

    /** The resh. */
    private int resh;

    /** The resx. */
    private final int resx;

    /** The resy. */
    private final int resy;

    /** The w. */
    private int w;

    /** The h. */
    private int h;

    /** The tw. */
    private int tw;

    /** The th. */
    private int th;

    /** The x. */
    private int x;

    /** The y. */
    private int y;

    /** The x. */
    private int mx;

    /** The y. */
    private int my;

    /** The lastw. */
    private int lastw;

    /** The lasth. */
    private int lasth;

    /** The res font size. */
    private int resFontSize;

    /** The font size. */
    private int fontSize;

    /** The font name. */
    private final String fontName;

    /**
     * Instantiates a new title screen.
     *
     * @param title
     *            the title
     * @param x
     *            the x
     * @param y
     *            the y
     */
    public TitleScreen(final String title, final int x, final int y) {
        this("/net/agilhard/gui/framework/swing/image/splashscreen.png",
            "/net/agilhard/gui/framework/swing/image/filler.png", title, "Serif", 40, x, y);
    }

    /**
     * Instantiates a new title screen.
     *
     * @param resource
     *            the resource
     * @param resourceFiller
     *            the resource filler
     * @param title
     *            the title
     * @param fontName
     *            the font name
     * @param fontSize
     *            the font size
     * @param x
     *            the x
     * @param y
     *            the y
     */
    public TitleScreen(final String resource, final String resourceFiller, final String title, final String fontName,
        final int fontSize, final int x, final int y) {
        this(resource, resourceFiller, "agilhard.net", title, fontName, fontSize, x, y);

    }

    /**
     * Instantiates a new title screen.
     *
     * @param resource
     *            the resource
     * @param resourceFiller
     *            the resource filler
     * @param mainTitle
     *            the main title
     * @param title
     *            the title
     * @param fontName
     *            the font name
     * @param fontSize
     *            the font size
     * @param x
     *            the x
     * @param y
     *            the y
     */
    public TitleScreen(final String resource, final String resourceFiller, final String mainTitle, final String title,
        final String fontName, final int fontSize, final int x, final int y) {
        this.resx = x;
        this.resy = y;
        this.fontName = fontName;
        this.fontSize = this.resFontSize = fontSize;
        this.title = title;
        this.mainTitle = mainTitle;

        InputStream in = this.getClass().getResourceAsStream(resource);
        if (in != null) {
            try {
                this.resourceImg = this.img = ImageIO.read(in);

                this.tw = this.lastw = this.resw = this.w = this.img.getWidth(null);
                this.th = this.lasth = this.resh = this.h = this.img.getHeight(null);

            }
            catch (final IOException ioe) {
                this.log.warn("I/O ERROR", ioe);
            }

        } else {
            this.log.warn("Image not found:" + resource);
        }

        if (this.resw == 0) {
            this.x = 0;
            this.my = 0;
        } else {
            this.x = this.resx * 2 * this.w / this.resw;
            this.mx = this.resx * 2 * this.w / this.resw;
        }

        if (this.resh == 0) {
            this.y = 0;
            this.my = 0;
        } else {
            this.y = this.resy * this.h / this.resh;
            this.my = this.resy * 2 * this.h / this.resh;
        }


        in = this.getClass().getResourceAsStream(resourceFiller);
        if (in != null) {
            try {
                this.resourceFillerImg = this.fillerImg = ImageIO.read(in);
            }
            catch (final IOException ioe) {
                this.log.warn("I/O ERROR", ioe);
            }

        } else {
            this.log.warn("Image not found");
        }

        this.addComponentListener(new ComponentAdapter() {

            @SuppressWarnings("synthetic-access")
            @Override
            public void componentResized(final ComponentEvent e) {
                final Dimension d = ((Component) e.getSource()).getSize();
                TitleScreen.this.adjustSize(d);
            }
        });
    }

    /**
     * Adjust size.
     *
     * @param d
     *            the d
     */
    private void adjustSize(final Dimension d) {
        this.tw = (int) d.getWidth();
        this.th = (int) d.getHeight();
        final double f = Math.min((double) this.tw / (double) this.resw, (double) this.th / (double) this.resh);
        this.w = (int) (this.resw * f);
        this.h = (int) (this.resh * f);
        if (this.w <= 0) {
            this.w = 1;
        }
        if (this.h <= 0) {
            this.h = 1;
        }
        this.repaint();
    }

    /* (non-Javadoc)
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(final Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;
        if (this.tw != this.lastw || this.th != this.lasth) {
            this.img = ImageUtil.createResizedCopy(this.resourceImg, this.w, this.h);
            if (this.tw - this.w > 0) {
                this.fillerImg = ImageUtil.createResizedCopy(this.resourceFillerImg, this.tw - this.w + 4, this.h);
            }
            this.lastw = this.tw;
            this.lasth = this.th;
            if (this.resw > 0 && this.resh > 0) {
                this.fontSize = Math.min(this.resFontSize * this.w / this.resw, this.resFontSize * this.h / this.resh);
                this.x = this.resx * 2 * this.w / this.resw;
                this.y = this.resy * this.h / this.resh;
                this.mx = this.resx * 2 * this.w / this.resw;
                this.my = this.resy * 2 * this.h / this.resh;
            } else {
                this.fontSize = 20;
                this.x = 0;
                this.y = 0;
                this.mx = 0;
                this.my = 0;
            }

        }

        g2.setBackground(Color.white);
        g2.setColor(Color.white);
        g2.drawImage(this.img, 0, 0, null);
        if (this.tw - this.w - 4 > 0) {
            g2.drawImage(this.fillerImg, this.w - 4, 0, null);
        }
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (this.th - this.h > 0) {
            g2.fillRect(0, this.h, this.w, this.th - this.h);
        }

        final Font font = new Font(this.fontName, Font.BOLD, this.fontSize);

        g2.setColor(Color.black);

        g2.setFont(font);
        g2.drawString(this.title, this.x, this.y);
        g2.drawString(this.mainTitle, this.mx, this.my);

    }

    /* (non-Javadoc)
     * @see javax.swing.JComponent#getPreferredSize()
     */
    @Override
    public Dimension getPreferredSize() {
        final Dimension d = this.getParent().getSize();
        return new Dimension(Math.max(this.resw, d.width), Math.max(this.resh, d.height));
    }

    /* (non-Javadoc)
     * @see javax.swing.JComponent#getMinimumSize()
     */
    @Override
    public Dimension getMinimumSize() {
        final Dimension d = this.getParent().getSize();
        return new Dimension(d.width, d.height);
    }

    /**
     * The main method.
     *
     * @param arg
     *            the arguments
     */
    public static void main(final String[] arg) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                final JFrame frame = new JFrame();
                frame.add("Center", new TitleScreen("AppUtil", 300, 250));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.pack();
            }
        });
    }

}
// CHECKSTYLE: ON
