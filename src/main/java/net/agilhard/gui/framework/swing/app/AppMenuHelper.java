package net.agilhard.gui.framework.swing.app;

import static net.agilhard.gui.framework.swing.app.AppConstants.ACTION_ATTACH;
import static net.agilhard.gui.framework.swing.app.AppConstants.ACTION_CLOSE;
import static net.agilhard.gui.framework.swing.app.AppConstants.ACTION_CLOSEALL;
import static net.agilhard.gui.framework.swing.app.AppConstants.ACTION_DETACH;
import static net.agilhard.gui.framework.swing.app.AppConstants.ACTION_QUIT;
import static net.agilhard.gui.framework.swing.app.AppConstants.ACTION_SETTINGS;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

/**
 * The Class AppMenuHelper.
 */
public class AppMenuHelper {

    /** The file menu. */
    private JMenu fileMenu;

    /** The detach menu item. */
    private JMenuItem detachMenuItem;

    /** The Constant SERVICE_MANAGER_PNG. */
    protected static final String PREFERENCES_PNG = "preferences.png";

    /** The main menu. */
    private JMenuBar mainMenu;

    /** The settings menu. */
    private JMenu settingsMenu;

    /** The settings menu in the tray. */
    private JMenu settingsMenuTray;

    /**
     * Gets the app menu bar.
     *
     * @param actionListener
     *            the action listener
     * @param forFrame
     *            the for frame
     * @return the app menu bar
     */
    public JMenuBar getAppMenuBar(final ActionListener actionListener, final boolean forFrame) {
        this.mainMenu = new JMenuBar();
        this.mainMenu.setFocusable(true);
        this.fileMenu = new JMenu(AppMessages.getI18n("MENU_FILE"));
        this.fileMenu.setIcon(AppIcon.getIcon("new.png"));
        this.mainMenu.add(this.fileMenu);

        this.addAppMenuItems(actionListener, this.mainMenu, this.fileMenu, forFrame);

        this.addCloseQuitAttachDetach(actionListener, this.fileMenu, forFrame);
        this.addSettingsMenu(actionListener, this.mainMenu, this.fileMenu, forFrame);

        return this.mainMenu;
    }

    /**
     * Adds the close quit attach detach.
     *
     * @param actionListener
     *            the action listener
     * @param csFileMenu
     *            the file menu
     * @param useAttach
     *            the use attach
     */
    protected void addCloseQuitAttachDetach(final ActionListener actionListener, final JMenu csFileMenu,
        final boolean useAttach) {
        JMenuItem mi;

        if (useAttach) {
            mi = new JMenuItem(AppMessages.getI18n("MENU_ATTACH"));
            mi.addActionListener(actionListener);
            mi.setActionCommand(ACTION_ATTACH);
            mi.setIcon(AppIcon.getIcon("add.png"));
            mi.setAccelerator(KeyStroke.getKeyStroke('A', java.awt.event.InputEvent.ALT_DOWN_MASK));
            csFileMenu.add(mi);
        } else {
            this.detachMenuItem = new JMenuItem(AppMessages.getI18n("MENU_DETACH"));
            this.detachMenuItem.addActionListener(actionListener);
            this.detachMenuItem.setActionCommand(ACTION_DETACH);
            this.detachMenuItem.setIcon(AppIcon.getIcon("remove.png"));
            csFileMenu.add(this.detachMenuItem);
            this.detachMenuItem.setAccelerator(KeyStroke.getKeyStroke('D', java.awt.event.InputEvent.ALT_DOWN_MASK));
        }

        mi = new JMenuItem(AppMessages.getI18n("MENU_CLOSE"));
        mi.addActionListener(actionListener);
        mi.setActionCommand(ACTION_CLOSE);
        mi.setIcon(AppIcon.getIcon("close.png"));
        mi.setAccelerator(KeyStroke.getKeyStroke('W', java.awt.event.InputEvent.CTRL_DOWN_MASK));

        csFileMenu.add(mi);

        mi = new JMenuItem(AppMessages.getI18n("MENU_CLOSEALL"));
        mi.addActionListener(actionListener);
        mi.setActionCommand(ACTION_CLOSEALL);
        mi.setIcon(AppIcon.getIcon("closeall.png"));
        mi.setAccelerator(KeyStroke.getKeyStroke('W',
            java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        csFileMenu.add(mi);

        mi = new JMenuItem(AppMessages.getI18n("MENU_QUIT"));
        mi.addActionListener(actionListener);
        mi.setActionCommand(ACTION_QUIT);
        mi.setIcon(AppIcon.getIcon("exit.png"));
        mi.setAccelerator(KeyStroke.getKeyStroke('Q', java.awt.event.InputEvent.ALT_DOWN_MASK));
        csFileMenu.add(mi);

    }

    /**
     * Adds the app menutems.
     *
     * @param actionListener
     *            the action listener
     * @param csMainMenu
     *            the main menu
     * @param csFileMenu
     *            the file menu
     * @param forFrame
     *            the for frame
     */
    @SuppressWarnings("unused")
    protected void addAppMenuItems(final ActionListener actionListener, final JMenuBar csMainMenu,
        final JMenu csFileMenu, final boolean forFrame) {
        // no specific Menu
    }

    /**
     * Adds the settings menu item.
     *
     * @param actionListener
     *            the action listener
     * @param csMainMenu
     *            the main menu
     * @param csFileMenu
     *            the cs file menu
     * @param forFrame
     *            the for frame
     */
    @SuppressWarnings("unused")
    protected void addSettingsMenu(final ActionListener actionListener, final JMenuBar csMainMenu,
        final JMenu csFileMenu, final boolean forFrame) {

        this.settingsMenu = new JMenu(AppMessages.getI18n("MENU_SETTINGS"));
        this.settingsMenu.setIcon(AppIcon.getIcon(PREFERENCES_PNG));

        final JMenuItem mi = new JMenuItem(AppMessages.getI18n("MENU_EDIT_SETTINGS"));

        //final UIDefaults defaults = javax.swing.UIManager.getDefaults();
        mi.setActionCommand(ACTION_SETTINGS);
        mi.addActionListener(actionListener);
        mi.setIcon(AppIcon.getIcon(PREFERENCES_PNG));
        this.settingsMenu.add(mi);

        if (csMainMenu != null) {
            csMainMenu.add(this.settingsMenu);
        }
    }

    /**
     * Adds the settings menu item.
     *
     * @param actionListener
     *            the action listener
     * @param trayMenu
     *            the tray menu
     * @param csFileMenu
     *            the cs file menu
     */
    @SuppressWarnings("unused")
    protected void addSettingsMenu(final ActionListener actionListener, final JPopupMenu trayMenu,
        final JMenu csFileMenu) {

        this.settingsMenuTray = new JMenu(AppMessages.getI18n("MENU_SETTINGS"));
        this.settingsMenuTray.setIcon(AppIcon.getIcon(PREFERENCES_PNG));

        final JMenuItem mi = new JMenuItem(AppMessages.getI18n("MENU_EDIT_SETTINGS"));

        mi.setActionCommand(ACTION_SETTINGS);
        mi.addActionListener(actionListener);
        mi.setIcon(AppIcon.getIcon(PREFERENCES_PNG));
        this.settingsMenuTray.add(mi);

        if (trayMenu != null) {
            trayMenu.add(this.settingsMenuTray);
        }
    }

    /**
     * Gets the file menu.
     *
     * @return the file menu
     */
    public JMenu getFileMenu() {
        return this.fileMenu;
    }

    /**
     * Sets the file menu.
     *
     * @param csFileMenu
     *            the FileMenu
     */
    public void setFileMenu(final JMenu csFileMenu) {
        this.fileMenu = csFileMenu;
    }

    /**
     * Set the detachMenuItem.
     *
     * @param detachMenuItem
     *            the detachMenuItem to set
     */
    public void setDetachMenuItem(final JMenuItem detachMenuItem) {
        this.detachMenuItem = detachMenuItem;
    }

    /**
     * Gets the detach menu item.
     *
     * @return the detach menu item
     */
    public JMenuItem getDetachMenuItem() {
        return this.detachMenuItem;
    }

    /**
     * Gets the main menu.
     *
     * @return the main menu
     */
    protected JMenuBar getMainMenu() {
        return this.mainMenu;
    }

    /**
     * Gets the settings menu.
     *
     * @return the settings menu
     */
    public JMenu getSettingsMenu() {
        return this.settingsMenu;
    }

    /**
     * Gets the settings menu in the tray.
     *
     * @return the settings menu in the tray
     */
    public JMenu getSettingsMenuTray() {
        return this.settingsMenuTray;
    }
}
