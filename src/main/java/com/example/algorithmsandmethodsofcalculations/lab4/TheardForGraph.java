package com.example.algorithmsandmethodsofcalculations.lab4;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import static com.example.algorithmsandmethodsofcalculations.lab4.LagrangeMethod.LagrangePolinomialInX;

public class TheardForGraph extends Thread{
    public void run(double[][] points) {
        // Создание данных для графика
        XYSeries series = new XYSeries("LagrangePolinomial");
        for (double x = 0; x < 6; x += 0.1) {
            series.add(x, LagrangePolinomialInX(points, x));
        }

        XYSeries series1 = new XYSeries("SourcePoints");
        for (int i = 0; i < points.length; i++) {
            series1.add(points[i][0], points[i][1]);
        }

        // Добавление данных в коллекцию
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        dataset.addSeries(series1);

        // Создание графика
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Интерполяционный полином Лагранжа",  // Заголовок графика
                "X",              // Надпись по оси X
                "Y",              // Надпись по оси Y
                dataset           // Данные для графика
        );

        // Настройка отображения точек
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShapesVisible(0, false); // Скрыть точки для первой серии (интерполяционный полином)
        renderer.setSeriesShapesVisible(1, true); // Показать точки для второй серии (точки из массива)
        renderer.setSeriesLinesVisible(1, false); // Скрыть линию для второй серии
        //renderer.setSeriesShapeSize(1, 3.0f); // Установить размер точек для второй серии
        renderer.setSeriesShape(1, new Ellipse2D.Double(-2, -2, 2, 2)); // Установить форму и размер точек для второй серии
        renderer.setSeriesPaint(1, Color.RED);
        plot.setRenderer(renderer);

        // Добавить подписи с координатами для точек
        for (int i = 0; i < series1.getItemCount(); i++) {
            double x = (double)series1.getX(i);
            double y = (double)series1.getY(i);
            XYTextAnnotation annotation = new XYTextAnnotation(String.format("(%.2f, %.2f)", x, y), x + 0.3, y + 0.4);
            annotation.setFont(new Font("Arial", Font.PLAIN, 10)); // Задать шрифт и размер подписей
            annotation.setPaint(Color.BLACK); // Задать цвет подписей
            plot.addAnnotation(annotation);
        }

        // Создание окна и добавление графика
        JFrame frame = new JFrame("Интерполяционный полином Лагранжа");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ChartPanel chartPanel = new ChartPanel(chart);
        frame.getContentPane().add(chartPanel);

        // Исправление: Установка размера окна
        frame.setSize(950, 550);
        frame.setAlwaysOnTop(true);

        // Исправление: Установка видимости окна после установки размера
        frame.setVisible(true);
    }
}
