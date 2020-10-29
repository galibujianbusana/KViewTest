package com.example.kview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.RenderNode;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import trade.utils.GsonUtil;
import trade.utils.StringUtils;
import trade.view.fund.FundMode;
import trade.view.fund.FundView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FundView mFundView;
    List<OriginFundMode> mOriginFundModeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        loadData();

    }

    private void initView() {
        mFundView = (FundView) findViewById(R.id.af_fv_fundview);

        /**
         * 定制,所有的画笔以及其它属性都已经暴露出来，有了更加大的定时灵活性。更多参数可以直接查看源码...
         */
        //常规set、get...
        mFundView.getBrokenPaint().setColor(getResources().getColor(R.color.colorAccent));//设置折现颜色
        mFundView.getInnerXPaint().setStrokeWidth(1);//设置内部x轴虚线的宽度,px
        mFundView.getBrokenPaint().setStrokeWidth(1);
        //链式调用
        mFundView
                .setBasePaddingTop(140)
                .setBasePaddingLeft(50)
                .setBasePaddingRight(40)
                .setBasePaddingBottom(30)
                .setLoadingText("正在加载，马上就来...");


    }

    private void initData() {
        mOriginFundModeList = new ArrayList<>();

    }


    private void loadData() {
       /*** Disposable subscribe = Observable.timer(StringUtils.getRadomNum(500, 3000), TimeUnit.MILLISECONDS)
                .map(map -> {
                    String originalFundData = FundSimulateNetAPI.getOriginalFundData(getApplication());
                    if (originalFundData == null) {
                        Log.e(TAG, "loadData: 从网络获取到的数据为空");
                        return null;
                    }
                    OriginFundMode[] originFunModes;
                    try {
                        originFunModes = GsonUtil.fromJson2Object(originalFundData, OriginFundMode[].class);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                    List<OriginFundMode> OriginFundModeList = Arrays.asList(originFunModes);
                    //开始适配图表数据
                    return adapterData(OriginFundModeList);
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mData -> {
                            if (StringUtils.isEmpty(mData)) {
                                Log.d(TAG, "loadData: data is empty!");
                                return;
                            }
                            mFundView.setDataList(mData);
                        }
                        , throwable -> throwable.printStackTrace()); ***/

        //unSubscription(subscribe);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String originalFundData = FundSimulateNetAPI.getOriginalFundData(getApplication());
                OriginFundMode[] originFunModes = null;
                try {
                    originFunModes = GsonUtil.fromJson2Object(originalFundData, OriginFundMode[].class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                List<OriginFundMode> OriginFundModeList = Arrays.asList(originFunModes);
                //开始适配图表数据
                List<FundMode> mData = adapterData(OriginFundModeList);

                mFundView.setDataList(mData);
            }
        },2000);
    }

    private List<FundMode> adapterData(List<OriginFundMode> originFundModeList) {
        List<FundMode> fundModeList = new ArrayList<>();//适配后的数据
        for (OriginFundMode originFundMode : originFundModeList) {
            FundMode fundMode = new FundMode(originFundMode.timestamp * 1000, originFundMode.actual);
            fundModeList.add(fundMode);
            Log.e(TAG, "adapterData: 适配之前：" + originFundMode.actual + "----->>" + fundMode.dataY);
        }
        return fundModeList;
    }

}