package com.miaoyunhan.browsing_volume.request;

import cn.edu.hfut.dmic.webcollector.plugin.net.OkHttpRequester;
import com.miaoyunhan.browsing_volume.entity.IpEntity;
import com.miaoyunhan.browsing_volume.utils.ProxyIpUtils;
import lombok.Data;
import okhttp3.OkHttpClient;

import java.net.*;

// 自定义的请求插件
// 可以设置随机代理选择器
@Data
public class MyRequester extends OkHttpRequester {

    @Override
    public OkHttpClient.Builder createOkHttpClientBuilder() {
        IpEntity ipEntity = null;
        try {
            ipEntity = ProxyIpUtils.getIp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.createOkHttpClientBuilder().proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ipEntity.getIp(), ipEntity.getPort())));
    }
}