package com.gent.config;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;

import static com.gent.util.Constants.RU_LANG;
import static com.gent.util.Constants.UK_LANG;

/**
 * Created by daria on 22.11.2016.
 */
public class ChangeLang {

    public static String redirectWithLang(HttpServletRequest request, String url, ModelMap model, String altURL) {
        String url2 = null;
        StringBuffer ur = request.getRequestURL();

        model.addAttribute("href", ur);

        if (LocaleContextHolder.getLocale().getLanguage().equals(UK_LANG)) {
            model.addAttribute("alternativeHref", ur.substring(0, ur.indexOf("/", 10)) + "/ru/" + altURL);
            model.addAttribute("alternativeLang", RU_LANG);
        } else if (LocaleContextHolder.getLocale().getLanguage().equals(RU_LANG)) {
            model.addAttribute("alternativeHref", ur.substring(0, ur.indexOf("/", 10)) + "/uk/" + altURL);
            model.addAttribute("alternativeLang", UK_LANG);
        }
        //if we have  lang-parametr then change URL
        if (request.getParameter("lang") != null) {
            if (request.getParameter("lang").equals(UK_LANG))
                url2 = ur.substring(0, ur.indexOf("/", 10)) + "/uk/" + url;
            else if (request.getParameter("lang").equals(RU_LANG)) {
                url2 = ur.substring(0, ur.indexOf("/", 10)) + "/ru/" + url;
            }
            return "redirect:" + url2;
        }
        return url;
    }


}
