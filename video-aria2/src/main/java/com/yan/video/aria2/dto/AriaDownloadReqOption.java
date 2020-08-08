package com.yan.video.aria2.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author likaiyan
 * @date 2020/7/1 5:59 下午
 */
@Getter
@Setter
public class AriaDownloadReqOption {

    /**
     * 重命名
     */
    private String out;

    /**
     * 下载目录
     */
    private String dir;

}
