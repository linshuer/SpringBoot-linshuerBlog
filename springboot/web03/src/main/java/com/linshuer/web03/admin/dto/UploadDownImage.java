package com.linshuer.web03.admin.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UploadDownImage {

    static public Result uploadDownUser(MultipartFile picture, String select,String path){

        //获取文件在服务器的储存位置
        File filePath = new File(path);
        System.out.println("文件的保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录:" + filePath);
            filePath.mkdir();
        }

       //获取原始文件名称(包含格式)
        String originalFileName = picture.getOriginalFilename();

        System.out.println("原始文件名称：" + originalFileName);
        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("文件类型：" + type);
        //获取文件名称（不包含格式）
        String name =null;
        String basedir = null;
        //设置文件新名称: 当前时间+文件名称（不包含格式）
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        if(select.equals("avatar")){//判断是背景图片还是头像
            name = "v-"+date+"avatar";
            basedir = "/avatar/";
        }else{
            name = "v-"+date+"background";
            basedir = "/background/";
        }

        String fileName =  name + "." + type;
        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);

        try {
            //将文件保存到服务器指定位置
            picture.transferTo(targetFile);
            System.out.println("上传成功");
            //将文件在服务器的存储路径返回
            System.out.println(path + "/" + fileName);
            return new Result(StatusCode.SUCCESS,"http://localhost:8080"+basedir+ fileName);
        } catch (IOException e) {
            System.out.println("上传失败");
            e.printStackTrace();
            return new Result(StatusCode.ERROR, "上传失败");
        }
    }
}
