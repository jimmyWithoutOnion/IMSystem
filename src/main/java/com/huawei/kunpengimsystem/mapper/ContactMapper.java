package com.huawei.kunpengimsystem.mapper;

import com.huawei.kunpengimsystem.entity.Contact;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ContactMapper {
    @Select("SELECT * FROM contacts")
    List<Contact> selectAllContact();


}
