package zad1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Gui extends JFrame {

    private JButton jb1;
    private JButton jb2;
    private JTextArea jta;
    private JScrollPane jsp;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private List<JButton> list;

    public Gui(){
        window();
        jb1 = new JButton("stop");
        jb2 = new JButton("create new");
        jta = new JTextArea();
        jta.setEditable(false);
        jsp = new JScrollPane(jta);
        jPanel1 = new JPanel(new BorderLayout());
        jPanel2 = new JPanel();
        this.getContentPane().add(jsp,BorderLayout.CENTER);
        jPanel1.add(jb1,BorderLayout.NORTH);
        jPanel1.add(jb2,BorderLayout.SOUTH);

        this.getContentPane().add(jPanel1,BorderLayout.NORTH);
        this.getContentPane().add(jPanel2,BorderLayout.SOUTH);

        list = new ArrayList<>();


    }

    public JButton getJb1() {
        return jb1;
    }

    public JButton getJb2() {
        return jb2;
    }

    public JTextArea getJta() {
        return jta;
    }

    public JPanel getjPanel2() {
        return jPanel2;
    }

    public List<JButton> getList() {
        return list;
    }

    public void setList(List<JButton> list) {
        this.list = list;
    }

    private void window(){
        this.pack();
        this.setSize(500,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("UTP10");
        this.setVisible(true);

    }

    public void dodajZdarzenie(ActionListener actionListener){
        jb1.addActionListener(actionListener);
        jb2.addActionListener(actionListener);

    }
}
