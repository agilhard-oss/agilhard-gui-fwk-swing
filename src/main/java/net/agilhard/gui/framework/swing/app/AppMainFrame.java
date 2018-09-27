package net.agilhard.gui.framework.swing.app;

import static net.agilhard.gui.framework.swing.app.AppConstants.ACTION_CLOSE;
import static net.agilhard.gui.framework.swing.app.AppConstants.ACTION_QUIT;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * The Class AppMainFrame.
 *
 * @param <M> the generic type
 */
@SuppressWarnings("serial")
public abstract class AppMainFrame<M extends AppManager> extends JFrame
    implements AppListener, ActionListener, ChangeListener, PropertyChangeListener {

    /** The tabbed pane. */
    private final JTabbedPane tabbedPane;

    /** The title screen. */
    private final Component titleScreen;

    /** The app manager. */
    private M appManager;

    /** The in quit. */
    private boolean inQuit;

    /** The Constant CONTAINER32_PNG. */
    private static final String AGILHARD32_PNG = "agilhard32.png";
    /**
     * Instantiates a new app main frame.
     *
     * @param name the name
     */
    protected AppMainFrame(final String name) {
        this(name, true);
        this.setTitle(name);
    }

    /**
     * Instantiates a new app main frame.
     *
     * @param name the name
     * @param visible visible on start flag
     */
    protected AppMainFrame(final String name, final boolean visible) {

        super(name);
        this.setName(name);

        this.setIconImage(AppIcon.getImage(AGILHARD32_PNG));

        this.titleScreen = this.createTitleScreen();
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        this.tabbedPane.addChangeListener(this);

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.enableEvents(AWTEvent.KEY_EVENT_MASK);

        this.init();

        if ( this.getAppManager() != null ) {
            this.getAppManager().addAppListener(this);
        }

        this.tabbedPane.setVisible(false);

        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor=GridBagConstraints.NORTH;
        panel.add(this.titleScreen, gbc);
        gbc.gridy++;
        panel.add(this.tabbedPane, gbc);
        this.getContentPane().add("Center", panel);

        this.updateApp();

        this.pack();
        this.setVisible(visible);
        this.setExtendedState(visible ? Frame.NORMAL : Frame.ICONIFIED);
        this.setResizable(true);

    }


    /**
     * Gets the app manager.
     *
     * @return the app manager
     */
    public M getAppManager() {
        return this.appManager;
    }

    /**
     * Sets the app manager.
     *
     * @param appManager the new app manager
     */
    public void setAppManager(final M appManager) {
        this.appManager = appManager;
    }

    /**
     * Initialize.
     */
    protected abstract void init();

    /**
     * Update App.
     */
    protected void updateApp() {
        final int tabCount = this.getTabbedPane().getTabCount();
        this.titleScreen.setVisible( tabCount <= 0 );
        this.tabbedPane.setVisible(tabCount >= 0);
        if ( this.getAppManager() != null && this.getAppManager().getMenuHelper() != null ) {
            final JMenuItem detachMenuItem = this.getAppManager().getMenuHelper().getDetachMenuItem();
            if ( detachMenuItem != null ) {
                detachMenuItem.setEnabled(tabCount > 0);
            }
        }
    }

    /**
     * Creates the title screen.
     *
     * @return the component
     */
    protected Component createTitleScreen() {
        return new TitleScreen(this.getName(), 300, 250);
    }

    /**
     * React on state change ChangeEvent.
     * @param e the event
     * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
     */
    @SuppressWarnings("unused")
    @Override
    public void stateChanged(final ChangeEvent e) {
        if (this.getTabbedPane().getSelectedComponent() != null) {
            this.getTabbedPane().getSelectedComponent().requestFocusInWindow();
        }
        this.updateApp();
    }

    /**
     * React on name change AppEvent.
     * @param ev a Event
     * @see net.agilhard.gui.framework.swing.app.AppListener#nameChanged(net.agilhard.gui.framework.swing.app.AppEvent)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void nameChanged(final AppEvent ev) {
        final  AbstractAppPanel panel = (AbstractAppPanel) ev.getSource();
        if (this.tabbedPane != null) {
            final int i = this.tabbedPane.indexOfComponent(panel);
            if (i >= 0) {
                this.tabbedPane.setTitleAt(i, panel.getName());
            }
        }
    }

    /**
     * Gets the count.
     *
     * @return the count
     */
    protected int getCount() {
        return this.appManager.getCount();
    }

    /**
     * React on app added AppEvent.
     * @param ev the event
     * @see net.agilhard.gui.framework.swing.app.AppListener#appAdded(net.agilhard.gui.framework.swing.app.AppEvent)
     */
    @Override
    public void appAdded(@SuppressWarnings("unused") final AppEvent ev) {
        if (this.tabbedPane.getTabCount() > 0) {
            this.titleScreen.setVisible(false);
            this.tabbedPane.setVisible(true);
            this.repaint();
        }
    }

    /**
     * React on app removed AppEvent.
     * @param ev the event
     * @see net.agilhard.gui.framework.swing.app.AppListener#appRemoved(net.agilhard.gui.framework.swing.app.AppEvent)
     */
    @SuppressWarnings("unused")
    @Override
    public void appRemoved(final AppEvent ev) {
        if (this.tabbedPane.getTabCount() <= 0) {
            this.titleScreen.setVisible(true);
            this.tabbedPane.setVisible(false);
        }

        if (this.inQuit && this.getCount() == 0) {
            System.exit(0);
        }
    }

    /**
     * Close.
     * @see net.agilhard.gui.framework.swing.app.AppListener#close()
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void close() {
        final Component component = this.getTabbedPane().getSelectedComponent();
        if ( component instanceof AbstractAppPanel ) {
            this.getAppManager().removeFromTabs(((AbstractAppPanel) component).getController());
        } else {
            this.tabbedPane.remove(component);
        }
        if ( this.getCount() == 0 ) {
            this.quit();
        }
    }

    /**
     * Dispose.
     * @see java.awt.Window#dispose()
     */
    @Override
    public void dispose() {
        this.quit();
    }

    /**
     * Quit.
     * @see net.agilhard.gui.framework.swing.app.AppListener#quit()
     */
    @Override
    public void quit() {
        this.inQuit = true;
        if (this.inQuit && this.getCount() == 0) {
            super.dispose();
            System.exit(0);
        } else {
            if (this.appManager != null) {
                this.appManager.closeAll();
                super.dispose();
                System.exit(0);
            }
        }
    }

    /**
     * Perform Action.
     *
     * @param e the Event
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void actionPerformed(final ActionEvent e) {
        final String action = e.getActionCommand();
        final Component component = this.tabbedPane.getSelectedComponent();
        if (action.equals(ACTION_CLOSE)) {
            this.close();
        } else if (action.equals(ACTION_QUIT)) {
            this.quit();
        }
        if ( component == null ) {
            return;
        }
        if ( component instanceof AbstractAppPanel ) {
            ((AbstractAppPanel) component).getController().actionPerformed(e);
        }
    }

    /**
     * React on property change.
     *
     * @param evt the evt
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        final Component component = this.tabbedPane.getSelectedComponent();
        if ( !(component instanceof AbstractAppPanel) ) {
            return;
        }
        if ( ((AbstractAppPanel) component).getController() instanceof PropertyChangeListener ) {
            ((PropertyChangeListener) ((AbstractAppPanel) component).getController()).propertyChange(evt);
        }
    }

    /**
     * Gets the tabbed pane.
     *
     * @return the tabbed pane
     */
    public JTabbedPane getTabbedPane() {
        return this.tabbedPane;
    }

}
