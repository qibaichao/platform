package com.cyou.service;

import com.cyou.common.core.Pager;
import com.cyou.common.core.ResultBean;
import com.cyou.common.core.ResultVoBean;
import com.cyou.vo.UserVo;

/**
 * 类UserService.java的实现描述：TODO 类实现描述
 * 
 * @author qibaichao 2014年7月21日 下午5:42:28
 */
public interface UserService {

    public ResultVoBean<UserVo> getUserById(int userId);

    public ResultBean addUser(UserVo userVo) throws Exception;

    public Pager getUserPager(Pager pager);

    public ResultBean deleteUser(int[] userId);

    public ResultBean updateUser(UserVo userVo);

}
