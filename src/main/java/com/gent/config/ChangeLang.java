package com.gent.config;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by daria on 22.11.2016.
 */
public class ChangeLang {

    public static String redirectWithLang(HttpServletRequest request, String url, ModelMap model, String altURL){
        String url2 = null;
        StringBuffer ur = request.getRequestURL();


        if(LocaleContextHolder.getLocale().getLanguage().equals("uk")){
            model.addAttribute("alternativeHref", ur.substring(0, ur.indexOf("/", 10))+"/ru/"+altURL);
            model.addAttribute("alternativeLang", "ru");
        }
        else
        if(LocaleContextHolder.getLocale().getLanguage().equals("ru")){
            model.addAttribute("alternativeHref", ur.substring(0, ur.indexOf("/", 10))+"/uk/"+altURL);
            model.addAttribute("alternativeLang", "uk");
        }
        //if we have get-parametr lang then change URL
        if(request.getParameter("lang") != null)
        {
            if(request.getParameter("lang").equals("uk"))
               url2 = ur.substring(0, ur.indexOf("/", 10))+"/uk/"+url;
            else
            if(request.getParameter("lang").equals("ru")){
                url2 = ur.substring(0, ur.indexOf("/", 10))+"/ru/"+url;
            }


            return "redirect:"+url2;
        }


        return url;
    }


}
