package com.huawei.kunpengimsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserContact implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键 用户id
     */
    private Long userId;

    /**
     * 主键 联系人id
     */
    private Long contactId;

    /**
     * 联系人姓名
     */
    private String name;

    /**
     * 创建时间
     */
    private Date createTime;
}
