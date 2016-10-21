package com.gent.controller;


import com.gent.model.Color;
import com.gent.model.Good;
import com.gent.model.Orders;
import com.gent.service.IGoodService;
import com.gent.service.IOrdersService;
import com.gent.service.IOrdersService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by daria on 15.10.2016.
 */
@Controller
@ComponentScan("com.gent")
public class OrderController {

    @Autowired
    IOrdersService orderService;


    @Autowired
    IGoodService goodService;

    @RequestMapping(value="addToBasket", method = RequestMethod.POST)
    public void addToBasket(ModelMap model, HttpServletResponse response,HttpServletRequest request, @RequestParam("goodId") String goodId) throws UnsupportedEncodingException {

//        String referer = request.getHeader("Referer");
//        return "redirect:"+ referer;

    }

    @RequestMapping(value="newOrder", method = RequestMethod.POST)
    public String addColor( @ModelAttribute("order") @Valid Orders newOrder, BindingResult result, ModelMap model, HttpServletRequest request) {
        if(!result.hasErrors()) {
            Cookie[] cookies = request.getCookies();
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
                int count = 0;
                List<Integer> listBasket = new ArrayList<Integer>();
                if (cook != null && cook.length() > 0) {
                    for (char ch : cook.toCharArray()) {
                        if (Character.isDigit(ch))
                            listBasket.add(Character.getNumericValue(ch));
                    }
                    List<Good> outList = goodService.getListGoods(listBasket);

                    System.out.println(outList.toString());
                    newOrder.setListGood(outList);

                    newOrder.setStatus(0);
                    newOrder.setDate(LocalDateTime.now());
                    System.out.println(newOrder.toString());

                    orderService.addOrders(newOrder);
                }

            }

        }

        return "redirect:orders";
    }

    @RequestMapping(value="orders")
    public String good(ModelMap model, HttpServletResponse response){

        List<Orders> listOrders = orderService.getAllOrders();


        model.addAttribute("ordersData",listOrders );
        Locale locale = LocaleContextHolder.getLocale();
        if(locale.getLanguage().equals("uk"))
            model.addAttribute("lang_code", "uaText");
        else
        if(locale.getLanguage().equals( "ru"))
            model.addAttribute("lang_code", "ruText");

        Cookie cookie = new Cookie("backetGentl", "");
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);

        for(Orders orders: listOrders)
            for(Good good: orders.getListGood())
            {
                goodService.changeStatus(good.getId(), 0);
            }

        return "orders";

    }


}