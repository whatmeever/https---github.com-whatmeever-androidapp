package com.waterfairy.gamecenter.fragment;

import com.waterfairy.gamecenter.utils.NetUtils;

/**
 * Created by Administrator on 2015/10/16.
 */
public class Rank_ThridFragment extends Rank_BaseFragment {
    @Override
    public String getUrl() {
        type="acclaim";
        return NetUtils.HTTPRANK;
    }
}
