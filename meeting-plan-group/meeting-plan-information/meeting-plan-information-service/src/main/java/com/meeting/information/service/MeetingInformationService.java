package com.meeting.information.service;

import com.meeting.information.model.dto.AddMeetingDto;
import com.meeting.information.model.dto.MeetingBaseInfoDto;
import com.meeting.information.model.dto.QueryMeetingParamsDto;
import com.meeting.information.model.po.MeetingBase;

import com.meeting.plan.model.PageParams;
import com.meeting.plan.model.PageResult;

/**
 * @Description 会议基本信息业务接口
 * @Author lixiao
 * @Date 2024/7/10
 */
public interface MeetingInformationService {

    /*
    分页查询*/
    PageResult<MeetingBase> queryMeetingList(PageParams pageParams, QueryMeetingParamsDto queryMeetingParamsDto);


    /*添加会议信息*/
    MeetingBaseInfoDto createMeetingBase(Long manageId, AddMeetingDto addMeetingDto);




}
