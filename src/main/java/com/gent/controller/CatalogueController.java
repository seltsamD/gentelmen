package com.gent.controller;

import com.gent.model.Good;
import com.gent.service.ICategoryService;
import com.gent.service.IColorService;
import com.gent.service.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

import static com.gent.config.ChangeLang.redirectWithLang;

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

    @RequestMapping(value="/{lang}/catalogue" , method = RequestMethod.GET )
    public String gullCatalogue(ModelMap model, HttpServletRequest request) {
        List<Good> listGood;

        listGood = goodService.getAllGoods();
        setPageData(model);

        int page = 0;
        if(request.getParameter("page") != null)
            page = Integer.valueOf(request.getParameter("page"));


        model.addAttribute("page",page);
        model.addAttribute("count", listGood.size());
        model.addAttribute("allData", listGood);
        model.addAttribute("lang", LocaleContextHolder.getLocale().getLanguage());


       return redirectWithLang(request, "catalogue");
    }

    @RequestMapping(value="/{lang}/catalogue/category")
    public String getCategoryById(ModelMap model, HttpServletRequest request) {

        int catId = Integer.parseInt(request.getParameter("id"));
        List<Good> listGood = goodService.getGoodsByCategorie(catId);
        model.addAttribute("count", listGood.size());
        model.addAttribute("allData", listGood);
        setPageData(model);

        return redirectWithLang(request, "catalogue");
    }

    @RequestMapping(value="/{lang}/catalogue/color")
    public String getColorById(ModelMap model, HttpServletRequest request) {

        int catId = Integer.parseInt(request.getParameter("id"));
        List<Good> listGood = goodService.getGoodsByColor(catId);
        model.addAttribute("count", listGood.size());
        model.addAttribute("allData", listGood);
        setPageData(model);

        return redirectWithLang(request, "catalogue");
    }
    @RequestMapping(value="/{lang}/catalogue/priceRange" , method = RequestMethod.POST)
    public String getBetweenPrice(HttpServletRequest request, ModelMap model, @RequestParam("amount1") String price1,  @RequestParam("amount2") String price2) {

        List<Good> listGood = goodService.getGoodBetweenPrice(Integer.valueOf(price1),Integer.valueOf(price2));

        model.addAttribute("count", listGood.size());
        model.addAttribute("allData", listGood);
        setPageData(model);

        return redirectWithLang(request, "catalogue");
    }

    private void setPageData(ModelMap model) {

        Locale locale = LocaleContextHolder.getLocale();

        model.addAttribute("lang", locale.getLanguage());
        model.addAttribute("maxPrice", goodService.getMaxPrice());
        model.addAttribute("minPrice", goodService.getMinPrice());

        model.addAttribute("colors", colorService.getAllColor());
        model.addAttribute("categories", categoryService.getSecondLevel());
    }
}
