package com.traffic.test.world;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 图片生成
 */
public class ChartToImage {

    public void saveChartAsImage(JFreeChart chart, String filePath) throws IOException {
        int width = 400;   // 图片的宽度
        int height = 300;  // 图片的高度
        File file = new File(filePath);
        ImageIO.write(chart.createBufferedImage(width, height), "PNG", file);
    }
}
