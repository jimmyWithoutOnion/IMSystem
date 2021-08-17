package com.huawei.kunpengimsystem.service;

import com.huawei.kunpengimsystem.entity.Attachment;
import com.huawei.kunpengimsystem.mapper.AttachmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AttachmentService")
@Transactional
public class AttachmentServiceImpl implements AttachmentService{
    @Autowired
    private AttachmentMapper attachmentMapper;

    @Override
    public int createAttachment(Attachment attachment) {
        return attachmentMapper.createAttachment(attachment);
    }
}
