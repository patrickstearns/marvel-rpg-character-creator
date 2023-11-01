package com.oblong.marvel.view;

import com.oblong.marvel.Driver;
import com.oblong.marvel.model.*;
import com.oblong.marvel.model.Character;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class CharacterPanel extends JPanel {

    public static void main(String[] args){
        try{
            Driver d = new Driver();
            CreationContext context = d.createRandomCharacter();
            JFrame frame = new JFrame("Marvel Super Heroes Character Generator");

            frame.setContentPane(new CharacterPanel(context));
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            frame.setSize(850, 550);
            frame.setLocation(20, 20);
            frame.setVisible(true);
            frame.pack();
        }
        catch(Throwable t){
            t.printStackTrace();
        }
    }

    private CreationContext context;

    public CharacterPanel(CreationContext context){
        setContext(context);
    }

    private void setContext(CreationContext context){
        this.context = context;
        relayout();
    }

    private void relayout(){
        removeAll();

        Character c = context.getCharacter();

        JLabel physicalFormLabel = new JLabel("Physical Form", JLabel.LEFT);
        if (c.getPhysicalForm() != null) physicalFormLabel.setText("Physical Form: "+c.getPhysicalForm().getName());

        JLabel originOfPowerLabel = new JLabel("Origin of Power", JLabel.LEFT);
        if (c.getOriginOfPower() != null) originOfPowerLabel.setText("Origin of Power: "+c.getOriginOfPower().getName());

        JButton rerollButton = new JButton("Reroll");
        rerollButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reroll();
            }
        });

        JButton exportButton = new JButton("Export");
        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                export();
            }
        });

        AbilityPanel fPanel = new AbilityPanel("Fighting", AbilityRank.fromValue(c.getFighting()), c.getFighting());
        AbilityPanel aPanel = new AbilityPanel("Agility", AbilityRank.fromValue(c.getAgility()), c.getAgility());
        AbilityPanel sPanel = new AbilityPanel("Strength", AbilityRank.fromValue(c.getStrength()), c.getStrength());
        AbilityPanel ePanel = new AbilityPanel("Endurance", AbilityRank.fromValue(c.getEndurance()), c.getEndurance());
        AbilityPanel rPanel = new AbilityPanel("Reason", AbilityRank.fromValue(c.getReason()), c.getReason());
        AbilityPanel iPanel = new AbilityPanel("Intuition", AbilityRank.fromValue(c.getIntuition()), c.getIntuition());
        AbilityPanel pPanel = new AbilityPanel("Psyche", AbilityRank.fromValue(c.getPsyche()), c.getPsyche());
        NumberPanel hPanel = new NumberPanel("Health", ""+c.getHealth());
        NumberPanel kPanel = new NumberPanel("Karma", ""+c.getKarma());
        AbilityPanel resPanel = new AbilityPanel("Resources", AbilityRank.fromValue(c.getResources()), c.getResources());
        AbilityPanel popPanel = new AbilityPanel("Popularity", AbilityRank.fromValue(c.getPopularity()), c.getPopularity());

        //now lay it all out
        //top area - form and origin
        JPanel topPanel = new JPanel();
        GroupLayout topLayout = new GroupLayout(topPanel);
        topPanel.setLayout(topLayout);
        topLayout.setAutoCreateGaps(true);
        topLayout.setAutoCreateContainerGaps(true);
        topLayout.setHorizontalGroup(
                topLayout.createSequentialGroup()
                        .addGroup(topLayout.createParallelGroup()
                                .addComponent(physicalFormLabel)
                                .addComponent(originOfPowerLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rerollButton)
                        .addComponent(exportButton)
        );
        topLayout.setVerticalGroup(
                topLayout.createParallelGroup()
                        .addGroup(topLayout.createSequentialGroup().addComponent(physicalFormLabel).addComponent(originOfPowerLabel))
                        .addComponent(rerollButton).addComponent(exportButton)
        );
        //topPanel.setPreferredSize(new Dimension(820, 60));

        //second area - primary attributes
        JLabel spacerLabel1 = new JLabel("   ");
        JPanel abilitiesPanel = new JPanel();
        GroupLayout paLayout = new GroupLayout(abilitiesPanel);
        abilitiesPanel.setLayout(paLayout);
        paLayout.setAutoCreateGaps(true);
        paLayout.setAutoCreateContainerGaps(true);
        paLayout.setHorizontalGroup(
                paLayout.createSequentialGroup()
                        .addComponent(fPanel)
                        .addComponent(aPanel)
                        .addComponent(sPanel)
                        .addComponent(ePanel)
                        .addComponent(rPanel)
                        .addComponent(iPanel)
                        .addComponent(pPanel)
                        .addComponent(spacerLabel1)
                        .addComponent(hPanel)
                        .addComponent(kPanel)
                        .addComponent(resPanel)
                        .addComponent(popPanel)
        );
        paLayout.setVerticalGroup(
            paLayout.createSequentialGroup()
                .addGroup(paLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(fPanel)
                        .addComponent(aPanel)
                        .addComponent(sPanel)
                        .addComponent(ePanel)
                        .addComponent(rPanel)
                        .addComponent(iPanel)
                        .addComponent(pPanel)
                        .addComponent(spacerLabel1)
                        .addComponent(hPanel)
                        .addComponent(kPanel)
                        .addComponent(resPanel)
                        .addComponent(popPanel)
                )
        );

        //fourth area - powers
        JPanel internalPowersPanel = new JPanel();
        if (context.getCharacter() != null && context.getCharacter().getPowers() != null){
            BoxLayout powersLayout = new BoxLayout(internalPowersPanel, BoxLayout.Y_AXIS);
            internalPowersPanel.setLayout(powersLayout);
            for (Power power: context.getCharacter().getPowers()){
                internalPowersPanel.add(new PowerPanel(power));
            }
            internalPowersPanel.add(Box.createVerticalGlue());
        }

        int powersScrollerHeight = Math.min(324, c.getPowers().size()*84);
        JScrollPane powersScroller = new JScrollPane(internalPowersPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        powersScroller.setBorder(BorderFactory.createTitledBorder("POWERS ("+context.getPowers()+" initial / "+context.getMaxPowers()+" max)"));
        powersScroller.setPreferredSize(new Dimension(820, powersScrollerHeight));
        powersScroller.setMaximumSize(new Dimension(820, powersScrollerHeight));

        //weaknesses
        StringBuilder wsb = new StringBuilder();
        if (context.getCharacter() != null && context.getCharacter().getWeaknesses() != null)
            for (Weakness weakness: context.getCharacter().getWeaknesses())
                wsb.append(weakness.getName()).append("\n").append(weakness.getNotes()).append("\n");
        JPanel weaknessesPanel = createTextPanel("WEAKNESSES ("+context.getCharacter().getWeaknesses().size()+")", wsb.toString());

        //fifth area - talents
        StringBuilder tsb = new StringBuilder();
        if (context.getCharacter() != null && context.getCharacter().getTalents() != null)
            for (Talent talent: context.getCharacter().getTalents())
                tsb.append("<b>").append(talent.getName()).append("</b> - ").append(talent.getDescription()).append("<br>");
        JPanel talentsPanel = createTextPanel("TALENTS ("+context.getTalents()+" initial / "+context.getMaxTalents()+" max)", tsb.toString());

        //sixth area - contacts
        StringBuilder csb = new StringBuilder();
        if (context.getCharacter() != null && context.getCharacter().getContacts() != null)
            for (Contact contact: context.getCharacter().getContacts())
                csb.append(contact.getName()).append(", ");
        String contactsText = csb.toString();
        if (csb.length() > 2) contactsText = csb.substring(0, csb.length()-2);
        JPanel contactsPanel = createTextPanel("CONTACTS ("+context.getContacts()+" initial / "+context.getMaxContacts()+" max)", contactsText);

        //last area - notes to player
        StringBuilder nsb = new StringBuilder("<ul>");
        for (String note: context.getNotes())
            nsb.append("<li>").append(note).append("</li>");
        nsb.append("</ul>");
        JPanel notesPanel = createTextPanel("NOTES ("+context.getNotes().size()+")", nsb.toString());

        //overall layout - top over pa and sa
        JPanel upperPanel = new JPanel(new BorderLayout());
        upperPanel.add(topPanel, BorderLayout.NORTH);
        upperPanel.add(abilitiesPanel, BorderLayout.CENTER);
        upperPanel.setMaximumSize(new Dimension(820, 200));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(upperPanel);
        mainPanel.add(powersScroller);
        mainPanel.add(weaknessesPanel);
        mainPanel.add(talentsPanel);
        mainPanel.add(contactsPanel);
        mainPanel.add(notesPanel);

        JScrollPane mainScrollPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.setPreferredSize(new Dimension(840, 550));
        add(mainScrollPane);

        repaint();
        validate();
    }
    
    private JPanel createTextPanel(String title, String content){
        JEditorPane textPane = new JEditorPane("text/html", content);
        textPane.setEditable(false);

        JPanel panel = new JPanel(new GridLayout(1, 1));
        panel.add(new JScrollPane(textPane));
        panel.setBorder(BorderFactory.createTitledBorder(title));
        panel.setPreferredSize(new Dimension(820, 100));
        panel.setMaximumSize(new Dimension(820, 100));
        return panel;
    }

    private void reroll(){
        Driver d = new Driver();
        setContext(d.createRandomCharacter());
    }

    private void export(){
        String html = context.getCharacter().toHTML(context);
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new FileNameExtensionFilter("HTML files", "html"));
        if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
            File file = jfc.getSelectedFile();
            if (!file.getName().endsWith(".html"))
                file = new File(file.getParent(), file.getName()+".html");
            BufferedWriter out = null;
            try{
                out = new BufferedWriter(new FileWriter(file));
                out.write(html);
                out.close();
                JOptionPane.showMessageDialog(this, "Saved!", "File Saved", JOptionPane.PLAIN_MESSAGE);
            }
            catch (IOException e){
                e.printStackTrace();
                if (out != null)
                    try{ out.close(); }
                    catch(Exception ignored){}
            }
        }
    }
}
