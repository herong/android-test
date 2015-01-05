package com.github.herong.android.eshopping;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainNavActivity extends ActionBarActivity {

    private final String TAG = "MainNavActivity";
    private ViewPager viewPager;

    private ImageView imgAnim;


    private ImgViewInfo[] imgViewInfo;

    private int currIndex;

    private MainNavActivity that ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        that = this;

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        setContentView(R.layout.activity_main_nav);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        this.viewPager = (ViewPager) findViewById(R.id.tabpager);


        init();

        initPageView();



    }

    private void init() {

        imgAnim = (ImageView) findViewById(R.id.imgAnim);

        //图标
                ImageView imgViewNavHome = (ImageView) findViewById(R.id.imgViewNavHome);
        ImageView imgViewNavFavorites = (ImageView) findViewById(R.id.imgViewNavFavorites);
        ImageView imgViewNavShoppingCart = (ImageView) findViewById(R.id.imgViewNavShoppingCart);
        ImageView imgViewNavMine = (ImageView) findViewById(R.id.imgViewNavMine);

        imgViewInfo = new ImgViewInfo[4];
        ImgViewInfo imgViewInfo0 = new ImgViewInfo();
        ImgViewInfo imgViewInfo1 = new ImgViewInfo();
        ImgViewInfo imgViewInfo2 = new ImgViewInfo();
        ImgViewInfo imgViewInfo3 = new ImgViewInfo();
        imgViewInfo[0] = imgViewInfo0;
        imgViewInfo[1] = imgViewInfo1;
        imgViewInfo[2] = imgViewInfo2;
        imgViewInfo[3] = imgViewInfo3;

        imgViewInfo0.setImageView(imgViewNavHome);
        imgViewInfo1.setImageView(imgViewNavFavorites);
        imgViewInfo2.setImageView(imgViewNavShoppingCart);
        imgViewInfo3.setImageView(imgViewNavMine);


        //位置
        Display currDisplay = getWindowManager().getDefaultDisplay();//获取屏幕当前分辨率
        Point point = new Point();
        currDisplay.getSize(point);
        int width = point.x / 4; //设置水平动画平移大小
        imgViewInfo0.setPosition(0);
        imgViewInfo1.setPosition(width);
        imgViewInfo2.setPosition(width * 2);
        imgViewInfo3.setPosition(width * 3);

        imgAnim.setMinimumWidth(width);

        imgViewInfo0.setImgNormal(R.drawable.nav_home_normal);
        imgViewInfo1.setImgNormal(R.drawable.nav_favorites_normal);
        imgViewInfo2.setImgNormal(R.drawable.nav_shopping_cart_normal);
        imgViewInfo3.setImgNormal(R.drawable.nav_mine_normal);

        imgViewInfo0.setImgActive(R.drawable.nav_home_active);
        imgViewInfo1.setImgActive(R.drawable.nav_favorites_active);
        imgViewInfo2.setImgActive(R.drawable.nav_shopping_cart_active);
        imgViewInfo3.setImgActive(R.drawable.nav_mine_active);

        bindNavImgClickListener();

    }


    private void bindPageChangeListener() {

        this.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                pressAnimation(currIndex, i);
                currIndex = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }

        });
    }

    private void pressAnimation(int currIndex, int targetIndex) {
        ActionBar actionBar = getSupportActionBar();
        String title = "";
        View v = null;
        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (targetIndex == 0) {
             v = inflator.inflate(R.layout.bar_top_home, null);
        } else if (targetIndex == 1) {
             v = inflator.inflate(R.layout.bar_top_shop, null);
        } else if (targetIndex == 2) {
             v = inflator.inflate(R.layout.bar_top_shopping_cart, null);
            TextView vTitle = (TextView) v.findViewById(R.id.bar_top_title);
            vTitle.setText(R.string.nav_favorites);
        } else if (targetIndex == 3) {
             v = inflator.inflate(R.layout.bar_top_mine, null);
            TextView vTitle = (TextView) v.findViewById(R.id.bar_top_title);
            vTitle.setText(R.string.nav_mine);
        }

        if (v != null) {
            actionBar.setCustomView(v);
        }


        imgViewInfo[targetIndex].getImageView().setImageDrawable(getResources().getDrawable(imgViewInfo[targetIndex].getImgActive()));
        Animation animation = new TranslateAnimation(imgViewInfo[currIndex].getPosition(), imgViewInfo[targetIndex].getPosition(), 0, 0);
        imgViewInfo[currIndex].getImageView().setImageDrawable(getResources().getDrawable(imgViewInfo[currIndex].getImgNormal()));
        animation.setFillAfter(true);// True:图片停在动画结束位置
        animation.setDuration(150);
        imgAnim.startAnimation(animation);


    }

    private void bindNavImgClickListener() {
        int i = 0;
        for (ImgViewInfo imgView : imgViewInfo) {
            imgView.getImageView().setOnClickListener(new ImgViewNavListenerClass(i++));
        }
    }

    private void initPageView() {

        //将要分页显示的View装入数组中
        LayoutInflater mLi = LayoutInflater.from(this);
        View lvNavHome = mLi.inflate(R.layout.activity_nav_home,null);
        View lvNavFavorites = mLi.inflate(R.layout.activity_nav_favorites, null);
        View lvNavShoppingCart = mLi.inflate(R.layout.activity_nav_shopping_cart, null);
        View lvNavMine = mLi.inflate(R.layout.activity_nav_mine, null);
        lvNavHome.findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "单击了:" + v.toString());
                switch (v.getId()) {
                    case R.id.btn_test:
                        Intent intent = new Intent(that, ActBarActivity.class);
                        startActivity(intent);
                        break;
                    default:

                }
            }
        });
        //每个页面的view数据
        final ArrayList<View> views = new ArrayList<View>();
        views.add(lvNavHome);
        views.add(lvNavFavorites);
        views.add(lvNavShoppingCart);
        views.add(lvNavMine);

        //填充ViewPager的数据适配器
        PagerAdapter pagerAdapter = new PagerAdapter() {


            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                ((ViewPager) container).removeView(views.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ((ViewPager) container).addView(views.get(position));
                return views.get(position);
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return (view == o);
            }
        };

        this.viewPager.setAdapter(pagerAdapter);

        bindPageChangeListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ImgViewNavListenerClass implements View.OnClickListener {

        private int index;

        public ImgViewNavListenerClass(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(index);
        }
    }


    private class ImgViewInfo {
        private ImageView imageView;

        private int position;

        private int imgNormal;

        private int imgActive;

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getImgNormal() {
            return imgNormal;
        }

        public void setImgNormal(int imgNormal) {
            this.imgNormal = imgNormal;
        }

        public int getImgActive() {
            return imgActive;
        }

        public void setImgActive(int imgActive) {
            this.imgActive = imgActive;
        }
    }
}
