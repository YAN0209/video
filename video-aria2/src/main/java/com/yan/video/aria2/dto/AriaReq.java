package com.yan.video.aria2.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * 请求aria2参数传输类
 * @author likaiyan
 * @date 2020/7/1 4:27 下午
 */
@Getter
@Setter
public class AriaReq {

    @Value("${aria2.jsonrpc}")
    private String jsonrpc;

    private String method;

    @Value("${aria2.id}")
    private String id;

    private List params;

}
