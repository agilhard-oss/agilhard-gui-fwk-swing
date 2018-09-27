package net.agilhard.gui.framework.swing.app;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * The Class AppMessages.
 */
public final class AppMessages {

    /**
     * Private constructor for utility class.
     */
    private AppMessages() {
        // .
    }

    /**
     * Gets the i18n.
     *
     * @param key
     *            the key
     * @param args
     *            the args
     * @return the i18n
     */
    public static String getI18n(final String key, final Object... args) {
        final ResourceBundle bundle = ResourceBundle.getBundle("net.agilhard.gui.framework.swing.app.AppMessages");
        return bundle == null ? null : getI18n(bundle, key, args);
    }

    /**
     * Gets the i18n.
     *
     * @param key
     *            the key
     * @param args
     *            the args
     * @return the i18n
     */
    public static String getI18KeyDefault(final String key, final String defaultValue, final Object... args) {
        final ResourceBundle bundle = ResourceBundle.getBundle("net.agilhard.terminal.AppMessages");
        return bundle == null ? null : getI18KeyDefault(bundle, key, defaultValue, args);
    }

    /**
     * Gets the i18n.
     *
     * @param bundle
     *            the bundle
     * @param key
     *            the key
     * @param args
     *            the args
     * @return the i18n
     */
    public static String getI18n(final ResourceBundle bundle, final String key, final Object... args) {
        String s = null;
        if (bundle == null) {
            return getI18n(key, args);
        } else {
            try {
                s = bundle.getString(key);
                s = MessageFormat.format(s, args);
            }
            catch (final Exception e) {
                s = "(" + key + "*)";
            }
        }
        return s;
    }

    /**
     * Gets the i18n.
     *
     * @param bundle
     *            the bundle
     * @param key
     *            the key
     * @return the i18n
     */
    public static String getI18KeyDefault(final ResourceBundle bundle, final String key, final String defaultValue,
        final Object... args) {
        String s = null;
        if (bundle == null) {
            return getI18KeyDefault(key, defaultValue, args);
        } else {
            try {
                s = bundle.getString(key);
                s = MessageFormat.format(s, args);
            }
            catch (final Exception e) {
                s = defaultValue;
            }
        }
        return s;
    }
}
