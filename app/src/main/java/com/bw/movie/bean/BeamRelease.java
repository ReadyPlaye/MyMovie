package com.bw.movie.bean;

import java.util.List;


public class BeamRelease {

    /**
     * result : [{"director":"贾樟柯","imageUrl":"http://172.17.8.100/images/movie/stills/jhen/jhen1.jpg","movieId":19,"name":"江湖儿女","score":9.7,"starring":"赵涛,廖凡,徐峥,梁嘉艳"},{"director":"林德禄","imageUrl":"http://172.17.8.100/images/movie/stills/ftfb3/ftfb(3)1.jpg","movieId":17,"name":"反贪风暴3","score":9.1,"starring":"古天乐,张智霖,郑嘉颖,邓丽欣,谢天华"},{"director":"钱嘉乐","imageUrl":"http://172.17.8.100/images/movie/stills/hjxd/hjxd1.jpg","movieId":18,"name":"黄金兄弟","score":9.3,"starring":"郑伊健,陈小春,谢天华,林晓峰"},{"director":"吕乐","imageUrl":"http://172.17.8.100/images/movie/stills/zdn/zdn1.jpg","movieId":21,"name":"找到你","score":8.5,"starring":"姚晨,马伊琍,袁文康,吴昊宸"},{"director":"庄文强","imageUrl":"http://172.17.8.100/images/movie/stills/ws/ws1.jpg","movieId":20,"name":"无双","score":8.6,"starring":"周润发,郭富城,张静初,冯文娟,廖启智"},{"director":"是枝裕和","imageUrl":"http://172.17.8.100/images/movie/stills/xtjz/xtjz1.jpg","movieId":9,"name":"小偷家族","score":9.4,"starring":"中川雅也,安藤樱,松冈茉优,"},{"director":"拉加·高斯内尔","imageUrl":"http://172.17.8.100/images/movie/stills/wxwd/wxwd1.jpg","movieId":7,"name":"汪星卧底","score":9.2,"starring":"卢达克里斯,威尔·阿奈特,娜塔莎·雷昂"},{"director":"乔·德特杜巴","imageUrl":"http://172.17.8.100/images/movie/stills/jcs/jcs1.jpg","movieId":11,"name":"巨齿鲨","score":9.6,"starring":"杰森·斯坦森,李冰冰,雷恩·威尔森"},{"director":"刘阔","imageUrl":"http://172.17.8.100/images/movie/stills/fyz/fyz1.jpg","movieId":12,"name":"风语咒","score":9.7,"starring":"路知行,阎萌萌,褚珺"},{"director":"黄渤","imageUrl":"http://172.17.8.100/images/movie/stills/ychx/ychx1.jpg","movieId":13,"name":"一出好戏","score":9.6,"starring":"黄渤,舒淇,王宝强,张艺兴"}]
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
         * director : 贾樟柯
         * imageUrl : http://172.17.8.100/images/movie/stills/jhen/jhen1.jpg
         * movieId : 19
         * name : 江湖儿女
         * score : 9.7
         * starring : 赵涛,廖凡,徐峥,梁嘉艳
         */

        private String director;
        private String imageUrl;
        private int movieId;
        private String name;
        private double score;
        private String starring;

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getStarring() {
            return starring;
        }

        public void setStarring(String starring) {
            this.starring = starring;
        }
    }
}
