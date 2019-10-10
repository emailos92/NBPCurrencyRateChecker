package gui;

import model.CurrencyCol;
import model.CurrencyColElem;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class JChartTest {

    private static XYDataset createDataSet(ArrayList<CurrencyCol> currencies) {

        TimeSeriesCollection ds = new TimeSeriesCollection();

        for (int i = 0; i < currencies.size(); i++) {
            TimeSeries ts = new TimeSeries(currencies.get(i).getCode());
            for (int j = 0; j < currencies.get(i).size(); j++) {
                LocalDate date;
                date = currencies.get(i).get(j).getDate();
                //System.out.println(date + " " + date.getYear() + " " + date.getMonthValue() + " " + date.getDayOfMonth());

                ts.add(new Day(date.getDayOfMonth(), date.getMonth().getValue(), date.getYear()), currencies.get(i).get(j).getExchangeRate());
            }
            ds.addSeries(ts);
        }

        return ds;
    }

    public static void JChartExample(ArrayList<CurrencyCol> currencies) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Currencies Plot");

                frame.setSize(800, 600);
                //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                XYDataset ds = createDataSet(currencies); //currencies

                JFreeChart chart = ChartFactory.createTimeSeriesChart("CurrencyChart", "Date", "ExchangeRate", ds);

                ChartPanel cp = new ChartPanel(chart);
                cp.setMouseZoomable(false);

                frame.getContentPane().add(cp);
            }
        });
    }


}
