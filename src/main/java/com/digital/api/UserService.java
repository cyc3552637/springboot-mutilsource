package com.digital.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.digital.entity.UserEntity;


public interface UserService {
    public String queryBySqlone(@RequestParam("name") String name);
    public String queryBySqltwo(@RequestParam("name") String name);
    public String  queryByMongo(@RequestParam("name") String name);

}