package com.bw.movie.utils;

/**
 * author:author${车文飞}
 * data:2019/1/23
 */
public class Contacts {
    //BaseUrl
    public static final String BASE_URL="http://172.17.8.100/movieApi/";
    //登录
    public static final String LOGIN_URL="user/v1/login";
    //注册
    public static final String ZHUCE_URL="user/v1/registerUser";
    public static final String HOT_URL="movie/v1/findHotMovieList";
    public static final String RELEASE_URL="movie/v1/findReleaseMovieList";
    public static final String COMINGSOON_URL_="movie/v1/findComingSoonMovieList";
    public static final String RECOMMEND_URL="cinema/v1/findRecommendCinemas";

    public static final String NEARMOVIE_URL="cinema/v1/findNearbyCinemas";

    public static final String FINDMOVIEBYID_URL="movie/v1/findMoviesById";
    //关注
    public static final String CLICKMOVIE_URL="cinema/v1/verify/followCinema";
    //取消关注
    public static final String CLEARMOVIE_URL="cinema/v1/verify/cancelFollowCinema";
    //电影详情
    public static final String MOVIE_DETAIL_URL="movie/v1/findMoviesDetail";
    //影院详情
    public static final String SKIPMOIVE_URL="cinema/v1/findCinemaInfo";
    //影院评论
    public static final String PINGLUNMOIVE_URL="cinema/v1/findAllCinemaComment";
    public static final String TIMEMOVIE_URL="movie/v1/findMovieScheduleList";
    //查看影片评论
    public static final String FINDMOVIE_COMMENT_URL="movie/v1/findAllMovieComment";
    //发表影片评论
    public static final String SEN_MOVIECOMMENT_URL="movie/v1/verify/movieComment";
    //关注电影
    public static final String FOLLMOVIE_URL="movie/v1/verify/followMovie";
    //取消关注电影
    public static final String CANCEL_FOLLOWMOVIE="movie/v1/verify/cancelFollowMovie";
    //影院评论点赞
    public static final String CINEMA_COMMENT_GREAT="cinema/v1/verify/cinemaCommentGreat";
    //影院评论
    public static final String CINEMA_COMMENT="cinema/v1/verify/cinemaComment";



}
