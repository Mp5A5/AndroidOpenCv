package com.mp5a5.opencv

import android.app.Application
import android.util.Log
import org.opencv.android.OpenCVLoader

/**
 * @describe ：
 * @author ：王文彬 on 2020/4/11 15：11
 * @email ：wangwenbin29@jd.com
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        OpenCVLoader.initDebug()

        val loadSuccess: Boolean = OpenCVLoader.initDebug()

        if (!loadSuccess) {
            Log.e("-->", "Opencv load Fail")
        } else {
            Log.e("-->", "Opencv load Success")
        }
    }
}