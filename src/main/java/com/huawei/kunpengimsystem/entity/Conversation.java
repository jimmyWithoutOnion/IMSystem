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
public class Conversation implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 对话名称
     */
    private String title;

    /**
     * 外键 对话创建者用户id
     */
    private Long creatorId;

    /**
     * 对话创建时间
     */
    private Date createTime;

    /**
     * 对话删除时间
     */
    private Date deleteTime;
}
