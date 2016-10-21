package com.gent.controller;


import com.gent.service.ICategoryService;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;

/**
 * Created by daria on 05.10.2016.
 */
@Controller
@ComponentScan("com.gent")
public class TextController {
//    @Autowired
//    private ICategoryService uaTextService;
//
//    @Autowired
//    private IRuTextService ruTextService;
//    @Autowired
//    private MessageSource messageSource;
//    @Autowired
//    private LocaleResolver localeResolver;
//
//
//    @RequestMapping(value="textpage")
//    public String textpage(ModelMap model, HttpServletRequest request){
//        setPageData(model);
//        return "textpage";
//    }
//
//    @RequestMapping(value="addText")
//    public String addText(ModelMap model,  HttpServletRequest request) {
//
//
//            UaText uaText = new UaText();
//            Integer type = Integer.valueOf(request.getParameter("type"));
//
//            uaText.setType(type);
//            uaText.setTitle(request.getParameter("utext"));
//            uaTextService.addText(uaText);
//
//
//            RuText ruText = new RuText();
//            ruText.setType(type);
//            ruText.setTitle(request.getParameter("rtext"));
//            ruTextService.addText(ruText);
//
//        model.addAttribute("text", new UaText());
//        setPageData(model);
//        return "textpage";
//    }
//    @RequestMapping(value="textyId")
//    public String getGoodById(ModelMap model, HttpServletRequest request) {
//        Integer id = Integer.valueOf(request.getParameter("id"));
//
//        model.addAttribute("text", uaTextService.getTextById(id));
//        model.addAttribute("rtext", uaTextService.getTextById(id));
//
//        setPageData(model);
//        model.addAttribute(good);
//        return "goodform";
//    }
//    @RequestMapping(value="updateText", method = RequestMethod.POST)
//    public String updateText(ModelMap model,  HttpServletRequest request) {
//
//        UaText uaText = new UaText();
//        Integer type = Integer.valueOf(request.getParameter("type"));
//
//        uaText.setType(type);
//        uaText.setTitle(request.getParameter("utext"));
//        uaTextService.updateText(uaText);
//
//
//        RuText ruText = new RuText();
//        ruText.setType(type);
//        ruText.setTitle(request.getParameter("rtext"));
//        ruTextService.updateText(ruText);
//
//            model.addAttribute(new UaText());
//            model.addAttribute("msg", getMsg("good.updated", request));
//        setPageData(model);
//
//        return "textpage";
//    }
//    @RequestMapping(value="deleteText")
//    public String deleteText(ModelMap model, HttpServletRequest request) {
//        Integer id = Integer.valueOf(request.getParameter("id"));
//
//        uaTextService.deleteText(id);
//        ruTextService.deleteText(id);
//
//
//        model.addAttribute(new UaText());
//        model.addAttribute("msg", getMsg("good.deleted", request));
//        setPageData(model);
//        return "textpage";
//    }
//
//    private void setPageData(ModelMap model){
//        int count = uaTextService.getCount() - 1;
//        model.addAttribute("uaText",uaTextService.getAllText());
//        model.addAttribute("ruText",ruTextService.getAllText());
//        model.addAttribute("textCount",count);
//        model.addAttribute("text", new UaText());
//    }
//    private String getMsg(String key, HttpServletRequest request) {
//        return messageSource.getMessage(key, null, localeResolver.resolveLocale(request));
//    }
}
