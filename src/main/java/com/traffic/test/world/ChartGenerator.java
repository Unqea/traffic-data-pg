package com.traffic.test.world;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PlotOrientation;

import java.util.List;

/**
 * 图表生成
 */
public class ChartGenerator {


    public JFreeChart generateLineChart(List<String> categories, List<Integer> values) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < categories.size(); i++) {
            dataset.addValue(values.get(i), "每月运量", categories.get(i));
        }
        return ChartFactory.createLineChart(
            "每月运量",       // 图表标题
            "月份",         // X 轴标签
            "运量",            // Y 轴标签
            dataset,            // 数据集
            PlotOrientation.VERTICAL,
            true,               // 是否显示图例
            true,               // 是否生成提示工具
            false               // 是否生成 URL 链接
        );
    }

    public JFreeChart generateBarChart(List<String> categories, List<Integer> values) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < categories.size(); i++) {
            dataset.addValue(values.get(i), "Series1", categories.get(i));
        }
        return ChartFactory.createBarChart(
            "Bar Chart",        // 图表标题
            "Category",         // X 轴标签
            "Value",            // Y 轴标签
            dataset,            // 数据集
            PlotOrientation.VERTICAL,
            true,               // 是否显示图例
            true,               // 是否生成提示工具
            false               // 是否生成 URL 链接
        );
    }
}
