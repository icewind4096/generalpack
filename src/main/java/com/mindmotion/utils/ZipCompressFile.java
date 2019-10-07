package com.mindmotion.utils;

import java.io.*;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by mecwa on 2019/10/7.
 */
public class ZipCompressFile {
    private final int BUFFERSIZE = 8192;

    private File zipFile;

    public ZipCompressFile(String fileName) {
        zipFile = new File(fileName);
    }

    public Integer compress(String directory) {
        File file = new File(directory);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
            ZipOutputStream out = new ZipOutputStream(cos);
            String basedir = "";
            compress(file, out, basedir);
            out.close();
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
            if (zipFile.exists() == true){
            }
            return 0;
        }
    }

    private void compress(File file, ZipOutputStream out, String basedir) {
        if (file.isDirectory() == false) {
            this.compressFile(file, out, basedir);
        } else {
            this.compressDirectory(file, out, basedir);
        }
    }

    private void compressDirectory(File directory, ZipOutputStream out, String basedir) {
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            compress(files[i], out, basedir + directory.getName() + "/");
        }
    }

    private void compressFile(File file, ZipOutputStream out, String basedir) {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            ZipEntry entry = new ZipEntry(basedir + file.getName());
            out.putNextEntry(entry);
            int count;
            byte data[] = new byte[BUFFERSIZE];
            while ((count = bis.read(data, 0, BUFFERSIZE)) != -1) {
                out.write(data, 0, count);
            }
            bis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
