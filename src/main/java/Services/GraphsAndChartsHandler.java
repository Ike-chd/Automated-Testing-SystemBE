package Services;

import java.io.*;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class GraphsAndChartsHandler {
   
   public static void main( String[ ] args ) throws Exception {
      DefaultPieDataset dataset = new DefaultPieDataset( );
      dataset.setValue("IPhone 5s", Double.valueOf(20));
      dataset.setValue("SamSung Grand", Double.valueOf(20));
      dataset.setValue("MotoG", Double.valueOf(40));
      dataset.setValue("Nokia Lumia", Double.valueOf(10));

      JFreeChart chart = ChartFactory.createPieChart(
         "Mobile Sales",   // chart title
         dataset,          // data
         true,             // include legend
         true,
         false);
         
      int width = 640;   /* Width of the image */
      int height = 480;  /* Height of the image */ 
      File pieChart = new File( "PieChart.jpeg" ); 
      ChartUtils.saveChartAsJPEG(pieChart, chart, width, height);
   }
}