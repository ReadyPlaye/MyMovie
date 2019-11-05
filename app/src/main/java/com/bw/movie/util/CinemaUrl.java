package com.bw.movie.util;


public class CinemaUrl {
    //根据电影院名称模糊查询电影院:GET
    public static final String CINEMA_ALL_URL="movieApi/cinema/v1/findAllCinemas";
    //查询推荐影院信息:GET
    public static final String CINEMA_RECOMMEND_URL="movieApi/cinema/v1/findRecommendCinemas";
    //查询附近影院:GET
    public static final String CINEMA_NEARBY_URL="movieApi/cinema/v1/findNearbyCinemas";
    //根据区域查询影院:GET
    public static final String CINEMA_REGION_URL="movieApi/cinema/v2/findCinemaByRegion";
    //查询影院信息明细:GET
    public static final String CINEMA_INFO_URL="movieApi/cinema/v1/findCinemaInfo";
    //查询影院用户评论列表:GET
    public static final String CINEMA_COMMENT_URL="movieApi/cinema/v1/findAllCinemaComment";
    //关注影院:GET
    public static final String CINEMA_FOLLOW_URL="movieApi/cinema/v1/verify/followCinema";
    //取消关注影院:GET
    public static final String CINEMA_NOFOLLOW_URL="movieApi/cinema/v1/verify/cancelFollowCinema";
    //查询影院下的电影排期:GET
    public static final String CINEMA_CCHEDULE_URL="movieApi/cinema/v2/findCinemaScheduleList";









}
