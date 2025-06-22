package com.example.aplikacjawalutowa.ui.history;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import com.example.aplikacjawalutowa.R;
import com.example.aplikacjawalutowa.data.remote.dto.CurrencyHistory;
import com.example.aplikacjawalutowa.databinding.ActivityHistoryBinding;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private HistoryViewModel viewModel;
    private ActivityHistoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        String currencyCode = getIntent().getStringExtra("CURRENCY_CODE");
        if (currencyCode == null) {
            finish();
            return;
        }

        setTitle("Historia kursu " + currencyCode);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        viewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        observeViewModel();
        viewModel.fetchHistory(currencyCode);
    }

    private void observeViewModel() {
        viewModel.getCurrencyHistory().observe(this, this::setupChart);
        viewModel.getIsLoading().observe(this, isLoading ->
                binding.progressBarHistory.setVisibility(isLoading ? View.VISIBLE : View.GONE));
        viewModel.getErrorMessage().observe(this, error -> {
            if (error != null && !error.isEmpty()) {
                Snackbar.make(binding.getRoot(), error, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void setupChart(CurrencyHistory history) {
        if (history == null || history.getRates().isEmpty()) return;

        List<Entry> entries = new ArrayList<>();
        List<String> dates = new ArrayList<>();

        List<CurrencyHistory.HistoryRate> rates = history.getRates();
        for (int i = 0; i < rates.size(); i++) {
            entries.add(new Entry(i, (float) rates.get(i).getMid()));
            dates.add(rates.get(i).getEffectiveDate());
        }

        LineDataSet dataSet = new LineDataSet(entries, "Kurs " + history.getCode());

        int colorPrimary = MaterialColors.getColor(this, com.google.android.material.R.attr.colorPrimary, Color.BLUE);
        dataSet.setColor(colorPrimary);
        dataSet.setLineWidth(2.5f);
        dataSet.setCircleColor(colorPrimary);
        dataSet.setCircleRadius(4f);
        dataSet.setDrawValues(false);

        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        dataSet.setDrawFilled(true);
        Drawable gradientDrawable = ContextCompat.getDrawable(this, R.drawable.chart_gradient);
        dataSet.setFillDrawable(gradientDrawable);

        LineData lineData = new LineData(dataSet);
        binding.lineChart.setData(lineData);

        XAxis xAxis = binding.lineChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(dates));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setLabelRotationAngle(-45);

        binding.lineChart.getDescription().setEnabled(false);
        binding.lineChart.getAxisRight().setEnabled(false);
        binding.lineChart.animateX(1000);
        binding.lineChart.invalidate();

        CustomMarkerView markerView = new CustomMarkerView(this, R.layout.marker_view, dates);
        binding.lineChart.setMarker(markerView);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}