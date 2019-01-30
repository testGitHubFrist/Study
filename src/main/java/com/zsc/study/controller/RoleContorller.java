package com.zsc.study.controller;

import com.zsc.study.model.po.Role;
import com.zsc.study.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: zhangshanchuang
 * @Date: 18/12/8 21:44
 * @description:
 */
@Controller
@Api(value = "用户权限接口")
public class RoleContorller {

    private Logger logger = LoggerFactory.getLogger(RoleContorller.class);

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "用户权限列表", notes = "用户权限列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "role/get/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Role queryRoleList(@ApiParam(required = true, name = "userId", value = "用户id") @PathVariable("userId") Integer userId) {
        logger.info("userId:{},userId:{}", userId, userId);
        return roleService.queryRoleByUserId(userId);
    }
}
