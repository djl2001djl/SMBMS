package com.DJL.service.role;

import com.DJL.dao.BaseDao;
import com.DJL.dao.role.RoleDao;
import com.DJL.dao.role.RoleDaoImpl;
import com.DJL.pojo.Role;

import java.sql.Connection;
import java.util.List;

/**
 * @author DJL
 * @create 2021-09-03 15:36
 */
public class RoleServiceImpl implements RoleService{

    private RoleDao roleDao;
    public RoleServiceImpl(){
        roleDao = new RoleDaoImpl();
    }
    public List<Role> getRoleList() {
        Connection connection = null;
        List<Role> roleList = null;
        try {
            connection = BaseDao.getConnection();
            roleList = roleDao.getRoleList(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return roleList;
    }
}
