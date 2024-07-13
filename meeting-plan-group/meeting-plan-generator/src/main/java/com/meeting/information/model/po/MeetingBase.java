package com.meeting.information.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author itcast
 */
@Data
@TableName("meeting_base")
public class MeetingBase implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会议标识id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 会议名称
     */
    private String meetingName;

    /**
     * 优先级
     */
    private Integer prio;

    /**
     * 类型
     */
    private String meetingType;

    /**
     * 会议描述
     */
    private String meetingDescription;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime changeDate;

    /**
     * 创建人
     */
    private String createPeople;

    /**
     * 更新人
     */
    private String changePeople;

    /**
     * 审核状态
     */
    private Integer auditStatus;

    /**
     * 未排会， 已排会  下线
     */
    private Integer state;

    /**
     * 会议室ID
     */
    private Long roomId;


}
