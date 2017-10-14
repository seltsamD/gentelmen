package com.gent.controller;

import com.gent.model.Category;
import com.gent.service.ICategoryService;
import com.gent.util.NotFoundException;
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
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by daria on 08.10.2016.
 */
@Controller
@ComponentScan("com.gent")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private LocaleResolver localeResolver;

    @RequestMapping(value = "/admin/categories")
    public ModelAndView categorys() {
        ModelAndView mv = new ModelAndView("categoryPage", "category", new Category());
        setPageData(mv.getModelMap());
        return mv;
    }

    @RequestMapping(value = "/admin/addCategory", method = RequestMethod.POST)
    public String addCategory(@ModelAttribute("category") @Valid Category category, BindingResult result,
                              ModelMap model, HttpServletRequest request) {
        if (!result.hasErrors()) {
            categoryService.addCategory(category);
            model.addAttribute(new Category());
        }
        setPageData(model);
        return "categoryPage";
    }

    @RequestMapping(value = "/admin/categoryById")
    public String getCategoryById(ModelMap model, HttpServletRequest request) throws NotFoundException {
        int pid = Integer.parseInt(request.getParameter("id"));
        Category category = categoryService.getCategoryById(pid);
        setPageData(model);
        model.addAttribute(category);
        return "categoryPage";
    }

    @RequestMapping(value = "/admin/updateCategory", method = RequestMethod.POST)
    public String updateCategory(@ModelAttribute("category") @Valid Category category, BindingResult result,
                                 ModelMap model, HttpServletRequest request) {
        if (!result.hasErrors()) {
            categoryService.updateCategory(category);
            model.addAttribute(new Category());
            model.addAttribute("msg", getMsg("updated", request));
        }
        setPageData(model);
        return "categoryPage";
    }

    @RequestMapping(value = "/admin/deleteCategory")
    public String deleteCategory(ModelMap model, HttpServletRequest request) {
        Long pid = Long.parseLong(request.getParameter("id"));
        try {
            categoryService.deleteCategory(pid);
            model.addAttribute("msg", getMsg("deleted", request));
        } catch (Exception e) {
            model.addAttribute("msg", getMsg("notDeleted", request));
        }

        model.addAttribute(new Category());
        setPageData(model);
        return "categoryPage";
    }

    private void setPageData(ModelMap model) {
        model.addAttribute("allData", categoryService.getAllCategory());
        model.addAttribute("parentList", categoryService.getFirstLevel());
        model.addAttribute("lang", LocaleContextHolder.getLocale().getLanguage()); //get locale language
    }

    private String getMsg(String key, HttpServletRequest request) {
        return messageSource.getMessage(key, null, localeResolver.resolveLocale(request));
    }
}
