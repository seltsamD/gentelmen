package com.gent.controller;

import com.gent.model.Good;
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
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    @RequestMapping(value = "order/api/getCountBasket", params = {"goodId"})
    public
    @ResponseBody
    String writeCookie(@CookieValue(value = "backetGentl", required = false) Cookie myCookie, @RequestParam(value = "goodId") String cookieName, HttpServletRequest request, HttpServletResponse response) {

        String strCook = null;
        int count = 0;
        if (myCookie != null) {
            String cook = null;
            try {
                cook = URLDecoder.decode(myCookie.getValue(), "UTF-8");
            } catch (UnsupportedEncodingException e) {

                e.printStackTrace();
            }

            if (!cook.contains(request.getParameter("goodId"))) {
                if(cook.length() <= 0)
                    strCook = '['+request.getParameter("goodId") + ']';
                else
                if (cook.length() == 3)
                    strCook = cook.substring(0, 2) + ',' + request.getParameter("goodId") + ']';
                else
                {

                    strCook = cook.substring(0, cook.length()-1) + ',' + request.getParameter("goodId") + ']';
                }


                Cookie cookie = null;
                try {
                    cookie = new Cookie("backetGentl", URLEncoder.encode(strCook, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                cookie.setMaxAge(365 * 24 * 60 * 60);
                cookie.setPath("/");
                response.addCookie(cookie);

            }
            count = StringUtils.countOccurrencesOf(strCook, ",") + 1;
        } else {
            strCook = '[' + request.getParameter("goodId") + ']';

            Cookie cookie = null;
            try {
                cookie = new Cookie("backetGentl", URLEncoder.encode(strCook, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            cookie.setMaxAge(365 * 24 * 60 * 60);
           cookie.setPath("/");
            response.addCookie(cookie);
            count = 1;

        }


        return String.valueOf(count);


    }

    @RequestMapping(value = "order/api/getBasket")
    public
    @ResponseBody
    String getCookie(@CookieValue(value = "backetGentl", required = false) Cookie myCookie, HttpServletRequest request, HttpServletResponse response) {

        int count = 0;
        if (myCookie != null) {
            String cook = null;
            try {
                cook = URLDecoder.decode(myCookie.getValue(), "UTF-8");
            } catch (UnsupportedEncodingException e) {

                e.printStackTrace();
            }


            if (cook != null && cook.length() > 2 && (cook.contains("[")) && (cook.contains("]")))
            {
                count = StringUtils.countOccurrencesOf(cook, ",") + 1;
            } else count = 0;

        } else count = 0;
        return String.valueOf(count);

    }


    static String getCountBasket(HttpServletRequest request) {
        int count = 0;
        Cookie[] cookies = request.getCookies();
        if(cookies != null)
        {
            Cookie myCookie = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("backetGentl")) {
                    myCookie = cookie;
                    break;
                }

            }


            if (myCookie != null) {
                String cook = null;
                try {
                    cook = URLDecoder.decode(myCookie.getValue(), "UTF-8");
                } catch (UnsupportedEncodingException e) {

                    e.printStackTrace();
                }

                if (cook != null && cook.length() > 2 && (cook.contains("[")) && (cook.contains("]")))
                {
                    count = StringUtils.countOccurrencesOf(cook, ",") + 1;
                } else count = 0;

            } else count = 0;

        }

        return String.valueOf(count);

    }


    @RequestMapping(value = "/{lang}/shopping-cart", method = RequestMethod.GET)
    public String shopCart(ModelMap model, HttpServletRequest request, HttpServletResponse response,
                           @PathVariable("lang") String lang) {
        Cookie[] cookies = request.getCookies();
        Cookie myCookie = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("backetGentl")) {
                myCookie = cookie;
                break;
            }

        }
        int count = 0;
        if (myCookie != null) {
            String cook = null;
            try {
                cook = URLDecoder.decode(myCookie.getValue(), "UTF-8");
            } catch (UnsupportedEncodingException e) {

                e.printStackTrace();
            }

            ArrayList<Integer>listBasket = new ArrayList<Integer>();
            if (cook != null && cook.length() > 0) {

                String str = cook.substring(1,cook.length()-1);
                str = str.replaceAll(" ","");
               String []ar = str.split(",");
                for (String ch: ar) {
                    listBasket.add( Integer.parseInt(ch));
                }


                List<Good> outList = goodService.getListGoods(listBasket);

                model.addAttribute("listBasket", outList);
                count = listBasket.size();
            } else count = 0;


        }

        model.addAttribute("countInBasket", count);
        model.addAttribute("lang", LocaleContextHolder.getLocale().getLanguage());

        model.addAttribute("order", new Orders());
        return "shoppingCart";

    }

    @RequestMapping(value = "deleteFromBasket")
    public String deleteFrom(@CookieValue(value = "backetGentl", required = false) Cookie myCookie, HttpServletRequest request, HttpServletResponse response) {
        int count = 0;
        if (myCookie != null) {
            String cook = null;
            try {
                cook = URLDecoder.decode(myCookie.getValue(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (cook.length() <= 3 )
             {
                 //remove cookie
                Cookie cookie = new Cookie("backetGentl", "");
                cookie.setMaxAge(-1);
                cookie.setPath("/");
                response.addCookie(cookie);
            } else {
                String strCook = null;
                String id = request.getParameter("id");
                ArrayList<Integer> listBasket = new ArrayList<Integer>();
                if (cook != null && cook.length() > 0) {
                    String str = cook.substring(1, cook.length() - 1);
                    str = str.replaceAll(" ", "");
                    String[] ar = str.split(",");
                    for (String ch : ar)
                        listBasket.add(Integer.valueOf(ch));

                    listBasket.remove(listBasket.indexOf(Integer.valueOf(id)));
                    strCook = listBasket.toString();
                }

                Cookie cookie = null;
                if (listBasket.size() > 0) {
                    try {
                        cookie = new Cookie("backetGentl", URLEncoder.encode(strCook, "UTF-8"));
                        cookie.setMaxAge(365 * 24 * 60 * 60);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    cookie = new Cookie("backetGentl", "");
                    cookie.setMaxAge(-1);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
        return "shopping-cart";
    }
}
