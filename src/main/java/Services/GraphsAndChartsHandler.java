package Services;

import java.io.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class GraphsAndChartsHandler {

    public static void main(String[] args) throws Exception {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Math", Double.valueOf(20));
        dataset.setValue("English", Double.valueOf(20));
        dataset.setValue("Science", Double.valueOf(40));
        dataset.setValue("Geo", Double.valueOf(10));

        JFreeChart chart = ChartFactory.createPieChart(
                "Hardest Topics",
                dataset,
                true,
                true,
                false);

        int width = 640;
        int height = 480;
        File pieChart = new File("PieChart.jpeg");
        if (!pieChart.exists()) {
            pieChart.createNewFile();
        }
        ChartUtils.saveChartAsJPEG(pieChart, chart, width, height);
    }
}
