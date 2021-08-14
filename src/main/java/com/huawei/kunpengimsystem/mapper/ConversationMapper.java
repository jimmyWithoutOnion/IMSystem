package com.huawei.kunpengimsystem.mapper;

import com.huawei.kunpengimsystem.entity.Conversation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ConversationMapper {

    /**
     * 查询所有对话
     *
     * @return 所有对话
     */
    List<Conversation> selectAllConversation();

    /**
     * 根据对话id查询对话
     *
     * @param id 主键
     * @return 对话
     */
    Conversation selectConversationById(@Param("id") Integer id);

    /**
     * 根据参与者的用户id查询所有对话信息
     *
     * @param userId 用户id
     * @return 对话列表
     */
    List<Conversation> selectAllConversationByParticipantUserId(@Param("userId") Integer userId);

    /**
     * 新建对话
     *
     * @param conversation 对话
     * @return 成功 - {@code 1} 失败- {@code 0}
     */
    int createConversation(@Param("conversation") Conversation conversation);

    /**
     * 根据id删除对话
     *
     * @param id 主键id
     * @return 成功 - {@code 1} 失败- {@code 0}
     */
    int deleteConversationById(@Param("id") Integer id);
}
