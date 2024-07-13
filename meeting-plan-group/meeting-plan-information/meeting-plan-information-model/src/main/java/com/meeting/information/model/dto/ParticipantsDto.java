package com.meeting.information.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description
 * @Author lixiao
 * @Date 2024/7/12
 */

@Data
@ApiModel(value="ParticipantsDto", description="参会人员信息")
public class ParticipantsDto {

    private String name;

    private Long staffId;

    private String need;


}
