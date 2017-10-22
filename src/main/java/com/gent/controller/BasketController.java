package com.gent.controller;

import com.gent.dto.GoodDTOExtend;
import com.gent.model.Orders;
import com.gent.service.IGoodService;
import com.gent.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.gent.config.ChangeLang.redirectWithLang;

/**
 * Created by daria on 19.10.2016.
 */
@Controller
@ComponentScan("com.gent")
public class BasketController {
    @Autowired
    IOrdersService orderService;
    @Autowired
    IGoodService goodService;

    boolean isNumber(String inline) {
        try {
            Integer.parseInt(inline);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @RequestMapping(value = "order/api/getCountBasket", params = {"goodId"})
    public
    @ResponseBody
    String writeCookie(@CookieValue(value = "cart", required = false) Cookie myCookie,
                       @RequestParam(value = "goodId") String goodId, HttpServletRequest request,
                       HttpServletResponse response) {

        List<String> listValue = new ArrayList<String>();
        if (myCookie != null && myCookie.getValue().length() > 0) {
            listValue = new ArrayList<String>(Arrays.asList(myCookie.getValue().split("-")));
        }

        if (listValue.size() <= 0)
            listValue.add(goodId);
        else {
            boolean flag = false;

            for (String value : listValue)
                if (value.equals(goodId))
                    flag = true;

            if (!flag)
                listValue.add(goodId);
        }
        Cookie cookie = null;
        try {
            cookie = new Cookie("cart", URLEncoder.encode(String.join("-", listValue), "utf8"));
            cookie.setMaxAge(365 * 24 * 60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return String.valueOf(listValue.size());


    }

    @RequestMapping(value = "order/api/getBasket")
    public
    @ResponseBody
    String getCookie(@CookieValue(value = "cart", required = false) Cookie myCookie, HttpServletRequest request, HttpServletResponse response) {
        if (myCookie != null && myCookie.getValue().length() > 0)
            return String.valueOf(StringUtils.countOccurrencesOf(myCookie.getValue(), "-") + 1);
        else return "0";


    }


    static String getCountBasket(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Cookie myCookie = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    myCookie = cookie;
                    break;
                }

            }


            if (myCookie != null && myCookie.getValue().length() > 0)
                return String.valueOf(StringUtils.countOccurrencesOf(myCookie.getValue(), "-") + 1);
            else return "0";
        } else return "0";

    }


    @RequestMapping(value = "/{lang}/shopping-cart", method = RequestMethod.GET)
    public String shopCart(@CookieValue(value = "cart", required = false) Cookie myCookie,
                           ModelMap model, HttpServletRequest request, HttpServletResponse response,
                           @PathVariable("lang") String lang) {

        int count = 0;
        if (myCookie != null && myCookie.getValue().length() > 0) {
            List<String> listValue = new ArrayList<String>();
            listValue = new ArrayList<String>(Arrays.asList(myCookie.getValue().split("-")));
            if (listValue.size() > 0) {
                List<GoodDTOExtend> outList = null;
                List<Integer> list2 = new ArrayList<Integer>();
                for (String str : listValue)
                    list2.add(Integer.valueOf(str));

                outList = goodService.getListGoodsDTO(list2);
                model.addAttribute("listBasket", outList);
                count = listValue.size();
            } else count = 0;

        } else count = 0;


        model.addAttribute("countInBasket", count);
        model.addAttribute("lang", LocaleContextHolder.getLocale().getLanguage());

        model.addAttribute("order", new Orders());
        return redirectWithLang(request, "shoppingCart", model, "shoppingCart");

    }

    @RequestMapping(value = "/{lang}/shopping-cart/deleteFromBasket")
    public String deleteFrom(@CookieValue(value = "cart", required = false) Cookie myCookie,
                             ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        int count = 0;

        if (myCookie != null && myCookie.getValue().length() > 0) {
            List<String> listValue = new ArrayList<String>();
            listValue = new ArrayList<String>(Arrays.asList(myCookie.getValue().split("-")));

            listValue.remove(request.getParameter("id"));

            Cookie cookie = null;
            try {
                cookie = new Cookie("cart", URLEncoder.encode(String.join("-", listValue), "utf8"));
                cookie.setMaxAge(365 * 24 * 60 * 60);
                cookie.setPath("/");
                response.addCookie(cookie);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (listValue.size() > 0) {
                List<GoodDTOExtend> outList = null;
                List<Integer> list2 = new ArrayList<Integer>();
                for (String str : listValue)
                    list2.add(Integer.valueOf(str));

                outList = goodService.getListGoodsDTO(list2);
                model.addAttribute("listBasket", outList);
                count = listValue.size();
            } else count = 0;


        } else count = 0;


        model.addAttribute("countInBasket", count);
        model.addAttribute("lang", LocaleContextHolder.getLocale().getLanguage());

        model.addAttribute("order", new Orders());
        return redirectWithLang(request, "shoppingCart", model, "shoppingCart");
    }
}
