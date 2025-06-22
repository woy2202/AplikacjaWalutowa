package com.example.aplikacjawalutowa.ui.history;

import android.content.Context;
import android.widget.TextView;
import com.example.aplikacjawalutowa.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import java.util.List;

public class CustomMarkerView extends MarkerView {

    private final TextView tvContent;
    private final List<String> dates;

    public CustomMarkerView(Context context, int layoutResource, List<String> dates) {
        super(context, layoutResource);
        tvContent = findViewById(R.id.tv_marker_content);
        this.dates = dates;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        int index = (int) e.getX();
        String date = dates.get(index);
        float value = e.getY();
        tvContent.setText(String.format("%s\nKurs: %.4f", date, value));
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2f), -getHeight() - 10f);
    }
}