package net.agilhard.gui.framework.swing.app;

import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * The Class AppPanel.
 *
 * @param <C> the generic type
 */
public abstract class AbstractAppPanel<C extends AppController<?, ?>> extends JPanel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7146409405769359919L;

    /** The app manager. */
    private final AppManager appManager;

    /** The controller. */
    private C controller;

    /**
     * Instantiates a new app panel.
     *
     * @param appManager the app manager
     */
    public AbstractAppPanel(final AppManager appManager) {
        super(new GridLayout());
        this.appManager = appManager;
    }

    /**
     * Instantiates a new app panel.
     *
     * @param appManager the app manager
     * @param layoutManager the layout manager
     */
    public AbstractAppPanel(final AppManager appManager, final LayoutManager layoutManager) {
        super(layoutManager);
        this.appManager = appManager;
    }

    /**
     * Set the name.
     * @param name the name
     * @see java.awt.Component#setName(java.lang.String)
     */
    @Override
    public void setName(final String name) {
        super.setName(name);
        if (this.appManager != null) {
            this.appManager.fireNameChanged(this);
        }
    }

    /**
     * Gets the app manager.
     *
     * @return the app manager
     */
    public AppManager getAppManager() {
        return this.appManager;
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
     * @param controller the new controller
     */
    public void setController(final C controller) {
        this.controller = controller;
    }

}
