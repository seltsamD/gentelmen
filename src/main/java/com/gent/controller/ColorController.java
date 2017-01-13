package com.gent.controller;

import com.gent.model.Color;
import com.gent.model.Color;
import com.gent.service.IColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by daria on 08.10.2016.
 */
@Controller
@ComponentScan("com.gent")
public class ColorController {
    @Autowired
    private IColorService colorService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private LocaleResolver localeResolver;

    @RequestMapping(value = "/admin/colors")
    public ModelAndView colors() {
        ModelAndView mv = new ModelAndView("colorPage", "color", new Color());
        setPageData(mv.getModelMap());
        return mv;
    }

    @RequestMapping(value = "/admin/addColor", method = RequestMethod.POST)
    public String addColor(@ModelAttribute("color") @Valid Color color, BindingResult result,
                           ModelMap model, HttpServletRequest request) {
        if (!result.hasErrors()) {
            colorService.addColor(color);
            model.addAttribute(new Color());
        }
        setPageData(model);
        return "colorPage";
    }

    @RequestMapping(value = "/admin/colorById")
    public String getColorById(ModelMap model, HttpServletRequest request) {
        int pid = Integer.parseInt(request.getParameter("id"));
        Color color = colorService.getColorById(pid);
        setPageData(model);
        model.addAttribute(color);
        return "colorPage";
    }

    @RequestMapping(value = "/admin/updateColor", method = RequestMethod.POST)
    public String updateColor(@ModelAttribute("color") @Valid Color color, BindingResult result,
                              ModelMap model, HttpServletRequest request) {
        if (!result.hasErrors()) {
            colorService.updateColor(color);
            model.addAttribute(new Color());
            model.addAttribute("msg", getMsg("updated", request));
        }
        setPageData(model);
        return "colorPage";
    }

    @RequestMapping(value = "/admin/deleteColor")
    public String deleteColor(ModelMap model, HttpServletRequest request) {
        int pid = Integer.parseInt(request.getParameter("id"));
        try {
            colorService.deleteColor(pid);

            model.addAttribute("msg", getMsg("deleted", request));
        } catch (Exception e) {
            model.addAttribute("msg", getMsg("notDeleted", request));
        }

        model.addAttribute(new Color());
        setPageData(model);
        return "colorPage";
    }

    private void setPageData(ModelMap model) {
        model.addAttribute("allData", colorService.getAllColor());
        model.addAttribute("lang", LocaleContextHolder.getLocale().getLanguage()); //get locale language
    }

    private String getMsg(String key, HttpServletRequest request) {
        return messageSource.getMessage(key, null, localeResolver.resolveLocale(request));
    }
}
