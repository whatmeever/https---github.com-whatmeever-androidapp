package com.waterfairy.gamecenter.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.waterfairy.gamecenter.R;
import com.waterfairy.gamecenter.category.SearchActivity;
import com.waterfairy.gamecenter.fragment.Rank_Base_DetailFragment;

public class Detail_RunkActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_back;
    private ImageView iv_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__runk);
        iv_back= (ImageView) findViewById(R.id.iv_back);
        iv_search= (ImageView) findViewById(R.id.iv_search);
        iv_search.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 1);
        Rank_Base_DetailFragment rank_base_detailFragment = new Rank_Base_DetailFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        rank_base_detailFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.frame, rank_base_detailFragment).commit();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                //返回键销毁详情页Activity；
                finish();
                break;
            case R.id.iv_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
        }

    }
}
