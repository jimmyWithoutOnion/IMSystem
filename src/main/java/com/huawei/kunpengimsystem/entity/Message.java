package com.huawei.kunpengimsystem.entity;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Message {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 外键 对话的id
     */
    private Integer conversationId;

    /**
     * 外键 发送用户的id
     */
    private Integer senderId;

    /**
     * 信息的类型：text，photo，file
     */
    private String messageType;

    /**
     * 信息内容
     */
    private String messageContext;

    /**
     * 信息创建时间
     */
    private Date createTime;

    /**
     * 信息删除时间
     */
    private Date deleteTime;
}
