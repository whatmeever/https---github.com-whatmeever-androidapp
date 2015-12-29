package com.waterfairy.gamecenter.fragment;

import com.waterfairy.gamecenter.utils.NetUtils;

/**
 *
 * @author james
 */
public class Rank_ThridFragment extends Rank_BaseFragment {
    @Override
    public String getUrl() {
        type="acclaim";
        return NetUtils.HTTPRANK;
    }
}
