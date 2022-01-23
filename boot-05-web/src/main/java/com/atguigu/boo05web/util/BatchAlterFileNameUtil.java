package com.atguigu.boo05web.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BatchAlterFileNameUtil {

    /**
     * 获取文件的绝对路径集合
     *
     * @param directoryPath 绝对路径
     * @param recursion 是否递归查询文件
     * @param oldSuffix
     * @param newSuffix
     * @return
     */
    public List<String> getFileList(String directoryPath, boolean recursion,
                                    String oldSuffix, String newSuffix) {
        List<String> fileList = new ArrayList<>();
        File file = new File(directoryPath);
        if (!file.exists()) {
            System.err.println("路径名定义的文件或目录不存在");
            return null;
        }

        if (file.isFile()) {
            fileList.add(file.getAbsolutePath());
            return fileList;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isFile()) {
                    fileList.add(f.getAbsolutePath());
                    alterFileName(f, oldSuffix, newSuffix);
                    continue;
                }
                if (recursion && f.isDirectory()) {
                    List<String> fileList2 = getFileList(f.getAbsolutePath(), recursion,
                                                            oldSuffix, newSuffix);
                    fileList.addAll(fileList2);
                }
            }
        }

        return fileList;
    }

    public void alterFileName(File file, String oldSuffix, String newSuffix) {
        if (file == null || !file.isFile()) {
            System.err.println("文件为空，无法修改后缀名");
            return;
        }

        if (oldSuffix == null || oldSuffix.trim().isEmpty()) {
            System.err.println("老后缀名参数为空，无法修改");
        }

        if (newSuffix == null || newSuffix.trim().isEmpty()) {
            System.err.println("新后缀名参数为空，无法修改");
        }

        String fileName = file.getName();
        if (fileName.endsWith("."+oldSuffix)) {
            fileName = fileName.substring(0, fileName.lastIndexOf(".")+1);
            fileName += newSuffix;
            file.renameTo(new File(file.getParent() + "/" + fileName));
        }
    }
}
