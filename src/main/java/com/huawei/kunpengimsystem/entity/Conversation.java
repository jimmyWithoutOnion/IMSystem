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
public class Conversation implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 对话名称
     */
    private String title;

    /**
     * 外键 对话创建者用户id
     */
    private Integer creatorId;

    /**
     * 对话创建时间
     */
    private Date createTime;

    /**
     * 对话删除时间
     */
    private Date deleteTime;
}
