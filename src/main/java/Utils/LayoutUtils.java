package Utils;

import javax.swing.*;
import java.awt.*;


//layoututils para wrapper que tiene FlowLayout para forzar Flowlayout a convertirse a un grid.Es un hack pq gridlayout no quiere trabajar con scrollpane
public abstract class LayoutUtils {
    public static void calculatePreferedSizeInGrid(JPanel wrapper, final int COLUMNS, final int GAP) {
        Dimension d = wrapper.getComponent(0).getPreferredSize();
        int rows = (wrapper.getComponentCount() + COLUMNS - 1) / COLUMNS;

        wrapper.setPreferredSize(new Dimension(d.width * COLUMNS, (d.height + GAP) * rows));

        wrapper.revalidate();
        wrapper.repaint();
    }

    public static void removeAllComponents(JPanel wrapper) {
        wrapper.removeAll();

        wrapper.revalidate();
        wrapper.repaint();
    }
}
