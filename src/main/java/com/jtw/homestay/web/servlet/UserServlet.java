/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtw.homestay.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jtw.homestay.domain.ResultInfo;
import com.jtw.homestay.domain.User;
import com.jtw.homestay.service.Impl.UserServiceImpl;
import com.jtw.homestay.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * @author  jml
 * @date    2020-3-5 10:00:42
 * @version V1.0
 * @desc
 */
@WebServlet(name="userServlet", urlPatterns={"/user/*"})
public class UserServlet extends BaseServlet {
    private UserService service=new UserServiceImpl();

    /**
     * 用户注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户输入的验证码
        String checkCode = request.getParameter("checkCode");
        //读取系统生成的验证码
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        //移除session中的验证码
        request.getSession().removeAttribute("CHECKCODE_SERVER");

        System.out.println("checkCode:"+checkCode);
        System.out.println("checkcode_server:"+checkcode_server);
        if(checkcode_server ==null || !checkcode_server.equalsIgnoreCase(checkCode)){
            //验证码错误
            ResultInfo info=new ResultInfo();
            info.setErrorMsg("验证码错误！");
            info.setFlag(false);
            //json序列化消息

            writeValue(info,response);
            return;
        }
        //1.获取数据
        Map<String, String[]> map =  request.getParameterMap();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String key = entry.getKey();
            String[] value = entry.getValue();
            System.out.println(key+":"+value[0]);
        }
        //System.out.println(map);
        //2.封装对象
        User user = new User();
        user.setRegdate(new Date().toLocaleString());
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //System.out.println(user);
        //3.调用service完成用户注册
        //UserService service=new UserServiceImpl();
        boolean flag=service.regist(user);
        ResultInfo info=new ResultInfo();
        //4.响应结果
        if(flag){
            info.setFlag(flag);
            info.setErrorMsg("注册成功！");
        }else{
            info.setFlag(false);
            info.setErrorMsg("注册失败!用户名已存在！");
        }
        //将info序列化为json对象

        writeValue(info,response);

    }

    /**
     * 验证码
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //服务器通知浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");
        //在内存中创建长：80，宽：40的图片
        //参数一：长
        //参数二：宽
        //参数三：颜色
        int width=80;
        int height=32;
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //获取画笔
        Graphics g=image.getGraphics();
        //设置画笔颜色
        g.setColor(Color.ORANGE);
        //填充图片
        g.fillRect(0,0,80,40);
        //产生4个随机验证码
        String checkCode=getCheckCode(4);
        //将验证码放到HttpSession中
        request.getSession().setAttribute("CHECKCODE_SERVER", checkCode);
//        System.out.println((String)request.getAttribute("CHECKCODE_SERVER"));
        //设置画笔颜色为绿色
        g.setColor(Color.GREEN);
        //设置字体及大小
        g.setFont(new Font("黑体",Font.BOLD,24));
        //将验证码写到图片上
        g.drawString(checkCode,15,25);
        //输出验证码到页面
        ImageIO.write(image,"png",response.getOutputStream());
        //System.out.println("验证码");
    }

    /**
     * 用户激活
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取激活码
        String code = request.getParameter("code");
        //2.调用service完成激活
        //UserService service=new UserServiceImpl();
        System.out.println(code);
        boolean flag=service.active(code);
        //3.判断标记
        String msg="";
        if(flag){
            msg="激活成功！请<a href='../login.html'>登录</a>";
        }else {
            msg="激活失败，请联系管理员！";
        }
        System.out.println(msg);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(msg);
    }

    /**
     * 用户登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,String[]> map = request.getParameterMap();

        User user=new User();

        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //校验验证码
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        String checkCode=request.getParameter("checkCode");
        if(!checkCode.equalsIgnoreCase(checkcode_server)){
            ResultInfo info=new ResultInfo();
            info.setErrorMsg("验证码错误！");
            info.setFlag(false);

            writeValue(info,response);
            return;
        }
        //UserService service=new UserServiceImpl();
        User u = service.login(user);
        ResultInfo info =new ResultInfo();
        if(u == null){
            //用户名或密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        if(u != null && !"Y".equals(u.getStatus())){
            //账号未激活
            info.setFlag(false);
            info.setErrorMsg("账号未激活，请前往邮箱激活账号！");
        }
        if(u != null && "Y".equals(u.getStatus())){
            //登录成功
            info.setFlag(true);
            //保存登录用户
            request.getSession().setAttribute("user",u);
        }

        writeValue(info,response);
    }

    /**
     * 用户退出
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

    /**
     * 当前登录用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        writeValue(user,response);
    }
    
    /**
     * 生成验证码
     * @param num
     * @return
     */
    private String getCheckCode(int num){
        //验证码字符集
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        //保存验证码
        StringBuffer checkCode=new StringBuffer();
        //产生随机数 0-62
        Random ran=new Random();
        for (int i = 0; i < num; i++) {
            int index=ran.nextInt(str.length());
            //生成验证码
            checkCode.append(str.charAt(index));
        }
        System.out.println(checkCode);
        return checkCode.toString();
    }
    
    /**
     * 查询所有用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    public void findAllUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<User>  users =service.findAllUser();
        writeValue(users,response);
    }
    
     /**
     * 删除用户信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String uid = request.getParameter("uid");
        Boolean flag = service.deleteUserByUid(Integer.parseInt(uid));
        ResultInfo info = new ResultInfo();
        if(flag){
            info.setData("删除成功！");
        }else{
            info.setData("删除失败！");
            
        }
        writeValue(info, response);
    }
    
    /**
     * 修改用户信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String map = request.getParameter("map");
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(map, User.class);
        Boolean flag = service.updateUser(user);
        ResultInfo info = new ResultInfo();
        if(flag){
            info.setData("修改成功！");
        }else{
            info.setData("修改失败！");
        }
        writeValue(info, response);
    }
}
