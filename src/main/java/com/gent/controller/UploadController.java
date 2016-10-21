package com.gent.controller;

/**
 * Created by daria on 01.10.2016.
 */
import java.io.*;
import java.net.URLDecoder;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

@Controller
public class UploadController {
    @Autowired
    ServletContext servletContext;

    @RequestMapping(value="/upload")
    public String singleUpload(){
        return "upload";
    }

    @RequestMapping(value="/singleSave", method=RequestMethod.POST )
    public @ResponseBody String singleSave(@RequestParam("file") MultipartFile file,
                                           @RequestParam("desc") String desc ){
        System.out.println("File Description:"+desc);
        String fileName = null;
        fileName = file.getOriginalFilename();

        String path = this.getClass().getClassLoader().getResource("").getPath();
        String fullPath = null;
        try {
            fullPath = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String pathArr[] = fullPath.split("/WEB-INF/images/goods");

        String rpath = String.valueOf(pathArr[0]);
        System.out.println(fullPath);
        System.out.println(pathArr[0]);
        if (!file.isEmpty()) {
            try {
                fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();



                BufferedOutputStream buffStream =
                        new BufferedOutputStream(new FileOutputStream(new File(rpath + fileName)));
                buffStream.write(bytes);
                buffStream.close();
                return "You have successfully uploaded " + rpath + fileName;
            } catch (Exception e) {
                return "You failed to upload " + rpath + fileName + ": " + e.getMessage();
            }
        } else {
            return "Unable to upload. File is empty.";
        }
    }

    @RequestMapping(value="/multipleUpload")
    public String multiUpload(){
        return "multipleUpload";
    }

//    @RequestMapping(value="/multipleSave", method=RequestMethod.POST )
//    public @ResponseBody String multipleSave(@RequestParam("file") MultipartFile[] files){
//        String fileName = null;
//        String msg = "";
//
//        if (files != null && files.length >0) {
//            for(int i =0 ;i< files.length; i++){
//                try {
//                    fileName = files[i].getOriginalFilename();
//                    byte[] bytes = files[i].getBytes();
//                    String path = "C:/jav/i18n/src/main/webapp/WEB-INF/resources/images/goods/";
//                    BufferedOutputStream buffStream =
//                            new BufferedOutputStream(new FileOutputStream(new File(path + fileName)));
//                    buffStream.write(bytes);
//                    buffStream.close();
//                    msg += "You have successfully uploaded " + fileName +"<br/>";
//                } catch (Exception e) {
//                    return "You failed to upload " + fileName + ": " + e.getMessage() +"<br/>";
//                }
//            }
//            return msg;
//        } else {
//            return "Unable to upload. File is empty.";
//        }
//    }
}
