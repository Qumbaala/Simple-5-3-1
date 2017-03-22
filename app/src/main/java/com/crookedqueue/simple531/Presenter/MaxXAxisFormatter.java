package com.crookedqueue.simple531.Presenter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

/**
 * Created by jason on 3/22/2017.
 */

public class MaxXAxisFormatter implements IAxisValueFormatter {
    List<String> dateStrings;

    public MaxXAxisFormatter(List<String> dateStrings) {
        this.dateStrings = dateStrings;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return (int) value + " (" + dateStrings.get((int)value-1) + ")";
    }
}
