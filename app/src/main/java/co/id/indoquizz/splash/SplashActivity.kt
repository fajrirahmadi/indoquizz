package co.id.indoquizz.splash

import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import co.id.indoquizz.MainActivity
import co.id.indoquizz.R
import co.id.indoquizz.base.view.BaseActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            this.showActivityAndFinishCurent(getIntent(this, MainActivity::class.java))
        }, 1000)
    }
}