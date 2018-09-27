package net.agilhard.gui.framework.swing.app;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.lang.reflect.Field;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class AppUtil.
 *
 * @author bei
 */
public final class AppUtil {

    /**
     * SLF4J logger for this class.
     */
    static final transient Logger LOG = LoggerFactory.getLogger(AppUtil.class);

    /**
     * Private Constructor for utility class.
     */
    private AppUtil() {
        // .
    }

    /**
     * Fix for java webstart problem when started without java-console.
     */
    public static void quickAndDirtyFixForProblemWithWebStartInJava7u25() {

        final String wsVersion = System.getProperty("javawebstart.version");
        if (wsVersion == null || !wsVersion.startsWith("javaws-10.25.2.")) {
            return;
        }
        LOG.info("Java Web Start 10.25.2.* (from JRE 7u25) detected - applying classloader fix");

        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(classLoader);

        try {
            SwingUtilities.invokeAndWait(new Runnable() {

                @Override
                public void run() {
                    try {
                        // Change context in all future threads
                        final Field field = EventQueue.class.getDeclaredField("classLoader");
                        field.setAccessible(true);
                        final EventQueue eventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();
                        field.set(eventQueue, classLoader);
                        // Change context in this thread
                        Thread.currentThread().setContextClassLoader(classLoader);
                    }
                    catch (final Exception e) {
                        LOG.error("Unable to apply 'fix' for java 1.7u25", e);
                    }
                }
            });
        }
        catch (final Exception e) {
            LOG.error("Unable to apply 'fix' for java 1.7u25", e);
        }
    }

}
