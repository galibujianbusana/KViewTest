package com.example.gsontag;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.gsontag.share.ShareUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Gson gson = new Gson();
    List<Provinces> info = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ShareUtil.shareContentNoUs(this,new File(""));


    }
    // 解析
    private void  gsonTest(){
        // 方法 1
        /**try {
         info = GsonUtils.jsonToArrayList(CityStrUtil.CITY, Root.Provinces.class);
         }catch (JsonSyntaxException e){
         e.printStackTrace();

         }
         Log.d(TAG,info.get(0).toString());*/

        // 方法2
        /**       Root.Provinces[] infos = new Root.Provinces[info.size()];
        infos = info.toArray(infos);
        try {
            info = GsonUtils.jsonToList(CityStrUtil.CITY, infos.getClass());
        }catch (JsonSyntaxException e){
            e.printStackTrace();
        }
        Log.d(TAG,info.get(0).toString());*/

        // 方法3
        try {
            info = GsonUtils.parseJSON(CityStrUtil.CITY, new TypeToken<ArrayList<Root.Provinces>>() {}.getType());
        }catch (JsonSyntaxException e){
            e.printStackTrace();
        }
        Log.d(TAG,info.get(0).toString());
    }

    //分享
    private void showBottomDialog() {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogPopTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.dialog, null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();



        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    private void share(){
        showBottomDialog();
        // shareFile2(this,getExternal(this) + File.separator + "contract.pdf");
    }

    public static String getExternal(Context context) {
        String cacheDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String path = cacheDir + File.separator + "中铁信托";
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        return path;
    }

    public static void shareFile2(Context context, String  fileName) {
        File file = new File(fileName);
        if (null != file && file.exists()) {
            Intent share = new Intent(Intent.ACTION_SEND);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileprovider",file);
                share.putExtra(Intent.EXTRA_STREAM, contentUri);
                share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } else {
                share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            }
            share.setType("application/pdf");//此处可发送多种文件
            share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            context.startActivity(Intent.createChooser(share, "分享文件"));
        } else {
            Toast.makeText(context, "分享文件不存在", Toast.LENGTH_SHORT).show();
        }
    }






    private void test2Gson(){

        String s = "[\"d0a2bc7741f942b6b2ee2a7aff6a0953\",\"f1cb67eff9e14554864bd5ba1c5ef3b0\",\"8fd2770f3c944938b4f9fa03988f6aea\",\"ea311eca9ce0435d98e9151be14f5642\"]";

        info = GsonUtils.parseJSON(s, new TypeToken<ArrayList<String>>() {}.getType());
        for (int i = 0; i < info.size(); i ++ ){
            Log.d(TAG, "test2Gson: " + info.get(i));
        }

    }
}
