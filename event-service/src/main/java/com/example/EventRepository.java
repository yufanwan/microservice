package com.example;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by 42278 on 2017/4/9.
 */
public interface EventRepository extends MongoRepository<Event,String> {

    List<Event> findByName(@Param("name") String name);
    List<Event> findByNumberLimit(@Param("numberLimit") Integer numberLimit);
}
