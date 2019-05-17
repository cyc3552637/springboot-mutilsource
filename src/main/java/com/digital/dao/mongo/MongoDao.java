package com.digital.dao.mongo;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.digital.entity.UserMongo;
import org.springframework.stereotype.Service;

@Service
public interface MongoDao extends MongoRepository<UserMongo, String> {
    public UserMongo findByUser(String user);


}
