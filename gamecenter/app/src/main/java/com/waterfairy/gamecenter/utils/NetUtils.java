package com.waterfairy.gamecenter.utils;

import java.util.Calendar;

/**
 *
 * @author james
 */
public class NetUtils {
    //参数说明 page_index 页码
    //       elapsedtime 当前时间 >> NetUtils.getCurrentTime();

    //推荐
    public static final String HTTPRECOMMEND = "http://gamecenter.vivo.com.cn/clientRequest/recommendList?";
    //排行
    public static final String HTTPRANK = "http://gamecenter.vivo.com.cn/clientRequest/rankList?";
    //分类
    public static final String HTTPCATEGORIES = "http://gamecenter.vivo.com.cn/clientRequest/categories?";
    //网游
    public static final String HTTPNETGAMES = "http://gamecenter.vivo.com.cn/clientRequest/netGames?";
    //详情
    public static final String HTTPDETAIL = "http://info.gamecenter.vivo.com.cn/clientRequest/gameDetail?";
    //评论
    public static final String HTTPCOMMENT = "http://gamecenter.vivo.com.cn/clientRequest/commentList?";
    //搜索
    public static final String HTTPSEARCH = "http://search.gamecenter.vivo.com.cn/clientRequest/searchGameList?";
    //分类详情页
    public static final String CATEITEM = "http://gamecenter.vivo.com.cn/clientRequest/typeGames?";
    //首发
    public static final String CATETOPIC = "http://gamecenter.vivo.com.cn/clientRequest/topicGame?";
    //专题
    public static final String TOPICLIST = "http://gamecenter.vivo.com.cn/clientRequest/topicList?";
    //活动
    public static final String ACTIVITYLIST = "http://gamecenter.vivo.com.cn/clientRequest/activityList?";

    //hot搜索
    public static final String HOTSEARCH = "http://search.gamecenter.vivo.com.cn/clientRequest/searchGame?";
    //新品
    public static final String NEWGAMELIST = "http://gamecenter.vivo.com.cn/clientRequest/newGameList?";
    //更多推荐
    public static final String RECOMMENDHISTORY = "http://gamecenter.vivo.com.cn/clientRequest/recommendHistory?";
    //topicgame
    public static final String TOPICGAME = "http://gamecenter.vivo.com.cn/clientRequest/topicGame?";
    //活动详情
    public static final String ACTIVITYDETAIL = "http://gamecenter.vivo.com.cn/clientRequest/activityDetail?";
    //netgame more
    public static final String NEWNETGAMELIST = "http://gamecenter.vivo.com.cn/clientRequest/newNetGameList?";

    //礼包详情
    public static final String giftdetails = "http://info.gamecenter.vivo.com.cn/clientRequest/gameDetail?";






    /*

    Calendar c = Calendar.getInstance();
    取得系统日期:year = c.get(Calendar.YEAR)
                   month = c.grt(Calendar.MONTH)
                   day = c.get(Calendar.DAY_OF_MONTH)
    取得系统时间：hour = c.get(Calendar.HOUR_OF_DAY);
                      minute = c.get(Calendar.MINUTE)
     */


    public static long getCurrentTime() {
        long time = 0;
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int milliSecond = calendar.get(Calendar.MILLISECOND);
        time = hour * 3600000 + minute * 60000 + second * 1000 + milliSecond;
        return time;
    }
}
