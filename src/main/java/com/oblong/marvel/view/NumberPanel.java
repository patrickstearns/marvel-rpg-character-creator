package com.oblong.marvel.view;

import javax.swing.*;
import java.awt.*;

public class NumberPanel extends JPanel {

    private JLabel label;
    private JLabel value;

    public NumberPanel(String text, String valueText){
        label = new JLabel(text, JLabel.CENTER);
        value = new JLabel(valueText, JLabel.CENTER);
        value.setFont(value.getFont().deriveFont(Font.BOLD).deriveFont(20f));

        doLayout();

        Dimension size = new Dimension(64, 60);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
    }

    public void doLayout(){
        removeAll();

        add(label);
        add(value);

        label.setBounds(0, 0, getWidth(), 18);
        value.setBounds(0, 20, getWidth(), getHeight()-22);
    }

    public void setValue(String valueText){ value.setText(valueText); }

}
