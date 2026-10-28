package com.deckbound.game

import android.app.Activity
import android.os.Bundle
import android.widget.Button

/**
 * Layar pertarungan (sementara masih placeholder).
 */
class GameActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        findViewById<Button>(R.id.btnBack).setOnClickListener { finish() }
    }
}
