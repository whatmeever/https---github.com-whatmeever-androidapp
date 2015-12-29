package com.waterfairy.gamecenter.BaseGame;

import java.util.List;

/**
 *
 * @author james
 */
public class NetGameBean {

    private boolean result;
    private List<NewgameEntity> newgame;
    private List<AdinfoEntity> adinfo;
    private boolean hasNext;
    private List<RecommendEntity> recommend;
    private List<ChartEntity> chart;
    private int current_page;

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setNewgame(List<NewgameEntity> newgame) {
        this.newgame = newgame;
    }

    public void setAdinfo(List<AdinfoEntity> adinfo) {
        this.adinfo = adinfo;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public void setRecommend(List<RecommendEntity> recommend) {
        this.recommend = recommend;
    }

    public void setChart(List<ChartEntity> chart) {
        this.chart = chart;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public boolean isResult() {
        return result;
    }

    public List<NewgameEntity> getNewgame() {
        return newgame;
    }

    public List<AdinfoEntity> getAdinfo() {
        return adinfo;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public List<RecommendEntity> getRecommend() {
        return recommend;
    }

    public List<ChartEntity> getChart() {
        return chart;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public class NewgameEntity {
        /**
         *
         *
         * activity : 0
         * apkurl :
         * categoryId : 5
         * comment : 4.5
         * commentNum : 11
         * download : 36828
         * editor : 0
         * first_pub : 0
         * fitModel : true
         * gameType : 经营策略
         * gift : 1
         * icon :
         * id : 40102
         * itemViewType : 0
         * name : 魔灵保卫者
         * net_game : 1
         * pkgName : com.manwei.ElfTD.vivo
         * recommend_desc :  可爱Q萌 ，清新可爱萌动你心！
         * recommendType : 0
         * size : 94479
         * type : 经营策略
         * versonName : 3.1.1
         * versonCode : 1
         *
         * pach:""
         *
         */

        private String patch;

        public String getPatch() {
            return patch;
        }

        public void setPatch(String patch) {
            this.patch = patch;
        }

        private String activity;
        private String apkurl;
        private String beta;
        private String gift;
        private String editor;
        private String gameType;
        private String net_game;
        private String icon;
        private String type;
        private String itemViewType;
        private int commentNum;
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
        private int categoryId;

        public String getBeta() {
            return beta;
        }

        public void setBeta(String beta) {
            this.beta = beta;
        }

        public void setGift(String gift) {
            this.gift = gift;
        }

        public void setEditor(String editor) {
            this.editor = editor;
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

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getGift() {
            return gift;
        }

        public String getEditor() {
            return editor;
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

        public int getCategoryId() {
            return categoryId;
        }
    }

    public class AdinfoEntity {
        /**
         * picUrl : http://img.wsdl.vivo.com.cn/appstore/gamecenter/upload/recommend/201510/201510231656522518.jpg
         * id : 163922
         * title : 部落战歌
         * relativeType : 1
         * relative : {"apkurl":"http://gamecenter.vivo.com.cn/clientRequest/gameDownload?id=35540&pkgName=com.platform.wowlegend.vivo","versonName":"5.1.0","size":158938,"pkgName":"com.platform.wowlegend.vivo","name":"部落战歌","icon":"http://img.wsdl.vivo.com.cn/appstore/developer/icon/201509/20150916175718929.jpg","versonCode":"159","comment":4.3,"id":35540}
         */
        private String picUrl;
        private int id;
        private String title;
        private String relativeType;
        private RelativeEntity relative;

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setRelativeType(String relativeType) {
            this.relativeType = relativeType;
        }

        public void setRelative(RelativeEntity relative) {
            this.relative = relative;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getRelativeType() {
            return relativeType;
        }

        public RelativeEntity getRelative() {
            return relative;
        }

        public class RelativeEntity {
            /**
             * apkurl : http://gamecenter.vivo.com.cn/clientRequest/gameDownload?id=35540&pkgName=com.platform.wowlegend.vivo
             * versonName : 5.1.0
             * size : 158938
             * pkgName : com.platform.wowlegend.vivo
             * name : 部落战歌
             * icon : http://img.wsdl.vivo.com.cn/appstore/developer/icon/201509/20150916175718929.jpg
             * versonCode : 159
             * comment : 4.3
             * id : 35540
             */
            private String apkurl;
            private String versonName;
            private int size;
            private String pkgName;
            private String name;
            private String icon;
            private String versonCode;
            private double comment;
            private int id;

            public void setApkurl(String apkurl) {
                this.apkurl = apkurl;
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

            public void setIcon(String icon) {
                this.icon = icon;
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

            public String getApkurl() {
                return apkurl;
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

            public String getIcon() {
                return icon;
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
        }
    }

    public  static  class RecommendEntity {
        /**
         * gift : 1
         * activity : 0
         * net_game :
         * icon : http://img.wsdl.vivo.com.cn/appstore/developer/icon/201510/20151020170638762.png
         * type : 经营策略
         * itemViewType : 0
         * commentNum : 0
         * apkurl : http://gamecenter.vivo.com.cn/clientRequest/gameDownload?id=40102&pkgName=com.manwei.ElfTD.vivo
         * fitModel : true
         * download : 36828
         * versonName : 3.1.1
         * size : 94479
         * pkgName : com.manwei.ElfTD.vivo
         * name : 魔灵保卫者
         * versonCode : 1
         * recommend_desc :  可爱Q萌 ，清新可爱萌动你心！
         * recommendType : 0
         * comment : 4.5
         * id : 40102
         * categoryId : 5
         * beta : 0
         * threeDimension : 0
         */
        private String gift;
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
        private int categoryId;
        private String beta;
        private String threeDimension;

        public void setGift(String gift) {
            this.gift = gift;
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

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public void setBeta(String beta) {
            this.beta = beta;
        }

        public void setThreeDimension(String threeDimension) {
            this.threeDimension = threeDimension;
        }

        public String getGift() {
            return gift;
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

        public int getCategoryId() {
            return categoryId;
        }

        public String getBeta() {
            return beta;
        }

        public String getThreeDimension() {
            return threeDimension;
        }
    }

    public class ChartEntity {
        /**
         * picUrl : http://img.wsdl.vivo.com.cn/appstore/gamecenter/upload/chart/201501/201501280343277454.png
         * id : 105
         * title : 礼包
         * relativeType : 4
         * relative : {"subUrl":"showPosition=1","name":"礼包","id":0}
         */
        private String picUrl;
        private int id;
        private String title;
        private String relativeType;
        private RelativeEntity relative;

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setRelativeType(String relativeType) {
            this.relativeType = relativeType;
        }

        public void setRelative(RelativeEntity relative) {
            this.relative = relative;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getRelativeType() {
            return relativeType;
        }

        public RelativeEntity getRelative() {
            return relative;
        }

        public class RelativeEntity {
            /**
             * subUrl : showPosition=1
             * name : 礼包
             * id : 0
             */
            private String subUrl;
            private String name;
            private int id;

            public void setSubUrl(String subUrl) {
                this.subUrl = subUrl;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSubUrl() {
                return subUrl;
            }

            public String getName() {
                return name;
            }

            public int getId() {
                return id;
            }
        }
    }
}
