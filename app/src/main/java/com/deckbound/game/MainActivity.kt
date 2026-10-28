package com.deckbound.game

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

/**
 * Halaman awal (menu utama) Deckbound.
 * Tombol: Mulai, Koleksi Kartu, Pengaturan, Keluar.
 */
class MainActivity : Activity() {

    private var floatAnimator: ObjectAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnCollection = findViewById<Button>(R.id.btnCollection)
        val btnSettings = findViewById<Button>(R.id.btnSettings)
        val btnExit = findViewById<Button>(R.id.btnExit)

        btnStart.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }
        btnCollection.setOnClickListener {
            showComingSoon(getString(R.string.btn_collection))
        }
        btnSettings.setOnClickListener {
            showComingSoon(getString(R.string.btn_settings))
        }
        btnExit.setOnClickListener { confirmExit() }

        findViewById<TextView>(R.id.versionLabel).text =
            getString(R.string.version_label, appVersionName())

        if (savedInstanceState == null) {
            playIntroAnimation(listOf(btnStart, btnCollection, btnSettings, btnExit))
        }
        startCardFloat(findViewById(R.id.cardFan))
    }

    override fun onDestroy() {
        floatAnimator?.cancel()
        super.onDestroy()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        confirmExit()
    }

    private fun showComingSoon(feature: String) {
        Toast.makeText(this, getString(R.string.coming_soon, feature), Toast.LENGTH_SHORT).show()
    }

    private fun confirmExit() {
        AlertDialog.Builder(this)
            .setTitle(R.string.exit_title)
            .setPositiveButton(R.string.exit_yes) { _, _ -> finishAffinity() }
            .setNegativeButton(R.string.exit_no, null)
            .show()
    }

    private fun appVersionName(): String =
        try {
            @Suppress("DEPRECATION")
            packageManager.getPackageInfo(packageName, 0).versionName ?: "-"
        } catch (e: Exception) {
            "-"
        }

    /** Tombol muncul satu per satu dari bawah. */
    private fun playIntroAnimation(buttons: List<View>) {
        val offset = 40f * resources.displayMetrics.density
        buttons.forEachIndexed { index, view ->
            view.alpha = 0f
            view.translationY = offset
            view.animate()
                .alpha(1f)
                .translationY(0f)
                .setStartDelay(150L + index * 90L)
                .setDuration(380L)
                .setInterpolator(DecelerateInterpolator())
                .start()
        }
    }

    /** Kipas kartu melayang naik-turun pelan. */
    private fun startCardFloat(cardFan: ImageView) {
        val distance = 8f * resources.displayMetrics.density
        floatAnimator = ObjectAnimator.ofFloat(cardFan, View.TRANSLATION_Y, 0f, -distance).apply {
            duration = 1800L
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }
    }
}
