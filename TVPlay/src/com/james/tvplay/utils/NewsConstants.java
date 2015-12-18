package com.james.tvplay.utils;

/**
 * 程序所需要的各种常量类
 * @author james
 *
 */
public interface NewsConstants {

	//对于参数:"多取少传"
	//新闻频道列表
	public static final String ENTERTAINMENT_CHANNEL = "娱乐";
	public static final String FINANCE_CHANNEL = "财经";
	public static final String MILITARY_CHANNEL = "军事";
	public static final String SOCIETY_CHANNEL = "社会";
	public static final String HOTDOT_CHANNEL = "热点";
	
	//新闻频道列表对应的code
	public static final int ENTERTAINMENT_CODE=0;
	public static final int FINANCE_CODE=1;
	public static final int MILITARY_CODE=2;
	public static final int SOCIETY_CODE=3;
	public static final int HOTDOT_CODE=4;
	
	String URL_NEWS= "http://mobile.api.hunantv.com/channel/getDetail?userId=&channelId=1010&type=normal";
	String URL_LIFE= "http://mobile.api.hunantv.com/channel/getDetail?userId=&channelId=1009&type=normal";
	String URL_MEMBER= "http://mobile.api.hunantv.com/channel/getDetail?userId=&channelId=1024&type=normal";
	String URL_CHILD= "http://mobile.api.hunantv.com/channel/getDetail?userId=&channelId=1024&type=normal";
	String URL_MUSIC= "http://mobile.api.hunantv.com/channel/getDetail?userId=&channelId=1007&type=normal";
	
	//各新闻频道的地址
	//娱乐新闻
	public static final String ENTERTAINMENT_URL="http://appapi.very-news.com/moreNews.m?pull_type=down&channel=entertainment&sort_time=0&version_name=1.2.1&start_count=1&udid=E6BA61777016BE968AEFEA9FB9A065EB&uid=";
	public static final String FINANCE_URL="http://appapi.very-news.com/moreNews.m?pull_type=down&channel=finance&sort_time=0&version_name=1.2.1&start_count=1&udid=E6BA61777016BE968AEFEA9FB9A065EB&uid=";
	public static final String MILITARY_URL="http://appapi.very-news.com/moreNews.m?pull_type=down&channel=military&sort_time=0&version_name=1.2.1&start_count=1&udid=E6BA61777016BE968AEFEA9FB9A065EB&uid=";
	public static final String SOCIETY_URL="http://appapi.very-news.com/moreNews.m?pull_type=down&channel=society&sort_time=0&version_name=1.2.1&start_count=1&udid=E6BA61777016BE968AEFEA9FB9A065EB&uid=";
	public static final String HOTDOT_URL="http://appapi.very-news.com/moreNews.m?pull_type=down&channel=hotspot&sort_time=0&version_name=1.2.1&start_count=1&udid=E6BA61777016BE968AEFEA9FB9A065EB&uid=";
}
