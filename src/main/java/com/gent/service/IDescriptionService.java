package com.gent.service;

import com.gent.model.Description;

import java.util.List;

/**
 * Created by daria on 10.10.2016.
 */

public interface IDescriptionService {
    List<Description> getAllDescription();
    Description getDescriptionById(int id);
    boolean addDescription(Description description);
    void updateDescription(Description description);
    void deleteDescription(int id);
}
