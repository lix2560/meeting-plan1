package com.meeting.plan.model;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Description 分页查询结果模型
 * @Author lixiao
 * @Date 2024/7/10
 */
@Data
@ToString
public class PageResult<T> implements Serializable {
    //数据列表
    private List<T> itmes;

    //总记录数
    private long counts;

    //当前页码
    private long page;

    //每页记录数
    private long pageSize;

    public PageResult(List<T> itmes,long counts,long page,long pageSize){
        this.itmes=itmes;
        this.counts=counts;
        this.page=page;
        this.pageSize=pageSize;



    }


}