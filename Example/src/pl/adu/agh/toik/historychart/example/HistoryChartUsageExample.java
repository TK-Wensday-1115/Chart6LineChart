package pl.adu.agh.toik.historychart.example;

import pl.edu.agh.toik.historychart.HistoryChart;
import pl.edu.agh.toik.historychart.HistoryChartFactory;
import pl.edu.agh.toik.historychart.TimeUnit;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Random;

/**
 * Created by Maciej Imiolek on 2016-05-11.
 */
public class HistoryChartUsageExample {

    private JFrame frame;
    private static HistoryChart chart;

    public HistoryChartUsageExample() {
        prepareGui();
    }

    public static void main(String[] args) throws InterruptedException {
        HistoryChartUsageExample example = new HistoryChartUsageExample();
        Random rand = new Random();

        new Thread(() -> {
            int lineId = chart.registerNewLine("Series 1");
            while (true) {
                try {
                    chart.addNewEntry(lineId, rand.nextDouble() * 50 + 30, new Date());
                    Thread.sleep(rand.nextInt(4000) + 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            int lineId = chart.registerNewLine("Series 2");
            while (true) {
                try {
                    chart.addNewEntry(lineId, rand.nextDouble() * 30 + 10, new Date());
                    Thread.sleep(rand.nextInt(4000) + 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void prepareGui() {
        frame = new JFrame("History Chart Example");
        frame.setSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chart = HistoryChartFactory.createNew("History Chart Example", "Time", TimeUnit.Second, "Speed", "m/s");

        frame.add(chart);
        frame.setVisible(true);
    }
}
