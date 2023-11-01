package com.oblong.marvel.view;

import com.oblong.marvel.model.AbilityRank;
import com.oblong.marvel.model.Power;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class PowerPanel extends JPanel {

    private JLabel powerClassLabel;
    private JLabel powerTypeLabel;
    private AbilityPanel rankPanel;
    private JScrollPane powerNotesScroller;

    public PowerPanel(Power power){
        powerClassLabel = new JLabel(power.getPowerClass().getName(), JLabel.LEFT);
        powerTypeLabel = new JLabel(power.getPowerType().getName(), JLabel.LEFT);

        rankPanel = new AbilityPanel("Rank", power.getRank(), power.getRank().getValue());

        JEditorPane powerNotesLabel = new JEditorPane("text/html", (power.getSubType() == null ? "" : power.getSubType())+" "+power.getPowerType().getShortNotes());
        powerNotesLabel.setEditable(false);
        powerNotesLabel.setMaximumSize(new Dimension(400, 60));
        powerNotesScroller = new JScrollPane(powerNotesLabel);
        powerNotesScroller.setPreferredSize(new Dimension(420, 60));

        setPreferredSize(new Dimension(780, 60));
        doLayout();
    }

    public void doLayout(){
        setLayout(null);
        removeAll();

        add(powerClassLabel);
        add(powerTypeLabel);
        add(rankPanel);
        add(powerNotesScroller);

        powerClassLabel.setBounds(0, 0, 160, 32);
        powerTypeLabel.setBounds(0, 26, 160, 32);
        rankPanel.setBounds(162, 0, 64, 60);
        powerNotesScroller.setBounds(228, 0, getWidth()-232, 60);
    }
}
