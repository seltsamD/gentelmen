package com.gent.controller;

import com.gent.model.Category;
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

    public <T> List<T> union(List<T> list1, List<T> list2) {
        Set<T> set = new HashSet<T>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<T>(set);
    }

    @RequestMapping(value="/{lang}/каталог" , method = RequestMethod.GET )
    public String gullCatalogue(ModelMap model, HttpServletRequest request,
                                @PathVariable("lang") String lang) throws UnsupportedEncodingException {
        List<Good> listGood = goodService.getAllGoods();
        for (Good good : listGood) {
            good.setFirm(good.getFirm().replace(' ', '-'));
            Category cat = good.getCategory();
            cat.setUaText(cat.getUaText().replace(' ', '-'));
            cat.setRuText(cat.getRuText().replace(' ', '-'));
            good.setCategory(cat);

        }

        setPageData(model);

        int page = 0;
        if(request.getParameter("page") != null)
            page = Integer.valueOf(request.getParameter("page"));


        boolean cat = false;
        boolean color = false;
        boolean price = false;
        boolean siz = false;

        String cat_id =  request.getParameter("cat_id");
        String color_id =  request.getParameter("color_id");
        String price1 = request.getParameter("price1");
        String price2 = request.getParameter("price2");
        String size = request.getParameter("size");


        Map<String, String[]> parameters = request.getParameterMap();

        for (String key : parameters.keySet())
        {
            String[] value = parameters.get(key);

            if (value != null && value.length > 0) {
                if (key.equals("color_id")) {
                    String[] value1 = parameters.get("color_id");
                    for (String aValue : value1) {
                        if (!color) {
                            List<Good> listByColor = goodService.getGoodsByColor(Integer.valueOf(aValue));
                            listGood.retainAll(listByColor);
                            color = true;
                        } else {
                            List<Good> listByColor = goodService.getGoodsByColor(Integer.valueOf(aValue));
                            listGood.addAll(listByColor);
                        }

                    }


                }
                else
                if (key.equals("cat_id")) {
                    String[] value1 = parameters.get("cat_id");
                    for (String aValue : value1) {
                        if (!cat) {
                            List<Good> listByCat = goodService.getGoodsByCategorie(Integer.valueOf(aValue));
                            listGood.retainAll(listByCat);
                            cat = true;
                        } else {
                            List<Good> listByCat = goodService.getGoodsByCategorie(Integer.valueOf(aValue));
                            listGood.addAll(listByCat);
                        }

                    }
                }
                else
                if (key.equals("size")) {
                    String[] value1 = parameters.get("cat_id");
                    for (String aValue : value1)
                        if(!cat) {
                            List<Good> listBySize = goodService.getGoodsBySize(aValue);
                            listGood.retainAll(listBySize);
                            siz = true;
                        }
                        else {
                            List<Good> listBySize = goodService.getGoodsBySize(aValue);
                            listGood.addAll(listBySize);
                        }
                }

            }
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
        StringBuffer ur = request.getRequestURL();
        String altURL = "";
        String description = "";
        if (lang.equals("uk"))
        {
            model.addAttribute("title", "Купити одяг та аксесуари, краватки та костюми у інтернет-магазині джентльмен.in.ua");
            altURL =  "каталог";
            description = "Великий вибір одягу, аксесуарів та костюмів. Швидка доставка та низькі ціни у нашому інтернет-магазині.";
        }
        else
        if (lang.equals("ru"))
        {
            model.addAttribute("title", "Купить одежду и аксессуары, галстуки и пиджаки в интернет-магазине джентльмен.in.ua");
            altURL =  "каталог";
            description = "Большой выбор одежды, аксессуаров и костюмов. Быстрая доставка и низкие цены в нашем интернет-магазине.";
        }


        model.addAttribute("description", description);
        if(request.getParameter("lang") != null)
            return redirectWithLang(request, URLEncoder.encode("каталог", "UTF-8"), model, altURL);
        return redirectWithLang(request, "catalogue", model, altURL);
    }

    @RequestMapping(value="/{lang}/каталог/{category}/{id}")
    public String getCategoryById(ModelMap model, HttpServletRequest request,
                                  @PathVariable("lang") String lang,
                                  @PathVariable("id") Integer catId,
                                  @PathVariable("category") String category) throws UnsupportedEncodingException {
        String altURL = "";
        List<Good> listGood = null;
        String description = "";
        category =  category.replaceAll("-", " ");

        if(request.getParameter("lang") != null) {
          setPageData(model);

            String url2 = "";

            String cat ="";
            if (request.getParameter("lang").equals("uk")){

                if(catId != 0)
                {
                    listGood = goodService.getGoodsByCategorie(catId);
                    cat = URLEncoder.encode(categoryService.getCategoryById(catId).getUaText().replaceAll(" ", "-"), "UTF-8");
                    url2 = URLEncoder.encode("каталог", "UTF-8")+ "/"+ cat;
                    altURL =  URLEncoder.encode("каталог", "UTF-8")+ "/"+ URLEncoder.encode(categoryService.getCategoryById(catId).getRuText().replaceAll(" ", "-"), "UTF-8");
                    description = "Великий вибір "+categoryService.getCategoryById(catId).getUaText()+" відомих брендів за низькими цінами. " +
                            " Кольори та фасони на будь-який смак та розмір.";
                }

            }
            else if (request.getParameter("lang").equals("ru")) {


                    if (catId != 0) {
                        listGood = goodService.getGoodsByCategorie(catId);
                    cat = URLEncoder.encode(categoryService.getCategoryById(catId).getRuText().replaceAll(" ", "-"), "UTF-8");
                    url2 = URLEncoder.encode("каталог", "UTF-8") + "/" + cat;
                    altURL =  URLEncoder.encode("каталог", "UTF-8") + "/" + URLEncoder.encode(categoryService.getCategoryById(catId).getUaText().replaceAll(" ", "-"), "UTF-8");
                        description = "Большой выбор "+categoryService.getCategoryById(catId).getRuText()+" известных брендов по низким ценам. " +
                                " Цвета и фасоны на любой вкус и размер.";
                    }


            }

        }
        else {


            if (catId != 0) {
                listGood = goodService.getGoodsByCategorie(catId);
                model.addAttribute("count", listGood.size());
                model.addAttribute("allData", listGood);
                setPageData(model);

                if (lang.equals("uk")) {
                    altURL = URLEncoder.encode("каталог", "UTF-8") + "/" + URLEncoder.encode(categoryService.getCategoryById(catId).getRuText().replaceAll(" ", "-"), "UTF-8");
                    description = "Великий вибір "+categoryService.getCategoryById(catId).getUaText()+" відомих брендів за низькими цінами. " +
                            " Кольори та фасони на будь-який смак та розмір.";
                }
                 else if (lang.equals("ru")){
                    altURL =  URLEncoder.encode("каталог", "UTF-8") + "/" + URLEncoder.encode(categoryService.getCategoryById(catId).getUaText().replaceAll(" ", "-"), "UTF-8");
                    description = "Большой выбор "+categoryService.getCategoryById(catId).getRuText()+" известных брендов по низким ценам. " +
                            " Цвета и фасоны на любой вкус и размер.";
                }


            }

        }

        if (catId != 0) {
            if (lang.equals("uk"))
                model.addAttribute("title", "Купити " + categoryService.getCategoryById(catId).getUaText() + " у інтернет-магазині джентльмен.in.ua");
            else if (lang.equals("ru"))
                model.addAttribute("title", "Купить " + categoryService.getCategoryById(catId).getRuText() + " в интернет-магазине джентльмен.in.ua");
        }
        model.addAttribute("description", description);

if(listGood.size() > 0){
    ArrayList<String> listSize = new ArrayList<String>();
    listSize.add(listGood.get(0).getSize());

    for(Good item: listGood)
        if(!listSize.contains(item.getSize()))
            listSize.add(item.getSize());

    model.addAttribute("listSize", listSize);
    model.addAttribute("countSize", listSize.size());
}

        for (Good good : listGood) {
            good.setFirm(good.getFirm().replace(' ', '-'));
            Category cat = good.getCategory();
            cat.setUaText(cat.getUaText().replace(' ', '-'));
            cat.setRuText(cat.getRuText().replace(' ', '-'));
            good.setCategory(cat);
        }


        boolean cat = false;
        boolean color = false;
        boolean price = false;
        boolean siz = false;

        String cat_id =  request.getParameter("cat_id");
        String color_id =  request.getParameter("color_id");
        String price1 = request.getParameter("price1");
        String price2 = request.getParameter("price2");
        String size = request.getParameter("size");


        Map<String, String[]> parameters = request.getParameterMap();

        for (String key : parameters.keySet())
        {
            String[] value = parameters.get(key);

            if (value != null && value.length > 0) {
                if (key.equals("color_id")) {
                    String[] value1 = parameters.get("color_id");
                    for (String aValue : value1) {
                        if (!color) {
                            List<Good> listByColor = goodService.getGoodsByColor(Integer.valueOf(aValue));
                            listGood.retainAll(listByColor);
                            color = true;
                        } else {
                            List<Good> listByColor = goodService.getGoodsByColor(Integer.valueOf(aValue));
                            listGood.addAll(listByColor);
                        }

                    }


                }
                else
                if (key.equals("cat_id")) {
                    String[] value1 = parameters.get("cat_id");
                    for (String aValue : value1) {
                        if (!cat) {
                            List<Good> listByCat = goodService.getGoodsByCategorie(Integer.valueOf(aValue));
                            listGood.retainAll(listByCat);
                            cat = true;
                        } else {
                            List<Good> listByCat = goodService.getGoodsByCategorie(Integer.valueOf(aValue));
                            listGood.addAll(listByCat);
                        }

                    }
                }
                else
                if (key.equals("size")) {
                    String[] value1 = parameters.get("size");
                    for (String aValue : value1)
                        if(!cat) {
                            List<Good> listBySize = goodService.getGoodsBySize(aValue);

                            for (Good item: listBySize) {
                                System.out.println(item.getId()+":  "+item.hashCode());
                            }
                            System.out.println("*********");
                            for (Good item: listGood) {
                                System.out.println(item.getId()+":  "+item.hashCode());
                            }

                            listGood.retainAll(listBySize);
                            siz = true;
                        }
                        else {
                            List<Good> listBySize = goodService.getGoodsBySize(aValue);
                            listGood.addAll(listBySize);
                        }
                }

            }
        }

        if(price1 != null && price2 != null)
        {
            List<Good> listByPrice = goodService.getGoodBetweenPrice(Integer.valueOf(price1),Integer.valueOf(price2));
            listGood.retainAll(listByPrice);
        }


        model.addAttribute("count", listGood.size());
        model.addAttribute("allData", listGood);
        return redirectWithLang(request, "catalogue", model, altURL); //call function for redirect
    }

    @RequestMapping(value={"/{lang}/каталог/цвет/{color}", "/{lang}/каталог/колір/{color}"})
    public String getColorById(ModelMap model, HttpServletRequest request,
                               @PathVariable("lang") String lang,
                               @PathVariable("color") String color) throws UnsupportedEncodingException {
        String altURL = "";
        int colorId = 0;
        List<Good> listGood = null;
        String url2 = "";
        String description = "";
        if(request.getParameter("lang") != null) {
           setPageData(model);
            String cat;
            if (request.getParameter("lang").equals("uk")){
                colorId  = colorService.getColoryByName("ru", color);
                if(colorId != 0)
                {
                    listGood = goodService.getGoodsByColor(colorId);
                    cat = URLEncoder.encode(colorService.getColorById(colorId).getUaText(), "UTF-8");
                    url2 = URLEncoder.encode("каталог", "UTF-8")+ "/"+ URLEncoder.encode("колор", "UTF-8")+ "/"+ cat;
                    altURL = URLEncoder.encode("каталог", "UTF-8")+ "/"+ URLEncoder.encode(colorService.getColorById(colorId).getRuText(), "UTF-8");
                    description = "Придбати одяг та аксесуари "+colorService.getColorById(colorId).getUaText()+" кольору. Великий вибір розмірів та фасоновів.";
                }

            }
            else if (request.getParameter("lang").equals("ru")) {
                if (colorId != 0) {
                    colorId = categoryService.getCategoryByName("uk", color);
                    listGood = goodService.getGoodsByColor(colorId);
                    cat = URLEncoder.encode(colorService.getColorById(colorId).getRuText(), "UTF-8");
                    url2 = URLEncoder.encode("каталог", "UTF-8") + "/" + URLEncoder.encode("цвет", "UTF-8") + "/" + cat;
                    altURL = URLEncoder.encode("каталог", "UTF-8") + "/" + URLEncoder.encode(colorService.getColorById(colorId).getUaText(), "UTF-8");
                    description = "Купить одежду и аксессуары  "+colorService.getColorById(colorId).getRuText()+" цвета. Большой выбор размеров и фасонов.";
                }
            }

        }
        else {
            colorId  = colorService.getColoryByName(lang, color);
            if(colorId != 0) {
                listGood = goodService.getGoodsByColor(colorId);

                setPageData(model);
                if (lang.equals("ru")){
                    String cat = URLEncoder.encode(colorService.getColorById(colorId).getRuText(), "UTF-8");
                    altURL = URLEncoder.encode("каталог", "UTF-8") + "/" + URLEncoder.encode("колір", "UTF-8") + "/" + URLEncoder.encode(colorService.getColorById(colorId).getUaText(), "UTF-8");
                    model.addAttribute("title", "Купить " + colorService.getColorById(colorId).getRuText() + "одежду и аксессуары, галстуки и пиджаки в интернет-магазине джентльмен.in.ua");
                    description = "Купить одежду и аксессуары  "+colorService.getColorById(colorId).getRuText()+" цвета. Большой выбор размеров и фасонов.";
                }
                 else if (lang.equals("uk")){
                    String cat = URLEncoder.encode(colorService.getColorById(colorId).getUaText(), "UTF-8");
                    altURL = URLEncoder.encode("каталог", "UTF-8") + "/" + URLEncoder.encode("цвет", "UTF-8") + "/" + URLEncoder.encode(colorService.getColorById(colorId).getRuText(), "UTF-8");
                    description = "Придбати одяг та аксесуари "+colorService.getColorById(colorId).getUaText()+" кольору. Великий вибір розмірів та фасоновів.";
                    model.addAttribute("title", "Купити " + colorService.getColorById(colorId).getUaText() + "одяг та аксесуари, краватки та костюми у інтернет-магазині джентльмен.in.ua");
                }

             }
            url2="catalogue";
        }


        for (Good good : listGood) {
            good.setFirm(good.getFirm().replace(' ', '-'));
            Category category = good.getCategory();
            category.setUaText(category.getUaText().replace(' ', '-'));
            category.setRuText(category.getRuText().replace(' ', '-'));
            good.setCategory(category);

        }

        String cat_id =  request.getParameter("cat_id");
        String color_id =  request.getParameter("color_id");
        String price1 = request.getParameter("price1");
        String price2 = request.getParameter("price2");
        String size = request.getParameter("size");


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

        if(size != null)
        {
            List<Good> listBySize = goodService.getGoodsBySize(size);
            listGood.retainAll(listBySize);
        }


        model.addAttribute("count", listGood.size());
        model.addAttribute("allData", listGood);
        return redirectWithLang(request, url2, model, altURL); //call function for redirect

    }
    @RequestMapping(value="/{lang}/catalogue/priceRange" , method = RequestMethod.POST)
    public String getBetweenPrice(HttpServletRequest request, ModelMap model,
                                  @PathVariable("lang") String lang,
                                  @RequestParam("amount1") String price1,  @RequestParam("amount2") String price2) {

        List<Good> listGood = goodService.getGoodBetweenPrice(Integer.valueOf(price1),Integer.valueOf(price2));

        for (Good good : listGood) {
            good.setFirm(good.getFirm().replace(' ', '-'));
            Category category = good.getCategory();
            category.setUaText(category.getUaText().replace(' ', '-'));
            category.setRuText(category.getRuText().replace(' ', '-'));
            good.setCategory(category);

        }
        model.addAttribute("count", listGood.size());
        model.addAttribute("allData", listGood);
        if (lang.equals("uk"))
            model.addAttribute("title", "Купити одяг та аксесуари за ціною від "+price1+" до "+price2+" у інтернет-магазині джентльмен.in.ua");
        else
        if (lang.equals("ru"))
            model.addAttribute("title", "Купить одежду и аксессуары, галстуки по цене от "+price1+" до "+price2+" гривен в интернет-магазине джентльмен.in.ua ");

        setPageData(model);

        return redirectWithLang(request, "catalogue", model, "catalogue");
    }

    private void setPageData(ModelMap model) {

        model.addAttribute("lang", LocaleContextHolder.getLocale().getLanguage());
        model.addAttribute("maxPrice", goodService.getMaxPrice());
        model.addAttribute("minPrice", goodService.getMinPrice());

        model.addAttribute("colors", colorService.getAllColor());
        List<Category> listSec = categoryService.getSecondLevel();
        for(Category cat: listSec)
        {
            cat.setUaText(cat.getUaText().replace(' ', '-'));
            cat.setRuText(cat.getRuText().replace(' ', '-'));
        }
        model.addAttribute("secondLevel",listSec);
        model.addAttribute("firstLevel", categoryService.getFirstLevel());
    }
}
