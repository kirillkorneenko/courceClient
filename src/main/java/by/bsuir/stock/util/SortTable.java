package by.bsuir.stock.util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class SortTable {

    public RowSorter<TableModel> sorter(JTable table,String columns[]){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object rows[][] = {};
        TableModel modelTable = new DefaultTableModel(rows, columns) {
            public Class getCollumnClass(int column) {
                Class returnValue;
                if ((column >= 0) && (column < getColumnCount())) {
                    returnValue = getValueAt(0, column).getClass();
                } else {
                    returnValue = Object.class;
                }
                return returnValue;
            }
        };

        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);

        return sorter;
    }

}
