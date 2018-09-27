package net.agilhard.gui.framework.swing.edit;

/**
 * Interface for a Panel which should have the Edit menu.
 * 
 * @author bei
 */
public interface EditSupport {

    /**
     * Configure the Edit Menu.
     * 
     * @param helper
     *            a EditorMenuHelper
     */
    void configureEditMenu(final EditorMenuHelper helper);

}
