package com.cyou.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyou.common.core.Pager;
import com.cyou.po.UserPo;
import org.springframework.stereotype.Repository;

/**
 * 类UserDao.java的实现描述：TODO 类实现描述
 * 
 * @author qibaichao 2014年7月22日 下午4:28:01
 */
@Repository
public interface UserDao {

    public UserPo selectUserById(int userId);

    public int insertUser(UserPo u);

    public int selectCount(@Param("pager") Pager pager);

    public List<UserPo> selectUserList(@Param("pager") Pager pager);

    public int deleteUser(@Param("userIds") int[] userIds);

    public int updateUser(UserPo userPo);

}
