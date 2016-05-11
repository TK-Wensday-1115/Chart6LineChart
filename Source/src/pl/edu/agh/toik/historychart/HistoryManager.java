package pl.edu.agh.toik.historychart;

import org.jfree.chart.axis.ValueAxis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

class HistoryManager extends JPanel implements ActionListener {
    private final ValueAxis timeAxis;
    private final Map<String, Double> multipliers;

    private JLabel label;
    private JSpinner amountSpinner;
    private JComboBox<String> unitComboBox;
    private JButton applyButton;

    public HistoryManager(ValueAxis timeAxis) {
        this.timeAxis = timeAxis;
        this.multipliers = initializeMultipliers();

        prepareGui();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int value = (int) amountSpinner.getValue();
        double multiplier = getMultiplier((String) unitComboBox.getSelectedItem());

        timeAxis.setFixedAutoRange(value * multiplier);
    }

    private double getMultiplier(String timeUnit) {
        return multipliers.get(timeUnit);
    }

    private Map<String, Double> initializeMultipliers() {
        Map<String, Double> multipliers = new HashMap<>();
        multipliers.put("Milliseconds", 1.0);
        multipliers.put("Seconds", 1000.0);
        multipliers.put("Minutes", 1000.0 * 60);
        multipliers.put("Hours", 1000.0 * 60 * 60);
        multipliers.put("Days", 1000.0 * 60 * 60 * 24);
        multipliers.put("Weeks", 1000.0 * 60 * 60 * 24 * 7);
        multipliers.put("Months", 1000.0 * 60 * 60 * 24 * 30);
        multipliers.put("Years", 1000.0 * 60 * 60 * 24 * 365);

        return multipliers;
    }

    private void prepareGui() {
        label = new JLabel("Show data from last");
        this.add(label);

        SpinnerNumberModel model = new SpinnerNumberModel(60, 1, 60, 1);
        amountSpinner = new JSpinner(model);
        this.add(amountSpinner);

        unitComboBox = new JComboBox<>( new String[] { "Milliseconds", "Seconds", "Minutes", "Hours", "Days", "Weeks", "Months", "Years" });
        unitComboBox.setSelectedIndex(1);
        this.add(unitComboBox);

        applyButton = new JButton("Apply");
        applyButton.addActionListener(this);
        this.add(applyButton);
    }
}
