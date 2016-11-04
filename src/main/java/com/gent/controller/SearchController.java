package com.gent.controller;

import com.gent.model.Good;
import com.gent.service.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by daria on 02.11.2016.
 */
@Controller
@ComponentScan("com.gent")
public class SearchController {
    @Autowired
    IGoodService goodService;
    @RequestMapping(value="search", method = RequestMethod.POST)
    public String updateText(ModelMap model, HttpServletRequest request) {
      List<Good> list =   goodService.search(request.getParameter("search_str"));
        model.addAttribute("allData", list);
        return "result";
    }
}
