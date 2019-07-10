package com.miaoyunhan.browsing_volume.crawl;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
import com.miaoyunhan.browsing_volume.entity.IpEntity;
import com.miaoyunhan.browsing_volume.utils.BrowseUtils;
import com.miaoyunhan.browsing_volume.utils.BrowseUtilsThread;
import com.miaoyunhan.browsing_volume.utils.ProxyIpUtils;

import java.util.ArrayList;

public class GetUrlsCrawler extends BreadthCrawler {

    private static ArrayList<String> blogUrls = new ArrayList<>();


    public GetUrlsCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        this.addSeed("https://blog.csdn.net/bin2277904333");
        this.addRegex("https://blog.csdn.net/bin2277904333.*");
        setThreads(10);
    }

    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {
        if (page.matchUrl("https://blog.csdn.net/bin2277904333/article/details/.*")){
            try {
                String text = page.select(".title-article").text();
                System.out.println("浏览量+1："+text);
                blogUrls.add(page.url());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args){
        try{
            GetUrlsCrawler getUrlsCrawler = new GetUrlsCrawler("crawler",true);
            getUrlsCrawler.start(2);

            for (int i = 0; i < 50; i++) {
                BrowseUtilsThread browseUtils = new BrowseUtilsThread();
                browseUtils.setUrls(blogUrls);
                Thread thread = new Thread(browseUtils);
                thread.start();
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
