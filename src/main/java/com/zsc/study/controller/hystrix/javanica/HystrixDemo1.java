package com.zsc.study.controller.hystrix.javanica;

import com.zsc.study.controller.RoleContorller;
import com.zsc.study.model.po.Role;
import com.zsc.study.service.hystrix.javanica.HystrixRoleService;
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
 * @Date: 20/2/13 20:21
 * @Description:
 */
@Controller
@Api(value = "用户权限接口")
public class HystrixDemo1  {
    private Logger logger = LoggerFactory.getLogger(RoleContorller.class);

    @Autowired
    private HystrixRoleService hystrixRoleService;

    @ApiOperation(value = "用户权限列表", notes = "用户权限列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/hystrix/role/get/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Role queryRoleList(@ApiParam(required = true, name = "userId", value = "用户id") @PathVariable("userId") Integer userId) throws Exception{
        logger.info("userId:{},userId:{}", userId, userId);
        return hystrixRoleService.queryRoleByUserId(userId);
    }
}
