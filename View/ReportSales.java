package View;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import controller.UserController;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ReportSales extends JFrame {

    public ReportSales(String title) {
        super(title);

        // Create a dataset
        DefaultPieDataset dataset = createDataset();

        // Create a pie chart based on the dataset
        JFreeChart chart = createChart(dataset);

        // Create a panel to display the chart
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 370));

        // Add the chart panel to the frame
        setContentPane(chartPanel);
    }

    private DefaultPieDataset createDataset() {
    	UserController userController = new UserController();
    	double[] result = new double[4];
		try {
			result = userController.getSalesPercentage();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	double AmusementAdult = result[0];
    	double AmusementChild = result[1];
    	double WaterParkRider = result[2];
    	double WaterParkSurfer = result[3];
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Waterpark-Surfer", WaterParkSurfer);
        dataset.setValue("Waterpark-Rider", WaterParkRider);
        dataset.setValue("Amusement Park-Adult", AmusementAdult);
        dataset.setValue("Amusement Park-Child", AmusementChild);
        return dataset;
    }

    private JFreeChart createChart(DefaultPieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Sales Distribution by Category",
                dataset,
                true,  // Include legend
                true,
                false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Waterpark-Surfer", Color.RED);
        plot.setSectionPaint("Waterpark-Rider", Color.GREEN);
        plot.setSectionPaint("Amusement Park-Adult", Color.BLUE);
        plot.setSectionPaint("Amusement Park-Child", Color.ORANGE);
        int staffid = 0;
        int cost = 0;
        StaffMenu frame = new StaffMenu(staffid,cost);
		frame.setVisible(true);
        return chart;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ReportSales frame = new ReportSales("TICKETS' CATEGORY SALES COMPARISON");
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
