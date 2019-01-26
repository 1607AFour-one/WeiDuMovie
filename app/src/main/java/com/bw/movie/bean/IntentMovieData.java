package com.bw.movie.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author:author${车文飞}
 * data:2019/1/26
 */
public class IntentMovieData implements Serializable {
    List<HotMovieData.ResultBean> mHotList;
    List<ReleaseMovieData.ResultBean> mReleaseList;
    List<ComingSoonData.ResultBean> mComingList;

    public IntentMovieData(List<HotMovieData.ResultBean> mHotList, List<ReleaseMovieData.ResultBean> mReleaseList, List<ComingSoonData.ResultBean> mComingList) {
        this.mHotList = mHotList;
        this.mReleaseList = mReleaseList;
        this.mComingList = mComingList;
    }

    public List<HotMovieData.ResultBean> getmHotList() {
        return mHotList;
    }

    public List<ReleaseMovieData.ResultBean> getmReleaseList() {
        return mReleaseList;
    }

    public List<ComingSoonData.ResultBean> getmComingList() {
        return mComingList;
    }
}
