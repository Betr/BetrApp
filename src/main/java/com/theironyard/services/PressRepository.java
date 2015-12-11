package com.theironyard.services;

import com.theironyard.entities.Press;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jessicahuffstutler on 12/10/15.
 */
public interface PressRepository extends CrudRepository<Press, Integer> {
}
