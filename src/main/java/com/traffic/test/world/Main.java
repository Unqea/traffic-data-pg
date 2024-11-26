package com.traffic.test.world;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jfree.chart.JFreeChart;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> categories = Arrays.asList("1月", "2月", "3月", "4月", "5月");
        List<Integer> values = Arrays.asList(100, 120, 130, 110, 140);
        
        // 生成图表
        ChartGenerator chartGenerator = new ChartGenerator();
        JFreeChart lineChart = chartGenerator.generateLineChart(categories, values);

        // 将图表保存为图片
        ChartToImage chartToImage = new ChartToImage();
        try {
            String chartImagePath = "/Users/yin/Downloads/chart.png";
            chartToImage.saveChartAsImage(lineChart, chartImagePath);

            // 将图表插入到 Word 模板
            WordInsertChart wordInsertChart = new WordInsertChart();
            String wordTemplatePath = "/Users/yin/Downloads/template.docx";
            String outputWordPath = "/Users/yin/Downloads/output.docx";
            wordInsertChart.insertChartToWord(chartImagePath, wordTemplatePath, outputWordPath);

            System.out.println("图表已插入到 Word 文件中！");
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
