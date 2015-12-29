package com.waterfairy.gamecenter.shouye;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.waterfairy.gamecenter.R;

/**
 *
 * @author james
 */
public abstract class ShouYeBaseFragment extends Fragment {
    public ImageView imageView;
    public ImageButton ibt;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.shoye_fragment,null);
        imageView = (ImageView) view.findViewById(R.id.iv);
        ibt = (ImageButton) view.findViewById(R.id.ibt);
        init();
        return view;
    }
    public abstract void init();
}
