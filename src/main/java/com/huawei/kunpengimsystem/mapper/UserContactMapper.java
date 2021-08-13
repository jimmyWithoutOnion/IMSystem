package com.huawei.kunpengimsystem.mapper;

import com.huawei.kunpengimsystem.entity.UserContact;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserContactMapper {

    /**
     * 查询所有联系人
     *
     * @return 联系人列表
     */
    List<UserContact> selectAllUserContact();

    /**
     * 根据用户id搜索联系人
     *
     * @param userId 用户id
     * @return 相关用户的所有联系人
     */
    List<UserContact> selectUserContactByUserId(@Param("userId") Integer userId);

    /**
     * 新建用户联系人
     *
     * @param userContact 用户联系人
     * @return 成功 - {@code 1} 失败- {@code 0}
     */
    int createUserContact(@Param("UserContact") UserContact userContact);

    /**
     * 删除用户联系人
     *
     * @param id 主键
     * @return 成功 - {@code 1} 失败- {@code 0}
     */
    int deleteUserContactById(@Param("id") Integer id);
}
