package com.atguigu.boo05web.util;

import com.sun.tools.javac.util.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BatchAlterFileNameUtilTest {

    @Test
    void getFileList() {
        BatchAlterFileNameUtil batchAlterFileNameUtil = new BatchAlterFileNameUtil();
        List<String> fileList = batchAlterFileNameUtil
                .getFileList("/Users/apple/Documents/record/22", true, "ts", "mp4");
        fileList.forEach(e -> System.out.println(e));
    }

}