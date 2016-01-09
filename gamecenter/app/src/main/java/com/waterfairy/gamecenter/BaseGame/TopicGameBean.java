package com.waterfairy.gamecenter.BaseGame;

import java.util.List;

/**
 * Created by shui on 2015/10/20.
 */
public class TopicGameBean {

    private boolean result;
    private List<MsgEntity> msg;
    private boolean hasNext;
    private int current_page;
    private ResultInfoEntity resultInfo;
    private InfoEntity info;

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setMsg(List<MsgEntity> msg) {
        this.msg = msg;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public void setResultInfo(ResultInfoEntity resultInfo) {
        this.resultInfo = resultInfo;
    }

    public void setInfo(InfoEntity info) {
        this.info = info;
    }

    public boolean isResult() {
        return result;
    }

    public List<MsgEntity> getMsg() {
        return msg;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public ResultInfoEntity getResultInfo() {
        return resultInfo;
    }

    public InfoEntity getInfo() {
        return info;
    }

    public class MsgEntity {
        /**
         * gift : 0
         * gameType : 角色扮演
         * activity : 0
         * net_game :
         * icon : http://img.wsdl.vivo.com.cn/appstore/developer/icon/201509/20150929174151878.jpg
         * type : 角色扮演
         * itemViewType : 0
         * commentNum : 0
         * apkurl : http://gamecenter.vivo.com.cn/clientRequest/gameDownload?id=39234&pkgName=com.tj.sbdsg.android.vivo
         * fitModel : true
         * download : 7856
         * versonName : 1.1.1.0925
         * size : 159154
         * pkgName : com.tj.sbdsg.android.vivo
         * name : 私奔到三国
         * versonCode : 3
         * recommend_desc : 英雄美人，尽入你手！
         * recommendType : 0
         * comment : 4.2
         * id : 39234
         * first_pub : 1
         */
        private String gift;
        private String gameType;
        private String activity;
        private String net_game;
        private String icon;
        private String type;
        private String itemViewType;
        private int commentNum;
        private String apkurl;
        private boolean fitModel;
        private int download;
        private String versonName;
        private int size;
        private String pkgName;
        private String name;
        private String versonCode;
        private String recommend_desc;
        private String recommendType;
        private double comment;
        private int id;
        private String first_pub;



        private String  date;
        private String   screenshot;

        private String  editor;
        private String  patch;
        private String   recommendDate;

        public String getEditor() {
            return editor;
        }

        public void setEditor(String editor) {
            this.editor = editor;
        }

        public String getPatch() {
            return patch;
        }

        public void setPatch(String patch) {
            this.patch = patch;
        }

        public String getRecommendDate() {
            return recommendDate;
        }

        public void setRecommendDate(String recommendDate) {
            this.recommendDate = recommendDate;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getScreenshot() {
            return screenshot;
        }

        public void setScreenshot(String screenshot) {
            this.screenshot = screenshot;
        }

        public void setGift(String gift) {
            this.gift = gift;
        }

        public void setGameType(String gameType) {
            this.gameType = gameType;
        }

        public void setActivity(String activity) {
            this.activity = activity;
        }

        public void setNet_game(String net_game) {
            this.net_game = net_game;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setItemViewType(String itemViewType) {
            this.itemViewType = itemViewType;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public void setApkurl(String apkurl) {
            this.apkurl = apkurl;
        }

        public void setFitModel(boolean fitModel) {
            this.fitModel = fitModel;
        }

        public void setDownload(int download) {
            this.download = download;
        }

        public void setVersonName(String versonName) {
            this.versonName = versonName;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public void setPkgName(String pkgName) {
            this.pkgName = pkgName;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setVersonCode(String versonCode) {
            this.versonCode = versonCode;
        }

        public void setRecommend_desc(String recommend_desc) {
            this.recommend_desc = recommend_desc;
        }

        public void setRecommendType(String recommendType) {
            this.recommendType = recommendType;
        }

        public void setComment(double comment) {
            this.comment = comment;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setFirst_pub(String first_pub) {
            this.first_pub = first_pub;
        }

        public String getGift() {
            return gift;
        }

        public String getGameType() {
            return gameType;
        }

        public String getActivity() {
            return activity;
        }

        public String getNet_game() {
            return net_game;
        }

        public String getIcon() {
            return icon;
        }

        public String getType() {
            return type;
        }

        public String getItemViewType() {
            return itemViewType;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public String getApkurl() {
            return apkurl;
        }

        public boolean isFitModel() {
            return fitModel;
        }

        public int getDownload() {
            return download;
        }

        public String getVersonName() {
            return versonName;
        }

        public int getSize() {
            return size;
        }

        public String getPkgName() {
            return pkgName;
        }

        public String getName() {
            return name;
        }

        public String getVersonCode() {
            return versonCode;
        }

        public String getRecommend_desc() {
            return recommend_desc;
        }

        public String getRecommendType() {
            return recommendType;
        }

        public double getComment() {
            return comment;
        }

        public int getId() {
            return id;
        }

        public String getFirst_pub() {
            return first_pub;
        }
    }

    public class ResultInfoEntity {
        /**
         * taskKey : d1d00jve40lq3uh
         */
        private String taskKey;

        public void setTaskKey(String taskKey) {
            this.taskKey = taskKey;
        }

        public String getTaskKey() {
            return taskKey;
        }
    }

    public class InfoEntity {
        /**
         * date : 2015-10-20 09:50:31
         * name :  每日首发
         * icon : http://img.wsdl.vivo.com.cn/appstore/gamecenter/upload/topic/201504/201504151038536114.jpg
         * topic_icon : http://img.wsdl.vivo.com.cn/appstore/gamecenter/upload/topic/201504/201504151038536964.jpg
         * id : 214
         * desc : 【10月20日了解手游动态，探索手游新玩法，分享游戏心得，尽在每日首发！
         */
        private String date;
        private String name;
        private String icon;
        private String topic_icon;
        private int id;
        private String desc;

        public void setDate(String date) {
            this.date = date;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setTopic_icon(String topic_icon) {
            this.topic_icon = topic_icon;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getDate() {
            return date;
        }

        public String getName() {
            return name;
        }

        public String getIcon() {
            return icon;
        }

        public String getTopic_icon() {
            return topic_icon;
        }

        public int getId() {
            return id;
        }

        public String getDesc() {
            return desc;
        }
    }
}
