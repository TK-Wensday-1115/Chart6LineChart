package pl.edu.agh.toik.historychart.prototype;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Random;

/**
 * Created by Maciej Imiolek on 2016-04-19.
 */
public class PrototypeChart extends ApplicationFrame {

    private static TimeSeriesCollection dataset;
    private JFreeChart chart;

    public PrototypeChart(String title) {
        super(title);

        this.dataset = new TimeSeriesCollection();
        this.chart = createChart();

        JPanel content = new JPanel(new BorderLayout());
        ChartPanel chartPanel = new ChartPanel(chart);
        content.add(chartPanel);
        chartPanel.setPreferredSize(new Dimension(500, 500));
        setContentPane(content);
    }

    private JFreeChart createChart() {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Test chart",
                "Time",
                "Values",
                this.dataset,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        ValueAxis yAxis = plot.getRangeAxis();
        yAxis.setAutoRange(true);

        return chart;
    }

    public static void main(String[] args) throws InterruptedException {
        PrototypeChart prototype = new PrototypeChart("Demo chart");
        prototype.pack();
        RefineryUtilities.centerFrameOnScreen(prototype);
        prototype.setVisible(true);

        addEntries();
    }

    private static void addEntries() throws InterruptedException {
        Random rand = new Random();
        TimeSeries series1 = new TimeSeries("series1", Second.class);
        dataset.addSeries(series1);

        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            series1.add(new Second(new Date()), rand.nextDouble());
        }

        TimeSeries series2 = new TimeSeries("series2", Second.class);
        dataset.addSeries(series2);

        while (true) {
            Thread.sleep(1000);
            series1.add(new Second(new Date()), rand.nextDouble());
            series2.add(new Second(new Date()), rand.nextDouble());
        }
    }
}
