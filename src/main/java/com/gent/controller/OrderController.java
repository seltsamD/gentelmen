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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

import static com.gent.config.ChangeLang.redirectWithLang;
import static com.gent.util.Constants.RU_LANG;
import static com.gent.util.Constants.UK_LANG;

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

    @RequestMapping(value = "addToBasket", method = RequestMethod.POST)
    public void addToBasket(ModelMap model, HttpServletResponse response, HttpServletRequest request,
                            @RequestParam("goodId") String goodId) throws UnsupportedEncodingException {


    }

    @RequestMapping(value = "/{lang}/shopping-cart/newOrder", method = RequestMethod.POST)
    public String addColor(@ModelAttribute("order") @Valid Orders newOrder, BindingResult result,
                           @CookieValue(value = "cart", required = false) Cookie myCookie,
                           ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        if (!result.hasErrors()) {

            if (myCookie != null && myCookie.getValue().length() > 0) {
                List<String> listValue = new ArrayList<String>();
                listValue = new ArrayList<String>(Arrays.asList(myCookie.getValue().split("-")));

                if (listValue.size() > 0) {
                    List<Good> outList = null;
                    List<Integer> list2 = new ArrayList<Integer>();
                    for (String str : listValue)
                        list2.add(Integer.valueOf(str));

                    outList = goodService.getListGoods(list2);
                    newOrder.setListGood(outList);

                    newOrder.setStatus(0);
                    newOrder.setDate(new Date());

                    orderService.addOrders(newOrder);
                    for (Good good : outList) {
                        goodService.changeStatus(good.getId(), 0);

                    }

                }


                Cookie cookie = new Cookie("cart", "");
                cookie.setMaxAge(-1);
                cookie.setPath("/");
                response.addCookie(cookie);
            }

            if (LocaleContextHolder.getLocale().getLanguage().equals(UK_LANG))
                model.addAttribute("msg", "Замовлення успішно оформлено!");
            else if (LocaleContextHolder.getLocale().getLanguage().equals(RU_LANG))
                model.addAttribute("msg", "Заказ успешно оформлен!");

        }


        model.addAttribute("lang", LocaleContextHolder.getLocale().getLanguage());
        return "myorders";
    }

    @RequestMapping(value = "{lang}/admin/orders")
    public String good(ModelMap model, HttpServletResponse response) {

        List<Orders> listOrders = orderService.getAllOrders();


        model.addAttribute("ordersData", listOrders);
        Locale locale = LocaleContextHolder.getLocale();
        if (locale.getLanguage().equals(UK_LANG))
            model.addAttribute("lang_code", "uaText");
        else if (locale.getLanguage().equals(RU_LANG))
            model.addAttribute("lang_code", "ruText");

        Cookie cookie = new Cookie("cart", "");
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);

        for (Orders orders : listOrders)
            for (Good good : orders.getListGood()) {
                goodService.changeStatus(good.getId(), 0);
            }

        return "orders";

    }

    @RequestMapping(value = "/{lang}/myorders", method = RequestMethod.GET)
    public String order(ModelMap model, HttpServletRequest request, @PathVariable("lang") String lang) {
        model.addAttribute("lang", lang);

        return redirectWithLang(request, "myorders", model, "myorders");

    }

    @RequestMapping(value = "/{lang}/getByPhone", method = RequestMethod.POST)
    public String orderByPhone(ModelMap model, HttpServletRequest request) {
        String phone = request.getParameter("phone");
        List<Orders> listOrders = orderService.getOrdersByPhone(phone);
        model.addAttribute("count", listOrders.size());
        model.addAttribute("ordersData", listOrders);
        Locale locale = LocaleContextHolder.getLocale();
        if (locale.getLanguage().equals(UK_LANG))
            model.addAttribute("lang", UK_LANG);
        else if (locale.getLanguage().equals(RU_LANG))
            model.addAttribute("lang", RU_LANG);

        return redirectWithLang(request, "myorders", model, "myorders");


    }


}
