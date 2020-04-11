package com.mp5a5.opencv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    sample_text.text = stringFromJNI()
  }
  external fun stringFromJNI(): String
  
  companion object {
    init {
      System.loadLibrary("native-lib")
    }
  }
}
