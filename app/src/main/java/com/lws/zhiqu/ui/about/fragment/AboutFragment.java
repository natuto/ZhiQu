package com.lws.zhiqu.ui.about.fragment;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.lws.zhiqu.R;
import com.lws.zhiqu.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by song on 2018/1/31.
 */

public class AboutFragment extends BaseFragment {
    @BindView(R.id.tv_download)
    TextView mDownload;
    @BindView(R.id.tv_github)
    TextView mGithub;
    public static AboutFragment newInstance(){
        AboutFragment aboutFragment = new AboutFragment();
        return aboutFragment;
    }
    @Override
    public void initView() {
        mDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri url = Uri.parse("https://fir.im/zhiqus");
                intent.setData(url);
                startActivity(intent);
            }
        });
        mGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri url = Uri.parse("https://github.com/natuto/ZhiQu");
                intent.setData(url);
                startActivity(intent);

            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about;
    }
}
