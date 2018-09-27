package net.agilhard.gui.framework.swing.app;

import static net.agilhard.gui.framework.swing.app.AppConstants.ACTION_ATTACH;
import static net.agilhard.gui.framework.swing.app.AppConstants.ACTION_CLOSE;
import static net.agilhard.gui.framework.swing.app.AppConstants.ACTION_CLOSEALL;
import static net.agilhard.gui.framework.swing.app.AppConstants.ACTION_DETACH;
import static net.agilhard.gui.framework.swing.app.AppConstants.ACTION_QUIT;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.SwingUtilities;

/**
 * The Class AppController.
 *
 * @param <P>
 *            the generic type
 * @param <F>
 *            the generic type
 */
public abstract class AppController<P extends AbstractAppPanel<?>, F extends AppFrame<?, P>> implements ActionListener {

    /** The manager. */
    private final AppManager manager;

    /** The panel. */
    private final P panel;

    /** The frame. */
    private F frame;

    /** The menu helper. */
    private AppMenuHelper menuHelper;

    /** The close_on_exit. */
    private boolean closeOnExit = true;

    /** The frame window listener. */
    private WindowListener frameWindowListener;

    /**
     * Instantiates a new app controller.
     *
     * @param panel
     *            the panel
     */
    public AppController(final P panel) {
        this.panel = panel;
        this.manager = panel.getAppManager();
    }

    /**
     * Sets the menu helper.
     *
     * @param menuHelper
     *            the new menu helper
     */
    public void setMenuHelper(final AppMenuHelper menuHelper) {
        this.menuHelper = menuHelper;
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
     * Sets the frame window listener.
     *
     * @param listener
     *            the new frame window listener
     */
    protected void setFrameWindowListener(final WindowListener listener) {
        this.frameWindowListener = listener;
    }

    /**
     * Removes the frame window listener.
     */
    protected void removeFrameWindowListener() {
        if (this.frame != null) {
            this.frame.removeWindowListener(this.frameWindowListener);
        }
    }

    /**
     * Gets the manager.
     *
     * @return the manager
     */
    public AppManager getManager() {
        return this.manager;
    }

    /**
     * Gets the panel.
     *
     * @return the panel
     */
    public P getPanel() {
        return this.panel;
    }

    /**
     * Sets the frame.
     *
     * @param frame
     *            the new frame
     */
    public void setFrame(final F frame) {
        this.removeFrameWindowListener();
        this.frame = frame;
        if (this.frame != null) {
            this.frameWindowListener = new WindowAdapter() {

                @SuppressWarnings("unused")
                @Override
                public void windowClosed(final WindowEvent e) {
                    AppController.this.close();
                }
            };
            frame.addWindowListener(this.frameWindowListener);
        }
        if (this.manager != null) {
            this.manager.addApp(this);
        }
    }

    /**
     * Gets the frame.
     *
     * @return the frame
     */
    public F getFrame() {
        return this.frame;
    }

    /**
     * Sets the close on exit.
     *
     * @param newCloseOnExit
     *            the new close on exit
     */
    public void setCloseOnExit(final boolean newCloseOnExit) {
        this.closeOnExit = newCloseOnExit;
    }

    /**
     * Attach.
     */
    public void attach() {
        if (this.manager != null && this.manager.hasTabs()) {
            if (this.frame != null) {
                this.frame.getContentPane().remove(this.panel);
                this.removeFrameWindowListener();
                this.frame.finishDispose();
                this.frame = null;
            }
            this.manager.addTab(this.panel);
        }
    }

    /**
     * Detach.
     */
    public abstract void detach();

    /**
     * Close.
     */
    public void close() {
        if (this.closeOnExit) {
            final F savFrame = this.frame;
            this.getManager().removeApp(this);
            if (savFrame != null) {
                savFrame.dispose();
            }
            this.frame = null;
        }
    }

    /**
     * Close all.
     */
    public void closeAll() {
        if (this.manager != null) {
            this.manager.closeAll();
        }
    }

    /**
     * Quit.
     */
    public void quit() {
        if (this.manager != null) {
            this.manager.fireQuit();
        }
    }

    /**
     * Action performed.
     *
     * @param e
     *            the event
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(final ActionEvent e) {

        final String command = e.getActionCommand();

        if (command.equals(ACTION_ATTACH)) {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    AppController.this.attach();
                }
            });
        } else if (command.equals(ACTION_DETACH)) {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    AppController.this.detach();
                }
            });
        } else if (command.equals(ACTION_CLOSE)) {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    AppController.this.close();
                }
            });
        } else if (command.equals(ACTION_CLOSEALL)) {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    AppController.this.closeAll();
                }
            });
        } else if (command.equals(ACTION_QUIT)) {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    AppController.this.quit();
                }
            });
        }
    }

    /**
     * Checks if is close_on_exit.
     *
     * @return true, if is close_on_exit
     */
    public boolean isCloseOnExit() {
        return this.closeOnExit;
    }

}
