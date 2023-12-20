package org.alexxarisis;

import org.alexxarisis.client.AdvancedTextToSpeechView;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new AdvancedTextToSpeechView().getJFrame().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}