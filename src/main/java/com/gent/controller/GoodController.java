package com.gent.controller;

import com.gent.model.Good;
import com.gent.service.ICategoryService;
import com.gent.service.IColorService;
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

import static com.gent.config.AppConfiguration.urlWriteImages;
import static com.gent.config.ChangeLang.redirectWithLang;
import static com.gent.util.Constants.RU_LANG;
import static com.gent.util.Constants.UK_LANG;

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

    ServletContext servletContext;

    @RequestMapping(value = "/admin/goodform")
    public String good(ModelMap model, HttpServletRequest request) {
        model.addAttribute("good", new Good());
        model.addAttribute("allData", goodService.getAllGoods());
        setPageData(model);
        return "goodform";

    }

    @RequestMapping(value = "/admin/addGood", method = RequestMethod.POST)
    public String addGood(@ModelAttribute("good") @Valid Good good, BindingResult result,
                          ModelMap model, HttpServletRequest request, @RequestParam("file") MultipartFile[] files) {
        if (!result.hasErrors()) {


            good.setColor(colorService.getColorById(good.getColor().getId()));
            good.setCategory(categoryService.getCategoryById(good.getCategory().getId()));
            good.setStatus(1);
            boolean flag = goodService.addGood(good);
            model.addAttribute(new Good());

            if (files.length > 0) {
                for (int i = 0; i < files.length; i++) {
                    try {

                        byte[] bytes = files[i].getBytes();
                        String path = "/home/daria/gent/goods/";
                        BufferedOutputStream buffStream =
                                new BufferedOutputStream(new FileOutputStream(new File(urlWriteImages + good.getId() + "_" + i + ".jpg")));
                        buffStream.write(bytes);
                        buffStream.close();


                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

            }

        }

        setPageData(model);
        return "goodform";

    }

    @RequestMapping(value = "/{lang}/good/{category}-{firm}-{color}/{id}", method = RequestMethod.GET)
    public String getInfoGood(ModelMap model, HttpServletRequest request, @PathVariable("lang") String lang
            , @PathVariable("category") String category, @PathVariable("firm") String firm,
                              @PathVariable("color") String color, @PathVariable("id") int id) {

        Good good = goodService.getGoodById(id);
        model.addAttribute("lang", LocaleContextHolder.getLocale().getLanguage());
        model.addAttribute("info", good);
        setPageData(model);
        String url2 = "";
        String altUrl = "";
        String description = "";
        boolean flag = false;
        String title = "";
        if (request.getParameter("lang") != null)
            if (request.getParameter("lang").equals(UK_LANG)) {
                lang = UK_LANG;
                flag = true;
            } else if (request.getParameter("lang").equals(RU_LANG)) {
                lang = RU_LANG;
                flag = true;
            }

        if (lang.equals(UK_LANG)) {
            try {

                altUrl = "good/" + URLEncoder.encode(good.getCategory().getRuText().replaceAll(" ", "-"), "UTF-8") + "-" + good.getFirm().replaceAll(" ", "-") + "-" + URLEncoder.encode(good.getColor().getRuText(), "UTF-8") + "/" + good.getId();
                description = good.getCategory().getUaText() + " фірми " + good.getFirm() + " придбати за низькою ціною з доставкою. " + good.getColor().getUaText() + " колір, розмір " + good.getSize();
                if (flag)
                    url2 = "good/" + URLEncoder.encode(good.getCategory().getUaText().replaceAll(" ", "-"), "UTF-8") + "-" + good.getFirm().replaceAll(" ", "-") + "-" + URLEncoder.encode(good.getColor().getUaText(), "UTF-8") + "/" + good.getId();
                else url2 = "goodInfo";
                title = "Купити " + good.getCategory().getUaText() + " " + good.getFirm() + " " + good.getColor().getUaText() + " у інтернет-магазині джентльмен.in.ua";

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


        } else if (lang.equals(RU_LANG)) {

            try {
                altUrl = "good/" + URLEncoder.encode(good.getCategory().getUaText().replaceAll(" ", "-"), "UTF-8") + "-" + good.getFirm().replaceAll(" ", "-") + "-" + URLEncoder.encode(good.getColor().getUaText(), "UTF-8") + "/" + good.getId();
                description = good.getCategory().getRuText() + " фирмы " + URLEncoder.encode(good.getFirm(), "UTF-8") + " купить по низкой цене. " + good.getColor().getRuText() + " цвет, размер " + URLEncoder.encode(good.getSize(), "UTF-8");
                if (flag)
                    url2 = "good/" + URLEncoder.encode(good.getCategory().getRuText().replaceAll(" ", "-"), "UTF-8") + "-" + good.getFirm().replaceAll(" ", "-") + "-" + URLEncoder.encode(good.getColor().getRuText(), "UTF-8") + "/" + good.getId();
                else url2 = "goodInfo";
                title = "Купить " + good.getCategory().getRuText() + " " + good.getFirm() + " " + good.getColor().getRuText() + " в интернет-магазине джентльмен.in.ua";

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("description", description);
        model.addAttribute("title", title);

        return redirectWithLang(request, url2, model, altUrl); //call function for redirect
    }

    @RequestMapping(value = "/admin/goodInfo")
    public String getInfo(ModelMap model, HttpServletRequest request) {
        int pid = Integer.parseInt(request.getParameter("id"));
        Good good = goodService.getGoodById(pid);

        model.addAttribute("info", good);
        setPageData(model);
        return "goodInfo";
    }

    @RequestMapping(value = "/admin/goodById")
    public String getGoodById(ModelMap model, HttpServletRequest request) {
        int pid = Integer.parseInt(request.getParameter("id"));
        Good good = goodService.getGoodById(pid);
        setPageData(model);
        model.addAttribute(good);
        return "goodform";
    }

    @RequestMapping(value = "/admin/updateGood", method = RequestMethod.POST)
    public String updateGood(@ModelAttribute("good") @Valid Good good, BindingResult result,
                             ModelMap model, HttpServletRequest request, @RequestParam("file") MultipartFile[] files, @RequestParam("flagImg") String flag) {

        if (!result.hasErrors()) {
            if (flag.equals("1")) {

                for (int i = 0; i < goodService.getGoodById(good.getId()).getCountImg(); i++) {

                    File file = new File(urlWriteImages + good.getId() + "_" + i + ".jpg");
                    file.delete();
                }

                if (files != null && files.length > 0) {
                    for (int i = 0; i < files.length; i++) {
                        try {
                            byte[] bytes = files[i].getBytes();
                            BufferedOutputStream buffStream =
                                    new BufferedOutputStream(new FileOutputStream(new File(urlWriteImages + good.getId() + "_" + i + ".jpg")));
                            buffStream.write(bytes);
                            buffStream.close();
                            System.out.println(urlWriteImages + good.getId() + "_" + i + ".jpg");

                        } catch (Exception e) {

                        }
                    }

                }
            }

            goodService.updateGood(good);
            model.addAttribute(new Good());
            model.addAttribute("msg", getMsg("updated", request));
        } else
            System.out.println(result.getAllErrors().toString());
        setPageData(model);
        return "goodform";
    }

    @RequestMapping(value = "/admin/deleteGood")
    public String deleteGood(ModelMap model, HttpServletRequest request) {
        int pid = Integer.parseInt(request.getParameter("id"));

        for (int i = 0; i < goodService.getGoodById(pid).getCountImg(); i++) {

            File file = new File(urlWriteImages + pid + "_" + i + ".jpg");
            file.delete();
        }

        goodService.deleteGood(pid);

        model.addAttribute(new Good());
        model.addAttribute("msg", getMsg("deleted", request));
        setPageData(model);
        return "goodform";
    }

    private void setPageData(ModelMap model) {
        model.addAttribute("allData", goodService.getAllGoods());
        model.addAttribute("colors", colorService.getAllColor());
        model.addAttribute("categories", categoryService.getSecondLevel());
        model.addAttribute("lang", LocaleContextHolder.getLocale().getLanguage()); //get locale language
        if (LocaleContextHolder.getLocale().getLanguage().equals(UK_LANG))
            model.addAttribute("lang_code", "uaText");
        else if (LocaleContextHolder.getLocale().getLanguage().equals(RU_LANG))
            model.addAttribute("lang_code", "ruText");
    }

    private String getMsg(String key, HttpServletRequest request) {
        return messageSource.getMessage(key, null, localeResolver.resolveLocale(request));
    }


}
