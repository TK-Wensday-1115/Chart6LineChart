package pl.edu.agh.toik.historychart;

/**
 * Responsible for creating new History Charts.
 */
public class HistoryChartFactory {
    /**
     * Constructs new History Chart with given parameters
     * @param name Chart header
     * @param xAxisLabel Time axis label
     * @param timeUnit Unit of time interval between data entries<br>
     *                 Example: if TimeUnit.Second is chosen and more than one entries occurring at the same second
     *                          are provided, each consecutive entry will override the previous one
     * @param yAxisLabel Value axis label
     * @param yAxisUnit Value axis unit
     * @return JPanel representation of a new History Chart
     */
    public static HistoryChart createNew(String name, String xAxisLabel, TimeUnit timeUnit, String yAxisLabel, String yAxisUnit) {
        return new HistoryChart(name, xAxisLabel, timeUnit, yAxisLabel, yAxisUnit);
    }
}
