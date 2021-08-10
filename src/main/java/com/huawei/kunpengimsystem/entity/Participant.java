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
public class Participant implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 外键 对话的id
     */
    private Long conversationId;

    /**
     * 外键 用户的id
     */
    private Long userId;

    /**
     * 类型：single，group
     */
    private String type;

    /**
     * 创建的时间
     */
    private Date createTime;
}
