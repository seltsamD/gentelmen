package com.gent.controller.rest;

import com.gent.dto.CategoryDTO;
import com.gent.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Created by daria on 06.08.2017.
 */
@RestController
@ComponentScan("com.gent")
@RequestMapping("/api/category")
public class CategoryRestController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> get(@QueryParam("id") int id) {
        return categoryService.convertListToDTO(categoryService.getChild(id));
    }

    @GetMapping("/tree")
    public List<CategoryDTO> getTree() {
        return categoryService.getCategoryTree();
    }

}
