package com.bw.movie.bean;

import java.util.List;


public class BeanDetails {

    /**
     * result : {"commentNum":0,"duration":"115分钟","imageUrl":"http://172.17.8.100/images/movie/stills/jcs/jcs1.jpg","movieActor":[{"name":"杰森·斯坦森","photo":"http://172.17.8.100/images/movie/actor/jcs/jiesen.jpg","role":"乔纳斯·泰勒"},{"name":"李冰冰","photo":"http://172.17.8.100/images/movie/actor/jcs/libingbing.jpg","role":"张苏茵"},{"name":"雷恩·威尔森","photo":"http://172.17.8.100/images/movie/actor/jcs/leien.jpg","role":"莫里斯"}],"movieDirector":[{"name":"乔·德特杜巴","photo":"http://172.17.8.100/images/movie/director/jcs/1.jpg"}],"movieId":11,"movieType":"动作 / 冒险 / 科幻","name":"巨齿鲨","placeOrigin":"美国,中国香港,中国大陆","posterList":["http://172.17.8.100/images/movie/stills/jcs/jcs1.jpg","http://172.17.8.100/images/movie/stills/jcs/jcs2.jpg","http://172.17.8.100/images/movie/stills/jcs/jcs3.jpg","http://172.17.8.100/images/movie/stills/jcs/jcs4.jpg","http://172.17.8.100/images/movie/stills/jcs/jcs5.jpg","http://172.17.8.100/images/movie/stills/jcs/jcs6.jpg"],"releaseTime":1536336000000,"score":9.6,"shortFilmList":[{"imageUrl":"http://172.17.8.100/images/movie/stills/jcs/jcs2.jpg","videoUrl":"http://172.17.8.100/video/movie/jcs/jcs1.ts"},{"imageUrl":"http://172.17.8.100/images/movie/stills/jcs/jcs3.jpg","videoUrl":"http://172.17.8.100/video/movie/jcs/jcs2.ts"},{"imageUrl":"http://172.17.8.100/images/movie/stills/jcs/jcs4.jpg","videoUrl":"http://172.17.8.100/video/movie/jcs/jcs3.ts"}],"summary":"一项由中国主导的国际科研项目，正在马里亚纳海沟深处进行时，遭遇未知生物攻击，科研人员被困海底。前美国海军陆战队深海潜水专家乔纳斯·泰勒（杰森·斯坦森 饰）受命前往营救，再度遭遇数年前曾经在深海给自己留下终身难以磨灭记忆的史前生物巨齿鲨。乔纳斯联手科研项目中的中国女科学家张苏茵（李冰冰 饰）成功营救了被困人员，但营救行动却意外造成了巨齿鲨逃离深海。当史前巨兽重返浅海，人类将为自己对大自然的贪婪付出惨重的代价......","whetherFollow":2}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        /**
         * commentNum : 0
         * duration : 115分钟
         * imageUrl : http://172.17.8.100/images/movie/stills/jcs/jcs1.jpg
         * movieActor : [{"name":"杰森·斯坦森","photo":"http://172.17.8.100/images/movie/actor/jcs/jiesen.jpg","role":"乔纳斯·泰勒"},{"name":"李冰冰","photo":"http://172.17.8.100/images/movie/actor/jcs/libingbing.jpg","role":"张苏茵"},{"name":"雷恩·威尔森","photo":"http://172.17.8.100/images/movie/actor/jcs/leien.jpg","role":"莫里斯"}]
         * movieDirector : [{"name":"乔·德特杜巴","photo":"http://172.17.8.100/images/movie/director/jcs/1.jpg"}]
         * movieId : 11
         * movieType : 动作 / 冒险 / 科幻
         * name : 巨齿鲨
         * placeOrigin : 美国,中国香港,中国大陆
         * posterList : ["http://172.17.8.100/images/movie/stills/jcs/jcs1.jpg","http://172.17.8.100/images/movie/stills/jcs/jcs2.jpg","http://172.17.8.100/images/movie/stills/jcs/jcs3.jpg","http://172.17.8.100/images/movie/stills/jcs/jcs4.jpg","http://172.17.8.100/images/movie/stills/jcs/jcs5.jpg","http://172.17.8.100/images/movie/stills/jcs/jcs6.jpg"]
         * releaseTime : 1536336000000
         * score : 9.6
         * shortFilmList : [{"imageUrl":"http://172.17.8.100/images/movie/stills/jcs/jcs2.jpg","videoUrl":"http://172.17.8.100/video/movie/jcs/jcs1.ts"},{"imageUrl":"http://172.17.8.100/images/movie/stills/jcs/jcs3.jpg","videoUrl":"http://172.17.8.100/video/movie/jcs/jcs2.ts"},{"imageUrl":"http://172.17.8.100/images/movie/stills/jcs/jcs4.jpg","videoUrl":"http://172.17.8.100/video/movie/jcs/jcs3.ts"}]
         * summary : 一项由中国主导的国际科研项目，正在马里亚纳海沟深处进行时，遭遇未知生物攻击，科研人员被困海底。前美国海军陆战队深海潜水专家乔纳斯·泰勒（杰森·斯坦森 饰）受命前往营救，再度遭遇数年前曾经在深海给自己留下终身难以磨灭记忆的史前生物巨齿鲨。乔纳斯联手科研项目中的中国女科学家张苏茵（李冰冰 饰）成功营救了被困人员，但营救行动却意外造成了巨齿鲨逃离深海。当史前巨兽重返浅海，人类将为自己对大自然的贪婪付出惨重的代价......
         * whetherFollow : 2
         */

        private int commentNum;
        private String duration;
        private String imageUrl;
        private int movieId;
        private String movieType;
        private String name;
        private String placeOrigin;
        private long releaseTime;
        private double score;
        private String summary;
        private int whetherFollow;
        private List<MovieActorBean> movieActor;
        private List<MovieDirectorBean> movieDirector;
        private List<String> posterList;
        private List<ShortFilmListBean> shortFilmList;

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public String getMovieType() {
            return movieType;
        }

        public void setMovieType(String movieType) {
            this.movieType = movieType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceOrigin() {
            return placeOrigin;
        }

        public void setPlaceOrigin(String placeOrigin) {
            this.placeOrigin = placeOrigin;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public int getWhetherFollow() {
            return whetherFollow;
        }

        public void setWhetherFollow(int whetherFollow) {
            this.whetherFollow = whetherFollow;
        }

        public List<MovieActorBean> getMovieActor() {
            return movieActor;
        }

        public void setMovieActor(List<MovieActorBean> movieActor) {
            this.movieActor = movieActor;
        }

        public List<MovieDirectorBean> getMovieDirector() {
            return movieDirector;
        }

        public void setMovieDirector(List<MovieDirectorBean> movieDirector) {
            this.movieDirector = movieDirector;
        }

        public List<String> getPosterList() {
            return posterList;
        }

        public void setPosterList(List<String> posterList) {
            this.posterList = posterList;
        }

        public List<ShortFilmListBean> getShortFilmList() {
            return shortFilmList;
        }

        public void setShortFilmList(List<ShortFilmListBean> shortFilmList) {
            this.shortFilmList = shortFilmList;
        }

        public static class MovieActorBean {
            /**
             * name : 杰森·斯坦森
             * photo : http://172.17.8.100/images/movie/actor/jcs/jiesen.jpg
             * role : 乔纳斯·泰勒
             */

            private String name;
            private String photo;
            private String role;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }
        }

        public static class MovieDirectorBean {
            /**
             * name : 乔·德特杜巴
             * photo : http://172.17.8.100/images/movie/director/jcs/1.jpg
             */

            private String name;
            private String photo;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }
        }

        public static class ShortFilmListBean {
            /**
             * imageUrl : http://172.17.8.100/images/movie/stills/jcs/jcs2.jpg
             * videoUrl : http://172.17.8.100/video/movie/jcs/jcs1.ts
             */

            private String imageUrl;
            private String videoUrl;

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getVideoUrl() {
                return videoUrl;
            }

            public void setVideoUrl(String videoUrl) {
                this.videoUrl = videoUrl;
            }
        }
    }
}
