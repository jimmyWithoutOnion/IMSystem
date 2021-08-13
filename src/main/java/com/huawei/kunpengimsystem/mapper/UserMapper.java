package com.huawei.kunpengimsystem.mapper;

import com.huawei.kunpengimsystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {
    /**
     * 查询所有用户啊
     *
     * @return 用户列表
     */
    List<User> selectAllUser();

    /**
     * 根据用户名和密码查询用户
     *
     * @param name 用户名
     * @param password 密码
     * @return 当前id的用户，不存在则是{@code null}
     */
    User selectUserByNameAndPassword(@Param("name") String name, @Param("password") String password);

    /**
     * 根据用户id查询用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    User selectUserByUserId(@Param("id") Integer id);

    /**
     * 新建用户
     *
     * @param user 用户
     * @return 成功 - {@code 1} 失败- {@code 0}
     */
    int createUser(@Param("user") User user);

    /**
     * 删除用户
     *
     * @param id 主键id
     * @return 成功 - {@code 1} 失败- {@code 0
     */
    int deleteUserById(@Param("id") Integer id);
}
