package com.miaoyunhan.browsing_volume.utils;

import java.util.ArrayList;

public class BrowseUtilsThread implements Runnable {

    private ArrayList<String> urls;

    public ArrayList<String> getUrls() {
        return urls;
    }

    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
    }

    @Override
    public void run() {
        while (true){
            try {
                BrowseUtils browseUtils = new BrowseUtils();
                browseUtils.setUrls(this.urls);
                browseUtils.browse();
            } catch (Exception e) {
                e.printStackTrace();
                Thread thread = new Thread(this);
                thread.start();
                Thread currentThread = Thread.currentThread();
                currentThread.stop();

            }
        }


    }
}
