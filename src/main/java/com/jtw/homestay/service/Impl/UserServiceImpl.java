package com.jtw.homestay.service.Impl;


import com.jtw.homestay.dao.OrderDao;
import com.jtw.homestay.dao.UserDao;
import com.jtw.homestay.domain.Order;
import com.jtw.homestay.domain.User;
import com.jtw.homestay.service.UserService;
import com.jtw.homestay.utils.DaoUtils;
import com.jtw.homestay.utils.MailUtils;
import com.jtw.homestay.utils.UUIDUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserServiceImpl implements UserService {


    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        SqlSession session = DaoUtils.getSession();
        UserDao userDao = session.getMapper(UserDao.class);

        //1.根据用户名查询用户对象
        User u = userDao.findByUserName(user.getUserName());
        
        //System.out.println(user);
        //2.判断 u 是否为空
        if(u != null){
            //用户名存在，注册失败
            return false;
        }
        //3.保存用户信息
        //3.1设置激活码
        user.setCode(UUIDUtils.getUuid());
        //3.2设置激活状态
        user.setStatus("N");

        userDao.addUser(user);
        //4.激活邮件发送，邮件正文？
        String content="<a href='http://127.0.0.1:8080/homestay/user/active?code="+user.getCode()+"'>测试邮件，激活账号请点击！</a>";
        // 发送邮件
        MailUtils.sendMail(user.getEmail(),content,"测试邮件");

        session.close();
        return true;
    }

    /**
     * 激活用户
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        SqlSession session = DaoUtils.getSession();
        UserDao userDao = session.getMapper(UserDao.class);
        //1.根据激活码查询用户
        User user = userDao.findByCode(code);
        //2.判断用户是否存在
        if(user != null){
            //设置状态为激活
            user.setStatus("Y");
            //更新激活状态
            userDao.updateStatus(user);
            session.close();
            return true;
        }
        session.close();
        return false;
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        SqlSession session = DaoUtils.getSession();
        UserDao userDao = session.getMapper(UserDao.class);
        User u = userDao.findByUsernameAndPassword(user.getUserName(),user.getPassword());
        session.close();
        return u;
    }

    /**
     * 查询所有用户
     * @return 
     */
    @Override
    public List<User> findAllUser() {

        SqlSession session = DaoUtils.getSession();
        UserDao userDao = session.getMapper(UserDao.class);
        List<User> users = userDao.findAll();
        session.close();
        return users;
    }

    /**
     * 删除用户
     * @param uid
     * @return 
     */
    @Override
    public Boolean deleteUserByUid(Integer uid) {
        SqlSession session = DaoUtils.getSession();
        UserDao userDao = session.getMapper(UserDao.class);
        Boolean flag = userDao.deleteUser(uid);

        session.close();
        return flag;
    }

    /**
     * 修改用户信息
     * @param user
     * @return 
     */
    @Override
    public Boolean updateUser(User user) {
        SqlSession session = DaoUtils.getSession();
        UserDao userDao = session.getMapper(UserDao.class);
        Boolean flag = userDao.updateUser(user);

        session.close();
        return flag;
    }

    @Override
    public Boolean pay(Order order) {
        SqlSession session = DaoUtils.getSession();
        OrderDao orderDao = session.getMapper(OrderDao.class);
        orderDao.updateOrder(order);
        return null;
    }
}
