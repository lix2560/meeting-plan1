package com.meeting.information.service.impl;

import com.ahubimk.base.execption.MeetingPlanException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meeting.information.mapper.*;

import com.meeting.information.model.dto.*;
import com.meeting.information.model.po.*;
import com.meeting.information.service.MeetingInformationService;
import com.meeting.plan.model.PageParams;
import com.meeting.plan.model.PageResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author lixiao
 * @Date 2024/7/10
 */
@Service
public class MeetingInformationServiceImpl implements MeetingInformationService {


    @Autowired
    MeetingBaseMapper meetingBaseMapper;
    @Autowired
    FreetimeMapper freetimeMapper;
    @Autowired
    MeetingStaffMapper meetingStaffMapper;
    @Autowired
    MeetingRoomMapper meetingRoomMapper;
    @Autowired
    StaffBaseMapper staffBaseMapper;


    @Override
    public PageResult<MeetingBase> queryMeetingList(PageParams pageParams, QueryMeetingParamsDto queryMeetingParamsDto) {


        //构建查询对象
        LambdaQueryWrapper<MeetingBase> queryWrapper=new LambdaQueryWrapper<>();


        //拼接查询条件
        //根据会议名称模糊查询  name like '%名称%'
        queryWrapper.like(StringUtils.isNotEmpty(queryMeetingParamsDto.getMeetingname()),MeetingBase::getMeetingName,queryMeetingParamsDto.getMeetingname());
        //根据状态
        queryWrapper.eq(StringUtils.isNotEmpty(queryMeetingParamsDto.getType()),MeetingBase::getMeetingType,queryMeetingParamsDto.getType());

        //分页参数
        Page<MeetingBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());

        //分页查询E page 分页参数, @Param("ew") Wrapper<T> queryWrapper 查询条件
        Page<MeetingBase> pageResult = meetingBaseMapper.selectPage(page, queryWrapper);

        //数据
        List<MeetingBase> items = pageResult.getRecords();
        //总记录数
        long total = pageResult.getTotal();

        //准备返回数据 List<T> items, long counts, long page, long pageSize
        PageResult<MeetingBase> meetingsBasePageResult = new PageResult<>(items, total, pageParams.getPageNo(), pageParams.getPageSize());
        return meetingsBasePageResult;
    }





//新增会议
    @Transactional
    @Override
    public MeetingBaseInfoDto createMeetingBase(Long manageId, AddMeetingDto addMeetingDto) {
        //合法性校验
        if(StringUtils.isBlank(addMeetingDto.getMeetingName())){
            throw  new MeetingPlanException("会议名字为空");
        }
        //......

        //新增对象
        MeetingBase meetingBase=new MeetingBase();
        //会议信息赋值给新增对象
        BeanUtils.copyProperties(addMeetingDto,meetingBase);

        meetingBase.setCreateDate(LocalDateTime.now());

        meetingBase.setState(0);

        meetingBase.setAuditStatus(0);
        meetingBase.setChangePeople("");

        int insert = meetingBaseMapper.insert(meetingBase);

        if (insert<=0) {
            throw new RuntimeException("新增课程基本信息失败");
        }
     // 写入时间表、
        List<FreeTimeDto> freeTime = addMeetingDto.getFreeTime();
        for (FreeTimeDto freeTimeDto:freeTime){
            Freetime freetime = new Freetime();
            freetime.setStartTime(freeTimeDto.getStartTime());
            freetime.setObjId(meetingBase.getId());
            freetime.setEndTime(freeTimeDto.getEndTime());
            freetime.setType(0);
            int insert1 = freetimeMapper.insert(freetime);
            if (insert1<=0) {
                throw new RuntimeException("新增开会时间信息失败");
            }
        }
//        LambdaQueryWrapper<MeetingBase> queryWrapper=new LambdaQueryWrapper<>();
//       MeetingBase meetingBase1 = meetingBaseMapper.selectOne(queryWrapper.eq(StringUtils.isNotEmpty(addMeetingDto.getMeetingName()), MeetingBase::getMeetingName, addMeetingDto.getMeetingName()));

        //写入参会人员表
        List<ParticipantsDto> participants = addMeetingDto.getParticipants();
        for (ParticipantsDto participantsDto:participants){
            MeetingStaff meetingStaff = new MeetingStaff();
            meetingStaff.setMeetingId(meetingBase.getId());
            StaffBase staffBase = staffBaseMapper.selectOne(new LambdaQueryWrapper<StaffBase>().eq(StaffBase::getStaffName, participantsDto.getName()));
            meetingStaff.setStaffId(staffBase.getId());
            meetingStaff.setNeed(participantsDto.getNeed());
            int insert1 = meetingStaffMapper.insert(meetingStaff);
            if (insert1<=0) {
                throw new RuntimeException("新增开会人员信息失败");
            }

        }
//填充返回类
        MeetingBaseInfoDto meetingBaseInfoDto = new MeetingBaseInfoDto();
        BeanUtils.copyProperties(addMeetingDto,meetingBaseInfoDto);
        LambdaQueryWrapper<MeetingRoom> objectQueryWrapper = new LambdaQueryWrapper<>();
        MeetingRoom meetingroom = meetingRoomMapper.selectOne(objectQueryWrapper.eq(MeetingRoom::getRoomId,addMeetingDto.getRoomId()));
        meetingBaseInfoDto.setRoomName(meetingroom.getRoomName());
        meetingBaseInfoDto.setRoomDress(meetingroom.getRoomDress());
        return meetingBaseInfoDto;
    }


    public MeetingBaseInfoDto getMeetingBaseById( Long meetingId) {

        MeetingBaseInfoDto meetingBaseInfoDto = new MeetingBaseInfoDto();

        MeetingBase meetingBase = meetingBaseMapper.selectById(meetingId);
        BeanUtils.copyProperties(meetingBase,meetingBaseInfoDto);
        LambdaQueryWrapper<MeetingRoom> objectQueryWrapper = new LambdaQueryWrapper<>();
        MeetingRoom meetingroom = meetingRoomMapper.selectOne(objectQueryWrapper.eq(MeetingRoom::getRoomId,meetingBase.getRoomId()));
        meetingBaseInfoDto.setRoomDress(meetingroom.getRoomDress());
        meetingBaseInfoDto.setRoomName(meetingroom.getRoomName());

        LambdaQueryWrapper<Freetime> freeTimeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<Freetime> freetimes = freetimeMapper.selectList(freeTimeLambdaQueryWrapper.eq(Freetime::getObjId,meetingBase.getId()));
        List<FreeTimeDto> freeTimeDtos=new ArrayList<>();
        for (Freetime freetime:freetimes){
            FreeTimeDto freeTimeDto = new FreeTimeDto();
            freeTimeDto.setEndTime(freetime.getEndTime());
            freeTimeDto.setStartTime(freetime.getStartTime());
            freeTimeDto.setObjId(freetime.getObjId());
            freeTimeDto.setFreeType(freetime.getType());
            freeTimeDtos.add(freeTimeDto);
        }
        meetingBaseInfoDto.setFreeTime(freeTimeDtos);
        ArrayList<ParticipantsDto> participantsDtos = new ArrayList<>();
        LambdaQueryWrapper<MeetingStaff> meetStaffLambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<MeetingStaff> meetingStaffs = meetingStaffMapper.selectList(meetStaffLambdaQueryWrapper.eq(MeetingStaff::getMeetingId,meetingId));
        for(MeetingStaff meetingStaff:meetingStaffs){
            ParticipantsDto participantsDto = new ParticipantsDto();
            participantsDto.setNeed(meetingStaff.getNeed());
            participantsDto.setStaffId(meetingStaff.getStaffId());
            StaffBase staffBase = staffBaseMapper.selectById(meetingStaff.getMeetingId());

            participantsDto.setName(staffBase.getStaffName());
            participantsDtos.add(participantsDto);
        }
        meetingBaseInfoDto.setParticipants(participantsDtos);

        return meetingBaseInfoDto;
    }




}
