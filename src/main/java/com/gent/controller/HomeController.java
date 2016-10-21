package com.gent.controller;

import com.gent.model.Good;
import com.gent.service.IGoodService;
import com.gent.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Locale;

import static com.gent.controller.BasketController.getCountBasket;

/**
 * Created by daria on 30.09.2016.
 */
@Controller
@ComponentScan("com.gent")
@RequestMapping("/")
public class HomeController {
    @Autowired
    private IGoodService goodService;
    @Autowired
    private IOrdersService orderService;
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String show (HttpServletRequest request, ModelMap model){
        Locale locale = LocaleContextHolder.getLocale();
        if(locale.getLanguage() == "uk")
            model.addAttribute("lang_code", "uaText");
        else
        if(locale.getLanguage() == "ru")
            model.addAttribute("lang_code", "ruText");
        model.addAttribute("allData", goodService.getRandomGoods());

        model.addAttribute("countInBasket", getCountBasket(request));

        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model){
        Locale locale = LocaleContextHolder.getLocale();
        if(locale.getLanguage() == "uk")
            model.addAttribute("lang_code", "uaText");
        else
        if(locale.getLanguage() == "ru")
            model.addAttribute("lang_code", "ruText");
        model.addAttribute("allData", goodService.getRandomGoods());

        model.addAttribute("countInBasket", getCountBasket(request));

        return "index";
    }



}