package com.cyou.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cyou.common.core.BaseServiceImpl;
import com.cyou.common.core.Pager;
import com.cyou.common.core.ResultBean;
import com.cyou.common.core.ResultVoBean;
import com.cyou.common.enums.ReturnCodeEnum;
import com.cyou.common.exception.LogicException;
import com.cyou.logic.UserLogic;
import com.cyou.po.UserPo;
import com.cyou.service.UserService;
import com.cyou.vo.UserVo;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类UserServiceImpl.java的实现描述：TODO 类实现描述
 *
 * @author qibaichao 2014年7月21日 下午5:44:40
 */
@Service
public class UserServiceImpl implements UserService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserLogic userLogic;

    @Override
    public ResultVoBean<UserVo> getUserById(int userId) {
        ResultVoBean<UserVo> resultVoBean = new ResultVoBean<UserVo>();
        UserVo userVo = null;
        try {
            UserPo po = this.userLogic.getUserById(userId);
            if (po != null) {
                userVo = new UserVo();
                BeanUtils.copyProperties(po, userVo);
            }
            resultVoBean.success(userVo);
        } catch (LogicException e) {
            resultVoBean.failure(e.getReturnCodeEnum(), e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            resultVoBean.failure(ReturnCodeEnum.SYSTEM_ERROR, e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return resultVoBean;
    }

    @Override
    @Transactional
    public ResultBean addUser(UserVo userVo) throws Exception{
        ResultBean resultBean = new ResultBean();
//        try {
            if (userVo == null) {
                logger.error("add user param error:");
                resultBean.failure(ReturnCodeEnum.PARAM_ERROR.getDesc());
                return resultBean;
            }
            UserPo userPo = new UserPo();
            BeanUtils.copyProperties(userVo, userPo);
            this.userLogic.addUser(userPo);
            //事务测试
            addUserTest(userVo);
//        } catch (LogicException e) {
//            resultBean.failure(e.getReturnCodeEnum(), e.getMessage());
//            logger.error(
//                    String.format("add User logic exception userVo:%s",
//                            JSON.toJSONString(userVo)), e);
//        }
//        } catch (Exception e) {
//            resultBean.failure(ReturnCodeEnum.SYSTEM_ERROR, e.getMessage());
//            logger.error(
//                    String.format("add User system error userVo:%s",
//                            JSON.toJSONString(userVo)), e);
//        }
        return resultBean;
    }

    @Transactional
    public void addUserTest(UserVo userVo) throws Exception {

        UserPo userPo = new UserPo();
        BeanUtils.copyProperties(userVo, userPo);
        this.userLogic.addUser(userPo);
        throw new RuntimeException();
    }

    @Override
    public Pager getUserPager(Pager pager) {

        logger.info("getUserPager select page method");
        try {
            List<UserPo> resultList = null;
            List<UserVo> voList = new ArrayList<UserVo>();
            UserVo vo = null;
            int totalCount = this.userLogic.selectCount(pager);
            pager.resetTotalCount(totalCount);
            if (totalCount > 0) {// 如果总条数为0，不查询操作
                resultList = this.userLogic.selectUserList(pager);
                for (UserPo userPo : resultList) {
                    vo = new UserVo();
                    BeanUtils.copyProperties(userPo, vo);
                    voList.add(vo);
                }
            }
            pager.setTotalCount(totalCount);
            pager.setResultList(voList);
            return pager;
        } catch (LogicException te) {
            logger.error(te.getMessage(), te);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return pager;
    }

    @Override
    @Transactional
    public ResultBean deleteUser(int[] userIds) {
        ResultBean resultBean = new ResultBean();
        try {
            this.userLogic.deleteUser(userIds);
        } catch (LogicException e) {
            resultBean.failure(e.getReturnCodeEnum(), e.getMessage());
            logger.error(String.format(
                    "delete User logic exception userIds:%s",
                    JSON.toJSONString(userIds)), e);
        } catch (Exception e) {
            resultBean.failure(ReturnCodeEnum.SYSTEM_ERROR, e.getMessage());
            logger.error(
                    String.format("delete User system error userIds:%s",
                            JSON.toJSONString(userIds)), e);
        }
        return resultBean;
    }

    @Override
    @Transactional
    public ResultBean updateUser(UserVo userVo) {
        ResultBean resultBean = new ResultBean();
        try {
            if (userVo == null) {
                logger.error("add user param error:");
                resultBean.failure(ReturnCodeEnum.PARAM_ERROR.getDesc());
                return resultBean;
            }
            UserPo userPo = new UserPo();
            BeanUtils.copyProperties(userVo, userPo);
            this.userLogic.updateUser(userPo);
        } catch (LogicException e) {
            resultBean.failure(e.getReturnCodeEnum(), e.getMessage());
            logger.error(
                    String.format("update User logic exception userVo:%s",
                            JSON.toJSONString(userVo)), e);
        } catch (Exception e) {
            resultBean.failure(ReturnCodeEnum.SYSTEM_ERROR, e.getMessage());
            logger.error(
                    String.format("update User system error userVo:%s",
                            JSON.toJSONString(userVo)), e);
        }
        return resultBean;

    }

}
