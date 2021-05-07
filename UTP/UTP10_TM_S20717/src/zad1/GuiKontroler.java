package zad1;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class GuiKontroler implements ActionListener {

    private Gui gui;
    private int counter1;
    private int counter2;
    private ExecutorService essa;
    private int b = 0;


    public GuiKontroler() {
        gui = new Gui();
        gui.dodajZdarzenie(this::actionPerformed);
        counter1 = 1;
        counter2 = 1000;
        essa = Executors.newCachedThreadPool();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String string = e.getActionCommand();
        switch (string) {
            case "stop":
                for (JButton jButton : gui.getList()) {
                    jButton.setText(jButton.getText() + " : Done");
                    jButton.setEnabled(false);
                    gui.getJb2().setEnabled(false);
                    gui.getJb1().setEnabled(false);

                }
                essa.shutdownNow();
                break;

            case "create new":
                String nazwa = "T" + counter1;
                JButton njb = new JButton(nazwa);

                gui.getList().add(njb);
                gui.getjPanel2().add(njb);
                gui.validate();
                Zadanie zadanie = new Zadanie(essa, counter2, gui.getjPanel2(), gui.getJta(), counter1, gui.getList(), njb);
                StatusZadania statusZadania = new StatusZadania(nazwa, njb);
                njb.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String command = e.getActionCommand();
                        if (command.equals(statusZadania.getS1())) {

                            zadanie.rozpocznijZadanie();
                            njb.setText("Suspend " + nazwa);

                        } else if (command.equals(statusZadania.getS2())) {

                            zadanie.zatrzymajZadanie();
                            njb.setText("Continue " + nazwa);

                        } else if (command.equals(statusZadania.getS3())) {

                            zadanie.wznowZadanie();
                            njb.setText("Suspend " + nazwa);

                        }
                        poWcisnieciuKlaiwsza(b, njb, nazwa, zadanie);

                    }
                });

                njb.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyChar() == 'c') {
                            b = 1;
                        }
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {
                        b = 0;
                    }
                });
                counter1++;
                counter2 += 1000;
                break;
        }
    }

    public void poWcisnieciuKlaiwsza(int x, JButton button, String nazwa, Zadanie zadanie) {
        if (x == 1) {
            button.setText(nazwa + ": Cancelled!");
            zadanie.getFuture().cancel(true);
            button.setEnabled(false);
        }
    }
}
