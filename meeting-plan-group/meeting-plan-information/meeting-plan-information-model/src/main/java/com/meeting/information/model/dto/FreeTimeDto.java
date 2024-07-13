package com.meeting.information.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description
 * @Author lixiao
 * @Date 2024/7/12
 */
@Data
public class FreeTimeDto {
//对象id标识
    private Long objId;
    //对象类型
    private Integer freeType;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

}
