package com.meeting.information.model.dto;

import com.meeting.information.model.po.MeetingBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description 课程基本信息dto
 * @author Mr.M
 * @date 2022/9/7 17:44
 * @version 1.0
 */
@Data
public class MeetingBaseInfoDto extends MeetingBase {


 /**
  * 开会时间段
  */
 List<FreeTimeDto> freeTime;

 /**
  * 参会人员列表
  */
 List<ParticipantsDto> participants;
 /**
  * 对应会议室名字
  */
 private String roomName;

 /**
  * 会议室地址
  */
 private String roomDress;




}
