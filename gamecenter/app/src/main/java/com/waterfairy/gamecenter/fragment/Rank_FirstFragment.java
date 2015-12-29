package com.waterfairy.gamecenter.fragment;

import com.waterfairy.gamecenter.utils.NetUtils;

/**
 *
 * @author james
 */
public class Rank_FirstFragment extends Rank_BaseFragment {
    @Override
    public String getUrl() {
        type="favor";
        return NetUtils.HTTPRANK;
    }
}
