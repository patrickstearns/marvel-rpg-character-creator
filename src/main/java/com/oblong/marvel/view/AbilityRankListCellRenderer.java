package com.oblong.marvel.view;

import com.oblong.marvel.model.AbilityRank;

import javax.swing.*;
import java.awt.*;

public class AbilityRankListCellRenderer implements ListCellRenderer<AbilityRank> {
    public Component getListCellRendererComponent(JList<? extends AbilityRank> list, AbilityRank value, int index, boolean isSelected, boolean cellHasFocus){
        JLabel c = new JLabel(value.getSymbol(), JLabel.CENTER);
        c.setFont(c.getFont().deriveFont(Font.BOLD));
        Color foreground = Color.BLACK, background, borderColor;
        switch(value){
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
            case Class1000: background = new Color(240, 150, 240); break;
            case Class3000: background = new Color(210, 145, 210); break;
            case Class5000: background = new Color(180, 140, 180); break;
            case Beyond: background = new Color(150, 135, 150); break;
        }

        c.setForeground(foreground);
        c.setBackground(background);
        list.setSelectionBackground(background);
        c.setVerticalAlignment(JLabel.CENTER);
        c.setHorizontalAlignment(JLabel.CENTER);

        borderColor = foreground;
        if (isSelected) borderColor = Color.YELLOW;
        //else if (isSelected) borderColor = Color.CYAN;
        c.setBorder(BorderFactory.createLineBorder(borderColor, 2, false));
        c.setOpaque(true);
        return c;
    }
}
