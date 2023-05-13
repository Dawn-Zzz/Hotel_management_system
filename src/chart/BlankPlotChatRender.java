package chart;

import java.awt.Graphics2D;

import chart.BlankPlotChart;
import chart.SeriesSize;

public abstract class BlankPlotChatRender {

    public abstract String getLabelText(int index);

    public abstract void renderSeries(BlankPlotChart chart, Graphics2D g2, SeriesSize size, int index);
}
