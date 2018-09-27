package net.agilhard.gui.framework.swing.edit;

import static net.agilhard.gui.framework.swing.edit.EditorConstants.ACTION_NEW_FILE;
import static net.agilhard.gui.framework.swing.edit.EditorConstants.ACTION_NEW_FILE_NEWFRAME;
import static net.agilhard.gui.framework.swing.edit.EditorConstants.ACTION_NEW_FILE_NEWTAB;
import static net.agilhard.gui.framework.swing.edit.EditorConstants.ACTION_OPEN_FILE;
import static net.agilhard.gui.framework.swing.edit.EditorConstants.ACTION_OPEN_FILE_NEWFRAME;
import static net.agilhard.gui.framework.swing.edit.EditorConstants.ACTION_OPEN_FILE_NEWTAB;
import static net.agilhard.gui.framework.swing.edit.SyntaxConstants.SYNTAX_STYLE_CSS;
import static net.agilhard.gui.framework.swing.edit.SyntaxConstants.SYNTAX_STYLE_DTD;
import static net.agilhard.gui.framework.swing.edit.SyntaxConstants.SYNTAX_STYLE_GROOVY;
import static net.agilhard.gui.framework.swing.edit.SyntaxConstants.SYNTAX_STYLE_HTML;
import static net.agilhard.gui.framework.swing.edit.SyntaxConstants.SYNTAX_STYLE_JAVA;
import static net.agilhard.gui.framework.swing.edit.SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT;
import static net.agilhard.gui.framework.swing.edit.SyntaxConstants.SYNTAX_STYLE_JSP;
import static net.agilhard.gui.framework.swing.edit.SyntaxConstants.SYNTAX_STYLE_NONE;
import static net.agilhard.gui.framework.swing.edit.SyntaxConstants.SYNTAX_STYLE_PERL;
import static net.agilhard.gui.framework.swing.edit.SyntaxConstants.SYNTAX_STYLE_PROPERTIES_FILE;
import static net.agilhard.gui.framework.swing.edit.SyntaxConstants.SYNTAX_STYLE_RUBY;
import static net.agilhard.gui.framework.swing.edit.SyntaxConstants.SYNTAX_STYLE_SQL;
import static net.agilhard.gui.framework.swing.edit.SyntaxConstants.SYNTAX_STYLE_UNIX_SHELL;
import static net.agilhard.gui.framework.swing.edit.SyntaxConstants.SYNTAX_STYLE_WINDOWS_BATCH;
import static net.agilhard.gui.framework.swing.edit.SyntaxConstants.SYNTAX_STYLE_XML;
import static net.agilhard.gui.framework.swing.edit.TextEditorConstants.ACTION_COPY;
import static net.agilhard.gui.framework.swing.edit.TextEditorConstants.ACTION_COPY_AS_RTF;
import static net.agilhard.gui.framework.swing.edit.TextEditorConstants.ACTION_CUT;
import static net.agilhard.gui.framework.swing.edit.TextEditorConstants.ACTION_EDITOR_MODE;
import static net.agilhard.gui.framework.swing.edit.TextEditorConstants.ACTION_FIND;
import static net.agilhard.gui.framework.swing.edit.TextEditorConstants.ACTION_GOTO_LINE;
import static net.agilhard.gui.framework.swing.edit.TextEditorConstants.ACTION_PASTE;
import static net.agilhard.gui.framework.swing.edit.TextEditorConstants.ACTION_RELOAD;
import static net.agilhard.gui.framework.swing.edit.TextEditorConstants.ACTION_REPLACE;
import static net.agilhard.gui.framework.swing.edit.TextEditorConstants.ACTION_SAVE;
import static net.agilhard.gui.framework.swing.edit.TextEditorConstants.ACTION_SAVEAS;

import java.awt.Event;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import net.agilhard.gui.framework.swing.app.AppIcon;
import net.agilhard.gui.framework.swing.app.AppMenuHelper;

/**
 * The Class EditorMenuHelper.
 */
public class EditorMenuHelper extends AppMenuHelper {

    /** The Constant RTF_PNG. */
    private static final String RTF_PNG = "rtf.png";

    /** The Constant GOTO_PNG. */
    private static final String GOTO_PNG = "goto.png";

    /** The Constant EDIT_FIND_REPLACE_PNG. */
    private static final String EDIT_FIND_REPLACE_PNG = "edit-find-replace.png";

    /** The Constant EDIT_FIND_PNG. */
    private static final String EDIT_FIND_PNG = "edit-find.png";

    /** The Constant RELOAD_PNG. */
    private static final String RELOAD_PNG = "reload.png";

    /** The Constant CUT_PNG. */
    private static final String CUT_PNG = "cut.png";

    /** The Constant PASTE_PNG. */
    private static final String PASTE_PNG = "paste.png";

    /** The Constant COPY_PNG. */
    private static final String COPY_PNG = "copy.png";

    /** The Constant EDIT_PNG. */
    private static final String EDIT_PNG = "edit.png";

    /** The Constant MIME_PNG. */
    private static final String MIME_PNG = "mime.png";

    /** The Constant SAVEAS_PNG. */
    private static final String SAVEAS_PNG = "saveas.png";

    /** The Constant SAVE_PNG. */
    private static final String SAVE_PNG = "save.png";

    /** The Constant OPEN_PNG. */
    private static final String OPEN_PNG = "open.png";

    /** The Constant NEW_PNG. */
    private static final String NEW_PNG = "new.png";

    /** The editor menu. */
    private JMenu editorMenu;

    /** The mode menu. */
    private JMenu modeMenu;

    /** The save menu item. */
    private JMenuItem saveMenuItem;

    /** The save as menu item. */
    private JMenuItem saveAsMenuItem;

    /** The paste menu item. */
    private JMenuItem pasteMenuItem;

    /** The cut menu item. */
    private JMenuItem cutMenuItem;

    /** The copy menu item. */
    private JMenuItem copyMenuItem;

    /** The reload menu item. */
    private JMenuItem reloadMenuItem;

    /** The replace menu item. */
    private JMenuItem replaceMenuItem;

    /*
     * (non-Javadoc)
     *
     * @see net.agilhard.guk.framework.swing.app.AppMenuHelper#addAppMenutems(java.awt.event.
     * ActionListener, javax.swing.JMenuBar, javax.swing.JMenu, boolean)
     */
    /** {@inheritDoc} */
    @Override
    public void addAppMenuItems(final ActionListener actionListener, final JMenuBar mainMenu, final JMenu fileMenu,
        final boolean forFrame) {
        this.addAppMenuItems(actionListener, mainMenu, fileMenu, forFrame, true);
    }

    /**
     * Adds the app menutems.
     *
     * @param actionListener
     *            the action listener
     * @param mainMenu
     *            the main menu
     * @param fileMenu
     *            the file menu
     * @param forFrame
     *            the for frame
     * @param withNewOpen
     *            the with new open
     */
    public void addAppMenuItems(final ActionListener actionListener, final JMenuBar mainMenu, final JMenu fileMenu,
        final boolean forFrame, final boolean withNewOpen) {

        if (withNewOpen) {
            JMenuItem mi = new JMenuItem(EditorMessages.getI18n("MENU_NEW_FILE"));
            mi.addActionListener(actionListener);
            mi.setActionCommand(ACTION_NEW_FILE);
            if (EditorMessages.getI18n("MENU_NEW_FILE_MNEMONIC") != null) {
                mi.setMnemonic(EditorMessages.getI18n("MENU_NEW_FILE_MNEMONIC").charAt(0));
            }
            mi.setIcon(AppIcon.getIcon(NEW_PNG));
            mi.setAccelerator(KeyStroke.getKeyStroke('N', java.awt.event.InputEvent.ALT_DOWN_MASK));
            fileMenu.add(mi);

            if (forFrame) {

                mi = new JMenuItem(EditorMessages.getI18n("MENU_NEW_FILE_NEWTAB"));
                mi.addActionListener(actionListener);
                mi.setActionCommand(ACTION_NEW_FILE_NEWTAB);
                mi.setIcon(AppIcon.getIcon(NEW_PNG));
                fileMenu.add(mi);

            } else {
                mi = new JMenuItem(EditorMessages.getI18n("MENU_NEW_FILE_NEWFRAME"));
                mi.addActionListener(actionListener);
                mi.setActionCommand(ACTION_NEW_FILE_NEWFRAME);
                mi.setIcon(AppIcon.getIcon(NEW_PNG));
                fileMenu.add(mi);

            }

            mi = new JMenuItem(EditorMessages.getI18n("MENU_OPEN_FILE"));
            mi.addActionListener(actionListener);
            mi.setActionCommand(ACTION_OPEN_FILE);
            if (EditorMessages.getI18n("MENU_OPEN_FILE_MNEMONIC") != null) {
                mi.setMnemonic(EditorMessages.getI18n("MENU_OPEN_FILE_MNEMONIC").charAt(0));
            }
            mi.setIcon(AppIcon.getIcon(OPEN_PNG));
            mi.setAccelerator(KeyStroke.getKeyStroke('O', java.awt.event.InputEvent.ALT_DOWN_MASK));

            fileMenu.add(mi);

            if (forFrame) {
                mi = new JMenuItem(EditorMessages.getI18n("MENU_OPEN_FILE_NEWTAB"));
                mi.addActionListener(actionListener);
                mi.setActionCommand(ACTION_OPEN_FILE_NEWTAB);
                mi.setIcon(AppIcon.getIcon(OPEN_PNG));
                fileMenu.add(mi);

            } else {

                mi = new JMenuItem(EditorMessages.getI18n("MENU_OPEN_FILE_NEWFRAME"));
                mi.addActionListener(actionListener);
                mi.setActionCommand(ACTION_OPEN_FILE_NEWFRAME);
                mi.setIcon(AppIcon.getIcon(OPEN_PNG));
                fileMenu.add(mi);
            }

        }

        JMenuItem mi = null;

        if (mainMenu != null) {
            mi = new JMenuItem(EditorMessages.getI18n("MENU_SAVE"));
            mi.addActionListener(actionListener);
            mi.setActionCommand(ACTION_SAVE);
            if (EditorMessages.getI18n("MENU_SAVE_MNEMONIC") != null) {
                mi.setMnemonic(EditorMessages.getI18n("MENU_SAVE_MNEMONIC").charAt(0));
            }
            mi.setIcon(AppIcon.getIcon(SAVE_PNG));
            this.saveMenuItem = mi;
            fileMenu.add(mi);
            mi.setAccelerator(KeyStroke.getKeyStroke('S', java.awt.event.InputEvent.CTRL_DOWN_MASK));

            mi = new JMenuItem(EditorMessages.getI18n("MENU_SAVEAS"));
            mi.addActionListener(actionListener);
            mi.setActionCommand(ACTION_SAVEAS);
            mi.setIcon(AppIcon.getIcon(SAVEAS_PNG));
            mi.setAccelerator(KeyStroke.getKeyStroke('S',
                java.awt.event.InputEvent.CTRL_DOWN_MASK | java.awt.event.InputEvent.SHIFT_DOWN_MASK));

            this.saveAsMenuItem = mi;
            fileMenu.add(mi);
        }

        if (mainMenu != null) {
            this.editorMenu = this.createEditorMenu(actionListener, true, true, false);
            mainMenu.add(this.editorMenu);
        }
    }

    /**
     * Gets the editor menu.
     *
     * @return the editor menu
     */
    public JMenu getEditorMenu() {
        return this.editorMenu;
    }

    /**
     * Gets the editor menu.
     *
     * @param actionListener
     *            the action listener
     * @param withModes
     *            the with modes
     * @param withReload
     *            the with reload
     * @param useShiftCopyPaste
     *            the use shift copy paste
     * @return the editor menu
     */
    public JMenu createEditorMenu(final ActionListener actionListener, final boolean withModes,
        final boolean withReload, final boolean useShiftCopyPaste) {
        final JMenu menu = new JMenu(EditorMessages.getI18n("MENU_EDITOR"));
        menu.setIcon(AppIcon.getIcon(EDIT_PNG));

        JMenuItem mi = null;
        char acc;

        if (withModes) {
            this.modeMenu = new JMenu(EditorMessages.getI18n("MENU_EDITOR_MODE"));
            this.modeMenu.setIcon(AppIcon.getIcon(MIME_PNG));

            final String[] modes = { SYNTAX_STYLE_NONE, SYNTAX_STYLE_GROOVY, SYNTAX_STYLE_JAVA, SYNTAX_STYLE_RUBY,
                SYNTAX_STYLE_SQL, SYNTAX_STYLE_PROPERTIES_FILE, SYNTAX_STYLE_XML, SYNTAX_STYLE_HTML, SYNTAX_STYLE_CSS,
                SYNTAX_STYLE_JAVASCRIPT, SYNTAX_STYLE_DTD, SYNTAX_STYLE_PERL, SYNTAX_STYLE_UNIX_SHELL,
                SYNTAX_STYLE_WINDOWS_BATCH, SYNTAX_STYLE_JSP };
            for (int i = 0; i < modes.length; i++) {
                mi = new JMenuItem(modes[i]);
                mi.setActionCommand(ACTION_EDITOR_MODE + modes[i]);
                mi.addActionListener(actionListener);
                this.modeMenu.add(mi);
            }
            menu.add(this.modeMenu);
        }

        mi = new JMenuItem(EditorMessages.getI18n("MENU_EDITOR_COPY"));
        mi.addActionListener(actionListener);
        if (EditorMessages.getI18n("MENU_EDITOR_RELOAD_MNEMONIC") != null) {
            if (useShiftCopyPaste) {
                mi.setAccelerator(
                    KeyStroke.getKeyStroke(EditorMessages.getI18n("MENU_EDITOR_COPY_ACCELERATOR").charAt(0),
                        Event.CTRL_MASK | Event.SHIFT_MASK));
            } else {
                mi.setAccelerator(KeyStroke
                    .getKeyStroke(EditorMessages.getI18n("MENU_EDITOR_COPY_ACCELERATOR").charAt(0), Event.CTRL_MASK));
            }
        }
        mi.setActionCommand(ACTION_COPY);
        mi.setIcon(AppIcon.getIcon(COPY_PNG));
        this.copyMenuItem = mi;
        menu.add(mi);

        mi = new JMenuItem(EditorMessages.getI18n("MENU_EDITOR_PASTE"));
        mi.addActionListener(actionListener);
        if (EditorMessages.getI18n("MENU_EDITOR_PASTE_ACCELERATOR") != null) {
            if (useShiftCopyPaste) {
                mi.setAccelerator(
                    KeyStroke.getKeyStroke(EditorMessages.getI18n("MENU_EDITOR_PASTE_ACCELERATOR").charAt(0),
                        Event.CTRL_MASK | Event.SHIFT_MASK));
            } else {
                mi.setAccelerator(KeyStroke
                    .getKeyStroke(EditorMessages.getI18n("MENU_EDITOR_PASTE_ACCELERATOR").charAt(0), Event.CTRL_MASK));
            }
        }
        mi.setActionCommand(ACTION_PASTE);
        mi.setIcon(AppIcon.getIcon(PASTE_PNG));
        this.pasteMenuItem = mi;
        menu.add(mi);

        mi = new JMenuItem(EditorMessages.getI18n("MENU_EDITOR_CUT"));
        mi.addActionListener(actionListener);
        if (EditorMessages.getI18n("MENU_EDITOR_CUT_ACCELERATOR") != null) {
            mi.setAccelerator(KeyStroke.getKeyStroke(EditorMessages.getI18n("MENU_EDITOR_CUT_ACCELERATOR").charAt(0),
                Event.CTRL_MASK));
        }
        mi.setActionCommand(ACTION_CUT);
        mi.setIcon(AppIcon.getIcon(CUT_PNG));
        this.cutMenuItem = mi;
        menu.add(mi);

        if (withReload) {
            mi = new JMenuItem(EditorMessages.getI18n("MENU_EDITOR_RELOAD"));
            mi.addActionListener(actionListener);
            acc = EditorMessages.getI18n("MENU_EDITOR_RELOAD_ACCELERATOR").charAt(0);
            mi.setActionCommand(ACTION_RELOAD);
            mi.setAccelerator(KeyStroke.getKeyStroke(acc, Event.CTRL_MASK));
            mi.setIcon(AppIcon.getIcon(RELOAD_PNG));
            this.reloadMenuItem = mi;
            menu.add(mi);
        }

        mi = new JMenuItem(EditorMessages.getI18n("MENU_EDITOR_FIND"));
        acc = EditorMessages.getI18n("MENU_EDITOR_FIND_ACCELERATOR").charAt(0);
        mi.setActionCommand(ACTION_FIND);
        mi.setAccelerator(KeyStroke.getKeyStroke(acc, Event.CTRL_MASK));
        mi.addActionListener(actionListener);
        mi.setIcon(AppIcon.getIcon(EDIT_FIND_PNG));
        menu.add(mi);

        mi = new JMenuItem(EditorMessages.getI18n("MENU_EDITOR_REPLACE"));
        acc = EditorMessages.getI18n("MENU_EDITOR_REPLACE_ACCELERATOR").charAt(0);
        mi.setActionCommand(ACTION_REPLACE);
        mi.setAccelerator(KeyStroke.getKeyStroke(acc, Event.CTRL_MASK));
        mi.addActionListener(actionListener);
        mi.setIcon(AppIcon.getIcon(EDIT_FIND_REPLACE_PNG));
        this.replaceMenuItem = mi;
        menu.add(mi);

        mi = new JMenuItem(EditorMessages.getI18n("MENU_EDITOR_GOTO_LINE"));
        acc = EditorMessages.getI18n("MENU_EDITOR_GOTO_LINE_ACCELERATOR").charAt(0);
        mi.setActionCommand(ACTION_GOTO_LINE);
        mi.setAccelerator(KeyStroke.getKeyStroke(acc, Event.CTRL_MASK));
        mi.addActionListener(actionListener);
        mi.setIcon(AppIcon.getIcon(GOTO_PNG));
        menu.add(mi);

        mi = new JMenuItem(EditorMessages.getI18n("MENU_EDITOR_COPY_AS_RTF"));
        mi.addActionListener(actionListener);
        mi.setActionCommand(ACTION_COPY_AS_RTF);
        mi.setIcon(AppIcon.getIcon(RTF_PNG));
        menu.add(mi);

        this.editorMenu = menu;
        return menu;
    }

    /**
     * Gets the mode menu.
     *
     * @return the mode menu
     */
    public JMenu getModeMenu() {
        return this.modeMenu;
    }

    /**
     * Gets the save menu item.
     *
     * @return the save menu item
     */
    public JMenuItem getSaveMenuItem() {
        return this.saveMenuItem;
    }

    /**
     * Gets the save as menu item.
     *
     * @return the save as menu item
     */
    public JMenuItem getSaveAsMenuItem() {
        return this.saveAsMenuItem;
    }

    /**
     * Gets the paste menu item.
     *
     * @return the paste menu item
     */
    public JMenuItem getPasteMenuItem() {
        return this.pasteMenuItem;
    }

    /**
     * Gets the cut menu item.
     *
     * @return the cut menu item
     */
    public JMenuItem getCutMenuItem() {
        return this.cutMenuItem;
    }

    /**
     * Gets the copy menu item.
     *
     * @return the copyMenuItem
     */
    public JMenuItem getCopyMenuItem() {
        return this.copyMenuItem;
    }

    /**
     * Gets the reload menu item.
     *
     * @return the reload menu item
     */
    public JMenuItem getReloadMenuItem() {
        return this.reloadMenuItem;
    }

    /**
     * Gets the replace menu item.
     *
     * @return the replace menu item
     */
    public JMenuItem getReplaceMenuItem() {
        return this.replaceMenuItem;
    }

    /**
     * hide all edit Menu items.
     */
    public void hideAllEditMenu() {
        final JMenu menu = this.getEditorMenu();
        if (menu != null) {
            menu.setEnabled(false);
            for (int i = 0; i < menu.getItemCount(); i++) {
                final JMenuItem item = menu.getItem(i);
                item.setEnabled(false);
                item.setVisible(false);
            }
        }
    }

    /**
     * Show all edit Menu items.
     */
    public void showAllEditMenu() {
        final JMenu menu = this.getEditorMenu();
        if (menu != null) {
            menu.setEnabled(true);
            for (int i = 0; i < menu.getItemCount(); i++) {
                final JMenuItem item = menu.getItem(i);
                item.setEnabled(true);
                item.setVisible(true);
            }
        }
    }

    /**
     * Enable only copy and paste but disable everything else.
     */
    public void showCopyPasteEditMenu() {
        final JMenu menu = this.getEditorMenu();
        if (menu != null) {
            menu.setEnabled(true);
            for (int i = 0; i < menu.getItemCount(); i++) {
                final JMenuItem item = menu.getItem(i);
                final String action = item.getActionCommand();
                if (ACTION_COPY.equals(action) || ACTION_PASTE.equals(action)) {
                    item.setEnabled(true);
                    item.setVisible(true);
                } else {
                    item.setEnabled(false);
                    item.setVisible(false);
                }
            }
        }
    }
}
