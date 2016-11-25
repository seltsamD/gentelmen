/*Dar 21.10*/
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
    private ICategoryDAO categoryDAO;

    @Override
    public List<Category> getAllCategory() {
        return categoryDAO.getAllCategory();
    }

    @Override
    public Category getCategoryById(int id) {
        Category text = categoryDAO.getCategoryById(id);
        return text;
    }

    @Override
    public boolean addCategory(Category text) {
        categoryDAO.addCategory(text);
        return true;
    }

    @Override
    public void updateCategory(Category text) {
        categoryDAO.updateCategory(text);
    }

    @Override
    public void deleteCategory(int id) {
        categoryDAO.deleteCategory(id);
    }

    @Override
    public List<Category> getFirstLevel() {
        return categoryDAO.getFirstLevel();
    }

    @Override
    public List<Category> getChild(int id) {
        return categoryDAO.getChild(id);
    }

    @Override
    public List<Category> getSecondLevel() {
        return categoryDAO.getSecondLevel();
    }


}
