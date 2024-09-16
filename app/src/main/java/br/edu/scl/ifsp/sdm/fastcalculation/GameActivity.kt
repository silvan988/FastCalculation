package br.edu.scl.ifsp.sdm.fastcalculation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.scl.ifsp.sdm.fastcalculation.Extras.EXTRA_SETTINGS
import br.edu.scl.ifsp.sdm.fastcalculation.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity(), OnPLayGame {
    private val activityGameBinding: ActivityGameBinding by lazy {
        ActivityGameBinding.inflate(layoutInflater)
    }
    private lateinit var settings: Settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityGameBinding.root)

        setSupportActionBar(activityGameBinding.gameTbIn.gameTB)
        supportActionBar?.apply {
            title = getString(R.string.app_name)
            subtitle = getString(R.string.game)
        }

        settings = intent.getParcelableExtra(EXTRA_SETTINGS) ?: Settings()
        /* Toast.makeText(this, settings.toString(), Toast.LENGTH_SHORT).show() ** // mostra a passagem dos parametros // */
        supportFragmentManager.beginTransaction().replace(R.id.gameFl, WelcomeFragment.newInstance(settings)).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_game, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.restartGameMI -> {
                onPlayGame()
                true
            }
            R.id.exitMi -> {
                finish()
                true
            }

            else -> {
                false
            }
        }
    }

    override fun onPlayGame() {
        supportFragmentManager.beginTransaction().replace(R.id.gameFl, GameFragment.newInstance(settings)).commit()
    }

}