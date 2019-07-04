package com.miaoyunhan.browsing_volume.entity;

import lombok.Data;

@Data
public class IpEntity {
    private String ip;
    private Integer port;
    private Long ttl;
}
