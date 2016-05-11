package pl.edu.agh.toik.historychart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.*;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * JPanel representation of a History Chart.
 * Presents continuous data gathered over time.
 * Each data source is represented by separate line on the chart.
 * </pre>
 */
public class HistoryChart extends JPanel {

    private JFreeChart chart;
    private TimeSeriesCollection dataSet;
    private Map<Integer, TimeSeries> lines;
    private HistoryManager historyManager;

    private int nextLineId;
    private TimeUnit timeUnit;

    /**
     * Constructs new History Chart with given parameters
     * @param name Chart header
     * @param xAxisLabel Time axis label
     * @param timeUnit Unit of time interval between data entries,
     *                 example: if TimeUnit.Second is chosen and more than one entries occurring at the same second
     *                          are provided, each consecutive entry will override the previous one
     * @param yAxisLabel Value axis label
     * @param yAxisUnit Value axis unit
     */
    HistoryChart(String name, String xAxisLabel, TimeUnit timeUnit, String yAxisLabel, String yAxisUnit) {
        prepareLayout();

        this.lines = new HashMap<>();
        this.dataSet = new TimeSeriesCollection();
        this.chart = createChart(name, xAxisLabel, yAxisLabel, yAxisUnit, dataSet);
        this.historyManager = new HistoryManager(chart.getXYPlot().getDomainAxis());
        this.add(this.historyManager, BorderLayout.SOUTH);

        this.nextLineId = 0;
        this.timeUnit = timeUnit;
    }

    /**
     * Adds new data series to a chart
     * @param label Will appear as line description in chart legend
     * @return New line handle - used to identify line in addNewEntry method executions
     */
    public int registerNewLine(String label) {
        TimeSeries newSeries = new TimeSeries(label);
        dataSet.addSeries(newSeries);
        lines.put(nextLineId, newSeries);

        return nextLineId++;
    }

    /**
     * Adds new entry to data series
     * Entries are not required to be added chronologically
     * @param lineId Line handle - obtained from registerNewLine method execution
     * @param value Data value
     * @param time Point of time at which the entry will appear on History Chart
     * @throws DataLineDoesNotExistException When line with given id does not exist
     */
    public void addNewEntry(int lineId, double value, Date time) throws DataLineDoesNotExistException {
        if (!lines.containsKey(lineId)) {
            throw new DataLineDoesNotExistException();
        }
        TimeSeries series = lines.get(lineId);
        series.addOrUpdate(getTimePeriod(time), value);
    }

    private void prepareLayout() {
        this.setLayout(new BorderLayout());
    }

    private JFreeChart createChart(String name, String xAxisLabel, String yAxisLabel, String yAxisUnit, TimeSeriesCollection dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(name,
                xAxisLabel,
                yAxisLabel + " [" + yAxisUnit + "]",
                dataset);

        chart.getXYPlot().getRangeAxis().setAutoRange(true);
        chart.getXYPlot().getDomainAxis().setFixedAutoRange(60000);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseZoomable(true);
        this.add(chartPanel, BorderLayout.CENTER);

        return chart;
    }

    private RegularTimePeriod getTimePeriod(Date time) {
        switch (timeUnit) {
            case Millisecond:
                return new Millisecond(time);
            case Second:
                return new Second(time);
            case Minute:
                return new Minute(time);
            case Hour:
                return new Hour(time);
            case Day:
                return new Day(time);
            case Week:
                return new Week(time);
            case Quarter:
                return new Quarter(time);
            case Year:
                return new Year(time);
            default:
                return new Second(time);
        }
    }
}
