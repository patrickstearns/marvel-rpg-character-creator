package com.oblong.marvel.view;

import com.oblong.marvel.model.AbilityRank;

import javax.swing.*;
import java.awt.*;

public class AbilityPanel extends JComponent {

    private static final Font FONT_SYMBOL = new Font(Font.SANS_SERIF, Font.BOLD, 20);
    private static final Font FONT_TEXT = new Font(Font.SANS_SERIF, Font.BOLD, 12);
    private static final Font FONT_SUBTEXT = new Font(Font.SANS_SERIF, Font.BOLD, 12);

    private String text;
    private AbilityRank rank;
    private Integer value;

    public AbilityPanel(String text, AbilityRank rank, Integer value){
        this.text = text;
        this.rank = rank;
        this.value = value;
        setOpaque(true);
    }

    public void setRank(AbilityRank rank){
        this.rank = rank;
        repaint();
    }

    public void paint(Graphics g1d){
        Graphics2D g = (Graphics2D)g1d;

        Paint foreground = Color.BLACK, background, borderColor = Color.BLACK;
        switch(rank){
            default: case None: background = Color.GRAY; break;
            case ShiftZero: background = Color.WHITE; break;
            case Feeble: background = new Color(215, 165, 230); break;
            case Poor: background = new Color(179, 164, 230); break;
            case Typical: background = new Color(164, 177, 230); break;
            case Good: background = new Color(165, 216, 231); break;
            case Excellent: background = new Color(165, 231, 216); break;
            case Remarkable: background = new Color(164, 230, 180); break;
            case Incredible: background = new Color(175, 230, 164); break;
            case Amazing: background = new Color(213, 231, 165); break;
            case Monstrous: background = new Color(231, 219, 165); break;
            case Unearthly: background = new Color(230, 180, 165); break;
            case ShiftX: background = new Color(240, 90, 80); foreground = Color.WHITE; break;
            case ShiftY: background = new Color(183, 45, 40); foreground = Color.WHITE; break;
            case ShiftZ: background = new Color(123, 0, 0); foreground = Color.WHITE; break;
            case Class1000: background = new GradientPaint(0f, 0f, new Color(240, 150, 240), 0f, getHeight(), new Color(200, 200, 0)); break;
            case Class3000: background = new GradientPaint(0f, 0f, new Color(200, 200, 0), 0f, getHeight(), new Color(0, 100, 200)); break;
            case Class5000: background = new GradientPaint(0f, 0f, new Color(0, 100, 200), 0f, getHeight(), new Color(240, 100, 240)); break;
            case Beyond: background = new GradientPaint(0f, 0f, new Color(255, 255, 255), 0f, getHeight(), new Color(0, 0, 0)); break;
        }

        //paint background
        g.setPaint(background);
        g.fillRect(0, 0, getWidth(), getHeight());

        //paint symbol
        g.setPaint(foreground);
        g.setFont(FONT_SYMBOL);
        int symbolWidth = g.getFontMetrics().stringWidth(rank.getSymbol());
        int symbolHeight = g.getFontMetrics().getHeight();
        g.drawString(rank.getSymbol(), (getWidth()-symbolWidth)/2, symbolHeight-6+(getHeight()-symbolHeight)/2);

        //paint text along top
        g.setFont(FONT_TEXT);
        int textWidth = g.getFontMetrics().stringWidth(text);
        int textHeight = g.getFontMetrics().getHeight();
        g.drawString(text, (getWidth()-textWidth)/2, textHeight-2);

        //paint name of rank along bottom
        g.setFont(FONT_SUBTEXT);
        int nameWidth = g.getFontMetrics().stringWidth(""+value);
        int nameHeight = g.getFontMetrics().getHeight();
        g.drawString(""+value, (getWidth()-nameWidth)/2, getHeight()-nameHeight/2+2);

        //finish up with the border
        if (background instanceof Color)
            borderColor = ((Color)background).darker();
        g.setPaint(borderColor);
        g.drawLine(0, 0, getWidth(), 0);
        g.drawLine(0, 1, getWidth(), 1);
        g.drawLine(0, getHeight()-2, getWidth(), getHeight()-2);
        g.drawLine(0, getHeight()-1, getWidth(), getHeight()-1);
        g.drawLine(0, 0, 0, getHeight());
        g.drawLine(1, 0, 1, getHeight());
        g.drawLine(getWidth()-2, 0, getWidth()-2, getHeight());
        g.drawLine(getWidth()-1, 0, getWidth()-1, getHeight());
    }
}
