package com.zsc.study.service.hystrix.javanica;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zsc.study.model.po.Role;
import com.zsc.study.persist.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @Auther: zhangshanchuang
 * @Date: 18/12/8 21:51
 * @Description:
 */
@Service
public class HystrixRoleService {

    @Autowired
    private RoleDao roleDao;

    @HystrixCommand(commandKey = "queryCommand",threadPoolKey = "queryPool")
    public Role queryRoleByUserId(Integer userId)  throws Exception{
        Random random = new Random();
        int r = random.nextInt(3);
        if(r>1){
            System.out.println("异常");
            int i = 1/0;
        }

        if(r==1){
            Thread.currentThread().sleep(5000);
            System.out.println("超时");
        }
        if(r==0){
            System.out.println("成功");
        }

        return roleDao.queryRoleById(userId);
    }


}
