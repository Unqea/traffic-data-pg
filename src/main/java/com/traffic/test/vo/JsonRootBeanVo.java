package com.traffic.test.vo;

import lombok.Data;

import java.util.Set;
@Data
public class JsonRootBeanVo {
    private String name;
    private String scnCode;
    private String scnParam;
    private Set<String> sdfInfos;

}
