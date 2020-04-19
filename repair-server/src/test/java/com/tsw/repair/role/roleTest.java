package com.tsw.repair.role;

import com.tsw.repair.role.dao.RoleDao;
import com.tsw.repair.role.model.Role;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class roleTest {

    @Resource
    RoleDao roleDao;

    @Test
    public void mainTest(){
        Role role =new Role();
        role.setRoleName("roleTest");

        int addRoleTest = roleDao.addRole(role);
        Assert.assertEquals(1,addRoleTest);

        Role roleInfo = roleDao.getRoleById(role.getRoleId());
        Assert.assertNotNull(roleInfo);
        Assert.assertEquals(roleInfo.getRoleName(),role.getRoleName());

        List<Role> roles = roleDao.getRoutesByRole();
        Assert.assertNotNull(roles);

        role.setRoleName("updateTest");
        int updateRoleTest = roleDao.updateRole(role);
        Assert.assertEquals(1,updateRoleTest);
        Assert.assertNotEquals(role.getRoleName(),roleInfo.getRoleName());

        int deleteRoleTest = roleDao.deleteRole(role.getRoleId());
        Assert.assertEquals(1,deleteRoleTest);
        roleInfo = roleDao.getRoleById(role.getRoleId());
        Assert.assertNull(roleInfo);

    }
}
