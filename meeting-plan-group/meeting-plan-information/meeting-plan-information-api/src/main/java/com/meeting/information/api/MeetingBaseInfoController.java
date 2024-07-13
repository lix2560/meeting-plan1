package com.meeting.information.api;

import com.meeting.information.model.dto.AddMeetingDto;
import com.meeting.information.model.dto.MeetingBaseInfoDto;
import com.meeting.information.model.dto.QueryMeetingParamsDto;
import com.meeting.information.model.po.MeetingBase;
import com.meeting.information.service.impl.MeetingInformationServiceImpl;
import com.meeting.plan.model.PageParams;
import com.meeting.plan.model.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 会议信息接口
 * @Author lixiao
 * @Date 2024/7/10
 */
@Api(value = "会议信息编辑接口",tags = "会议信息编辑接口")
@RestController
public class MeetingBaseInfoController {
    @Autowired
    MeetingInformationServiceImpl meetingInformationService;


    @ApiOperation("会议信息查询接口")
    @PostMapping("/meeting/list")
    public PageResult<MeetingBase> list(PageParams pageParams, @RequestBody(required = false) QueryMeetingParamsDto queryMeetingParamsDto){
        PageResult<MeetingBase> meetingsPageResult = meetingInformationService.queryMeetingList(pageParams, queryMeetingParamsDto);
        return meetingsPageResult;
    }


    @ApiOperation("新增会议基础信息")
    @PostMapping("/meeting")
    public MeetingBaseInfoDto createMeetingBase(@RequestBody AddMeetingDto addMeetingDto){
        Long manageId=12334123L;
        MeetingBaseInfoDto meetingBase = meetingInformationService.createMeetingBase(manageId, addMeetingDto);
        return meetingBase;
    }


    @ApiOperation("根据会议id查询信息")
    @GetMapping("/meeting/{meetingId}")
    public MeetingBaseInfoDto getMeetingBaseById(@PathVariable Long meetingId) {

        return meetingInformationService.getMeetingBaseById(meetingId);


    }


}
