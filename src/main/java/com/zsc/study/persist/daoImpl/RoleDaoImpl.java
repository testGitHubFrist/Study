package com.zsc.study.persist.daoImpl;

import com.zsc.study.model.po.Role;
import com.zsc.study.persist.dao.RoleDao;
import com.zsc.study.persist.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Auther: zhangshanchuang
 * @Date: 18/12/8 22:36
 * @Description:
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role queryRoleById(Integer userId) {
        return roleMapper.queryRoleById(userId);
    }
}
