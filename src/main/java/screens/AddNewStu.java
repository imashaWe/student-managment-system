package screens;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import services.Database;

public class AddNewStu extends JFrame{
    private JTextField fnameField;
    private JTextField lnameField;
    private JTextField nicField;
    private JTextField phoneField;
    private JButton backButton;
    private JPanel jPanel;
    private JButton saveButton;

    private Database database = new Database();

    private int studentId = 0;

    public  AddNewStu() {
        add(jPanel);
        setSize(500,500);
        setLocationRelativeTo(null);

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                new Dashboard().setVisible(true);
                super.mouseClicked(e);
            }
        });

        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final String fname = fnameField.getText();
                final  String lname = lnameField.getText();
                final  String nic = nicField.getText();
                final  String phone = phoneField.getText();
                if (studentId == 0){
                    database.addNewStu(fname,lname,nic,phone);
                } else {
                    database.edit(studentId,fname,lname,nic,phone);
                }


                setVisible(false);
                new Dashboard().setVisible(true);

                super.mouseClicked(e);
            }
        });
    }

    public void initStudent(int id,String fname,String lname,String nic,String phone) {
        studentId = id;
        fnameField.setText(fname);
        lnameField.setText(lname);
        nicField.setText(nic);
        phoneField.setText(phone);
    }
}
