package pl.edu.agh.toik.historychart;

/**
 * Created by Maciej Imiolek on 2016-05-11.
 */
public class HistoryChartFactory {
    /**
     * Constructs new History Chart with given parameters
     * @param name Chart header
     * @param xAxisLabel Time axis label
     * @param timeUnit Unit of time interval between data entries
     * @param yAxisLabel Value axis label
     * @param yAxisUnit Value axis unit
     * @return JPanel representation of a new History Chart
     */
    public static HistoryChart createNew(String name, String xAxisLabel, TimeUnit timeUnit, String yAxisLabel, String yAxisUnit) {
        return new HistoryChart(name, xAxisLabel, timeUnit, yAxisLabel, yAxisUnit);
    }
}
