package com.digital.service;

import java.util.List;

import com.digital.dao.mongo.MongoDao;
import com.digital.util.datasource.DataSourceAnnotation;
import com.digital.util.datasource.DataSourceNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.digital.api.UserService;
import com.digital.dao.UserDao;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userdao;
    @Autowired
    private MongoDao mongoDao;

    @Value("${server.port}")
    String port;
    @DataSourceAnnotation(DataSourceNames.ONE)
    public String queryBySqlone(String name) {
        // TODO Auto-generated method stub
        List list=userdao.querySqlone(name);
        String user=userdao.querySqlone(name).get(0).getUser();
        return user+" i am from port:" +port;
    }
    @DataSourceAnnotation(DataSourceNames.TWO)
    public String queryBySqltwo(String name) {
        // TODO Auto-generated method stub
        List list=userdao.querySqltwo(name);
        String user=userdao.querySqltwo(name).get(0).getUser();
        return user+" i am from port:" +port;
    }

    public String queryByMongo(String name) {
        // TODO Auto-generated method stub
        String user=mongoDao.findByUser(name).getUser();
        return user+" i am from port:" +port;
    }
}
