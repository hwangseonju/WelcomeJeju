package com.ssafy.boonmoja.fileupload.repository;

import com.ssafy.boonmoja.fileupload.model.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<Photo, String> { }
