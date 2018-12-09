package com.zsc.study.service;

import com.zsc.study.model.po.Role;
import com.zsc.study.persist.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: zhangshanchuang
 * @Date: 18/12/8 21:51
 * @Description:
 */
@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role queryRoleByUserId(Integer userId) {
        return roleDao.queryRoleById(userId);
    }
}
