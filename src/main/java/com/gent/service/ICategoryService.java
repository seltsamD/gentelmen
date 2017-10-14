package com.gent.service;

import com.gent.dto.CategoryDTO;
import com.gent.model.Category;
import com.gent.util.NotFoundException;

import java.util.List;

/**
 * Created by daria on 05.10.2016.
 */
public interface ICategoryService {

    List<Category> getAllCategory();

    Category getCategoryById(int id) throws NotFoundException;
    boolean addCategory(Category text);
    void updateCategory(Category text);

    void deleteCategory(Long id);
    List<Category> getFirstLevel();
    List<Category> getChild(int id);
    List<Category> getSecondLevel();
    int getCategoryByName(String lang, String text);

    List<CategoryDTO> getCategoryTree();

    //mapper
    CategoryDTO convertToDTO(Category category, String lang) throws NotFoundException;

    CategoryDTO convertToDTO(Category category) throws NotFoundException;

    List<CategoryDTO> convertListToDTO(List<Category> categories);

    List<CategoryDTO> convertListToDTO(List<Category> categories, String lang);
}
