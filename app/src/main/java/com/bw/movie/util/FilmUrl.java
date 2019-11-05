package com.bw.movie.util;


public class FilmUrl {
    //查询正在上映电影列表:GET
    public static final String RELEASE_URL="movieApi/movie/v2/findReleaseMovieList";
    //查询即将上映电影列表:GET  http://172.17.8.100/movieApi/movie/v2/findComingSoonMovieList
    public static final String COMING_URL="movieApi/movie/v2/findComingSoonMovieList";
    //查询热门电影列表:GET
    public static final String HOT_URL="movieApi/movie/v2/findHotMovieList";
    //查询电影详情:GET
    public static final String MOVIES_DETAIL_URL="movieApi/movie/v2/findMoviesDetail";
    //根据关键字查询电影信息:GET
    public static final String MOVIES_KETWORD_URL="movieApi/movie/v2/findMovieByKeyword";
    //关注电影:GET
    public static final String MOVIES_FOLLOW_URL="movieApi/movie/v1/verify/followMovie";
    //取消关注电影:GET
    public static final String MOVIES_NO_FOLLOW_URL="movieApi/movie/v1/verify/cancelFollowMovie";
    //预约:POST
    public static final String MOVIES_RESERVE_URL="movieApi/movie/v2/verify/reserve";
    //根据电影id,区域id 查询播放影院信息:GET
    public static final String MOVIES_CINEMA_INFOBY_REGION_URL="movieApi/movie/v2/findCinemasInfoByRegion";
    //根据电影id，时间 查询播放影院信息:GET
    public static final String MOVIES_CINEMA_INFOBY_FATE_URL="movieApi/movie/v2/findCinemasInfoByDate";
    //根据电影价格查询播放影院信息:GET
    public static final String MOVIES_CINEMA_INFOBY_PRICE_URL="movieApi/movie/v2/findCinemasInfoByPrice";
    //根据电影ID和影院ID查询影厅排期列表:GET
    public static final String MOVIE_SCHEDULE_URL="movieApi/movie/v2/findMovieSchedule";
    //根据影厅id 查询座位信息:GET
    public static final String SEATINFO_URL="movieApi/movie/v2/findSeatInfo";
    //购票下单:POST
    public static final String MOVIES_TICKETS_URL="movieApi/movie/v2/verify/buyMovieTickets";

}
