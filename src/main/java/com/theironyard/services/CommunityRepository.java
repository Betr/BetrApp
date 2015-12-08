package com.theironyard.services;

import com.theironyard.entities.Community;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jessicahuffstutler on 12/7/15.
 */
public interface CommunityRepository extends CrudRepository<Community, Integer> {

}
