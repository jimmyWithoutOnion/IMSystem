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
public class Attachment implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 外键 信息id
     */
    private Long messageId;

    /**
     * 文件的地址
     */
    private String fileAddress;

    /**
     * 文件创建的时间
     */
    private Date createTime;
}
