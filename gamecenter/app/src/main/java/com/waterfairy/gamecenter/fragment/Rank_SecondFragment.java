package com.waterfairy.gamecenter.fragment;

import com.waterfairy.gamecenter.utils.NetUtils;

/**
 *
 * @author james
 */
public class Rank_SecondFragment extends Rank_BaseFragment {
    @Override
    public String getUrl() {
        type="hot";
        return NetUtils.HTTPRANK;
    }
}
