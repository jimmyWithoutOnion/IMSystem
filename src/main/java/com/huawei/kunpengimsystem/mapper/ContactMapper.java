package com.huawei.kunpengimsystem.mapper;

import com.huawei.kunpengimsystem.entity.Contact;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ContactMapper {

    /**
     * 查询所有联系人
     *
     * @return 联系人列表
     */
    List<Contact> selectAllContact();

    /**
     * 根据id查询联系人
     *
     * @return 联系人
     */
    Contact selectContactById(@Param("id") Integer id);

    /**
     * 根据用户id查询联系人
     *
     * @param userId 用户id
     * @return 联系人列表
     */
    List<Contact> selectContactByUserId(@Param("userId") Integer userId);

    /**
     * 新建联系人
     *
     * @param contact 用户
     * @return 成功 - {@code 1} 失败- {@code 0}
     */
    int createContact(@Param("contact") Contact contact);

    /**
     * 根据id删除联系人
     *
     * @param id 主键id
     * @return 成功 - {@code 1} 失败- {@code 0}
     */
    int deleteContactById(@Param("id") Integer id);
}
