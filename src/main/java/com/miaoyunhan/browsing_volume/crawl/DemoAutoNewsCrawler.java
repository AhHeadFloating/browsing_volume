package com.miaoyunhan.browsing_volume.crawl;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
import com.miaoyunhan.browsing_volume.entity.IpEntity;
import com.miaoyunhan.browsing_volume.request.MyRequester;
import com.miaoyunhan.browsing_volume.utils.ProxyIpUtils;

public class DemoAutoNewsCrawler extends BreadthCrawler {

    public DemoAutoNewsCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        /*start pages*/
        this.addSeed("https://blog.csdn.net/bin2277904333");
        /*for(int pageIndex = 2; pageIndex <= 5; pageIndex++) {
            String seedUrl = String.format("https://blog.github.com/page/%d/", pageIndex);
            this.addSeed(seedUrl);
        }*/

        /*fetch url like "https://blog.github.com/2018-07-13-graphql-for-octokit/" */
        this.addRegex("https://blog.csdn.net/bin2277904333/article/details/.*");
        /*do not fetch jpg|png|gif*/
        //this.addRegex("-.*\\.(jpg|png|gif).*");
        /*do not fetch url contains #*/
        //this.addRegex("-.*#.*");

        setThreads(10);
        getConf().setTopN(100);

        //enable resumable mode
        //setResumable(true);
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        try {
            String text = page.select(".title-article").text();
            System.out.println("浏览量+1："+text);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {

        DemoAutoNewsCrawler crawler = new DemoAutoNewsCrawler("crawl", true);
        while (true){
            crawler.setRequester(new MyRequester());
            /*start crawl with depth of 4*/
            crawler.start(2);
        }

    }

}