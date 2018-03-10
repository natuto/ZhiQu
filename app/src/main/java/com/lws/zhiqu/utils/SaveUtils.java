package com.lws.zhiqu.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by song on 2017/8/20.
 */

public class SaveUtils {

    public static void copyFile(File fromFile,File toFile) throws IOException {
        FileInputStream ins = new FileInputStream(fromFile);
        FileOutputStream out = new FileOutputStream(toFile);
        byte[] b = new byte[1024];
        int n=0;
        while((n=ins.read(b))!=-1){
            out.write(b, 0, b.length);
        }

        ins.close();
        out.close();
    }
    public static  void download(final File file, final Context context) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                File pictureFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile();
                File appDir = new File(pictureFolder ,"zhiqu");
                if (!appDir.exists()) {
                    appDir.mkdirs();
                }
                String fileName = System.currentTimeMillis() + ".jpg";
                File destFile = new File(appDir, fileName);
                try {
                    copyFile(file, destFile);


                } catch (IOException e) {
                    e.printStackTrace();
                }

                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                        Uri.fromFile(new File(destFile.getPath()))));


            }
        }).start();
        Toast.makeText(context,"保存成功",Toast.LENGTH_SHORT).show();
    }


}
