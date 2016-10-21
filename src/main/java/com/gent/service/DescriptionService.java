package com.gent.service;


import com.gent.dao.IDescriptionDAO;
import com.gent.model.Description;
import javassist.runtime.Desc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by daria on 10.10.2016.
 */
@Service
public class DescriptionService implements IDescriptionService {

    @Autowired
    IDescriptionDAO descriptionDAO;

    @Override
    public List<Description> getAllDescription() {
        return descriptionDAO.getAllDescription();
    }

    @Override
    public Description getDescriptionById(int id) {
        Description description = descriptionDAO.getDescriptionById(id);
        return description;
    }

    @Override
    public boolean addDescription(Description description) {
        descriptionDAO.addDescription(description);
        return true;
    }

    @Override
    public void updateDescription(Description description) {
        descriptionDAO.updateDescription(description);
    }

    @Override
    public void deleteDescription(int id) {
        descriptionDAO.deleteDescription(id);
    }
}
