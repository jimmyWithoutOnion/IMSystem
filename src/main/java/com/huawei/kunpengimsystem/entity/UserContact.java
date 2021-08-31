package com.huawei.kunpengimsystem.entity;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserContact {

    /**
     * 主键 用户id
     */
    private Integer userId;

    /**
     * 主键 联系人id
     */
    private Integer contactId;

    /**
     * 联系人姓名
     */
    private String name;

    /**
     * 创建时间
     */
    private Date createTime;
}
