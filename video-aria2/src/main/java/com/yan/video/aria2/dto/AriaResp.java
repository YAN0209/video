package com.yan.video.aria2.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author likaiyan
 * @date 2020/7/1 4:36 下午
 */
@Getter
@Setter
public class AriaResp {

    private String id;

    private String jsonrpc;

    private Map<String, String> result;

}
