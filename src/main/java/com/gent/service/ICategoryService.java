package com.gent.service;

import com.gent.dto.CategoryDTO;
import com.gent.model.Category;

import java.util.List;

/**
 * Created by daria on 05.10.2016.
 */
public interface ICategoryService {

    List<Category> getAllCategory();
    Category getCategoryById(int id);
    boolean addCategory(Category text);
    void updateCategory(Category text);
    void deleteCategory(int id);
    List<Category> getFirstLevel();
    List<Category> getChild(int id);
    List<Category> getSecondLevel();
    int getCategoryByName(String lang, String text);

    List<CategoryDTO> getCategoryTree();

    //mapper
    CategoryDTO convertToDTO(Category category, String lang);

    CategoryDTO convertToDTO(Category category);

    List<CategoryDTO> convertListToDTO(List<Category> categories);

    List<CategoryDTO> convertListToDTO(List<Category> categories, String lang);
}
