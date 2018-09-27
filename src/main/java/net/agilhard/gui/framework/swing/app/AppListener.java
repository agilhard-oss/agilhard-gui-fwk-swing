package net.agilhard.gui.framework.swing.app;

/**
 * The listener interface for receiving app events.
 * The class that is interested in processing a app
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addAppListener</code> method. When
 * the app event occurs, that object's appropriate
 * method is invoked.
 *
 * @see AppEvent
 */
public interface AppListener {

    /**
     * Close.
     */
    void close();

    /**
     * Quit.
     */
    void quit();

    /**
     * Name changed.
     *
     * @param ev
     *            the ev
     */
    void nameChanged(AppEvent ev);

    /**
     * App added.
     *
     * @param ev
     *            the ev
     */
    void appAdded(AppEvent ev);

    /**
     * App removed.
     *
     * @param ev
     *            the ev
     */
    void appRemoved(AppEvent ev);
}
