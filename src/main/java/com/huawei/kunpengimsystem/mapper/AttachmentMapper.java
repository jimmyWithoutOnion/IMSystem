package com.huawei.kunpengimsystem.mapper;

import com.huawei.kunpengimsystem.entity.Attachment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AttachmentMapper {

    /**
     * 查询所有附件
     *
     * @return 附件列表
     */
    List<Attachment> selectAllAttachment();

    /**
     * 根据消息id查询附件
     *
     * @param messageId 消息id
     * @return 附件
     */
    Attachment selectAttachmentByMessageId(@Param("messageId") Integer messageId);

    /**
     * 创建新附件
     *
     * @param attachment 附件
     * @return 成功 - {@code 1} 失败- {@code 0}
     */
    int createAttachment(@Param("attachment") Attachment attachment);

    /**
     * 根据附件id删除附件
     *
     * @param id 附件id
     * @return 成功 - {@code 1} 失败- {@code 0}
     */
    int deleteAttachmentById(@Param("id") Integer id);
}
