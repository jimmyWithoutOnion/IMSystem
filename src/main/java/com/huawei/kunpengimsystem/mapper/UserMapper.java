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
     * 根据id查询用户
     *
     * @param id 主键id
     * @return 当前id的用户，不存在则是{@code null}
     */
    User selectUserById(@Param("id") Long id);

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
    int deleteUser(@Param("id") Long id);
}
