package com.gent.controller;

import com.gent.model.Category;
import com.gent.model.Color;
import com.gent.service.ICategoryService;
import com.gent.service.IColorService;
import com.gent.service.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * Created by daria on 22.10.2016.
 */
@Controller
@ComponentScan("com.gent")
public class CatalogueController {

    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IColorService colorService;
    @Autowired
    private IGoodService goodService;

    @RequestMapping(value="catalogue")
    public String gullCatalogue(ModelMap model, HttpServletRequest request) {
         setPageData(model);
        model.addAttribute("allData", goodService.getAllGoods());
        return "catalogue";
    }

    @RequestMapping(value="category")
    public String getCategoryById(ModelMap model, HttpServletRequest request) {
        int catId = Integer.parseInt(request.getParameter("id"));

        model.addAttribute("allData", goodService.getGoodsByCategorie(catId));
       setPageData(model);
        return "catalogue";
    }

    private void setPageData(ModelMap model) {

        Locale locale = LocaleContextHolder.getLocale();
        if(locale.getLanguage().equals("uk"))
            model.addAttribute("lang_code", "uaText");
        else
        if(locale.getLanguage().equals( "ru"))
            model.addAttribute("lang_code", "ruText");

        model.addAttribute("colors", colorService.getAllColor());
        model.addAttribute("categories", categoryService.getAllCategory());
    }
}
