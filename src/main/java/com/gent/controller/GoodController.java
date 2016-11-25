package com.gent.controller;

import com.gent.config.AppConfiguration;
import com.gent.model.Color;
import com.gent.model.Description;
import com.gent.model.Good;
import com.gent.service.ICategoryService;
import com.gent.service.IColorService;
import com.gent.service.IDescriptionService;
import com.gent.service.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;


import java.net.URLEncoder;
import java.util.Locale;

import static com.gent.config.AppConfiguration.urlWriteImages;
import static com.gent.config.ChangeLang.redirectWithLang;

/**
 * Created by daria on 30.09.2016.
 */

@Controller
@ComponentScan("com.gent")
public class GoodController {

    @Autowired
    private IGoodService goodService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private LocaleResolver localeResolver;
    @Autowired
    private IColorService colorService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IDescriptionService descriptionService;
    @Autowired
    ServletContext servletContext;
    @RequestMapping(value="/{lang}/admin/goodform")
    public String good(ModelMap model, HttpServletRequest request){

        model.addAttribute("good",new Good());
        model.addAttribute("allData", goodService.getAllGoods());

        setPageData(model);
        return "goodform"; //call function for redirect

    }

    @RequestMapping(value="/{lang}/admin/addGood", method = RequestMethod.POST)
    public String addGood(@ModelAttribute("good") @Valid Good good, BindingResult result,
                            ModelMap model, HttpServletRequest request,  @RequestParam("file") MultipartFile[] files) {
        if(!result.hasErrors()) {



            good.setColor(colorService.getColorById(good.getColor().getId()));
            good.setCategory(categoryService.getCategoryById(good.getCategory().getId()));
            good.setStatus(1);
            boolean flag = goodService.addGood(good);
            model.addAttribute(new Good());
            String fileName = null;
            String msg = "";
            System.out.println("%%%%%"+files.length);

            if (files.length > 0) {
                for(int i =0 ;i< files.length; i++){
                    try {
                        fileName = files[i].getOriginalFilename();
                        byte[] bytes = files[i].getBytes();

//                        String path = "C:/jav/i18n/goods/";

//                        String path = "/home/daria/gent/goods/";
                        BufferedOutputStream buffStream =
                                new BufferedOutputStream(new FileOutputStream(new File(urlWriteImages + good.getId()+"_"+i+".jpg")));
                        buffStream.write(bytes);
                        buffStream.close();

                        System.out.println("%%%%%"+urlWriteImages + good.getId()+"_"+i+".jpg");


                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

            }

        }

        setPageData(model);
        return "goodform";

    }
    @RequestMapping(value="/{lang}/good/{category}-{firm}-{color}/{id}", method = RequestMethod.GET)
    public String getInfoGood(ModelMap model, HttpServletRequest request, @PathVariable("lang") String lang
            , @PathVariable("category") String category, @PathVariable("firm") String firm,
                              @PathVariable("color") String color, @PathVariable("id") int id) {

        Good good = goodService.getGoodById(id);
        model.addAttribute("lang", LocaleContextHolder.getLocale().getLanguage());
        model.addAttribute("info",good);
        setPageData(model);

        if(request.getParameter("lang") != null)
        {
            String url2 ="";
            StringBuffer ur = request.getRequestURL();



            if(request.getParameter("lang").equals("ua"))


                try {
                    String cat = URLEncoder.encode(good.getCategory().getUaText(), "UTF-8");
                    String col = URLEncoder.encode(good.getColor().getUaText(), "UTF-8");
                    String parent = URLEncoder.encode( categoryService.getCategoryById(good.getCategory().getParent()).getUaText(), "UTF-8");
                    url2 = "/ua/"+parent+"/"+cat+"/"+good.getFirm()+"/"+col+"/"+good.getId();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            else
            if(request.getParameter("lang").equals("ru"))
                try {
                    String cat = URLEncoder.encode(good.getCategory().getRuText(), "UTF-8");
                    String col = URLEncoder.encode(good.getColor().getRuText(), "UTF-8");
                    String parent = URLEncoder.encode( categoryService.getCategoryById(good.getCategory().getParent()).getUaText(), "UTF-8");
                    url2 = "/ru/"+parent+"/"+cat+"/"+good.getFirm()+"/"+col+"/"+good.getId();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            try {
                request.setCharacterEncoding("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            redirectWithLang(request, "index"); //call function for redirect

        }
        return "goodInfo";
    }

    @RequestMapping(value="/{lang}/admin/goodInfo")
    public String getInfo(ModelMap model, HttpServletRequest request) {
        int pid = Integer.parseInt(request.getParameter("id"));
        Good good = goodService.getGoodById(pid);

        model.addAttribute("info",good);
        setPageData(model);
        return "goodInfo";
    }
    @RequestMapping(value="/{lang}/admin/goodById")
    public String getGoodById(ModelMap model, HttpServletRequest request) {
        int pid = Integer.parseInt(request.getParameter("id"));
        Good good = goodService.getGoodById(pid);
        setPageData(model);
        model.addAttribute(good);
        return "goodform";
    }
    @RequestMapping(value="/{lang}/admin/updateGood", method = RequestMethod.POST)
    public String updateGood(@ModelAttribute("good") @Valid Good good, BindingResult result,
                               ModelMap model, HttpServletRequest request,  @RequestParam("file") MultipartFile[] files, @RequestParam("flagImg") String flag) {

        if(!result.hasErrors()) {
            System.out.println("@@@@@@@@@@"+flag);

            if(flag.equals("1"))
            {
//                String path = "/home/daria/gent/goods/";
//                String path = "C:/jav/i18n/goods/";
                for(int i=0; i<goodService.getGoodById(good.getId()).getCountImg(); i++)
                {

                    File file = new File(urlWriteImages + good.getId()+"_"+i+".jpg");
                    file.delete();
                }
                String fileName = null;
                String msg = "";

                if (files != null && files.length >0) {
                    for(int i =0 ;i< files.length; i++){
                        try {
                            fileName = files[i].getOriginalFilename();
                            byte[] bytes = files[i].getBytes();


                            BufferedOutputStream buffStream =
                                    new BufferedOutputStream(new FileOutputStream(new File(urlWriteImages + good.getId()+"_"+i+".jpg")));
                            buffStream.write(bytes);
                            buffStream.close();
                            System.out.println(urlWriteImages + good.getId()+"_"+i+".jpg");

                        } catch (Exception e) {

                        }
                    }

                }
            }

            goodService.updateGood(good);
             model.addAttribute(new Good());
            model.addAttribute("msg", getMsg("updated", request));
        }
        else
        System.out.println(result.getAllErrors().toString());
        setPageData(model);
        return "redirect:goodform";
    }
    @RequestMapping(value="/{lang}/admin/deleteGood")
    public String deleteGood(ModelMap model, HttpServletRequest request) {
        int pid = Integer.parseInt(request.getParameter("id"));




//        String path = "/home/daria/gent/goods/";
//        String path = "C:/jav/i18n/goods/";
        for(int i=0; i<goodService.getGoodById(pid).getCountImg(); i++)
        {

            File file = new File(urlWriteImages + pid+"_"+i+".jpg");
            file.delete();
        }

        goodService.deleteGood(pid);

        model.addAttribute(new Good());
        model.addAttribute("msg", getMsg("deleted", request));
        setPageData(model);
        return "redirect:goodform";
    }
    private void setPageData(ModelMap model) {
        model.addAttribute("allData", goodService.getAllGoods());
        Locale locale = LocaleContextHolder.getLocale();

        model.addAttribute("colors", colorService.getAllColor());
        model.addAttribute("categories", categoryService.getSecondLevel());
        model.addAttribute("lang", LocaleContextHolder.getLocale().getLanguage()); //get locale language
        model.addAttribute("lang_code", LocaleContextHolder.getLocale().getLanguage()+"Text"); //get locale language
    }
    private String getMsg(String key, HttpServletRequest request) {
        return messageSource.getMessage(key, null, localeResolver.resolveLocale(request));
    }


}
