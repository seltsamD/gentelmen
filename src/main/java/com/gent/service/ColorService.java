package com.gent.service;

import com.gent.dao.IColorDAO;
import com.gent.model.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by daria on 05.10.2016.
 */
@Service
public class ColorService implements IColorService {

    @Autowired
    private IColorDAO colorDAO;


    @Override
    public List<Color> getAllColor() {
        return colorDAO.getAllColor();
    }

    @Override
    public Color getColorById(int id) {
        Color text = colorDAO.getColorById(id);
        return text;
    }

    @Override
    public boolean addColor(Color text) {
        colorDAO.addColor(text);
        return true;
    }

    @Override
    public void updateColor(Color text) {
        colorDAO.updateColor(text);
    }

    @Override
    public void deleteColor(int id) {
        colorDAO.deleteColor(id);
    }


}
