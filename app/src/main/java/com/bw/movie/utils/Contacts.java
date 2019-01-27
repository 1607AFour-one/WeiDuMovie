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
    //根据Id查找点电影信息
    public static final String FINDMOVIEBYID_URL="movie/v1/findMoviesById";
    //关注
    public static final String CLICKMOVIE_URL="cinema/v1/verify/followCinema";
    //取消关注
    public static final String CLEARMOVIE_URL="cinema/v1/verify/cancelFollowCinema";
    //电影详情
    public static final String MOVIE_DETAIL_URL="movie/v1/findMoviesDetail";



}
