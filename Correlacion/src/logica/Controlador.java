/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import vista.frmPrincipal;

/**
 *
 * @author Chris
 */
public class Controlador implements ActionListener {

    public static frmPrincipal frmGUI;//Objeto del tipo frmPrincipal
    DefaultTableModel modItems = new DefaultTableModel();
    double valorR = 0;
    XYSeriesCollection oDataset = new XYSeriesCollection();
    JFreeChart oChart = ChartFactory.createXYLineChart("", "Valor X", "Valor Y", oDataset, PlotOrientation.VERTICAL, false, true, false);
    ChartPanel oPanel = new ChartPanel(oChart);

    public Controlador(frmPrincipal frmGUI) {
        this.frmGUI = frmGUI;
        this.frmGUI.btnIngresar.addActionListener(this);
        this.frmGUI.btnGenerar.addActionListener(this);
    }

    //Eventos Botones
    public void actionPerformed(ActionEvent e) {

        //Pulsar boton Nuevo
        if (e.getSource() == frmGUI.btnIngresar) {
            cargarTabla();
        }
        if (e.getSource() == frmGUI.btnGenerar) {
            hacerCalculo();
            pintarGrafica();
        }
    }

    public static Double formatearDecimales(Double numero, Integer numeroDecimales) {
        return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
    }

    public void iniciarGUI() {
        String titColumna[] = {"Xi", "Yi"};
        modItems.setColumnIdentifiers(titColumna);
        frmGUI.tblItems.setModel(modItems);
        //Iniciar propiedades del form
        frmGUI.setResizable(true);
        frmGUI.setLocationRelativeTo(null);
        frmGUI.setTitle("Programa 1");
        frmGUI.setVisible(true);
    }

    private void cargarTabla() {
        limpiarTabla();
        int numFilas = Integer.valueOf(frmGUI.txtItems.getText());
        String dataFilas[] = {"", ""};
        for (int i = 0; i < numFilas; i++) {
            modItems.addRow(dataFilas);
        }
        frmGUI.txtItems.setText("");
    }

    private void limpiarTabla() {
        DefaultTableModel tblModelo = (DefaultTableModel) frmGUI.tblItems.getModel();
        int numFilas = tblModelo.getRowCount();
        for (int i = numFilas - 1; i >= 0; i--) {
            tblModelo.removeRow(i);
        }

        frmGUI.lblValmedX.setText("0");
        frmGUI.lblValmedY.setText("0");
        frmGUI.lblDsvtX.setText("0");
        frmGUI.lblDsvtY.setText("0");
        frmGUI.lblCovarianza.setText("0");
        frmGUI.lblValR.setText("0");
        frmGUI.pnlGrafico.removeAll();
        oDataset.removeAllSeries();

    }

    // Calcular los valores necesarios
    public void hacerCalculo() {

        int numFilas = frmGUI.tblItems.getRowCount();
        double Xi = 0, Yi = 0;
        double sumaColX = 0, sumaColY = 0;
        double cuadradoX = 0, cuadradoY = 0, produXY = 0;
        double mediaX = 0, mediaY = 0;
        double dsvTipX = 0, dsvTipY = 0;
        double covarianza = 0;

        for (int i = 0; i < numFilas; i++) {

            try {

                Xi = Double.valueOf(frmGUI.tblItems.getValueAt(i, 0).toString());
                Yi = Double.valueOf(frmGUI.tblItems.getValueAt(i, 1).toString());

                sumaColX += Double.valueOf(frmGUI.tblItems.getValueAt(i, 0).toString());
                sumaColY += Double.valueOf(frmGUI.tblItems.getValueAt(i, 1).toString());

                cuadradoX += (Double.valueOf(frmGUI.tblItems.getValueAt(i, 0).toString()) * Double.valueOf(frmGUI.tblItems.getValueAt(i, 0).toString()));
                cuadradoY += (Double.valueOf(frmGUI.tblItems.getValueAt(i, 1).toString()) * Double.valueOf(frmGUI.tblItems.getValueAt(i, 1).toString()));

                produXY += (Xi * Yi);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(frmGUI, "Ha ocurrido un error. " + e, "Validar", JOptionPane.ERROR_MESSAGE);
            }

        }

        mediaX = sumaColX / numFilas;
        frmGUI.lblValmedX.setText(String.valueOf(mediaX));

        mediaY = sumaColY / numFilas;
        frmGUI.lblValmedY.setText(String.valueOf(mediaY));

        dsvTipX = Math.sqrt(cuadradoX / numFilas - (Math.pow(mediaX, 2)));
        frmGUI.lblDsvtX.setText(String.valueOf(String.format("%.4f", dsvTipX)));

        dsvTipY = Math.sqrt(cuadradoY / numFilas - (Math.pow(mediaY, 2)));
        frmGUI.lblDsvtY.setText(String.valueOf(String.format("%.4f", dsvTipY)));

        covarianza = (produXY / numFilas) - (mediaX * mediaY);
        frmGUI.lblCovarianza.setText(String.valueOf(String.format("%.4f", covarianza)));

        valorR = covarianza / (dsvTipX * dsvTipY);
        frmGUI.lblValR.setText(String.valueOf(String.format("%.2f", valorR)));

    }

    // Generar el grafico
    public void pintarGrafica() {

        oPanel.setFillZoomRectangle(false);
        oPanel.setMaximumSize(frmGUI.pnlGrafico.getSize());
        oPanel.setMaximumDrawWidth(660);
        oPanel.setMaximumDrawHeight(340);
        XYSeries oSeries = new XYSeries("Valores");
        oSeries.clear();

        int numFilas = frmGUI.tblItems.getRowCount();
        double Xi = 0, Yi = 0;

        for (int i = 0; i < numFilas; i++) {

            try {

                Xi = Double.valueOf(frmGUI.tblItems.getValueAt(i, 0).toString());
                Yi = Double.valueOf(frmGUI.tblItems.getValueAt(i, 1).toString());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(frmGUI, "Ha ocurrido un error. " + e, "Validar", JOptionPane.ERROR_MESSAGE);
            }

            oSeries.add(Xi, Yi);
        }

        oDataset.addSeries(oSeries);

        //Maneja atributos de las lineas
        XYPlot oPlot = oChart.getXYPlot();
        XYLineAndShapeRenderer oRenderer = new XYLineAndShapeRenderer();
        oRenderer.setSeriesPaint(0, Color.RED);
        oRenderer.setSeriesStroke(0, new BasicStroke(2.0f));
        oPlot.setRenderer(oRenderer);

        frmGUI.pnlGrafico.setLayout(new java.awt.BorderLayout());
        frmGUI.pnlGrafico.add(oPanel);
        frmGUI.pnlGrafico.validate();
    }

}
