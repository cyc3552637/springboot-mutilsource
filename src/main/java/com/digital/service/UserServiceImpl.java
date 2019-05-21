package com.digital.service;

import java.io.IOException;
import java.util.List;

import com.digital.dao.mongo.MongoDao;
import com.digital.util.datasource.DataSourceAnnotation;
import com.digital.util.datasource.DataSourceNames;
import com.mongodb.gridfs.GridFSDBFile;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import com.digital.api.UserService;
import com.digital.dao.UserDao;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userdao;
    @Autowired
    private MongoDao mongoDao;
    @Autowired
    private GridFsTemplate gridFsTemplate;

    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


    @Value("${server.port}")
    String port;
//    @DataSourceAnnotation(DataSourceNames.ONE)
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
    public byte[] queryByMongoImage(@RequestParam(value = "fileName", required = true) String fileName) throws IOException {

        List<GridFSDBFile> result = gridFsTemplate.find(new Query().addCriteria(Criteria.where("filename").is(fileName)));
        if (result == null || result.size() == 0) {
            log.info("File not found" + fileName);
            throw new RuntimeException("No file with name: " + fileName);
        }
        log.info("File found " + fileName);
        return IOUtils.toByteArray(result.get(0).getInputStream());
    }
}
