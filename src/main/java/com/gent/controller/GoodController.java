package com.gent.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Locale;

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
    @RequestMapping(value="/admin/goodform")
    public String good(ModelMap model){

        model.addAttribute(new Good());
        model.addAttribute("allData", goodService.getAllGoods());

        setPageData(model);
      return "goodform";

    }

    @RequestMapping(value="/admin/addGood", method = RequestMethod.POST)
    public String addGood(@ModelAttribute("good") @Valid Good good, BindingResult result,
                            ModelMap model, HttpServletRequest request,  @RequestParam("file") MultipartFile[] files) {
        if(!result.hasErrors()) {


            Description desc = new Description();
            desc.setRuText(good.getDescription().getRuText());
            desc.setUaText(good.getDescription().getUaText());
            descriptionService.addDescription(desc);

            good.setDescription(desc);
            good.setColor(colorService.getColorById(good.getColor().getId()));
            good.setCategory(categoryService.getCategoryById(good.getCategory().getId()));
            good.setStatus(1);
            boolean flag = goodService.addGood(good);
            model.addAttribute(new Good());
            String fileName = null;
            String msg = "";
            System.out.println("%%%%%"+files.length);

            if (files != null && files.length >0) {
                for(int i =0 ;i< files.length; i++){
                    try {
                        fileName = files[i].getOriginalFilename();
                        byte[] bytes = files[i].getBytes();

//                        String path = "C:/jav/i18n/goods/";
                        String path = "/home/daria/gent/goods/";
                        BufferedOutputStream buffStream =
                                new BufferedOutputStream(new FileOutputStream(new File(path + good.getId()+"_"+i+".jpg")));
                        buffStream.write(bytes);
                        buffStream.close();

                        System.out.println(path + good.getId()+"_"+i+".jpg");


                    } catch (Exception e) {

                    }
                }

            }

        }

        setPageData(model);
        return "goodform";

    }
    @RequestMapping(value="/admin/goodInfo")
    public String getInfo(ModelMap model, HttpServletRequest request) {
        int pid = Integer.parseInt(request.getParameter("id"));
        Good good = goodService.getGoodById(pid);

        model.addAttribute("info",good);
        setPageData(model);
        return "goodInfo";
    }
    @RequestMapping(value="/admin/goodById")
    public String getGoodById(ModelMap model, HttpServletRequest request) {
        int pid = Integer.parseInt(request.getParameter("id"));
        Good good = goodService.getGoodById(pid);
        setPageData(model);
        model.addAttribute(good);
        return "goodform";
    }
    @RequestMapping(value="/admin/updateGood", method = RequestMethod.POST)
    public String updateGood(@ModelAttribute("good") @Valid Good good, BindingResult result,
                               ModelMap model, HttpServletRequest request,  @RequestParam("file") MultipartFile[] files, @RequestParam("flagImg") String flag) {

        if(!result.hasErrors()) {
            System.out.println("@@@@@@@@@@"+flag);

            if(flag.equals("1"))
            {
                String path = "/home/daria/gent/goods/";
//                String path = "C:/jav/i18n/goods/";
                for(int i=0; i<goodService.getGoodById(good.getId()).getCountImg(); i++)
                {

                    File file = new File(path + good.getId()+"_"+i+".jpg");
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
                                    new BufferedOutputStream(new FileOutputStream(new File(path + good.getId()+"_"+i+".jpg")));
                            buffStream.write(bytes);
                            buffStream.close();
                            System.out.println(path + good.getId()+"_"+i+".jpg");

                        } catch (Exception e) {

                        }
                    }

                }
            }
            int descId = goodService.getGoodById(good.getId()).getDescription().getId();

            Description desc = descriptionService.getDescriptionById(descId);
            desc.setRuText(good.getDescription().getRuText());
            desc.setUaText(good.getDescription().getUaText());


            descriptionService.updateDescription(desc);
           good.setDescription(desc);
            goodService.updateGood(good);
             model.addAttribute(new Good());
            model.addAttribute("msg", getMsg("updated", request));
        }
        else
        System.out.println(result.getAllErrors().toString());
        setPageData(model);
        return "redirect:goodform";
    }
    @RequestMapping(value="/admin/deleteGood")
    public String deleteGood(ModelMap model, HttpServletRequest request) {
        int pid = Integer.parseInt(request.getParameter("id"));

        int descId = goodService.getGoodById(pid).getDescription().getId();


        String path = "/home/daria/gent/goods/";
//        String path = "C:/jav/i18n/goods/";
        for(int i=0; i<goodService.getGoodById(pid).getCountImg(); i++)
        {

            File file = new File(path + pid+"_"+i+".jpg");
            file.delete();
        }

        goodService.deleteGood(pid);
        descriptionService.deleteDescription(descId);
        model.addAttribute(new Good());
        model.addAttribute("msg", getMsg("deleted", request));
        setPageData(model);
        return "redirect:goodform";
    }
    private void setPageData(ModelMap model) {
        model.addAttribute("allData", goodService.getAllGoods());
        Locale locale = LocaleContextHolder.getLocale();
        if(locale.getLanguage().equals("uk"))
            model.addAttribute("lang_code", "uaText");
        else
        if(locale.getLanguage().equals( "ru"))
            model.addAttribute("lang_code", "ruText");

        model.addAttribute("colors", colorService.getAllColor());
        model.addAttribute("categories", categoryService.getAllCategory());
    }
    private String getMsg(String key, HttpServletRequest request) {
        return messageSource.getMessage(key, null, localeResolver.resolveLocale(request));
    }


}
