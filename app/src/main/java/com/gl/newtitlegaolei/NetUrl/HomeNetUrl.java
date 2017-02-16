package com.gl.newtitlegaolei.NetUrl;

/**
 * 作者：高镭
 * 时间：2017/2/15 18:58
 * 班级：1501A
 */
public class HomeNetUrl {
    public static String Url(String url, int page){
        String HomeUrl="http://c.m.163.com/nc/article/headline/"+url+"/"+page+"-20.html";
        return HomeUrl;
    }

}
