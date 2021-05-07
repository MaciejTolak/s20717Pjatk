package zad1;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.xml.crypto.dom.DOMCryptoContext;
import java.awt.*;
import java.util.Map;

public class Window extends JFrame {

    private JPanel jp1;
    private JPanel jp2;
    private JTextArea jTextArea1;
    private JTextArea jTextArea2;
    private JTextArea jTextArea3;
    private JFXPanel jfxPanel;

    public Window(String weather, double rate1, double rate2, String country) throws HeadlessException {

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(new Dimension(300, 600));
        this.setTitle("Title");
        this.setVisible(true);
        jp1 = new JPanel(new BorderLayout());
        jp2 = new JPanel();

        jTextArea1 = new JTextArea();
        jTextArea2 = new JTextArea();
        jTextArea3 = new JTextArea();

        try {
            jTextArea1.setText(pogoda(weather));
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        jTextArea2.setText("Względem PLN kurs wynosi: " + rate2);
        jTextArea3.setText("Kurs bazowego kraju: " + rate1);

        jTextArea1.setEditable(false);
        jTextArea2.setEditable(false);
        jTextArea3.setEditable(false);


        jp1.add(jTextArea2, BorderLayout.SOUTH);
        jp1.add(jTextArea3, BorderLayout.NORTH);

        jp2.add(jTextArea1);

        this.add(jp1, BorderLayout.SOUTH);
        this.add(jp2, BorderLayout.NORTH);

        jfxPanel = new JFXPanel();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                WebView webView = new WebView();
                webView.getEngine().load("https://wikipedia.org/wiki/" + country);
                jfxPanel.setScene(new Scene(webView));

            }
        });

        this.add(jfxPanel, BorderLayout.CENTER);

    }


    public String pogoda(String string) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(string);

        Map<String, Double> map = (Map<String, Double>) jsonObject.get("main");

        String pogoda = "Temperatura: " + map.get("temp") + " Temperatura minimalna: " + map.get("temp_min") + " Temperatura maksymalna: " + map.get("temp_max");


        return pogoda;

    }

}
