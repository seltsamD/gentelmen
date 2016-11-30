package com.gent.dao;


import com.gent.model.Color;

import java.util.List;

/**
 * Created by daria on 05.10.2016.
 */
public interface IColorDAO {

    List<Color> getAllColor();
    Color getColorById(int id);
    boolean addColor(Color color);
    void updateColor(Color color);
    void deleteColor(int id);
    int getColoryByName(String lang, String text);
}
