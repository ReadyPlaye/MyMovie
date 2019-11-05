package com.bw.movie.util;


public class UserUrl {
//    public static final String BASE_URL="http://mobile.bwstudent.com/";
//    public static final String BASE_URL="http://172.17.8.100/";
    public static boolean isBeBug = true;
    //外网地址
    public static final String BASE_EXT_URL="http://mobile.bwstudent.com/";
    //内网
    public static final String BASE_NA_URL="http://172.17.8.100/";
    public static String baseUser(){
        return isBeBug == true ? BASE_NA_URL:BASE_EXT_URL;
    }
    //
    public static final String BASE_URL=baseUser();

    //发送邮箱验证码:POST
    public static final String EMAIL_CODE_URL="movieApi/user/v2/sendOutEmailCode";
    //注册:POST
    public static final String REGISTER_URL="movieApi/user/v2/register";
    //登陆:POST
    public static final String LOGIN_URL="movieApi/user/v2/login";
    //微信登陆:POST
    public static final String WX_LOGIN_URL="movieApi/user/v1/weChatBindingLogin";
    //根据用户ID查询用户信息:GET
    public static final String USER_INFOBY_URL="movieApi/user/v1/verify/getUserInfoByUserId";
    //查询用户关注电影列表:GET
    public static final String USER_MOVIE_URL="movieApi/user/v2/verify/findUserFollowMovieList";
    //查询用户关注影院列表:GET
    public static final String USER_CINEMA_URL="movieApi/user/v2/verify/findUserFollowCinemaList";
    //查询看过的电影:GET
    public static final String USER_3_URL="movieApi/user/v2/verify/findSeenMovie";
    //我的电影票:GET
    public static final String USER_TICKET_URL="movieApi/user/v2/verify/findMyMovieTicket";
    //购票记录:GET
    public static final String USER_5_URL="movieApi/user/v2/verify/findUserBuyTicketRecord";
    //查询用户预约电影信息:GET
    public static final String USER_RESERVE_URL="movieApi/user/v2/verify/findUserReserve";




}
