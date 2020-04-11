package com.mp5a5.opencv

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_opencv.*

/**
 * @describe ：
 * @author ：王文彬 on 2020/4/1 18：43
 * @email ：wangwenbin29@jd.com
 */
class OpenCvActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opencv)
        bitmap = BitmapFactory.decodeResource(resources, R.mipmap.person)
        iv_image.setImageBitmap(bitmap)
        btn_btn1.setOnClickListener(this)
        btn_btn2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        v?.id?.let {
            when (it) {
                R.id.btn_btn1 -> {
                    showGrayImg()
                }
                R.id.btn_btn2 -> {
                    showRgbImg()
                }
            }
        }
    }

    private fun showRgbImg() {
        bitmap = BitmapFactory.decodeResource(resources, R.mipmap.person)
        iv_image.setImageBitmap(bitmap)
    }

    private fun showGrayImg() {
        val w = bitmap.width
        val h = bitmap.height
        val pixels = IntArray(w * h)
        bitmap.getPixels(pixels, 0, w, 0, 0, w, h)
        val resultData: IntArray = NativeLibUtils.bitmap2Gray(pixels, w, h)
        val resultImage = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        resultImage.setPixels(resultData, 0, w, 0, 0, w, h)
        iv_image.setImageBitmap(resultImage)
    }
}

