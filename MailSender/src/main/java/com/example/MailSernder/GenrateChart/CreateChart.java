package com.example.MailSernder.GenrateChart;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Service;

@Service
public class CreateChart {

	public File createPieChart(Map<String, Double> data, String chartTitle) throws IOException {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (Map.Entry<String, Double> entry : data.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        JFreeChart chart = ChartFactory.createPieChart(chartTitle, dataset, true, true, false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setCircular(true);

        // Save chart as PNG image
        File chartFile = new File("pie_chart.png");
        ChartUtils.saveChartAsPNG(chartFile, chart, 800, 600);

        return chartFile;
    }
	
}
