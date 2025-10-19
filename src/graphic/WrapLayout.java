// arquivo: WrapLayout.java
package graphic;

import java.awt.*;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class WrapLayout extends FlowLayout {

    public WrapLayout() { super(); }
    public WrapLayout(int align) { super(align); }
    public WrapLayout(int align, int hgap, int vgap) { super(align, hgap, vgap); }

    @Override
    public Dimension preferredLayoutSize(Container target) {
        return layoutSize(target, true);
    }

    @Override
    public Dimension minimumLayoutSize(Container target) {
        Dimension minimum = layoutSize(target, false);
        minimum.width -= (getHgap() + 1);
        return minimum;
    }

    private Dimension layoutSize(Container target, boolean preferred) {
        synchronized (target.getTreeLock()) {
            int targetWidth = target.getWidth();

            if (targetWidth == 0) {
                Container parent = target.getParent();
                if (parent instanceof JScrollPane) {
                    targetWidth = ((JScrollPane) parent).getViewport().getWidth();
                }
            }

            int hgap = getHgap();
            int vgap = getVgap();
            Insets insets = target.getInsets();
            int maxWidth = targetWidth > 0
                    ? targetWidth - insets.left - insets.right - hgap * 2
                    : Integer.MAX_VALUE;

            Dimension dim = new Dimension(0, 0);
            int rowWidth = 0;
            int rowHeight = 0;

            int nmembers = target.getComponentCount();
            for (int i = 0; i < nmembers; i++) {
                if (!target.getComponent(i).isVisible()) continue;

                Dimension d = preferred
                        ? target.getComponent(i).getPreferredSize()
                        : target.getComponent(i).getMinimumSize();

                if (rowWidth + d.width > maxWidth) {
                    addRow(dim, rowWidth, rowHeight);
                    rowWidth = 0;
                    rowHeight = 0;
                }

                if (rowWidth != 0) rowWidth += hgap;
                rowWidth += d.width;
                rowHeight = Math.max(rowHeight, d.height);
            }

            addRow(dim, rowWidth, rowHeight);

            dim.width += insets.left + insets.right + hgap * 2;
            dim.height += insets.top + insets.bottom + vgap * 2;

            // garante que o preferredSize não fique menor que o viewport (evita barra horizontal)
            Container scrollPane = SwingUtilities.getAncestorOfClass(JScrollPane.class, target);
            if (scrollPane != null) {
                int viewportWidth = ((JScrollPane) scrollPane).getViewport().getWidth();
                dim.width = Math.max(dim.width, viewportWidth);
            }
            return dim;
        }
    }

    private void addRow(Dimension dim, int rowWidth, int rowHeight) {
        if (rowWidth == 0) return;
        if (dim.width > 0) dim.width = Math.max(dim.width, rowWidth);
        else dim.width = rowWidth;
        if (dim.height > 0) dim.height += getVgap();
        dim.height += rowHeight;
    }
}
