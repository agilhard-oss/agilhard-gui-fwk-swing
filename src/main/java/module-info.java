module net.agilhard.gui.framework.swing {
    exports net.agilhard.gui.framework.swing.app;
    exports net.agilhard.gui.framework.swing.edit;
    opens net.agilhard.gui.framework.swing.icons;
    opens net.agilhard.gui.framework.swing.image;
    requires java.desktop;
    requires org.slf4j;
}