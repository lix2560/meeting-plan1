package com.meeting.information.service.impl;

import com.meeting.information.model.po.MeetingBase;
import com.meeting.information.mapper.MeetingBaseMapper;
import com.meeting.information.service.MeetingBaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itcast
 */
@Slf4j
@Service
public class MeetingBaseServiceImpl extends ServiceImpl<MeetingBaseMapper, MeetingBase> implements MeetingBaseService {

}
