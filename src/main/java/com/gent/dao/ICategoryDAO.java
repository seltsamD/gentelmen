package com.gent.dao;



import com.gent.model.Category;

import java.util.List;

/**
 * Created by daria on 05.10.2016.
 */
public interface ICategoryDAO {

    List<Category> getAllCategory();
    Category getCategoryById(int id);
    boolean addCategory(Category text);
    void updateCategory(Category text);
    void deleteCategory(int id);

}
