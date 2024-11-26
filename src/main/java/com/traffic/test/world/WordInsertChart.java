package com.traffic.test.world;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.util.IOUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图片插入world
 */
public class WordInsertChart {

    public void insertChartToWord(String chartImagePath, String wordTemplatePath, String outputWordPath) throws IOException, InvalidFormatException {
        // 加载模板文件
        FileInputStream templateFile = new FileInputStream(wordTemplatePath);
        XWPFDocument doc = new XWPFDocument(templateFile);

        // 遍历所有段落，查找占位符并插入图表
        for (XWPFParagraph paragraph : doc.getParagraphs()) {
            for (XWPFRun run : paragraph.getRuns()) {
                String text = run.getText(0);
                if (text != null && text.contains("${chart1}")) {
                    // 替换占位符
                    text = text.replace("${chart1}", "");
                    run.setText(text, 0);

                    // 插入图片到当前位置
                    FileInputStream chartImage = new FileInputStream(chartImagePath);
                    run.addPicture(chartImage, XWPFDocument.PICTURE_TYPE_PNG, chartImagePath, Units.toEMU(300), Units.toEMU(200));
                    chartImage.close();
                }
            }
        }

        // 输出生成的 Word 文件
        try (FileOutputStream out = new FileOutputStream(outputWordPath)) {
            doc.write(out);
        }
        templateFile.close();
    }
}
