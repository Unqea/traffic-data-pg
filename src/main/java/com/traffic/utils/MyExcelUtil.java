/*
package com.traffic.utils;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.aliyun.odps.simpleframework.xml.transform.InvalidFormatException;
import com.traffic.entity.ExcelBean;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyExcelUtil {

    public static void main(String[] args) throws IOException, InvalidFormatException {
        List<List<String>> lists = readExcelData("/Users/yin/Downloads/cam.xlsx", "春运场景 (2)");
        List<ExcelBean> excelBeans = new ArrayList<>();
        JSONArray objects = JSONUtil.parseArray(lists);
        for (Object object : objects) {
            JSONArray objects1 = JSONUtil.parseArray(object);
            if (objects1.size() == 7) {
                if (objects1.get(0).equals("一级")) {
                    continue;
                }
                ExcelBean excelBean = new ExcelBean();
                excelBean.setOne((String) objects1.get(0));
                excelBean.setTwo((String) objects1.get(1));
                excelBean.setThree((String) objects1.get(2));
                excelBean.setNumber((String) objects1.get(3));
                excelBean.setName((String) objects1.get(4));
                excelBean.setCode((String) objects1.get(5));
                excelBean.setNotes((String) objects1.get(6));
                excelBeans.add(excelBean);
            }
        }

        for (int i = 0; i < excelBeans.size(); i++) {

            ExcelBean excelBean = excelBeans.get(i);
            if (ObjUtil.isEmpty(excelBean.getOne())) {
                excelBean.setOne(excelBeans.get(i - 1).getOne());
            }
            if (ObjUtil.isEmpty(excelBean.getTwo())) {
                excelBean.setTwo(excelBeans.get(i - 1).getTwo());
            }
            if (ObjUtil.isEmpty(excelBean.getThree())) {
                excelBean.setThree(excelBeans.get(i - 1).getThree());
            }
        }

        for (ExcelBean excelBean : excelBeans) {
            System.out.println(excelBean);
        }


    }

    */
/**
     * 读取Excel数据
     *
     * @param filePath  文件路径
     * @param sheetName 工作表名称
     * @return 以List<List < String>>形式返回Excel中的数据
     * @throws IOException
     * @throws InvalidFormatException
     *//*

    public static List<List<String>> readExcelData(String filePath, String sheetName) throws IOException, InvalidFormatException {
        List<List<String>> data = new ArrayList<>();
        // 创建工作簿
        Workbook workbook = WorkbookFactory.create(new File(filePath));
        // 获取工作表
        Sheet sheet = workbook.getSheet(sheetName);
        // 获取行数
        int rowCount = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                int columnCount = row.getLastCellNum();
                List<String> rowData = new ArrayList<>();
                for (int j = 0; j < columnCount; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        // 根据单元格数据类型获取值
                        switch (cell.getCellType()) {
                            case STRING:
                                rowData.add(cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                rowData.add(Double.toString(cell.getNumericCellValue()));
                                break;
                            case BOOLEAN:
                                rowData.add(Boolean.toString(cell.getBooleanCellValue()));
                                break;
                            case FORMULA:
                                rowData.add(cell.getCellFormula());
                                break;
                            default:
                                rowData.add("");
                        }
                    } else {
                        rowData.add("");
                    }
                }
                data.add(rowData);
            }
        }
        return data;
    }
}
*/
