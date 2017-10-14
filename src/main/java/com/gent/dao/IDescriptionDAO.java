package com.gent.dao;

import com.gent.model.Description;

/**
 * Created by daria on 28.09.2017.
 */
public interface IDescriptionDAO {
    Description findById(int id);

    void update(Description description);
}
