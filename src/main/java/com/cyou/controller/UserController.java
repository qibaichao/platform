package com.cyou.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.cyou.common.exception.LogicException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cyou.common.core.Pager;
import com.cyou.common.core.ResultBean;
import com.cyou.common.core.ResultVoBean;
import com.cyou.common.enums.ReturnCodeEnum;
import com.cyou.service.UserService;
import com.cyou.vo.UserVo;

/**
 * 类UserController.java的实现描述：TODO 类实现描述
 * 
 * @author qibaichao 2014年7月22日 下午4:33:45
 */
@Controller
@RequestMapping("/testUser")
public class UserController extends BaseController {

    @Resource
    public UserService userService;

    /**
     * 页面转向
     * 
     * @author qibaichao
     * @param url
     *            参数url
     * @return String
     */
    @RequestMapping("/index_{url}")
    public String index(@PathVariable String url, HttpServletRequest request) {
        request.setAttribute("forward", url);
        return "index";
    }

    @RequestMapping("/showUserAdd")
    public String showUserAdd() {
        return "testUser/userAdd";
    }

    /**Cacheable
     * @Author qibaichao
     * @MethodName saveUser
     * @param u
     * @return
     * @Date Sep 9, 2013
     * @Description:添加用户
     */
    @RequestMapping("/userAdd")
    @ResponseBody
    public ResultBean userAdd(UserVo u) {
        ResultBean resultBean = new ResultBean();
        try {
            resultBean = userService.addUser(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultBean;
    }

    /**
     * @Author qibaichao
     * @MethodName saveUser
     * @param u
     * @return
     * @Date Sep 9, 2013
     * @Description:删除用户
     */
    @RequestMapping("/userDelete")
    @ResponseBody
    public ResultBean userDelete(int[] ids) {
        ResultBean resultBean = new ResultBean();
        this.userService.deleteUser(ids);
        return resultBean;
    }

    /**
     * @Author qibaichao
     * @MethodName showUserUpdate
     * @param param
     * @return
     * @Date Sep 10, 2013
     * @Description: 显示修改页面
     */
    @RequestMapping("/showUserModify")
    public ModelAndView showUserUpdate(int param) {
        ModelAndView modelAndView = new ModelAndView("testUser/userModify");
        ResultVoBean<UserVo> resultVoBean = this.userService.getUserById(param);

        // 跳转到错误页面
        if (resultVoBean.getReturnCodeEnum().getCode() != ReturnCodeEnum.SUCCESS
                .getCode()) {
            modelAndView.setViewName("error/error");
            modelAndView.addObject("errors", resultVoBean.getReturnCodeEnum()
                    .getDesc());
            return modelAndView;
        }
        modelAndView.addObject("userVo", resultVoBean.getValue());
        return modelAndView;
    }

    /**
     * @Author qibaichao
     * @MethodName userUpdate
     * @param userPo
     * @return
     * @Date Sep 10, 2013
     * @Description:修改
     */
    @RequestMapping("/userModify")
    @ResponseBody
    public ResultBean userModify(UserVo userVo) {
        ResultBean resultBean = new ResultBean();
        resultBean = this.userService.updateUser(userVo);
        return resultBean;
    }

    @RequestMapping("/userLook")
    public ModelAndView userLook(int param) {
        ModelAndView modelAndView = new ModelAndView("testUser/userLook");
        ResultVoBean<UserVo> resultVoBean = this.userService.getUserById(param);
        // 跳转到错误页面
        if (resultVoBean.getReturnCodeEnum().getCode() != ReturnCodeEnum.SUCCESS
                .getCode()) {
            modelAndView.setViewName("error/error");
            modelAndView.addObject("errors", resultVoBean.getReturnCodeEnum()
                    .getDesc());
            return modelAndView;
        }
        modelAndView.addObject("userVo", resultVoBean.getValue());
        return modelAndView;
    }

    /**
     * @Author qibaichao
     * @MethodName queryUser
     * @param pager
     * @return
     * @Date Sep 5, 2013
     * @Description: 分页查询
     */
    @RequestMapping("/userQuery")
    public ModelAndView userQuery(Pager pager) {

        ModelAndView modelAndView = new ModelAndView("testUser/userListQuery");
        pager = this.userService.getUserPager(pager);
        modelAndView.addObject("pager", pager);
        return modelAndView;

    }
}
