package com.jtw.homestay.service;



import com.jtw.homestay.domain.Order;
import com.jtw.homestay.domain.User;

import java.util.List;

public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean regist(User user);

    /**
     * 激活用户
     * @param code
     * @return
     */
    boolean active(String code);

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 查询所有用户信息
     * @return 
     */
    public List<User> findAllUser();

    /**
     * 删除用户
     * @param uid
     * @return 
     */
    public Boolean deleteUserByUid(Integer uid);

    /**
     * 修改用户信息
     * @param user
     * @return 
     */
    public Boolean updateUser(User user);

    /**
     * 用户支付
     * @param order
     * @return
     */
    Boolean pay(Order order);
}
