package com.gent.controller;

import com.gent.dto.GoodDTOExtend;
import com.gent.model.Good;
import com.gent.service.ICategoryService;
import com.gent.service.IColorService;
import com.gent.service.IGoodService;
import com.gent.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.gent.config.AppConfiguration.urlWriteImages;
import static com.gent.config.ChangeLang.redirectWithLang;
import static com.gent.util.Constants.*;

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
                          ModelMap model, HttpServletRequest request, @RequestParam("file") MultipartFile[] files) throws NotFoundException {
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
public String getInfoGood(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable String lang
        , @PathVariable("category") String category, @PathVariable("firm") String firm,
                          @PathVariable("color") String color, @PathVariable("id") int id) throws NotFoundException {

    Good good = goodService.getGoodById(id);
    LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
    localeResolver.setLocale(request, response, StringUtils.parseLocaleString(lang));
    model.addAttribute("lang", lang);
    model.addAttribute("info", GoodDTOExtend.convertToDTO(good));
    StringBuilder url2 = new StringBuilder();
    StringBuilder altUrl = new StringBuilder();
    StringBuilder description = new StringBuilder();
    boolean flag = false;
    StringBuilder title = new StringBuilder();
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

            altUrl.append(GOOD).append(SLASH).append(URLEncoder.encode(good.getCategory().getRuText().replaceAll(" ", "-"), "UTF-8"))
                    .append(DASH).append(good.getFirm().replaceAll(" ", "-")).append(DASH)
                    .append(URLEncoder.encode(good.getColor().getRuText(), "UTF-8")).append(SLASH).append(good.getId());

            description.append(good.getCategory().getUaText()).append(" фірми ").append(good.getFirm())
                    .append(" придбати за низькою ціною з доставкою. ").append(good.getColor().getUaText())
                    .append(" колір, розмір ")
                    .append(good.getSize());
            if (flag)
                url2.append(GOOD).append(SLASH).append(URLEncoder.encode(good.getCategory().getUaText().replaceAll(" ", "-"), "UTF-8"))
                        .append(DASH).append(good.getFirm().replaceAll(" ", "-")).append(DASH)
                        .append(URLEncoder.encode(good.getColor().getUaText(), "UTF-8")).append(SLASH).append(good.getId());
            else url2.append("goodInfo");
            title.append("Купити ").append(good.getCategory().getUaText()).append(" ").append(good.getFirm())
                    .append(" колір у інтернет-магазині джентльмен.in.ua");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    } else if (lang.equals(RU_LANG)) {

        try {
            altUrl.append(GOOD).append(SLASH).append(URLEncoder.encode(good.getCategory().getUaText().replaceAll(" ", "-"), "UTF-8"))
                        .append(DASH).append(good.getFirm().replaceAll(" ", "-")).append(DASH)
                        .append(URLEncoder.encode(good.getColor().getUaText(), "UTF-8")).append(SLASH).append(good.getId());

            description.append(good.getCategory().getRuText()).append(" фирмы ").append(good.getFirm())
                    .append(" купить по низкой цене. ").append(good.getColor().getUaText())
                    .append(" цвет, размер ")
                    .append(good.getSize());

            if (flag)
                url2.append(GOOD).append(SLASH).append(URLEncoder.encode(good.getCategory().getRuText().replaceAll(" ", "-"), "UTF-8"))
                        .append(DASH).append(good.getFirm().replaceAll(" ", "-")).append(DASH)
                        .append(URLEncoder.encode(good.getColor().getRuText(), "UTF-8")).append(SLASH).append(good.getId());
            else url2.append("goodInfo");
            title.append("Купить ").append(good.getCategory().getUaText()).append(" ").append(good.getFirm())
                    .append(" цвет в интернет-магазине джентльмен.in.ua");


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        }
    model.addAttribute("description", description);
    model.addAttribute("title", title);
    return redirectWithLang(request, url2.toString(), model, altUrl.toString()); //call function for redirect
}

    @RequestMapping(value = "/admin/goodInfo")
    public String getInfo(ModelMap model, HttpServletRequest request) throws NotFoundException {
        int pid = Integer.parseInt(request.getParameter("id"));
        Good good = goodService.getGoodById(pid);

        model.addAttribute("info", good);
        setPageData(model);
        return "goodInfo";
    }

    @RequestMapping(value = "/admin/goodById")
    public String getGoodById(ModelMap model, HttpServletRequest request) throws NotFoundException {
        int pid = Integer.parseInt(request.getParameter("id"));
        Good good = goodService.getGoodById(pid);
        setPageData(model);
        model.addAttribute(good);
        return "goodform";
    }

    @RequestMapping(value = "/admin/updateGood", method = RequestMethod.POST)
    public String updateGood(@ModelAttribute("good") @Valid Good good, BindingResult result,
                             ModelMap model, HttpServletRequest request, @RequestParam("file") MultipartFile[] files, @RequestParam("flagImg") String flag) throws NotFoundException {

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
    public String deleteGood(ModelMap model, HttpServletRequest request) throws NotFoundException {
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
        model.addAttribute("categories", categoryService.getCategoryTree());
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
