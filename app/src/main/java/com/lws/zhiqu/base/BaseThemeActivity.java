package com.lws.zhiqu.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lws.zhiqu.R;
import com.lws.zhiqu.theme.ThemeColor;
import com.lws.zhiqu.utils.SpUtils;
import com.lws.zhiqu.theme.Theme;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

import static com.lws.zhiqu.theme.Theme.Blue;
import static com.lws.zhiqu.theme.Theme.BlueGrey;
import static com.lws.zhiqu.theme.Theme.Brown;
import static com.lws.zhiqu.theme.Theme.Cyan;
import static com.lws.zhiqu.theme.Theme.DeepOrange;
import static com.lws.zhiqu.theme.Theme.DeepPurple;
import static com.lws.zhiqu.theme.Theme.Green;
import static com.lws.zhiqu.theme.Theme.Indigo;
import static com.lws.zhiqu.theme.Theme.LightGreen;
import static com.lws.zhiqu.theme.Theme.Lime;
import static com.lws.zhiqu.theme.Theme.Orange;
import static com.lws.zhiqu.theme.Theme.Pink;
import static com.lws.zhiqu.theme.Theme.Purple;
import static com.lws.zhiqu.theme.Theme.Red;
import static com.lws.zhiqu.theme.Theme.Teal;

/**
 * Created by song on 2018/1/26.
 */

public abstract class BaseThemeActivity extends SupportActivity{
    public Unbinder unbindre;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTheme();
        setTranslucentStatus();
        setContentView(getlayoutId());
        unbindre =  ButterKnife.bind(this);
        initView(savedInstanceState);

    }

    public void initTheme() {
        String theme = SpUtils.getString(this ,ThemeColor.APPTHEME, ThemeColor.PURPLE);
        switch (theme) {
            case ThemeColor.BLUE:
                setTheme(R.style.BlueTheme);
                break;
            case ThemeColor.RED:
                setTheme(R.style.RedTheme);
                break;
            case ThemeColor.BROWN:
                setTheme(R.style.BrownTheme);
                break;
            case ThemeColor.GREEN:
                setTheme(R.style.GreenTheme);
                break;
            case ThemeColor.PURPLE:
                setTheme(R.style.PurpleTheme);
                break;
            case ThemeColor.TEAL:
                setTheme(R.style.TealTheme);
                break;
            case ThemeColor.PINK:
                setTheme(R.style.PinkTheme);
                break;
            case ThemeColor.DEEPPURPLE:
                setTheme(R.style.DeepPurpleTheme);
                break;
            case ThemeColor.ORANGE:
                setTheme(R.style.OrangeTheme);
                break;
            case ThemeColor.INDIGO:
                setTheme(R.style.IndigoTheme);
                break;
            case ThemeColor.LIGHTGREEN:
                setTheme(R.style.LightGreenTheme);
                break;
            case ThemeColor.LIME:
                setTheme(R.style.LimeTheme);
                break;
            case ThemeColor.DEEPORANGR:
                setTheme(R.style.DeepOrangeTheme);
                break;
            case ThemeColor.CYAN:
                setTheme(R.style.CyanTheme);
                break;
            case ThemeColor.BLUEGREY:
                setTheme(R.style.BlueGreyTheme);
                break;

        }

    }
    private void setTranslucentStatus() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindre.unbind();
    }

    protected abstract int getlayoutId() ;
    protected abstract void initView(Bundle savedInstanceState) ;
}

