package com.miaoyunhan.browsing_volume.utils;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.SSLs;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miaoyunhan.browsing_volume.entity.IpEntity;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.http.client.HttpClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProxyIpUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static IpEntity getIp() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("order", "ccdd291ec0aa803c7485d240df758849");
        map.put("json", "1");
        map.put("sep",5);

        //插件式配置请求参数（网址、请求参数、编码、client）
        HttpConfig config = HttpConfig.custom().timeout(2000)
                .url("http://api.ip.data5u.com/dynamic/get.html")	          //设置请求的url
                .map(map)	          //设置请求参数，没有则无需设置
                .encoding("utf-8"); //设置请求和返回编码，默认就是Charset.defaultCharset()

        String result1 = HttpClientUtil.post(config);     //get请求
        Map<String,Object> resultMap = objectMapper.readValue(result1, Map.class);
        List<Map> dataList = (List)resultMap.get("data");
        IpEntity ipEntity = new IpEntity();
        BeanUtils.populate(ipEntity,dataList.get(0));
        System.out.println(ipEntity);
        return ipEntity;
    }

    public static void main(String[] args) throws Exception {
        /*IpEntity ip = getIp();
        System.out.println(ip);*/
    }
}
