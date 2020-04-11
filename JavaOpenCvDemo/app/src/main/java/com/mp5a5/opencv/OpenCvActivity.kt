package com.mp5a5.opencv

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_opencv.*
import org.opencv.android.BaseLoaderCallback
import org.opencv.android.CameraBridgeViewBase
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2
import org.opencv.android.LoaderCallbackInterface
import org.opencv.android.OpenCVLoader
import org.opencv.core.Core
import org.opencv.core.Mat

    class OpenCvActivity : AppCompatActivity(), CvCameraViewListener2 {

        private lateinit var mRotateFrame: Mat
        private lateinit var mRgbFrame: Mat

        public override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            setContentView(R.layout.activity_opencv)

            jcv_surface_view.setCvCameraViewListener(this)
            // 开启前置摄像头
            jcv_surface_view.setCameraIndex(CameraBridgeViewBase.CAMERA_ID_FRONT)
        }

        public override fun onPause() {
            super.onPause()
            if (jcv_surface_view != null) {
                jcv_surface_view!!.disableView()
            }
        }

        public override fun onResume() {
            super.onResume()
            if (!OpenCVLoader.initDebug()) {
                OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION, this, mLoaderCallback)
            } else {
                mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS)
            }
        }

        public override fun onDestroy() {
            super.onDestroy()
            if (jcv_surface_view != null) {
                jcv_surface_view!!.disableView()
            }
        }

        override fun onCameraViewStarted(width: Int, height: Int) {
            mRotateFrame = Mat()
            mRgbFrame = Mat()
        }

        override fun onCameraViewStopped() {}
        override fun onCameraFrame(inputFrame: CvCameraViewFrame): Mat {
            mRgbFrame = inputFrame.rgba()
            // 旋转屏幕
            Core.flip(mRgbFrame, mRotateFrame, 1)
            return mRotateFrame
        }

        private val mLoaderCallback: BaseLoaderCallback = object : BaseLoaderCallback(this) {
            override fun onManagerConnected(status: Int) {
                when (status) {
                    LoaderCallbackInterface.SUCCESS -> {
                        jcv_surface_view!!.enableView()
                    }
                    else -> {
                        super.onManagerConnected(status)
                    }
                }
            }
        }
    }