package com.gent.service;

import com.gent.model.Color;

import java.util.List;

/**
 * Created by daria on 05.10.2016.
 */
public interface IColorService {
    List<Color> getAllColor();
    Color getColorById(int id);
    boolean addColor(Color color);
    void updateColor(Color color);
    void deleteColor(int id);
}
