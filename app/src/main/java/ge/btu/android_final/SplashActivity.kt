package ge.btu.android_final

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {

    private var splashTimeOut = 4200L;
    private lateinit var container: ImageView;
    private lateinit var animationDrawable: AnimationDrawable;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        setContentView(R.layout.activity_splash)
        prepareForRedirect()
        init()
    }

    override fun onResume() {
        super.onResume()
        animationDrawable.start();
        checkAnimationStatus(50, animationDrawable);
    }


    private fun init() {
        container = findViewById(R.id.image_container);
        container.setBackgroundResource(R.drawable.splash_animation);
        animationDrawable = container.background as AnimationDrawable;
    }

    private fun prepareForRedirect() {
        Handler().postDelayed(Runnable {
            val i = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }, splashTimeOut)
    }

    private fun checkAnimationStatus(time: Int, animationDrawable: AnimationDrawable) {
        val handler = Handler()
        handler.postDelayed({
            if (animationDrawable.current !== animationDrawable.getFrame(animationDrawable.numberOfFrames - 1)) checkAnimationStatus(
                time,
                animationDrawable
            ) else finish()
        }, time.toLong())
    }


}
