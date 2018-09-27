package net.agilhard.gui.framework.swing.app;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

/**
 * The Class AppManager.
 */
public class AppManager {

    /** The count. */
    private int count;

    /** The tabbed pane. */
    private JTabbedPane tabbedPane;

    /** The menu helper. */
    private transient AppMenuHelper menuHelper;

    /** The applications. */
    private final transient List<AppController<?, ?>> applications = new ArrayList<>();

    /** The app listeners. */
    private final List<AppListener> appListeners = new ArrayList<>();

    /**
     * Instantiates a new app manager.
     */
    public AppManager() {
        // .
    }

    /**
     * Instantiates a new app manager.
     *
     * @param tabbedPane
     *            the tabbed pane
     * @param menuHelper
     *            the menu helper
     */
    public AppManager(final JTabbedPane tabbedPane, final AppMenuHelper menuHelper) {
        this.tabbedPane = tabbedPane;
        this.menuHelper = menuHelper;
    }

    /**
     * Checks for tabs.
     *
     * @return true, if successful
     */
    public boolean hasTabs() {
        return this.tabbedPane != null;
    }

    /**
     * Gets the count.
     *
     * @return the count
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Gets the tabbed pane.
     *
     * @return the tabbed pane
     */
    public JTabbedPane getTabbedPane() {
        return this.tabbedPane;
    }

    /**
     * Sets the count.
     *
     * @param count
     *            the new count
     */
    public void setCount(final int count) {
        this.count = count;
    }

    /**
     * Adds the app listener.
     *
     * @param listener
     *            the listener
     */
    public void addAppListener(final AppListener listener) {
        if (!this.appListeners.contains(listener)) {
            this.appListeners.add(listener);
        }
    }

    /**
     * Removes the terminal listener.
     *
     * @param listener
     *            the listener
     */
    public void removeAppListener(final AppListener listener) {
        if (this.appListeners.contains(listener)) {
            this.appListeners.remove(listener);
        }
    }

    /**
     * Fire name changed.
     *
     * @param source
     *            the source
     */
    void fireNameChanged(final AbstractAppPanel<?> source) {
        final JFrame frame = source.getController().getFrame();
        if (frame != null) {
            frame.setTitle(source.getName());
        }
        if (this.appListeners.size() > 0) {
            final AppEvent ev = new AppEvent(source);
            for (final AppListener l : this.appListeners) {
                final AppListener listener = l;
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        listener.nameChanged(ev);
                    }
                });
            }
        }
    }

    /**
     * Adds the tab.
     *
     * @param panel
     *            the panel
     */
    public void addTab(final AbstractAppPanel<?> panel) {
        final Dimension d = panel.getPreferredSize();
        this.tabbedPane.add(panel);
        this.tabbedPane.setSelectedComponent(panel);
        Component parent = this.tabbedPane.getParent();
        while (!(parent == null || parent instanceof Frame)) {
            parent = parent.getParent();
        }
        if (parent != null) {
            final Frame frame = (Frame) parent;
            frame.setVisible(true);
            frame.toFront();
            frame.setExtendedState(Frame.NORMAL);
            frame.requestFocus();
        }
        panel.setSize(d);
        this.addApp(panel.getController());
    }

    /**
     * Removes the from tabs.
     *
     * @param app
     *            the app
     */
    public void removeFromTabs(final AppController<?, ?> app) {
        if (this.tabbedPane != null && app != null && app.getPanel() != null) {
            if (this.tabbedPane.indexOfComponent(app.getPanel()) > -1) {
                this.tabbedPane.remove(app.getPanel());
                this.removeApp(app);
                if (this.tabbedPane.getTabCount() > 0) {
                    this.tabbedPane.setSelectedIndex(0);
                }
            }
        }
    }

    /**
     * Adds the app.
     *
     * @param app
     *            the app
     */
    public void addApp(final AppController<?, ?> app) {
        if (!this.applications.contains(app)) {
            this.applications.add(app);
            this.count++;
            this.fireAppAdded(app.getPanel());
        }
    }

    /**
     * Removes the app.
     *
     * @param app
     *            the app
     */
    public void removeApp(final AppController<?, ?> app) {
        if (this.applications.contains(app)) {

            if (app.getFrame() != null) {
                this.applications.remove(app);
                this.count--;
                app.getFrame().finishDispose();
            } else if (this.tabbedPane != null) {
                this.applications.remove(app);
                this.count--;
                this.tabbedPane.remove(app.getPanel());
            }
            this.fireAppRemoved(app.getPanel());
        }
    }

    /**
     * Close all.
     */
    public void closeAll() {
        // copy to avoid concurrent modification in event handler
        final List<AppController<?, ?>> copy = new ArrayList<>();
        for (final AppController<?, ?> app : this.applications) {
            copy.add(app);
        }
        for (final AppController<?, ?> app : copy) {
            app.close();
        }
    }

    /**
     * Fire app added.
     *
     * @param source
     *            the source
     */
    private void fireAppAdded(final Object source) {
        if (this.appListeners.size() > 0) {
            final AppEvent ev = new AppEvent(source);
            for (final AppListener l : this.appListeners) {
                final AppListener listener = l;
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        listener.appAdded(ev);
                    }
                });
            }
        }
    }

    /**
     * Fire app removed.
     *
     * @param source
     *            the source
     */
    private void fireAppRemoved(final Object source) {
        if (this.appListeners.size() > 0) {
            final AppEvent ev = new AppEvent(source);
            for (final AppListener l : this.appListeners) {
                final AppListener listener = l;
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        listener.appRemoved(ev);
                    }
                });
            }
        }
    }

    /**
     * Fire quit.
     */
    public void fireQuit() {
        if (this.appListeners.size() > 0) {
            for (final AppListener l : this.appListeners) {
                final AppListener listener = l;
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        listener.quit();
                    }
                });
            }
        }
    }

    /**
     * Gets the menu helper.
     *
     * @return the menu helper
     */
    public AppMenuHelper getMenuHelper() {
        return this.menuHelper;
    }

    /**
     * Gets the main Frame.
     *
     * @return the main frame
     */
    public Window getMainWindow() {
        return SwingUtilities.windowForComponent(this.tabbedPane);
    }
}
