package com.traffic.utils;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

/**
 * 减少图片体积攻击
 */
public class ImageConverter {
    public static void main(String[] args) {
        String sourceFolderPath = "/Users/yin/Downloads/666/";
        String targetFolderPath = "/Users/yin/Downloads/777/";

        convertImages(sourceFolderPath, targetFolderPath);
    }

    public static void convertImages(String sourceFolderPath, String targetFolderPath) {
        File sourceFolder = new File(sourceFolderPath);
        File targetFolder = new File(targetFolderPath);

        // 创建目标文件夹
        if (!targetFolder.exists()) {
            targetFolder.mkdirs();
        }

        // 获取源文件夹下所有文件
        File[] files = sourceFolder.listFiles();

        if (files == null) {
            System.out.println("源文件夹为空或者不存在！");
            return;
        }

        // 遍历文件
        for (File file : files) {
            if (file.isFile() && file.getName().toLowerCase().endsWith(".jpeg")) {
                try {
                    // 读取图片
                    BufferedImage image = ImageIO.read(file);

                    // 获取图片文件大小
                    long fileSizeInBytes = file.length();
                    long fileSizeInKB = fileSizeInBytes / 1024; // 转换为KB

                    // 如果图片小于1M，则进行格式转换并保存
                    if (fileSizeInKB < 1024) {
                        File outputFile = new File(targetFolder, file.getName().replace(".jpeg", ".jpg"));
                        compressAndSaveImage(image, outputFile);
                        System.out.println("转换完成：" + outputFile.getAbsolutePath());
                    }

                } catch (IOException e) {
                    System.out.println("无法转换文件：" + file.getAbsolutePath());
                    e.printStackTrace();
                }
            }
        }
    }

    public static void compressAndSaveImage(BufferedImage image, File outputFile) throws IOException {
        // 获取文件的输出流
        FileImageOutputStream outputStream = new FileImageOutputStream(outputFile);

        // 获取JPEG图像的写入器
        ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
        writer.setOutput(outputStream);

        // 设置压缩参数
        ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.7f); // 设置压缩质量，可以调整此参数以达到更小的文件大小

        // 写入图片
        writer.write(null, new javax.imageio.IIOImage(image, null, null), param);

        // 关闭流
        outputStream.close();
        writer.dispose();
    }
}
