package com.huawei.kunpengimsystem.entity;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Attachment {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 外键 信息id
     */
    private Integer messageId;

    /**
     * 文件的地址
     */
    private String fileAddress;

    /**
     * 文件校验码
     */
    private String fileCheckCode;

    /**
     * 文件创建的时间
     */
    private Date createTime;
}
