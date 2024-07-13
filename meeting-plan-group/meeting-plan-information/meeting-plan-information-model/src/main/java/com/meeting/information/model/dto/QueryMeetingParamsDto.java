package com.meeting.information.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @Description
 * @Author lixiao
 * @Date 2024/7/10
 */
@Data
@ToString
public class QueryMeetingParamsDto {
    //会议名称
    @ApiModelProperty("会议名称")
    private String meetingname;
//状态
    @ApiModelProperty("状态")
    private String type;


}
