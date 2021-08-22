package com.huawei.kunpengimsystem.mapper;

import com.huawei.kunpengimsystem.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MessageMapper {

    /**
     * 查询所有消息
     *
     * @return 消息列表
     */
    List<Message> selectAllMessage();

    /**
     * 根据对话id查询消息
     *
     * @param conversationId 对话id
     * @return 消息列表
     */
    List<Message> selectMessageByConversationId(@Param("conversationId") Integer conversationId);

    /**
     * 根据对话id和数量限制查询消息
     *
     * @param conversationId 对话id
     * @param limit 数量限制
     * @return 消息列表
     */
    List<Message> selectMessageByConversationIdWithNumberLimit(@Param("conversationId") Integer conversationId, @Param("limit") Integer limit);
    /**
     * 创建新消息
     *
     * @param message 消息
     * @return 成功 - {@code 1} 失败- {@code 0}
     */
    int createMessage(@Param("message") Message message);

    /**
     * 创建假数据
     *
     * @param message 消息
     * @return 成功 - {@code 1} 失败- {@code 0}
     */
    int createMockMessage(@Param("message") Message message);

    /**
     * 根据id删除消息
     *
     * @param id 主键
     * @return 成功 - {@code 1} 失败- {@code 0}
     */
    int deleteMessageById(@Param("id") Integer id);
}
