package zad1;

import javax.swing.*;

public class StatusZadania {
    private String nazwa;
    private JButton njb;

    private String s1;
    private String s2;
    private String s3;

    public StatusZadania(String nazwa, JButton njb) {
        this.nazwa = nazwa;
        this.njb = njb;
        s1 = nazwa;
        s2 = "Suspend "+nazwa;
        s3 = "Continue "+nazwa;
    }

    public String getS1() {
        return s1;
    }

    public String getS2() {
        return s2;
    }

    public String getS3() {
        return s3;
    }
}
