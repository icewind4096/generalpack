package com.mindmotion.utils;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

/**
 * Created by mecwa on 2019/10/7.
 */

public class ZipCompressFile{
    public static Boolean compress(String destFileName, String sourceDirectoryName) {
        File destFile = new File(destFileName);
        File sourceFile = new File(sourceDirectoryName);

        Project project = new Project();

        Zip zip = new Zip();
        zip.setProject(project);
        zip.setDestFile(destFile);
        zip.setUpdate(true);

        FileSet fileSet = new FileSet();
        fileSet.setProject(project);
        fileSet.setDir(sourceFile);
        zip.addFileset(fileSet);
        zip.execute();

        return true;
    }
}
