package by.bsuir.stock.window.windowAdmin;

import by.bsuir.stock.bean.StockEntity;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import javax.swing.*;

public class StatisticsWindow extends javax.swing.JDialog {

    public StatisticsWindow(StockEntity stock) {
        this.stock = stock;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        PieDataset dataset = createSampleDataset();

        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        pack();
    }

    private PieDataset createSampleDataset() {

        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Загруженно", stock.getCongestion());
        result.setValue("Свободно", stock.getMaxAmount() - stock.getCongestion());
        return result;

    }

    private JFreeChart createChart(PieDataset dataset) {

        JFreeChart chart = ChartFactory.createPieChart3D(
                "Статистика склада",
                dataset,
                true,
                true,
                false
        );

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");
        return chart;

    }

    private StockEntity stock;
}
