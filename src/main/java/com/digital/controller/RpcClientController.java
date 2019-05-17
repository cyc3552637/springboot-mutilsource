package com.digital.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.digital.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class RpcClientController {
    private static Logger log=LoggerFactory.getLogger(RpcClientController.class);
    @Autowired
    UserService userservice;
    @RequestMapping(value = "/useractionsqlone")
    public String userActionsqlone(HttpServletRequest request,@RequestParam(value="name",required=false) String name){  //拦截用户发送的/useraction请求

        if(name==null){
            log.info("namenull");
        }
        else{
            log.info(name);
        }
        return userservice.queryBySqlone(name);
    }
    @RequestMapping(value = "/useractionsqltwo")
    public String userActionsqltwo(HttpServletRequest request,@RequestParam(value="name",required=false) String name){  //拦截用户发送的/useraction请求

        if(name==null){
            log.info("namenull");
        }
        else{
            log.info(name);
        }
        return userservice.queryBySqltwo(name);
    }
    @RequestMapping(value = "/useractionmongo")
    public String userActionmongo(HttpServletRequest request,@RequestParam(value="name",required=false) String name){  //拦截用户发送的/useraction请求

        if(name==null){
            log.info("namenull");
        }
        else{
            log.info(name);
        }
        return userservice.queryByMongo(name);
    }

}
