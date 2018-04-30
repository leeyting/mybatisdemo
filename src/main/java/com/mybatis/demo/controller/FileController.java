package com.mybatis.demo.controller;

import com.mybatis.demo.base.exception.ParamAssertException;
import com.mybatis.demo.base.result.ResultFactory;
import com.mybatis.demo.base.result.ResultV1;
import com.mybatis.demo.base.utils.ParamAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Author: liyao
 * @Description: 文件上传
 * @Date: Created in 2018/04/30 21:19
 */

@RestController
@RequestMapping(value = "/file")
public class FileController {

    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping(value = "/upload")
    public ResultV1 upload(@RequestParam MultipartFile file) throws ParamAssertException {
        ParamAssert.assertTrue(!file.isEmpty(), "文件为空");
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String filePath = "F:\\home\\bea1\\fv\\images";
        File dest = new File(filePath, System.currentTimeMillis() + suffixName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return ResultFactory.SuccessV1("上传成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return ResultFactory.FailedV1("上传失败");
    }

    @PostMapping(value = "/batch/upload")
    public ResultV1 handleFileUpload(@RequestParam MultipartFile[] file) {
        try {
            String filePath = "F:\\home\\bea1\\fv\\images";
            for (MultipartFile item : file) {
                String fileName = item.getOriginalFilename();
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                File dest = new File(filePath, System.currentTimeMillis() + suffixName);
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                item.transferTo(dest);
            }
            return ResultFactory.SuccessV1("上传成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultFactory.FailedV1("上传失败");
    }

}
