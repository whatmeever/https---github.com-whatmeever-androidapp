package com.waterfairy.gamecenter.BaseGame;

import java.util.List;

/**
 * Created by shui on 2015/10/24.
 */
public class ActivityDetailBean {
    private boolean result;
    private ResultInfoEntity resultInfo;
    private MsgEntity msg;

    public MsgEntity getMsg() {
        return msg;
    }

    public void setMsg(MsgEntity msg) {
        this.msg = msg;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public ResultInfoEntity getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(ResultInfoEntity resultInfo) {
        this.resultInfo = resultInfo;
    }

    public class MsgEntity {
        private String comment_status;
        private String desc;
        private String endDate;
        private String icon_url;
        private int id;
        private String startDate;
        private String status;
        private String summary;
        private int tag;
        private String title;
        private String view_type;
//        private List<GameInfoEntities> gameInfo;
//
//        public List<GameInfoEntities> getGameInfo() {
//            return gameInfo;
//        }

        private GameInfoEntities gameInfo;
//        public void setGameInfo(List<GameInfoEntities> gameInfo) {
//            this.gameInfo = gameInfo;
//        }

        public GameInfoEntities getGameInfo() {
            return gameInfo;
        }

        public void setGameInfo(GameInfoEntities gameInfo) {
            this.gameInfo = gameInfo;
        }

        public class GameInfoEntities {
            private String activity;
            private String apkurl;
            private float comment;
            private int commentNum;
            private int download;
            private String first_pub;
            private String fitModel;
            private String gameType;
            private String gift;
            private String icon;
            private int id;
            private String itemViewType;
            private String name;
            private String net_game;
            private String patch;
            private String pkgName;
            private String recommendType;
            private String recommend_desc;
            private int size;
            private String type;
            private String versonCode;
            private String versonName;

            public String getActivity() {
                return activity;
            }

            public void setActivity(String activity) {
                this.activity = activity;
            }

            public String getApkurl() {
                return apkurl;
            }

            public void setApkurl(String apkurl) {
                this.apkurl = apkurl;
            }

            public float getComment() {
                return comment;
            }

            public void setComment(float comment) {
                this.comment = comment;
            }

            public int getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(int commentNum) {
                this.commentNum = commentNum;
            }

            public int getDownload() {
                return download;
            }

            public void setDownload(int download) {
                this.download = download;
            }

            public String getFirst_pub() {
                return first_pub;
            }

            public void setFirst_pub(String first_pub) {
                this.first_pub = first_pub;
            }

            public String getFitModel() {
                return fitModel;
            }

            public void setFitModel(String fitModel) {
                this.fitModel = fitModel;
            }

            public String getGameType() {
                return gameType;
            }

            public void setGameType(String gameType) {
                this.gameType = gameType;
            }

            public String getGift() {
                return gift;
            }

            public void setGift(String gift) {
                this.gift = gift;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getItemViewType() {
                return itemViewType;
            }

            public void setItemViewType(String itemViewType) {
                this.itemViewType = itemViewType;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNet_game() {
                return net_game;
            }

            public void setNet_game(String net_game) {
                this.net_game = net_game;
            }

            public String getPatch() {
                return patch;
            }

            public void setPatch(String patch) {
                this.patch = patch;
            }

            public String getPkgName() {
                return pkgName;
            }

            public void setPkgName(String pkgName) {
                this.pkgName = pkgName;
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

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getVersonCode() {
                return versonCode;
            }

            public void setVersonCode(String versonCode) {
                this.versonCode = versonCode;
            }

            public String getVersonName() {
                return versonName;
            }

            public void setVersonName(String versonName) {
                this.versonName = versonName;
            }
        }

        public String getComment_status() {
            return comment_status;
        }

        public void setComment_status(String comment_status) {
            this.comment_status = comment_status;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getIcon_url() {
            return icon_url;
        }

        public void setIcon_url(String icon_url) {
            this.icon_url = icon_url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public int getTag() {
            return tag;
        }

        public void setTag(int tag) {
            this.tag = tag;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getView_type() {
            return view_type;
        }

        public void setView_type(String view_type) {
            this.view_type = view_type;
        }
    }

    public class ResultInfoEntity {
        private String taskKey;

        public String getTaskKey() {
            return taskKey;
        }

        public void setTaskKey(String taskKey) {
            this.taskKey = taskKey;
        }
    }

}
