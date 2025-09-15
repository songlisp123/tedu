package com.weiboLast.demo.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.weiboLast.demo.base.response.JsonResult;
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
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Tag(name = "04用户上传信息模块")
@RestController
@RequestMapping("/v1/file")
public class UploadController {

    /**
     * 文件上传功能
     */
    @PostMapping("/upload")
    @Operation(summary = "用户上传信息")
    @ApiOperationSupport(order = 100)
    public JsonResult upload(MultipartFile file) throws IOException {
        /*
            1.接收客户端传递的文件;
            2.将文件保存到服务器中;
            3.返回成功信息给客户端;
         */
        //终极目标: i:/files/2025/09/11/20714d37-a89b-4a3e-8b8b-7394ee85d418.jpg
        //1.获取原始文件名: zly.jpg;
        String filename = file.getOriginalFilename();
        //2.获取文件名后缀: .jpg;
        String suffix = filename.substring(filename.lastIndexOf("."));
        //3.处理文件名: 20714d37-a89b-4a3e-8b8b-7394ee85d418.jpg
        filename = UUID.randomUUID() + suffix;
        //4.处理日期路径: /2025/09/11/
        SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
        String datePath = sdf.format(new Date());
        String staticPath = "I:/files";
        //5.创建保存文件的目录:I:/files/2025/09/11/
        File dirFile = new File(staticPath + datePath);
        if (!dirFile.exists()){
            dirFile.mkdirs();
        }

        //6.保存文件到服务器: D:/files/2025/09/11/20714d37-a89b-4a3e-8b8b-7394ee85d418.jpg
        String filePath = staticPath + datePath + filename;
        file.transferTo(new File(filePath));

        //前端工程师:获取图片在服务器中的路径,进行页面的图片回显;
        //http://localhost:8080/2025/09/11/2eea5205-6b39-4115-b4c1-96c1dfec1a51.jpg
        return JsonResult.ok(datePath + filename);
    }
}









