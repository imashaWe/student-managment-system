package screens;

import services.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dashboard extends JFrame{
    private JButton addNewButton;
    private JButton deleteButton;
    private JButton editButton;
    private JPanel jPanle;
    private JTable table;
    private javax.swing.JScrollPane JScrollPane;
    private JTextField searchField;
    private JButton searchButton;

    final Database database = new Database();

    public Dashboard() {
        add(jPanle);
        setSize(500,500);
        setLocationRelativeTo(null);

        addNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                new AddNewStu().setVisible(true);
                super.mouseClicked(e);
            }
        });

        editButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final int r = table.getSelectedRow();
                if (r == -1)return;
                AddNewStu addNewStu = new AddNewStu();
                String id = (String) table.getValueAt(r,0);
                String fname = (String) table.getValueAt(r,1);
                String lname = (String) table.getValueAt(r,2);
                String nic = (String) table.getValueAt(r,3);
                String phone = (String) table.getValueAt(r,4);
                addNewStu.initStudent(Integer.parseInt(id),fname,lname,nic,phone);
                setVisible(false);
                addNewStu.setVisible(true);
                super.mouseClicked(e);
            }
        });

        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final int r = table.getSelectedRow();
                if (r == -1)return;
                int response =  JOptionPane.showConfirmDialog(jPanle,"You want to delete this student","Are you sure?",JOptionPane.YES_OPTION,JOptionPane.CANCEL_OPTION);
                if (response == 0) {
                    String id = (String) table.getValueAt(r,0);
                    database.delete(Integer.parseInt(id));
                    createTable("");
                }
                super.mouseClicked(e);
            }
        });

        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                createTable(searchField.getText());
                super.mouseClicked(e);
            }
        });
        createTable("");

    }
    private void createTable(String q){

        final String[] columnNames = {"Id","First Name","Last Name","NIC","Phone"};
        final DefaultTableModel tableModel = new DefaultTableModel(columnNames,0);

        for (Object[] r:database.getAllStudent(q)) {
            tableModel.addRow(r);
        }

       table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        JScrollPane.setViewportView(table);
    }
}
