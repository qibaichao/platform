package com.cyou;

import java.util.Random;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.cyou.common.core.ResultBean;
import com.cyou.common.core.ResultVoBean;
import com.cyou.service.UserService;
import com.cyou.vo.UserVo;

public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService ;

    /**
     * 查询数据库测试
     */
    @Test
    public void getUserById() {

        try {
            ResultVoBean<UserVo> resultVoBean = userService.getUserById(1);
            System.out.println(JSON.toJSON(resultVoBean));
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    /**
     * 插入数据库测试
     */
    @Test
    public void addUser() {
        try {
            UserVo u = new UserVo();
            u.setUserId(11);
            u.setUserName("ceshi" + new Random().nextInt(1000));
            u.setEmail("44");
            u.setPhone("11");
            u.setAddress("11");
            u.setAge(11);
            ResultBean resultBean = userService.addUser(u);
            System.out.println(JSON.toJSON(resultBean));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @Author qibaichao
     * @MethodName testUpdate
     * @Date Sep 9, 2013
     * @Description:测试修改
     */
    @Test
    public void testUpdate() {
        try {
            UserVo userVo = new UserVo();
            userVo.setUserId(2);
            userVo.setUserName("qibaichao");
            ResultBean resultBean = this.userService.updateUser(userVo);
            System.out.println(JSON.toJSON(resultBean));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            int[] userId = {
                    20, 30
            };
            this.userService.deleteUser(userId);
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * service事物控制测试
     */
    @Test
    public void transactionTest() throws Exception{
        UserVo u = new UserVo();
        u.setUserName("ceshi" + new Random().nextInt(1000));
        userService.addUser(u);
        System.out.println("*********** success");
    }

}
