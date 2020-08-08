package com.yan.video.common;

import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author likaiyan
 * @date 2020/3/31 10:06 下午
 */
@Getter
@Setter
public class CommonPage<T> {

    private int pageNum;
    private int pageSize;
    private int totalPage;
    private long total;
    private List<T> data;

    public static <T> CommonPage<T> restPage(List<T> page) {
        CommonPage<T> commonPage = new CommonPage<T>();
        PageInfo<T> pageInfo = new PageInfo<T>(page);
        commonPage.setPageNum(pageInfo.getPageNum());
        commonPage.setPageSize(pageInfo.getPageSize());
        commonPage.setTotalPage(pageInfo.getPages());
        commonPage.setTotal(pageInfo.getTotal());
        commonPage.setData(pageInfo.getList());
        return commonPage;
    }

    /**
     * 将SpringData分页后的list转为分页信息
     */
    public static <T> CommonPage<T> restPage(Page<T> pageInfo) {
        CommonPage<T> result = new CommonPage<T>();
        result.setTotalPage(pageInfo.getTotalPages());
        result.setPageNum(pageInfo.getNumber());
        result.setPageSize(pageInfo.getSize());
        result.setTotal(pageInfo.getTotalElements());
        result.setData(pageInfo.getContent());
        return result;
    }

}
