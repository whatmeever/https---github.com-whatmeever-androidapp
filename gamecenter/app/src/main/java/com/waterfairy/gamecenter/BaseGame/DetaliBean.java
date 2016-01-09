package com.waterfairy.gamecenter.BaseGame;

import java.util.List;

/**
 * Created by Administrator on 2015/10/19.
 */
public class DetaliBean {
    public List<CommentEntity>comment;
    public static class CommentEntity{
        private int id;
        private String comment;
        private double score;
        private String version;
        private String user;
        private String date;
        private String model;



        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }
    }
    public List<CommentEntity> getComment() {
        return comment;
    }
    public void setComment(List<CommentEntity> comment) {
        this.comment = comment;
    }
   public static class Game {
        private String name;
        private String icon;
        private String type;
        private String date;
        public String desc;
        private int commentNum;
        private String screenshot;
        private int size;
        private double comment;
        private int download;
        private float versonCoode;
        private String versonName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public String getScreenshot() {
            return screenshot;
        }

        public void setScreenshot(String screenshot) {
            this.screenshot = screenshot;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public double getComment() {
            return comment;
        }

        public void setComment(double comment) {
            this.comment = comment;
        }

        public int getDownload() {
            return download;
        }

        public void setDownload(int download) {
            this.download = download;
        }

        public float getVersonCoode() {
            return versonCoode;
        }

        public void setVersonCoode(float versonCoode) {
            this.versonCoode = versonCoode;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

       public String getVersonName() {
           return versonName;
       }

       public void setVersonName(String versonName) {
           this.versonName = versonName;
       }
       //    @Override
//    public String toString() {
//        return "Game{" +
//                "name='" + name + '\'' +
//                ", icon='" + icon + '\'' +
//                '}';
//    }
    }
    public static class RelatedEmtity{
        private int id;
        private String name;
        private String icon;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
    public static class Comment_SC{
        private int score_1;
        private int score_2;
        private  int score_3;
        private int score_4;
        private int score_5;

        public int getScore_1() {
            return score_1;
        }

        public void setScore_1(int scrore_1) {
            this.score_1 = score_1;
        }

        public int getScore_2() {
            return score_2;
        }

        public void setScore_2(int score_2) {
            this.score_2 = score_2;
        }

        public int getScore_3() {
            return score_3;
        }

        public void setScore_3(int score_3) {
            this.score_3 = score_3;
        }

        public int getScore_4() {
            return score_4;
        }

        public void setScore_4(int score_4) {
            this.score_4 = score_4;
        }

        public int getScore_5() {
            return score_5;
        }

        public void setScore_5(int score_5) {
            this.score_5 = score_5;
        }
    }

}
