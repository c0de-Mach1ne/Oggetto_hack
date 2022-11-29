package com.example.oggettoonboarding.fragments.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.oggettoonboarding.R
import com.example.oggettoonboarding.databinding.FragmentGameBinding
import com.example.oggettoonboarding.fragments.models.Game
import com.example.oggettoonboarding.utils.ItemClickListener

class GameFragment: Fragment(R.layout.fragment_game) {

    private lateinit var binding: FragmentGameBinding
    private val gameAdapter by lazy {
        GameAdapter(
            object: ItemClickListener<Game>{
                override fun onClickItem(value: Game) {
                    TODO("Not yet implemented")
                }
            }
        )
    }
    private val viewModel by viewModels<GameViewModel> { GameViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater, container, false)
        observeViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun observeViewModel(){

    }

    private fun initRecycler() = with(binding) {
        recycler.adapter = gameAdapter
    }
}