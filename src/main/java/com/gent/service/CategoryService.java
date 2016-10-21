package com.gent.service;

import com.gent.dao.ICategoryDAO;
import com.gent.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by daria on 05.10.2016.
 */
@Service
public class CategoryService implements ICategoryService {


    @Autowired
    private ICategoryDAO uaCategoryDAO;

    @Override
    public List<Category> getAllCategory() {
        return uaCategoryDAO.getAllCategory();
    }

    @Override
    public Category getCategoryById(int id) {
        Category text = uaCategoryDAO.getCategoryById(id);
        return text;
    }

    @Override
    public boolean addCategory(Category text) {
        uaCategoryDAO.addCategory(text);
        return true;
    }

    @Override
    public void updateCategory(Category text) {
        uaCategoryDAO.updateCategory(text);
    }

    @Override
    public void deleteCategory(int id) {
        uaCategoryDAO.deleteCategory(id);
    }


}
