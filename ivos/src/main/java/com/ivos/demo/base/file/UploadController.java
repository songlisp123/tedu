package com.ivos.demo.base.file;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ivos.demo.base.response.JsonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Tag(name = "图片上传模块")
@RestController
@RequestMapping("/v1/file/")
public class UploadController {

    private final String dirPath = "i:/files";

    @Operation(summary = "上传图片功能")
    @ApiOperationSupport(order = 10)
    @PostMapping("upload")
    public JsonResult upload(MultipartFile file) {
        log.debug("控制器参数：{}",file);
        String originalFilename = file.getOriginalFilename();

        //截取文件名后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        //获取唯一文件名
        originalFilename = UUID.randomUUID().toString() + suffix;
        log.debug("原始图片名称:{}",originalFilename);

        //指定一个目录用来上传图片


        //将当前日期装换为多层文件夹路径的格式
        SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
        String datePath = sdf.format(new Date());
        //准备文件夹对象，存放多级目录
        File dirFile = new File(dirPath + datePath);

        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        //存放图片到当前文件夹
        String filePath = dirPath+datePath+originalFilename;
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.debug(datePath+originalFilename);
        return JsonResult.ok(datePath+originalFilename);
    }

    @PostMapping("delete")
    @Operation(summary = "图片删除功能")
    @ApiOperationSupport(order = 20)
    public JsonResult delete(String imgUrl) {
        log.debug("删除路径：{}",imgUrl);
        new File(dirPath+imgUrl).delete();
        return JsonResult.ok();
    }
}
