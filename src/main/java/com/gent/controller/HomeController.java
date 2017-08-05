package com.gent.controller;

import com.gent.model.Good;
import com.gent.service.ICategoryService;
import com.gent.service.IColorService;
import com.gent.service.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.gent.config.ChangeLang.redirectWithLang;
import static com.gent.controller.BasketController.getCountBasket;
import static com.gent.util.Constants.RU_LANG;
import static com.gent.util.Constants.UK_LANG;

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
    private IColorService colorService;
    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = {"allImages"}, method = RequestMethod.GET)
    public String showImg (HttpServletRequest request, ModelMap model, HttpServletResponse response) {


        List<Good> list = goodService.getAllGoods();
        model.addAttribute("data", list);
        return "allImages";
    }

    @RequestMapping(value = {"index","/{lang}/index", "/"}, method = RequestMethod.GET)
    public String show (HttpServletRequest request, ModelMap model, HttpServletResponse response){
//
//        List<Good> list =goodService.getRandomGoods();
//        for (Good good : list) {
//            good.setFirm(good.getFirm().replace(' ', '-'));
//            Category cat = good.getCategory();
//            cat.setUaText(cat.getUaText().replace(' ', '-'));
//            cat.setRuText(cat.getRuText().replace(' ', '-'));
//            good.setCategory(cat);
//
//        }
//
//        List<Category> listSec = categoryService.getSecondLevel();
//        for(Category cat: listSec)
//        {
//            cat.setUaText(cat.getUaText().replace(' ', '-'));
//            cat.setRuText(cat.getRuText().replace(' ', '-'));
//        }

        //model.addAttribute("allData", list ); //get 10 random goods
        model.addAttribute("lang", LocaleContextHolder.getLocale().getLanguage()); //get locale language
        model.addAttribute("countInBasket", getCountBasket(request)); //count good in the basket
        StringBuffer ur = request.getRequestURL();

        String altURL = "";
        if (LocaleContextHolder.getLocale().getLanguage().equals(UK_LANG))
            altURL = ur.substring(0, ur.indexOf("/", 10))+"/ru/";
       else if (LocaleContextHolder.getLocale().getLanguage().equals(RU_LANG))
            altURL = ur.substring(0, ur.indexOf("/", 10))+"/uk";

        return redirectWithLang(request, "index", model, altURL); //call function for redirect
    }

    // for all admin-page
    @RequestMapping(value = {"admin**"}, method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("lang", LocaleContextHolder.getLocale().getLanguage()); //get locale language
        model.addObject("title", "Admin page");
        model.addObject("message", "This is protected page - Admin Page!");
        model.setViewName("admin");
        return model;

    }


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;

    }

    @RequestMapping(value="logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value = "/{lang}/about", method = RequestMethod.GET)
    public String aboutPageLang (HttpServletRequest request, ModelMap model) {
        model.addAttribute("lang", LocaleContextHolder.getLocale().getLanguage());
        return redirectWithLang(request, "about", model, "about");
    }
    @RequestMapping(value = "/{lang}/comments", method = RequestMethod.GET)
    public String aboutComments (HttpServletRequest request, ModelMap model) {
        model.addAttribute("lang", LocaleContextHolder.getLocale().getLanguage());

        return redirectWithLang(request, "comments", model, "comments");
    }
}
