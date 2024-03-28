package com.traffic.vo;


import lombok.Data;

import java.util.List;

@Data
public class YwdrwllVo {
    private String code;
    private List<Ywdrwll> data;
    private String message;
    private String traceId;
}
