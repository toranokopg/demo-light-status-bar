package io.github.toranoko0518.demo.lsb

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import io.github.toranoko0518.demo.lsb.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var color: Int by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            swWic.setOnCheckedChangeListener { _, checked -> window.lightStatusBarWic(checked) }
            swSuv.setOnCheckedChangeListener { _, checked -> window.lightStatusBarSuv(checked) }
        }

        color = window.statusBarColor
    }

    private fun Window.lightStatusBarWic(checked: Boolean) {
        statusBarColor = if (checked) Color.WHITE else color
        WindowInsetsControllerCompat(this, decorView).run { isAppearanceLightStatusBars = checked }
    }

    @Suppress("DEPRECATION")
    private fun Window.lightStatusBarSuv(checked: Boolean) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return

        statusBarColor = if (checked) Color.WHITE else color
        decorView.run {
            systemUiVisibility =
                if (checked) systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                else systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
    }
}
