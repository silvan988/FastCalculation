package br.edu.ifsp.scl.fastcalculation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import br.edu.ifsp.scl.fastcalculation.Extras.EXTRA_SCORE
import br.edu.ifsp.scl.fastcalculation.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private lateinit var fragmentResultBinding: FragmentResultBinding


    private lateinit var score: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            score = it.getString(EXTRA_SCORE).toString()

        }
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentResultBinding = FragmentResultBinding.inflate(inflater, container, false)

        fragmentResultBinding .apply {
            finalScoreTv.text = score
            restartGameBt.setOnClickListener{
                (context as OnPLayGame).onPlayGame() //Polimorfismo
            }
        }


        return fragmentResultBinding.root
    }

    companion object {
        fun newInstance(score: String) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_SCORE, score)
                }
            }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.restartGameMi).isVisible = false
    }
}