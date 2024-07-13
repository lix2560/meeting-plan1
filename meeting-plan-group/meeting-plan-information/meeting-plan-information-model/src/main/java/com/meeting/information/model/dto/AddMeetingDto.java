package com.meeting.information.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @description 添加会议dto
 * @author lix
 * @date 2024/7/12 17:40
 * @version 1.0
 */
@Data
@ApiModel(value="AddMeetingDto", description="新增会议基本信息")
public class AddMeetingDto {

 @NotEmpty(message = "会议名称不能为空")
 @ApiModelProperty(value = "会议名称", required = true)
 private String meetingName;

 @NotEmpty(message = "优先级不能为空")
 @ApiModelProperty(value = "优先级", required = true)
 private Integer prio;


 @NotEmpty(message = "会议类型不能为空")
 @ApiModelProperty(value = "会议类型")
 private String meetingType;


 @ApiModelProperty(value = "会议描述", required = true)
 private String meetingDescription;

 @ApiModelProperty(value = "创建人", required = true)
 private String createPeople;

 /**
  * 会议室ID
  */

 @ApiModelProperty(value = "会议室id", required = true)
 private Long roomId;

 @NotEmpty(message = "参与人员不能为空")
 @ApiModelProperty(value = "参与人员", required = true)
 List<ParticipantsDto> participants;

 @NotEmpty(message = "会议时间不为空")
 @ApiModelProperty(value = "会议时间", required = true)
 List<FreeTimeDto> freeTime;



}
