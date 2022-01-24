/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.test;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Jesus
 */
public class ImageRenderer extends DefaultTableCellRenderer {

    JLabel lbl = new JLabel();
    ImageIcon icon;

    public ImageRenderer(ImageIcon icon) {
        this.icon = icon;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        
        lbl.setIcon(icon);
        return lbl;
    }

}
