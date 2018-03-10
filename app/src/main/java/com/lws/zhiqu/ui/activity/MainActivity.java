package com.lws.zhiqu.ui.activity;

import android.Manifest;
import android.animation.Animator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.lws.zhiqu.R;
import com.lws.zhiqu.theme.ColorView;
import com.lws.zhiqu.theme.ThemeColor;
import com.lws.zhiqu.utils.SpUtils;
import com.lws.zhiqu.utils.SystemUtils;
import com.lws.zhiqu.utils.ThemeUtils;
import com.lws.zhiqu.base.BaseThemeActivity;
import com.lws.zhiqu.theme.ColorRelativeLayout;
import com.lws.zhiqu.theme.ColorUiUtil;
import com.lws.zhiqu.theme.ResideLayout;
import com.lws.zhiqu.theme.Theme;
import com.lws.zhiqu.ui.about.fragment.AboutFragment;
import com.lws.zhiqu.ui.fuli.fragment.FuLiFragment;
import com.lws.zhiqu.ui.gank.fragment.GankFragment;
import com.lws.zhiqu.ui.home.fragment.HomeFragment;
import com.lws.zhiqu.ui.movie.fragment.MovieFragment;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;


public class MainActivity extends BaseThemeActivity implements ColorChooserDialog.ColorCallback{



    @BindView(R.id.ganhuo)
    LinearLayout mGanhuo;
    @BindView(R.id.fuli) LinearLayout mFuli;
    @BindView(R.id.home) LinearLayout mHome;
    @BindView(R.id.movie) LinearLayout mFilm;
    @BindView(R.id.about) LinearLayout mAbout;
    @BindView(R.id.theme)LinearLayout mTheme;
    @BindView(R.id.scrollView) ScrollView mScrollView;
    @BindView(R.id.menu) ColorRelativeLayout mMenu;
    @BindView(R.id.container) FrameLayout mContainer;
    @BindView(R.id.resideLayout) ResideLayout mResideLayout;
    @BindView(R.id.status_bar)
    ColorView mStatusBar;
    public int FIRST = 0;
    public int SECOND = 1;
    public int THIRD = 2;
    public int FOURTH = 3;
    public int FIFTH = 4;

    private SupportFragment[] mFragments = new SupportFragment[5];



    @Override
    protected int getlayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mStatusBar.setVisibility(View.VISIBLE);
        mStatusBar.getLayoutParams().height = SystemUtils.getStatusHeight(this);
        mStatusBar.setLayoutParams(mStatusBar.getLayoutParams());

        if (SpUtils.getBoolean(this, "isFirst", true)) {
            mResideLayout.openPane();
            SpUtils.putBoolean(this, "isFirst", false);
        }
        if (savedInstanceState ==null) {
            mFragments[FIRST] = HomeFragment.newInstance();
            mFragments[SECOND] = GankFragment.newInstance();
            mFragments[THIRD] = MovieFragment.newInstance();
            mFragments[FOURTH] = FuLiFragment.newInstance();
            mFragments[FIFTH] = AboutFragment.newInstance();
            loadMultipleRootFragment(R.id.container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH],
                    mFragments[FIFTH]);
        }else{
            mFragments[FIRST] = findFragment(HomeFragment.class);
            mFragments[SECOND] = findFragment(GankFragment.class);
            mFragments[THIRD] = findFragment(MovieFragment.class);
            mFragments[FOURTH] = findFragment(FuLiFragment.class);
            mFragments[FIFTH] = findFragment(AboutFragment.class);

        }


    }

     @OnClick( {R.id.home,R.id.ganhuo,R.id.movie,R.id.fuli,R.id.about,R.id.theme})
     public void onClick(View view){
         switch (view.getId()) {
             case R.id.home:
                 mResideLayout.closePane();

                 showHideFragment(mFragments[FIRST]);
                 break;
             case R.id.ganhuo:
                 mResideLayout.closePane();

                 showHideFragment(mFragments[SECOND]);
                 break;
             case R.id.movie:
                 mResideLayout.closePane();

                 showHideFragment(mFragments[THIRD]);
                 break;
             case R.id.fuli:
                 mResideLayout.closePane();

                 showHideFragment(mFragments[FOURTH]);
                 break;
             case R.id.about:
                 mResideLayout.closePane();

                 showHideFragment(mFragments[FIFTH]);
                 break;
             case R.id.theme:
                 new ColorChooserDialog.Builder(this, R.string.theme)
                         .customColors(R.array.colors, null)
                         .doneButton(R.string.done)
                         .cancelButton(R.string.cancel)
                         .allowUserColorInput(false)
                         .allowUserColorInputAlpha(false)
                         .show();
                 break;



         }

     }


    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, @ColorInt int selectedColor) {
        if (selectedColor == ThemeUtils.getThemeColor(this, R.attr.colorPrimary))
            return;
       

        if (selectedColor == getResources().getColor(R.color.colorBluePrimary)) {
            setTheme(R.style.BlueTheme);
            SpUtils.putString(this,ThemeColor.APPTHEME, ThemeColor.BLUE);

        } else if (selectedColor == getResources().getColor(R.color.colorRedPrimary)) {
            setTheme(R.style.RedTheme);
            SpUtils.putString(this,ThemeColor.APPTHEME, ThemeColor.RED);


        } else if (selectedColor == getResources().getColor(R.color.colorBrownPrimary)) {
            setTheme(R.style.BrownTheme);
            SpUtils.putString(this,ThemeColor.APPTHEME, ThemeColor.BROWN);

        } else if (selectedColor == getResources().getColor(R.color.colorGreenPrimary)) {
            setTheme(R.style.GreenTheme);
            SpUtils.putString(this,ThemeColor.APPTHEME, ThemeColor.GREEN);

        } else if (selectedColor == getResources().getColor(R.color.colorPurplePrimary)) {
            setTheme(R.style.PurpleTheme);
            SpUtils.putString(this,ThemeColor.APPTHEME, ThemeColor.PURPLE);

        } else if (selectedColor == getResources().getColor(R.color.colorTealPrimary)) {
            setTheme(R.style.TealTheme);
            SpUtils.putString(this,ThemeColor.APPTHEME, ThemeColor.TEAL);

        } else if (selectedColor == getResources().getColor(R.color.colorPinkPrimary)) {
            setTheme(R.style.PinkTheme);
            SpUtils.putString(this,ThemeColor.APPTHEME, ThemeColor.PINK);

        } else if (selectedColor == getResources().getColor(R.color.colorDeepPurplePrimary)) {
            setTheme(R.style.DeepPurpleTheme);
            SpUtils.putString(this,ThemeColor.APPTHEME, ThemeColor.DEEPPURPLE);

        } else if (selectedColor == getResources().getColor(R.color.colorOrangePrimary)) {
            setTheme(R.style.OrangeTheme);
            SpUtils.putString(this,ThemeColor.APPTHEME, ThemeColor.ORANGE);

        } else if (selectedColor == getResources().getColor(R.color.colorIndigoPrimary)) {
            setTheme(R.style.IndigoTheme);
            SpUtils.putString(this,ThemeColor.APPTHEME, ThemeColor.INDIGO);

        } else if (selectedColor == getResources().getColor(R.color.colorLightGreenPrimary)) {
            setTheme(R.style.LightGreenTheme);
            SpUtils.putString(this,ThemeColor.APPTHEME, ThemeColor.LIGHTGREEN);

        } else if (selectedColor == getResources().getColor(R.color.colorDeepOrangePrimary)) {
            setTheme(R.style.DeepOrangeTheme);
            SpUtils.putString(this,ThemeColor.APPTHEME, ThemeColor.DEEPORANGR);

        } else if (selectedColor == getResources().getColor(R.color.colorLimePrimary)) {
            setTheme(R.style.LimeTheme);
            SpUtils.putString(this,ThemeColor.APPTHEME, ThemeColor.LIME);

        } else if (selectedColor == getResources().getColor(R.color.colorBlueGreyPrimary)) {
            setTheme(R.style.BlueGreyTheme);
            SpUtils.putString(this,ThemeColor.APPTHEME, ThemeColor.BLUEGREY);

        } else if (selectedColor == getResources().getColor(R.color.colorCyanPrimary)) {
            setTheme(R.style.CyanTheme);
            SpUtils.putString(this,ThemeColor.APPTHEME, ThemeColor.CYAN);

        }
        final View rootView = getWindow().getDecorView();
        rootView.setDrawingCacheEnabled(true);
        rootView.buildDrawingCache(true);

        final Bitmap localBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);
        if (null != localBitmap && rootView instanceof ViewGroup) {
            final View tmpView = new View(getApplicationContext());
            tmpView.setBackgroundDrawable(new BitmapDrawable(getResources(), localBitmap));
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) rootView).addView(tmpView, params);
            tmpView.animate().alpha(0).setDuration(400).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    ColorUiUtil.changeTheme(rootView, getTheme());
                    System.gc();
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    ((ViewGroup) rootView).removeView(tmpView);
                    localBitmap.recycle();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            }).start();
        }
    }
}
