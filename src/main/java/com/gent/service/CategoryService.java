/*Dar 21.10*/
package com.gent.service;

import com.gent.controller.SitemapController;
import com.gent.dao.ICategoryDAO;
import com.gent.dto.CategoryDTO;
import com.gent.model.Category;
import com.gent.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.gent.util.Constants.*;

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
    public Category getCategoryById(int id) throws NotFoundException {
        Category category = categoryDAO.getCategoryById(id);
        if (null == category) {
            throw new NotFoundException("Not found category with id " + id);
        }
        return category;
    }

    @Override
    public boolean addCategory(Category item) {
        categoryDAO.addCategory(item);
        SitemapController.addCategoryToSitemap(convertToDTO(item, UK_LANG));
        return true;
    }

    @Override
    @Transactional
    public void updateCategory(Category text) {
        categoryDAO.updateCategory(text);
    }

    @Override
    public void deleteCategory(Long id) {
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

    @Override
    public int getCategoryByName(String lang, String text) {
        return categoryDAO.getCategoryByName(lang, text);
    }

    @Override
    public List<CategoryDTO> getCategoryTree() {
        List<CategoryDTO> resultList = new ArrayList<CategoryDTO>();
        for (Category category : categoryDAO.getFirstLevel()) {
            CategoryDTO categoryDTO = convertToDTO(category);
            List<CategoryDTO> childList = categoryDAO.getChild(category.getId()).stream()
                    .map(childCategory -> convertToDTO(childCategory))
                    .collect(Collectors.toList());
            categoryDTO.setChild(childList);
            resultList.add(categoryDTO);
        }
        return resultList;
    }

    @Override
    public CategoryDTO convertToDTO(Category category, String lang) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setDate(category.getData());
        if (lang.equals(UK_LANG)) {
            categoryDTO.setName(category.getUaText());
            if (category.getLevel() == 0) {
                categoryDTO.setHref(DOMAIN + lang + SLASH + encode("каталог") + SLASH +
                        encode(category.getUaText().replaceAll(" ", "-")) + SLASH + category.getId());
                categoryDTO.setAltHref(DOMAIN + RU_LANG + SLASH + encode("каталог") + SLASH +
                        encode(category.getRuText().replaceAll(" ", "-")) + SLASH + category.getId());

            } else {
                Category parent = null;
                try {
                    parent = getCategoryById(category.getParent());
                    categoryDTO.setHref(DOMAIN + lang + SLASH + encode("каталог") + SLASH +
                            encode(parent.getUaText().replaceAll(" ", "-")) + SLASH +
                            encode(category.getUaText().replaceAll(" ", "-")) + SLASH + category.getId());
                    categoryDTO.setAltHref(DOMAIN + RU_LANG + SLASH + encode("каталог") + SLASH +
                            encode(parent.getRuText().replaceAll(" ", "-")) + SLASH +
                            encode(category.getRuText().replaceAll(" ", "-")) + SLASH + category.getId());
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }

            }
        } else {
            categoryDTO.setName(category.getRuText());
            if (category.getLevel() == 0) {
                categoryDTO.setHref(DOMAIN + lang + SLASH + encode("каталог") + SLASH +
                        encode(category.getRuText().replaceAll(" ", "-")) + SLASH + category.getId());
                categoryDTO.setAltHref(DOMAIN + UK_LANG + SLASH + encode("каталог") + SLASH +
                        encode(category.getUaText().replaceAll(" ", "-")) + SLASH + category.getId());

            } else {
                Category parent = null;
                try {
                    parent = getCategoryById(category.getParent());
                    categoryDTO.setHref(DOMAIN + lang + SLASH + encode("каталог") + SLASH +
                            encode(parent.getRuText().replaceAll(" ", "-")) + SLASH +
                            encode(category.getRuText().replaceAll(" ", "-")) + SLASH + category.getId());
                    categoryDTO.setAltHref(DOMAIN + UK_LANG + SLASH + encode("каталог") + SLASH +
                            encode(parent.getUaText().replaceAll(" ", "-")) + SLASH +
                            encode(category.getUaText().replaceAll(" ", "-")) + SLASH + category.getId());
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return categoryDTO;
    }

    @Override
    public CategoryDTO convertToDTO(Category category) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return convertToDTO(category, lang);
    }

    @Override
    public List<CategoryDTO> convertListToDTO(List<Category> categories) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return categories.stream().map(item -> convertToDTO(item, lang)).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> convertListToDTO(List<Category> categories, String lang) {
        return categories.stream().map(item -> convertToDTO(item, lang)).collect(Collectors.toList());
    }

    public static String encode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
