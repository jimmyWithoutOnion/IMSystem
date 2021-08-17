package com.huawei.kunpengimsystem.mapper;

import com.huawei.kunpengimsystem.entity.Participant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ParticipantMapper {

    /**
     * 查询所有参与者
     *
     * @return 参与者列表
     */
    List<Participant> selectAllParticipant();

    /**
     *
     * @param userId 用户id
     * @return  参与者列表
     */
    List<Participant> selectParticipantByUserId(@Param("userId") Integer userId);

    /**
     * 查询所有对话的其他用户啊名字
     *
     * @param conversationId 对话id
     * @param userId 用户id
     * @return 其他用户名字
     */
    String selectUserNameByConversationId(@Param("conversationId") Integer conversationId, @Param("userId") Integer userId);

    /**
     * 根据用户id和联系人id查找对话id
     *
     * @param userId 用户id
     * @param contactId 联系人id
     * @return 对话id
     */
    Integer selectConversationIdByUserIdAndContactId(@Param("userId") Integer userId, @Param("contactId") Integer contactId);

    /**
     * 创建参与者
     *
     * @param participant 参与者
     * @return 成功 - {@code 1} 失败- {@code 0}
     */
    int createParticipant(@Param("participant") Participant participant);

    /**
     * 根据id删除参与者
     *
     * @param id 主键id
     * @return 成功 - {@code 1} 失败- {@code 0}
     */
    int deleteParticipantById(@Param("id") Integer id);
}
