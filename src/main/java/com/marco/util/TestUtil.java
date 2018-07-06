package com.marco.util;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * Created by landun on 2018/7/6.
 */
public class TestUtil {

    public static void main(String[] args) {
        int limit = 1024 * 1024 * 1024;
        byte[] data = new byte[limit];
        for (int i = 0; i < limit; i++) {
            data[i] = 'A';
        }
        System.out.println("初始化完成");
        String apath = "E:\\aa.txt";
        String bpath = "E:\\bb";
        long startTime = System.currentTimeMillis();
        writerFileByteArrayThread(apath, bpath, data, true);
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    public static boolean writerFileByteArrayThread(String filePath, String pathValue,
                                                    byte[] data, boolean isWriteBackReceiveFile) {
        FileOutputStream fos = null; //输出到文件流
        DataOutputStream dos = null;
        try {
            File dirFile = new File(pathValue);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }

            fos = new FileOutputStream(filePath);
            dos = new DataOutputStream(fos);

            dos.write(data, 0, data.length);
            dos.flush();
            dos.close();

            if (true) {
                writeBackReceiveFile(filePath, pathValue, new File(filePath));
            }

            return true;
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            try {
                if (null != fos) {
                    fos.close();
                }
                if (null != dos) {
                    dos.close();
                }
            } catch (Exception e2) {
            }
        }

        return false;
    }

    private static void writeBackReceiveFile(String filePath, String dirPath, File file) {
        try {
            String fileBakPath = filePath.replace("filereceive", "filereceiveback");
            File filebak = new File(fileBakPath);
            File dirFileback = new File(dirPath.replace("filereceive", "filereceiveback"));

            if (!dirFileback.exists()) {
                dirFileback.mkdirs();
            }

            if (filebak.exists()) {
                file.delete();
                filebak.createNewFile();
            }
            fileChannelCopy(file, filebak);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void fileChannelCopy(File s, File t) {
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();// 得到对应的文件通道
            out = fo.getChannel();// 得到对应的文件通道
            in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fi != null) {
                    fi.close();
                }
                if (in != null) {
                    in.close();
                }
                if (fo != null) {
                    fo.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
