package com.waterfairy.gamecenter.BaseGame;

import java.util.List;

/**
 * Created by shui on 2015/10/20.
 */
public class ActivityListBean  {

    /**
     * result : true
     * msg : [{"gift_size":5,"new_gift_size":2,"pkgName":"com.thedream.pilijianghu.vivo","new_gift_tag":true,"icon":"http://img.wsdl.vivo.com.cn/appstore/developer/icon/201510/2015100817401862.png","id":38656,"title":"霹雳江湖"},{"gift_size":1,"new_gift_size":1,"pkgName":"com.morefun.xsanguo.vivo","new_gift_tag":true,"icon":"http://img.wsdl.vivo.com.cn/appstore/developer/icon/201508/20150806111507656.jpg","id":14566,"title":"X三国"},{"gift_size":15,"new_gift_size":1,"pkgName":"com.gzyouai.tlzr.vivo","new_gift_tag":true,"icon":"http://img.wsdl.vivo.com.cn/appstore/developer/icon/201503/20150312123527640.png","id":13608,"title":"屠龙之刃（红名爆装）"},{"gift_size":2,"new_gift_size":1,"pkgName":"com.mobimirage.android.Legend.vivo","new_gift_tag":true,"icon":"http://img.wsdl.vivo.com.cn/appstore/developer/icon/201509/20150923113222461.jpg","id":11463,"title":"决战沙城（传奇授权）"},{"gift_size":3,"new_gift_size":3,"pkgName":"com.tigercool.fmz.v5.vivo","new_gift_tag":true,"icon":"http://img.wsdl.vivo.com.cn/appstore/developer/icon/201507/20150714195945106.jpg","id":14554,"title":"伏魔者(新英雄)"},{"gift_size":7,"new_gift_size":1,"pkgName":"com.Magicworks.StarFleetDawn.vivo","new_gift_tag":true,"icon":"http://img.wsdl.vivo.com.cn/appstore/developer/icon/201509/20150923133011860.jpg","id":37788,"title":"星舰：黎明"},{"gift_size":2,"new_gift_size":2,"pkgName":"com.longsgoo.icefire.vivo","new_gift_tag":true,"icon":"http://img.wsdl.vivo.com.cn/appstore/developer/icon/201510/20151015160830979.jpg","id":39610,"title":"冰与火传说"},{"gift_size":3,"new_gift_size":1,"pkgName":"com.ferrygame.yunzhongge.vivo","new_gift_tag":true,"icon":"http://img.wsdl.vivo.com.cn/appstore/developer/icon/201510/20151010084601318.jpg","id":35826,"title":"云中歌正版手游"},{"gift_size":4,"new_gift_size":1,"pkgName":"com.youzu.cshjz.vivo","new_gift_tag":true,"icon":"http://img.wsdl.vivo.com.cn/appstore/developer/icon/201508/20150807120555172.png","id":30788,"title":"超时空机战(张馨予约你飞机大战)"},{"gift_size":6,"new_gift_size":1,"pkgName":"com.youzu.snsgz.vivo","new_gift_tag":true,"icon":"http://img.wsdl.vivo.com.cn/appstore/developer/icon/201509/20150929154712174.png","id":13397,"title":"少年三国志(狂送3万元宝)"},{"gift_size":7,"new_gift_size":3,"pkgName":"com.askd.pocketmon.vivo","new_gift_tag":true,"icon":"http://img.wsdl.vivo.com.cn/appstore/developer/icon/201507/20150717153603840.jpg","id":28434,"title":"口袋妖怪复刻"},{"gift_size":3,"new_gift_size":3,"pkgName":"com.funnyhux.pokemon.vivo","new_gift_tag":true,"icon":"http://img.wsdl.vivo.com.cn/appstore/developer/icon/201510/20151019123811445.png","id":39548,"title":"口袋妖怪GBA"},{"gift_size":3,"new_gift_size":3,"pkgName":"cn.mobage.g12000142.vivo","new_gift_tag":true,"icon":"http://img.wsdl.vivo.com.cn/appstore/developer/icon/201510/20151010165101578.jpg","id":39242,"title":"NBA梦之队2"},{"gift_size":1,"new_gift_size":1,"pkgName":"com.snailcolor.cs.vivo","new_gift_tag":true,"icon":"http://img.wsdl.vivo.com.cn/appstore/developer/icon/201510/20151014121224300.jpg","id":39604,"title":"全球反恐"},{"gift_size":15,"new_gift_size":1,"pkgName":"com.ttxw.vivo","new_gift_tag":true,"icon":"http://img.wsdl.vivo.com.cn/appstore/developer/icon/201510/20151008183925649.png","id":9769,"title":"天天炫舞（浪漫舞会）"},{"gift_size":21,"new_gift_size":5,"pkgName":"org.mxxx.suyou.qycom.vivo","new_gift_tag":true,"icon":"http://img.wsdl.vivo.com.cn/appstore/developer/icon/201507/2015070114202420.jpg","id":14278,"title":"梦幻仙缘"},{"gift_size":1,"new_gift_size":1,"pkgName":"com.shinian.wineleven.vivo","new_gift_tag":true,"icon":"http://img.wsdl.vivo.com.cn/appstore/developer/icon/201510/20151010111205625.jpg","id":39586,"title":"传奇十一人-足球经理"},{"gift_size":6,"new_gift_size":2,"pkgName":"com.hoolai.fentianzhinu.vivo","new_gift_tag":true,"icon":"http://img.wsdl.vivo.com.cn/appstore/developer/icon/201509/20150908161304928.jpg","id":31214,"title":"焚天之怒"},{"gift_size":21,"new_gift_size":3,"pkgName":"com.platform2y9y.youai.common.xianjianhj.vivo","new_gift_tag":true,"icon":"http://img.wsdl.vivo.com.cn/appstore/developer/icon/201508/20150814130423112.png","id":28226,"title":"仙剑幻境"},{"gift_size":7,"new_gift_size":1,"pkgName":"com.suyou.mxxx.vivo","new_gift_tag":true,"icon":"http://img.wsdl.vivo.com.cn/appstore/developer/icon/201507/2015071718005298.jpg","id":13665,"title":"梦想仙侠（新资料片之群霸天下）"}]
     * hasNext : true
     * current_page : 1
     */
    private boolean result;
    private List<MsgEntity> msg;
    private boolean hasNext;
    private int current_page;

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

    public class MsgEntity {
        /**
         * gift_size : 5
         * new_gift_size : 2
         * pkgName : com.thedream.pilijianghu.vivo
         * new_gift_tag : true
         * icon : http://img.wsdl.vivo.com.cn/appstore/developer/icon/201510/2015100817401862.png
         * id : 38656
         * title : 霹雳江湖
         */
        private int gift_size;
        private int new_gift_size;
        private String pkgName;
        private boolean new_gift_tag;
        private String icon;
        private int id;
        private String title;

        public void setGift_size(int gift_size) {
            this.gift_size = gift_size;
        }

        public void setNew_gift_size(int new_gift_size) {
            this.new_gift_size = new_gift_size;
        }

        public void setPkgName(String pkgName) {
            this.pkgName = pkgName;
        }

        public void setNew_gift_tag(boolean new_gift_tag) {
            this.new_gift_tag = new_gift_tag;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getGift_size() {
            return gift_size;
        }

        public int getNew_gift_size() {
            return new_gift_size;
        }

        public String getPkgName() {
            return pkgName;
        }

        public boolean isNew_gift_tag() {
            return new_gift_tag;
        }

        public String getIcon() {
            return icon;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }
    }
}
