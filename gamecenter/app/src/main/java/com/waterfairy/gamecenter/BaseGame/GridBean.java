package com.waterfairy.gamecenter.BaseGame;

import java.util.List;

/**
 * Created by Administrator on 2015/10/16.
 */
public class GridBean {

    /**
     * result : true
     * msg : [{"gameNum":8574,"color":"#85a621","name":"休闲益智","icon":"http://img.wsdl.vivo.com.cn/appstore/gamecenter/upload/typeicon/201412/201412111007226234.jpg","typeListDesc":"休闲,消除,音乐,儿童益智,捕鱼,解谜,另类","id":8,"appCnNames":"游戏中心1.6正式版,开心消消乐,消灭糖果星星","isNet":false},{"gameNum":997,"color":"#2e997e","name":"角色扮演","icon":"http://img.wsdl.vivo.com.cn/appstore/gamecenter/upload/typeicon/201412/201412111037255111.jpg","typeListDesc":"魔幻,仙侠,回合制,三国,武侠,西游","id":4,"appCnNames":"时空猎人,乱斗西游2（全面革新）,去吧皮卡丘（全民宝贝）","isNet":false},{"gameNum":1501,"color":"#2e6399","name":"飞行射击","icon":"http://img.wsdl.vivo.com.cn/appstore/gamecenter/upload/typeicon/201412/201412111036521907.jpg","typeListDesc":"打飞机,FPS,模拟飞行,射击,坦克","id":9,"appCnNames":"全民枪战（火线突击）,穿越火线(单机版),熊出没之丛林大战","isNet":false},{"gameNum":1882,"color":"#cc7014","name":"经营策略","icon":"http://img.wsdl.vivo.com.cn/appstore/gamecenter/upload/typeicon/201412/201412111037184212.jpg","typeListDesc":"战争策略,养成,塔防,经营,沙盒模拟","id":5,"appCnNames":"植物大战僵尸2高清版,我的世界(口袋沙盘),熊出没2保卫海滩","isNet":false},{"gameNum":129,"color":"#b2476b","name":"棋牌天地","icon":"http://img.wsdl.vivo.com.cn/appstore/gamecenter/upload/typeicon/201412/201412111037106168.jpg","typeListDesc":"棋类,单机,麻将,斗地主,纸牌,桌游","id":10,"appCnNames":"欢乐斗地主,欢乐斗地主（送话费）,单机斗地主","isNet":false},{"gameNum":829,"color":"#2794b2","name":"网络游戏","icon":"http://img.wsdl.vivo.com.cn/appstore/gamecenter/upload/typeicon/201412/201412111036443960.jpg","typeListDesc":"3D,RPG,策略网游,动作竞技,卡牌","id":1,"appCnNames":"时空猎人,全民枪战（火线突击）,乱斗西游2（全面革新）","isNet":true},{"gameNum":2021,"color":"#2e6399","name":"动作冒险","icon":"http://img.wsdl.vivo.com.cn/appstore/gamecenter/upload/typeicon/201412/201412111036348600.jpg","typeListDesc":"跑酷,横版,格斗,冒险,跳跃","id":6,"appCnNames":"天天酷跑,地铁跑酷,神庙逃亡2","isNet":false},{"gameNum":1630,"color":"#FF6666","name":"赛车体育","icon":"http://img.wsdl.vivo.com.cn/appstore/gamecenter/upload/typeicon/201412/201412111037021795.jpg","typeListDesc":"赛车,足球,极限,摩托,篮球,台球,运动","id":55,"appCnNames":"天天飞车,3D终极狂飙4,3D狂野飞车-极速前进","isNet":false}]
     * special : [{"color":"#c1d708","referId":186,"name":"男生最爱","icon":"http://img.wsdl.vivo.com.cn/appstore/gamecenter/upload/type/201502/201502281748499509.jpg","id":3},{"color":"#ba87ff","referId":184,"name":"女神必备","icon":"http://img.wsdl.vivo.com.cn/appstore/gamecenter/upload/type/201501/201501280403543325.png","id":2},{"color":"#80c4f3","referId":164,"name":"3D网游","icon":"http://img.wsdl.vivo.com.cn/appstore/gamecenter/upload/type/201501/201501280403106509.png","id":4},{"color":"#ffad54","referId":193,"name":"儿童大全","icon":"http://img.wsdl.vivo.com.cn/appstore/gamecenter/upload/type/201503/201503091151591223.png","id":1}]
     * timestamp : 1445001229311
     */
    private boolean result;
    private List<MsgEntity> msg;
    private List<SpecialEntity> special;
    private String timestamp;
    private List<MsgEntity> list;

    public List<MsgEntity> getList() {
        return list;
    }

    public void setList(List<MsgEntity> list) {
        this.list = list;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setMsg(List<MsgEntity> msg) {
        this.msg = msg;
    }

    public void setSpecial(List<SpecialEntity> special) {
        this.special = special;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isResult() {
        return result;
    }

    public List<MsgEntity> getMsg() {
        return msg;
    }

    public List<SpecialEntity> getSpecial() {
        return special;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public class MsgEntity {
        /**
         * gameNum : 8574
         * color : #85a621
         * name : 休闲益智
         * icon : http://img.wsdl.vivo.com.cn/appstore/gamecenter/upload/typeicon/201412/201412111007226234.jpg
         * typeListDesc : 休闲,消除,音乐,儿童益智,捕鱼,解谜,另类
         * id : 8
         * appCnNames : 游戏中心1.6正式版,开心消消乐,消灭糖果星星
         * isNet : false
         */
        private int gameNum;
        private String color;
        private String typeListDesc;
        private String appCnNames;
        private boolean isNet;
        /**
         *    * gift : 0
         * net_game :
         * icon : http://img.wsdl.vivo.com.cn/appstore/developer/icon/201406/20140604110132594.jpg
         * official : 1
         * type : 经营策略
         * itemViewType : 0
         * commentNum : 0
         * apkurl : http://gamecenter.vivo.com.cn/clientRequest/gameDownload?id=9733&pkgName=com.sxiaoao.farmTD3
         * fitModel : true
         * download : 132876
         * versonName : 3.8.0
         * size : 15662
         * pkgName : com.sxiaoao.farmTD3
         * name : 僵尸农场2014
         * versonCode : 36
         * recommend_desc : 蔬菜塔防游戏，多种不同属性植物，能自由搭配组合。
         * recommendType : 0
         * comment : 4.3
         * id : 9733
         * first_pub : 0
         * desc : 蔬菜塔防游戏，多种不同属性植物，能自由搭配组合。
         */

        private String gift;
        private String net_game;
        private String icon;
        private int official;
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
        private String desc;

            //这四个是gift页面.gridview中不同的四个属性;
        private int gift_size;
        private int new_gift_size;
        private boolean new_gift_tag;
        private String title;

        public int getGift_size() {
            return gift_size;
        }

        public void setGift_size(int gift_size) {
            this.gift_size = gift_size;
        }

        public int getNew_gift_size() {
            return new_gift_size;
        }

        public void setNew_gift_size(int new_gift_size) {
            this.new_gift_size = new_gift_size;
        }

        public boolean isNew_gift_tag() {
            return new_gift_tag;
        }

        public void setNew_gift_tag(boolean new_gift_tag) {
            this.new_gift_tag = new_gift_tag;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isNet() {
            return isNet;
        }

        public String getGift() {
            return gift;
        }

        public void setGift(String gift) {
            this.gift = gift;
        }

        public String getNet_game() {
            return net_game;
        }

        public void setNet_game(String net_game) {
            this.net_game = net_game;
        }

        public int getOfficial() {
            return official;
        }

        public void setOfficial(int official) {
            this.official = official;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getItemViewType() {
            return itemViewType;
        }

        public void setItemViewType(String itemViewType) {
            this.itemViewType = itemViewType;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public String getApkurl() {
            return apkurl;
        }

        public void setApkurl(String apkurl) {
            this.apkurl = apkurl;
        }

        public boolean isFitModel() {
            return fitModel;
        }

        public void setFitModel(boolean fitModel) {
            this.fitModel = fitModel;
        }

        public int getDownload() {
            return download;
        }

        public void setDownload(int download) {
            this.download = download;
        }

        public String getVersonName() {
            return versonName;
        }

        public void setVersonName(String versonName) {
            this.versonName = versonName;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getPkgName() {
            return pkgName;
        }

        public void setPkgName(String pkgName) {
            this.pkgName = pkgName;
        }

        public String getVersonCode() {
            return versonCode;
        }

        public void setVersonCode(String versonCode) {
            this.versonCode = versonCode;
        }

        public String getRecommend_desc() {
            return recommend_desc;
        }

        public void setRecommend_desc(String recommend_desc) {
            this.recommend_desc = recommend_desc;
        }

        public String getRecommendType() {
            return recommendType;
        }

        public void setRecommendType(String recommendType) {
            this.recommendType = recommendType;
        }

        public double getComment() {
            return comment;
        }

        public void setComment(double comment) {
            this.comment = comment;
        }

        public String getFirst_pub() {
            return first_pub;
        }

        public void setFirst_pub(String first_pub) {
            this.first_pub = first_pub;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setGameNum(int gameNum) {
            this.gameNum = gameNum;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setTypeListDesc(String typeListDesc) {
            this.typeListDesc = typeListDesc;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setAppCnNames(String appCnNames) {
            this.appCnNames = appCnNames;
        }

        public void setIsNet(boolean isNet) {
            this.isNet = isNet;
        }

        public int getGameNum() {
            return gameNum;
        }

        public String getColor() {
            return color;
        }

        public String getName() {
            return name;
        }

        public String getIcon() {
            return icon;
        }

        public String getTypeListDesc() {
            return typeListDesc;
        }

        public int getId() {
            return id;
        }

        public String getAppCnNames() {
            return appCnNames;
        }

        public boolean isIsNet() {
            return isNet;
        }
    }

    public class SpecialEntity {
        /**
         * color : #c1d708
         * referId : 186
         * name : 男生最爱
         * icon : http://img.wsdl.vivo.com.cn/appstore/gamecenter/upload/type/201502/201502281748499509.jpg
         * id : 3
         */
        private String color;
        private int referId;
        private String name;
        private String icon;
        private int id;

        public void setColor(String color) {
            this.color = color;
        }

        public void setReferId(int referId) {
            this.referId = referId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getColor() {
            return color;
        }

        public int getReferId() {
            return referId;
        }

        public String getName() {
            return name;
        }

        public String getIcon() {
            return icon;
        }

        public int getId() {
            return id;
        }
    }


    private String hotWord_array;
    private int line;
    private String hotWord;


    public void setHotWord_array(String hotWord_array) {
        this.hotWord_array = hotWord_array;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setHotWord(String hotWord) {
        this.hotWord = hotWord;
    }



    public String getHotWord_array() {
        return hotWord_array;
    }

    public int getLine() {
        return line;
    }

    public String getHotWord() {
        return hotWord;
    }


}
