package chart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;

public class LegendItem extends javax.swing.JPanel {

    public LegendItem(ModelLegend data) {

        lbColor = new LabelColor();
        lbName = new JLabel();

        lbColor.setText("labelColor1");

        lbName.setForeground(new java.awt.Color(100, 100, 100));
        lbName.setText("Name");
        
        FlowLayout fl = new FlowLayout();
        fl.setAlignment(FlowLayout.LEFT);
        this.add(lbName);
        setOpaque(false);
        lbColor.setBackground(data.getColor());
        lbName.setText(data.getName());
        lbName.setForeground(data.getColor());
    }
    private LabelColor lbColor;
    private javax.swing.JLabel lbName;
}

