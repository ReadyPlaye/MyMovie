package com.bw.movie.bean;

import java.util.List;


public class BeanCinemaComment {

    /**
     * result : [{"commentContent":"很棒","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-08-24/20190824094110.jpg","commentId":22,"commentTime":1567561874000,"commentUserId":13519,"commentUserName":"张茂森","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"${title}","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-08-28/20190828131359.jpg","commentId":21,"commentTime":1567473435000,"commentUserId":13514,"commentUserName":"乔总","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"你傻逼把","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-09-07/20190907140903.jpg","commentId":12,"commentTime":1567341399000,"commentUserId":13472,"commentUserName":"红衣佳人白衣友","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commentContent : 很棒
         * commentHeadPic : http://172.17.8.100/images/movie/head_pic/2019-08-24/20190824094110.jpg
         * commentId : 22
         * commentTime : 1567561874000
         * commentUserId : 13519
         * commentUserName : 张茂森
         * greatHeadPic : []
         * greatNum : 0
         * hotComment : 0
         * isGreat : 0
         */

        private String commentContent;
        private String commentHeadPic;
        private int commentId;
        private long commentTime;
        private int commentUserId;
        private String commentUserName;
        private int greatNum;
        private int hotComment;
        private int isGreat;

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCommentHeadPic() {
            return commentHeadPic;
        }

        public void setCommentHeadPic(String commentHeadPic) {
            this.commentHeadPic = commentHeadPic;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public int getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(int commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getCommentUserName() {
            return commentUserName;
        }

        public void setCommentUserName(String commentUserName) {
            this.commentUserName = commentUserName;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public int getHotComment() {
            return hotComment;
        }

        public void setHotComment(int hotComment) {
            this.hotComment = hotComment;
        }

        public int getIsGreat() {
            return isGreat;
        }

        public void setIsGreat(int isGreat) {
            this.isGreat = isGreat;
        }
    }
}
