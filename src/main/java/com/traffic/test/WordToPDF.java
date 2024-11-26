/*
package com.traffic.test;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileOutputStream;
import java.math.BigInteger;

public class WordToPDF {
    public static void main(String[] args) {
        try {
            // 创建一个新的Word文档
            XWPFDocument document = new XWPFDocument();

            // 创建标题
            XWPFParagraph title = document.createParagraph();
            title.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun titleRun = title.createRun();
            titleRun.setText("附件 1");
            titleRun.setBold(true);
            titleRun.setFontSize(10);

            // 创建第二行标题
            XWPFParagraph subtitle = document.createParagraph();
            subtitle.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun subtitleRun = subtitle.createRun();
            subtitleRun.setText("新朔铁路隐患报告单");
            subtitleRun.setBold(true);
            subtitleRun.setFontSize(10);

            // 添加空行
            document.createParagraph();

            // 创建表格（9行5列）
            XWPFTable table = document.createTable(9, 6);

            // 设置表格宽度（例如，表格宽度为100%）
            CTTblWidth tableWidth = table.getCTTbl().getTblPr().addNewTblW();
            tableWidth.setW(BigInteger.valueOf(7500));  // 表示75%的宽度
            tableWidth.setType(STTblWidth.DXA);

            // 设置表格对齐方式（居中、左对齐、右对齐）
            CTTblPr tblPr = table.getCTTbl().getTblPr();
            CTJc jc = tblPr.addNewJc();
            jc.setVal(STJc.CENTER); // 表格居中对齐


            //第一行
            setFontType(table.getRow(0).getCell(0).getParagraphs().get(0).createRun(), "报告单位（部门）", 8, "宋体");
            setFontType(table.getRow(0).getCell(1).getParagraphs().get(0).createRun(), "负责人", 8, "宋体");
            setFontType(table.getRow(0).getCell(2).getParagraphs().get(0).createRun(), "报告时间", 8, "宋体");
            setFontType(table.getRow(0).getCell(3).getParagraphs().get(0).createRun(), "初步评估等级", 8, "宋体");
            setFontType(table.getRow(0).getCell(4).getParagraphs().get(0).createRun(), "评估时间", 8, "宋体");
            setFontType(table.getRow(0).getCell(5).getParagraphs().get(0).createRun(), "报告联系人及电话", 8, "宋体");

            //第二行(内容待填充)
            setFontType(table.getRow(1).getCell(0).getParagraphs().get(0).createRun(), "", 8, "宋体");
            setFontType(table.getRow(1).getCell(1).getParagraphs().get(0).createRun(), "", 8, "宋体");
            setFontType(table.getRow(1).getCell(2).getParagraphs().get(0).createRun(), "", 8, "宋体");
            setFontType(table.getRow(1).getCell(3).getParagraphs().get(0).createRun(), "", 8, "宋体");
            setFontType(table.getRow(1).getCell(4).getParagraphs().get(0).createRun(), "", 8, "宋体");
            setFontType(table.getRow(1).getCell(5).getParagraphs().get(0).createRun(), "", 8, "宋体");

            //第三行
            setFontType(table.getRow(2).getCell(0).getParagraphs().get(0).createRun(), "隐患来源：自行排查□ 上级检查□ 行业监管部门督办□ 地方政府有关部门督办□", 8, "宋体");
            //第四行
            setFontType(table.getRow(3).getCell(0).getParagraphs().get(0).createRun(), "隐患性质：重大隐患□ 需公司协调解决□", 8, "宋体");
            //第五行
            setFontType(table.getRow(4).getCell(0).getParagraphs().get(0).createRun(), "隐患现状和产生原因：", 8, "宋体");
            //第六行
            setFontType(table.getRow(5).getCell(0).getParagraphs().get(0).createRun(), "隐患的危害程度及影响范围：", 8, "宋体");
            //第七行
            setFontType(table.getRow(6).getCell(0).getParagraphs().get(0).createRun(), "隐患防控措施：", 8, "宋体");
            //第八行
            setFontType(table.getRow(7).getCell(0).getParagraphs().get(0).createRun(), "隐患整改措施及计划：", 8, "宋体");
            //第九行
            setFontType(table.getRow(8).getCell(0).getParagraphs().get(0).createRun(), "应急预案简述：", 8, "宋体");

            //合并单元格
            mergeCell(table, 0, 5, 2, 3, 4, 5, 6, 7, 8);


            // 保存Word文档
            try (FileOutputStream out = new FileOutputStream("report.docx")) {
                document.write(out);
            }

            System.out.println("Word document created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //设置字体大小、格式、内容
    private static void setFontType(XWPFRun run, String text, int fontSize, String fontFamily) {
        run.setFontSize(fontSize);
        run.setFontFamily(fontFamily);
        run.setText(text);
    }

    */
/**
     * 合并单元格
     *
     * @param table    表
     * @param fromCell 从第几列
     * @param toCell   到几列
     * @param row      行
     *//*

    private static void mergeCell(XWPFTable table, int fromCell, int toCell, int... row) {
        for (int i : row) {
            mergeCellsHorizontal(table, i, 0, 5);
        }
    }


    // 横向合并单元格
    private static void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
        XWPFTableCell cell = table.getRow(row).getCell(fromCell);
        cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.Enum.forString("restart"));
        for (int cellIndex = fromCell + 1; cellIndex <= toCell; cellIndex++) {
            table.getRow(row).getCell(cellIndex).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.Enum.forString("continue"));
        }
    }

    // 纵向合并单元格
    private static void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            if (rowIndex == fromRow) {
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.Enum.forString("restart"));
            } else {
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.Enum.forString("continue"));
            }
        }
    }


}
*/
