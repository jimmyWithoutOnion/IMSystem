package com.huawei.kunpengimsystem.mapper;

import com.huawei.kunpengimsystem.entity.UserContact;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserContactMapper {

    List<UserContact> selectAllUserContact();

    List<UserContact> selectUserContactByUserId(@Param("user_id") Long userId);

    int createUserContact(@Param("UserContact") UserContact userContact);

    int deleteUserCOntact(@Param("id") Long id);
}
