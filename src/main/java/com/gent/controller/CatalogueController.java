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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

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



    @RequestMapping(value="/{lang}/каталог" , method = RequestMethod.GET )
    public String gullCatalogue(ModelMap model, HttpServletRequest request,
                                @PathVariable("lang") String lang) throws UnsupportedEncodingException {
        List<Good> listGood = goodService.getAllGoods();
        setPageData(model);

        int page = 0;
        if(request.getParameter("page") != null)
            page = Integer.valueOf(request.getParameter("page"));

        String cat_id =  request.getParameter("cat_id");
        String color_id =  request.getParameter("color_id");
        String price1 = request.getParameter("price1");
        String price2 = request.getParameter("price2");


        if(cat_id != null) {
            List<Good> listByCat = goodService.getGoodsByCategorie(Integer.valueOf(request.getParameter("cat_id")));
            listGood.retainAll(listByCat);
        }

        if(color_id != null)
        {
            List<Good> listByColor = goodService.getGoodsByColor(Integer.valueOf(request.getParameter("color_id")));
            listGood.retainAll(listByColor);
        }

        if(price1 != null && price2 != null)
        {
            List<Good> listByPrice = goodService.getGoodBetweenPrice(Integer.valueOf(price1),Integer.valueOf(price2));
            listGood.retainAll(listByPrice);
        }

        model.addAttribute("page",page);
        model.addAttribute("count", listGood.size());
        model.addAttribute("allData", listGood);
        model.addAttribute("lang", LocaleContextHolder.getLocale().getLanguage());

        if(request.getParameter("lang") != null)
            return redirectWithLang(request, URLEncoder.encode("каталог", "UTF-8"), model, "каталог");
        return redirectWithLang(request, "catalogue", model, "каталог");
    }

    @RequestMapping(value="/{lang}/каталог/{category}")
    public String getCategoryById(ModelMap model, HttpServletRequest request,
                                  @PathVariable("lang") String lang,
                                  @PathVariable("category") String category) throws UnsupportedEncodingException {
        String altURL = "";
        if(request.getParameter("lang") != null) {

            int catId = 0;
            List<Good> listGood = null;

            setPageData(model);

            String url2 = "";

            String cat ="";
            if (request.getParameter("lang").equals("uk")){
                catId  = categoryService.getCategoryByName("ru", category);
                listGood = goodService.getGoodsByCategorie(catId);
                cat = URLEncoder.encode(categoryService.getCategoryById(catId).getUaText(), "UTF-8");
                url2 = URLEncoder.encode("каталог", "UTF-8")+ "/"+ cat;
                altURL = URLEncoder.encode("каталог", "UTF-8")+ "/"+ URLEncoder.encode(categoryService.getCategoryById(catId).getRuText(), "UTF-8");

            }
            else if (request.getParameter("lang").equals("ru")){
                catId  = categoryService.getCategoryByName("uk", category);
                listGood = goodService.getGoodsByCategorie(catId);
                cat = URLEncoder.encode(categoryService.getCategoryById(catId).getRuText(), "UTF-8");
                url2 = URLEncoder.encode("каталог", "UTF-8")+ "/"+ cat;
                altURL = URLEncoder.encode("каталог", "UTF-8")+ "/"+ URLEncoder.encode(categoryService.getCategoryById(catId).getUaText(), "UTF-8");
            }

            model.addAttribute("count", listGood.size());
            model.addAttribute("allData", listGood);
            return redirectWithLang(request, url2, model, altURL); //call function for redirect
        }
        else {
            int catId = 0;
            catId  = categoryService.getCategoryByName(lang, category);
            List<Good> listGood = goodService.getGoodsByCategorie(catId);
            model.addAttribute("count", listGood.size());
            model.addAttribute("allData", listGood);
            setPageData(model);

            if (lang.equals("uk"))
                altURL = URLEncoder.encode("каталог", "UTF-8")+ "/"+ URLEncoder.encode(categoryService.getCategoryById(catId).getRuText(), "UTF-8");
            else
            if (lang.equals("ru"))
                altURL = URLEncoder.encode("каталог", "UTF-8")+ "/"+ URLEncoder.encode(categoryService.getCategoryById(catId).getUaText(), "UTF-8");

        }
        return redirectWithLang(request, "catalogue", model, altURL); //call function for redirect
    }

    @RequestMapping(value={"/{lang}/каталог/цвет/{color}", "/{lang}/каталог/колір/{color}"})
    public String getColorById(ModelMap model, HttpServletRequest request,
                               @PathVariable("lang") String lang,
                               @PathVariable("color") String color) throws UnsupportedEncodingException {
        String altURL = "";
        if(request.getParameter("lang") != null) {

            int colorId = 0;
            List<Good> listGood = null;

            setPageData(model);

            String url2 = "";

            String cat;
            if (request.getParameter("lang").equals("uk")){
                colorId  = colorService.getColoryByName("ru", color);
                listGood = goodService.getGoodsByColor(colorId);
                cat = URLEncoder.encode(colorService.getColorById(colorId).getUaText(), "UTF-8");
                url2 = URLEncoder.encode("каталог", "UTF-8")+ "/"+ URLEncoder.encode("колор", "UTF-8")+ "/"+ cat;
                altURL = URLEncoder.encode("каталог", "UTF-8")+ "/"+ URLEncoder.encode(colorService.getColorById(colorId).getRuText(), "UTF-8");

            }
            else if (request.getParameter("lang").equals("ru")){
                colorId  = categoryService.getCategoryByName("uk", color);
                listGood = goodService.getGoodsByColor(colorId);
                cat = URLEncoder.encode(colorService.getColorById(colorId).getRuText(), "UTF-8");
                url2 = URLEncoder.encode("каталог", "UTF-8")+ "/"+ URLEncoder.encode("цвет", "UTF-8")+ "/"+ cat;
                altURL = URLEncoder.encode("каталог", "UTF-8")+ "/"+ URLEncoder.encode(colorService.getColorById(colorId).getUaText(), "UTF-8");
            }


            model.addAttribute("count", listGood.size());
            model.addAttribute("allData", listGood);
            return redirectWithLang(request, url2, model, altURL); //call function for redirect
        }
        else {
            int catId = 0;
            catId  = colorService.getColoryByName(lang, color);
            List<Good> listGood = goodService.getGoodsByColor(catId);
            model.addAttribute("count", listGood.size());
            model.addAttribute("allData", listGood);
            setPageData(model);
            if (lang.equals("ru"))
                altURL = URLEncoder.encode("каталог", "UTF-8")+ "/"+  URLEncoder.encode("колір", "UTF-8")+ "/"+ URLEncoder.encode(colorService.getColorById(catId).getUaText(), "UTF-8");
            else
            if (lang.equals("uk"))
                altURL = URLEncoder.encode("каталог", "UTF-8")+ "/"+  URLEncoder.encode("цвет", "UTF-8")+ "/"+ URLEncoder.encode(colorService.getColorById(catId).getRuText(), "UTF-8");

        }



//        if(request.getParameter("lang") != null)
//            return redirectWithLang(request, URLEncoder.encode("каталог/цвет/"+color, "UTF-8"), model,"color");
        return redirectWithLang(request, "catalogue", model, altURL);
    }
    @RequestMapping(value="/{lang}/catalogue/priceRange" , method = RequestMethod.POST)
    public String getBetweenPrice(HttpServletRequest request, ModelMap model,
                                  @RequestParam("amount1") String price1,  @RequestParam("amount2") String price2) {

        List<Good> listGood = goodService.getGoodBetweenPrice(Integer.valueOf(price1),Integer.valueOf(price2));

        model.addAttribute("count", listGood.size());
        model.addAttribute("allData", listGood);
        setPageData(model);

        return redirectWithLang(request, "catalogue", model, "catalogue");
    }

    private void setPageData(ModelMap model) {

        model.addAttribute("lang", LocaleContextHolder.getLocale().getLanguage());
        model.addAttribute("maxPrice", goodService.getMaxPrice());
        model.addAttribute("minPrice", goodService.getMinPrice());

        model.addAttribute("colors", colorService.getAllColor());
        model.addAttribute("categories", categoryService.getSecondLevel());
    }
}
