package com.devin.ciserver.controller;

/**
 * Created by devin on 2018/8/8.
 */
import com.thoughtworks.xstream.XStream;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by devin on 2018/8/8
 */
@Api(value = "集成服务-环境管理接口")
@RequestMapping("/api/env-admin/v1")
@RestController
public class EnvironmentV1Controller extends BaseController{
    @ApiOperation(value = "获取可用系统环境变量")
    @RequestMapping(value = "/env", method = RequestMethod.GET)
    public String m1(){
        return "111";
    }



}
