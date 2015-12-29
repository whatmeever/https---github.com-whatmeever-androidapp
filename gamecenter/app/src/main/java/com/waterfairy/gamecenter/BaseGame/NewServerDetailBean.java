package com.waterfairy.gamecenter.BaseGame;

/**
 *
 * @author james
 */
public class NewServerDetailBean {

    /**
     * result : true
     * msg : {"summary":"《仙剑神域》于10月27日上午11:00开启新区\u201c慷慨仗义\u201d!快来一起萌萌哒!","endDate":"2015年10月27日","gameInfo":{"date":"2014年11月04日","icon":"http://img.wsdl.vivo.com.cn/appstore/developer/icon/201504/20150401103650589.png","patch":"59893_57452:7447:1297194381:2739996265","apkurl":"http://gamecenter.vivo.com.cn/clientRequest/gameDownload?id=12218&pkgName=com.jingwan.xjsy.vivo","fitModel":true,"download":65791,"versonName":"2.8.0.0","size":77118,"pkgName":"com.jingwan.xjsy.vivo","name":"仙剑神域","versonCode":"59893","comment":4.7,"id":12218,"first_pub":"0"},"id":83862,"title":"慷慨仗义","startDate":"2015年10月27日","status":"2","desc":"<!DOCTYPE html><html><head><meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"><meta charset=\"UTF-8\"><meta name=\"format-detection\" content=\"telephone=no\" /><meta name=\"format-detection\" content=\"email=no\" /><title>vivo游戏中心<\/title><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1, target-densitydpi=medium-dpi, user-scalable=no\"><script src=\"http://download0.vivo.com.cn/appstore/script/jquery-1.7.2.min.js\" type=\"text/javascript\"><\/script><link href=\"http://download0.vivo.com.cn/appstore/css/activity.css\" rel=\"stylesheet\" type=\"text/css\" /><\/head><body><p><span style=\"color: rgb(102, 102, 102); font-family: Arial, Helvetica, sans-serif, 宋体, 微软雅黑; font-size: 14px; line-height: 24px; text-indent: 28px; background-color: rgb(255, 255, 255);\">超蓝光版电影剧情，感动中国，泪流满面，但是想想都要哭了，这么高清的画面从来都木有过啊!真武侠大招，秒杀张无鸡，拳打鹿小凤，脚踢令狐葱，菊撞欧阳蜂，让你摇身一变成大虾!最牛交易系统，懂不懂什么是免费?懂不懂什么是天上掉馅饼?懂不懂什么是隔壁老王地上捡了个石头卖给二狗拿钱买了条金项链?超神帮战、国战、跨服战，一血!大杀特杀!杀人如麻!无人能挡!Monster Kill! Gold like! 你懂的!不用幻想!不用YY!女神歪歪与你面对面，线对线，点对点，钱包内的\u201c小东西\u201d准备好了吗!<\/span><\/p><script type=\"text/javascript\">var tag = $(\"table\");    for (var k = 0; k < tag.length; k++) { $(tag[k]).removeAttr(\"style\");  } var imgs = $(\"img\");    for (var i = 0; i < imgs.length; i++) {  $(imgs[i]).removeAttr(\"style\"); } $(\"body\").findFourGridPictureById(\"*\").each(function(){  var str=$(this).css(\"font-size\"); var reg = /^\\d+(\\.\\d{2})?/; var stat = reg.exec(str)[0];  if (stat>14) { $(this).css({\"font-size\":\"16px\",\"font-weight\":\"normal\", \"line-height\":\"1.7em\"}); } else{    $(this).css({\"font-size\":\"14px\",\"font-weight\":\"normal\"});   };   })<\/script><\/body><\/html>"}
     */
    private boolean result;
    private MsgEntity msg;

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setMsg(MsgEntity msg) {
        this.msg = msg;
    }

    public boolean isResult() {
        return result;
    }

    public MsgEntity getMsg() {
        return msg;
    }

    public class MsgEntity {
        /**
         * summary : 《仙剑神域》于10月27日上午11:00开启新区“慷慨仗义”!快来一起萌萌哒!
         * endDate : 2015年10月27日
         * gameInfo : {"date":"2014年11月04日","icon":"http://img.wsdl.vivo.com.cn/appstore/developer/icon/201504/20150401103650589.png","patch":"59893_57452:7447:1297194381:2739996265","apkurl":"http://gamecenter.vivo.com.cn/clientRequest/gameDownload?id=12218&pkgName=com.jingwan.xjsy.vivo","fitModel":true,"download":65791,"versonName":"2.8.0.0","size":77118,"pkgName":"com.jingwan.xjsy.vivo","name":"仙剑神域","versonCode":"59893","comment":4.7,"id":12218,"first_pub":"0"}
         * id : 83862
         * title : 慷慨仗义
         * startDate : 2015年10月27日
         * status : 2
         * desc : <!DOCTYPE html><html><head><meta http-equiv="content-type" content="text/html; charset=UTF-8"><meta charset="UTF-8"><meta name="format-detection" content="telephone=no" /><meta name="format-detection" content="email=no" /><title>vivo游戏中心</title><meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1, target-densitydpi=medium-dpi, user-scalable=no"><script src="http://download0.vivo.com.cn/appstore/script/jquery-1.7.2.min.js" type="text/javascript"></script><link href="http://download0.vivo.com.cn/appstore/css/activity.css" rel="stylesheet" type="text/css" /></head><body><p><span style="color: rgb(102, 102, 102); font-family: Arial, Helvetica, sans-serif, 宋体, 微软雅黑; font-size: 14px; line-height: 24px; text-indent: 28px; background-color: rgb(255, 255, 255);">超蓝光版电影剧情，感动中国，泪流满面，但是想想都要哭了，这么高清的画面从来都木有过啊!真武侠大招，秒杀张无鸡，拳打鹿小凤，脚踢令狐葱，菊撞欧阳蜂，让你摇身一变成大虾!最牛交易系统，懂不懂什么是免费?懂不懂什么是天上掉馅饼?懂不懂什么是隔壁老王地上捡了个石头卖给二狗拿钱买了条金项链?超神帮战、国战、跨服战，一血!大杀特杀!杀人如麻!无人能挡!Monster Kill! Gold like! 你懂的!不用幻想!不用YY!女神歪歪与你面对面，线对线，点对点，钱包内的“小东西”准备好了吗!</span></p><script type="text/javascript">var tag = $("table");    for (var k = 0; k < tag.length; k++) { $(tag[k]).removeAttr("style");  } var imgs = $("img");    for (var i = 0; i < imgs.length; i++) {  $(imgs[i]).removeAttr("style"); } $("body").findFourGridPictureById("*").each(function(){  var str=$(this).css("font-size"); var reg = /^\d+(\.\d{2})?/; var stat = reg.exec(str)[0];  if (stat>14) { $(this).css({"font-size":"16px","font-weight":"normal", "line-height":"1.7em"}); } else{    $(this).css({"font-size":"14px","font-weight":"normal"});   };   })</script></body></html>
         */
        private String summary;
        private String endDate;
        private GameInfoEntity gameInfo;
        private int id;
        private String title;
        private String startDate;
        private String status;
        private String desc;

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public void setGameInfo(GameInfoEntity gameInfo) {
            this.gameInfo = gameInfo;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getSummary() {
            return summary;
        }

        public String getEndDate() {
            return endDate;
        }

        public GameInfoEntity getGameInfo() {
            return gameInfo;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getStartDate() {
            return startDate;
        }

        public String getStatus() {
            return status;
        }

        public String getDesc() {
            return desc;
        }

        public class GameInfoEntity {
            /**
             * date : 2014年11月04日
             * icon : http://img.wsdl.vivo.com.cn/appstore/developer/icon/201504/20150401103650589.png
             * patch : 59893_57452:7447:1297194381:2739996265
             * apkurl : http://gamecenter.vivo.com.cn/clientRequest/gameDownload?id=12218&pkgName=com.jingwan.xjsy.vivo
             * fitModel : true
             * download : 65791
             * versonName : 2.8.0.0
             * size : 77118
             * pkgName : com.jingwan.xjsy.vivo
             * name : 仙剑神域
             * versonCode : 59893
             * comment : 4.7
             * id : 12218
             * first_pub : 0
             */
            private String date;
            private String icon;
            private String patch;
            private String apkurl;
            private boolean fitModel;
            private int download;
            private String versonName;
            private int size;
            private String pkgName;
            private String name;
            private String versonCode;
            private double comment;
            private int id;
            private String first_pub;

            public void setDate(String date) {
                this.date = date;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public void setPatch(String patch) {
                this.patch = patch;
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

            public void setComment(double comment) {
                this.comment = comment;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setFirst_pub(String first_pub) {
                this.first_pub = first_pub;
            }

            public String getDate() {
                return date;
            }

            public String getIcon() {
                return icon;
            }

            public String getPatch() {
                return patch;
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
    }
}
