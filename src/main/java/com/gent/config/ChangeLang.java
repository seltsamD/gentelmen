package com.gent.config;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by daria on 22.11.2016.
 */
public class ChangeLang {

    public static String redirectWithLang(HttpServletRequest request, String url){
        //if we have get-parametr lang then change URL
        if(request.getParameter("lang") != null)
        {

            String url2 = null;
            StringBuffer ur = request.getRequestURL();

            if(request.getParameter("lang").equals("uk"))
                url2 = ur.substring(0, ur.indexOf("/", 10))+"/uk/"+url;
            else
            if(request.getParameter("lang").equals("ru"))
                url2 = ur.substring(0, ur.indexOf("/", 10))+"/ru/"+url;

            return "redirect:"+url2;
        }

        return url;
    }


}
