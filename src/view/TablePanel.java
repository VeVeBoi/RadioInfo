/*
TablePanel
Author: Vedad Coric
*/

package view;

import controller.ProgSelectionListener;
import model.ProgramList;
import model.ProgramTable;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.time.LocalDateTime;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;

/**
 * TablePanel Class
 *
 * This UI class extends JPanel and constructs
 * the panel with a JTable to visualize the radio
 * programs.
 */
public class TablePanel extends JPanel {

    JTable table;
    JScrollPane scrollPane;
    ListSelectionModel model;
    public ProgramTable tableModel;

    /**
     * TablePanel
     * Constructor method
     * @param programList ProgramListModel
     */
    public TablePanel(ProgramList programList){

        String[] columnNames = {"Program", "Start", "End"};
        tableModel = new ProgramTable(programList);
        table = new JTable(tableModel);
        this.model = table.getSelectionModel();
        /* set header title */
        for(int i = 0; i < 3; i++){
            table.getColumnModel().getColumn(i).setHeaderValue(columnNames[i]);
        }
        /* Set coloring of the table rows according to time */
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table,
                                                           Object value,
                                                           boolean isSelected,
                                                           boolean hasFocus,
                                                           int row,
                                                           int col) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                LocalDateTime startTime = (LocalDateTime) table.getModel().getValueAt(row, 2);
                if (startTime.isBefore(LocalDateTime.now())) {
                    setBackground(Color.GRAY);
                    setForeground(Color.BLACK);
                } else{
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());
                }
                value = startTime.format(ISO_LOCAL_TIME);
                return this;
            }
        });
        scrollPane = new JScrollPane(table);
        scrollPane.setViewportView(table);
        scrollPane.setPreferredSize(new Dimension(1000, 720));
        table.setAutoCreateRowSorter(true);
        add(scrollPane);
    }

    /**
     * addSelectionListener
     * This  method is used to trigger ActionEvents
     * for clicking into the table.
     * @param listener
     */
    public void addSelectionListener(ProgSelectionListener listener){
        model.addListSelectionListener(listener);
    }

    /**
     * getTable
     * getter method for the table object
     * @return
     */
    public JTable getTable(){
        return table;
    }
}
