package com.gent.controller.rest;

import com.gent.dto.CategoryDTO;
import com.gent.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by daria on 06.08.2017.
 */
@RestController
@ComponentScan("com.gent")
@RequestMapping("/api/category/")
public class CategoryRestController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> get() {
        return categoryService.getCategoryTree();
    }
}
