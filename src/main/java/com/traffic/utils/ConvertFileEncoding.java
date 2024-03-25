package com.traffic.utils;

import java.io.*;

/**
 * 文本格式转换
 */
public class ConvertFileEncoding {
    public static void main(String[] args) {
        // 输入文件路径
        String inputFilePath = "/Users/yin/Downloads/三体.txt";
        // 输出文件路径
        String outputFilePath = "/Users/yin/Downloads/new三体.txt";

        try {
            // 读取GB2312编码的文件
            FileInputStream fis = new FileInputStream(inputFilePath);
            InputStreamReader isr = new InputStreamReader(fis, "GB2312");
            BufferedReader br = new BufferedReader(isr);

            // 写入UTF-8编码的新文件
            FileOutputStream fos = new FileOutputStream(outputFilePath);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);

            // 逐行读取并写入
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }

            // 关闭资源
            br.close();
            bw.close();
            isr.close();
            osw.close();
            fis.close();
            fos.close();

            System.out.println("文件转换完成！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
