package net.agilhard.gui.framework.swing.edit;

import java.util.ResourceBundle;

import net.agilhard.gui.framework.swing.app.AppMessages;

/**
 * The Class EditorMessages.
 */
public final class EditorMessages {

    /**
     * The resource bundle.
     */
    private static final ResourceBundle bundle =
        ResourceBundle.getBundle("net.agilhard.gui.framework.swing.edit.EditorMessages");

    /**
     * Private constructor for utility class.
     */
    private EditorMessages() {
        // .
    }

    /**
     * Gets the i18n.
     *
     * @param key
     *            the key
     * @return the i18n
     */
    public static String getI18n(final String key, final Object... args) {
        return AppMessages.getI18n(bundle, key, args);
    }
}
