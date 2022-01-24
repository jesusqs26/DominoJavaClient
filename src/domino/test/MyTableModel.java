/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.test;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jesus
 */
public class MyTableModel extends AbstractTableModel{

    @Override
    public int getRowCount() {
        return 4;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         return (rowIndex * columnIndex);
    }
    
}
