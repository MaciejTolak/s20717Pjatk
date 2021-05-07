package zad1;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class MyTableRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      setHorizontalAlignment(SwingConstants.RIGHT);

      if((Integer)value>20000){
          c.setForeground(Color.RED);
      }else
      {
          c.setForeground(Color.BLACK);
      }

      return c;
    }
}
