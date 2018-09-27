package net.agilhard.gui.framework.swing.app;

import java.util.EventObject;

/**
 * The Class AppEvent.
 */
public class AppEvent extends EventObject {

    /**
     * A serialVersionUID.
     */
    private static final long serialVersionUID = -2387669854632743049L;

    /**
     * Instantiates a new app event.
     *
     * @param source
     *            the source
     */
    public AppEvent(final Object source) {
        super(source);
    }

}
