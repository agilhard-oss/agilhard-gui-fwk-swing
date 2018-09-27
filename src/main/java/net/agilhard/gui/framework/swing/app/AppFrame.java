package net.agilhard.gui.framework.swing.app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * The Class AppFrame.
 *
 * @param <C>
 *            the generic type
 * @param <PANEL>
 *            the generic type
 */
@SuppressWarnings("serial")
public class AppFrame<C extends AppController<?, ?>, PANEL extends AbstractAppPanel<C>> extends JFrame
    implements ActionListener {

    /** The panel. */
    private PANEL panel;

    /** The controller. */
    private C controller;

    /** The b disposing. */
    private boolean disposing;

    /** The Constant CONTAINER32_PNG. */
    private static final String AGILHARD32_PNG = "agilhard32.png";

    /**
     * Instantiates a new app frame.
     */
    protected AppFrame() {
        super();
        this.setIconImage(AppIcon.getImage(AGILHARD32_PNG));

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    /**
     * Instantiates a new app frame.
     *
     * @param title
     *            the title
     */
    protected AppFrame(final String title) {
        super(title);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    /* (non-Javadoc)
     * @see java.awt.Window#dispose()
     */
    @Override
    public void dispose() {
        if (!this.disposing) {
            this.disposing = true;
            if (this.controller != null) {
                this.controller.close();
            }
        } else {
            this.finishDispose();
        }
    }

    /**
     * Finish dispose.
     */
    public void finishDispose() {
        super.dispose();
    }

    /**
     * Gets the panel.
     *
     * @return the panel
     */
    public PANEL getPanel() {
        return this.panel;
    }

    /**
     * Sets the panel.
     *
     * @param newPanel
     *            the new panel
     */
    protected void setPanel(final PANEL newPanel) {
        this.panel = newPanel;
    }

    /**
     * Gets the controller.
     *
     * @return the controller
     */
    public C getController() {
        return this.controller;
    }

    /**
     * Sets the controller.
     *
     * @param controller
     *            the new controller
     */
    protected void setController(final C controller) {
        this.controller = controller;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        this.controller.actionPerformed(e);
    }
}
