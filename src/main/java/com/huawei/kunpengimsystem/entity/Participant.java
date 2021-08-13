package com.huawei.kunpengimsystem.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Participant implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 外键 对话的id
     */
    private Integer conversationId;

    /**
     * 外键 用户的id
     */
    private Integer userId;

    /**
     * 类型：single，group
     */
    private String type;

    /**
     * 创建的时间
     */
    private Date createTime;
}
