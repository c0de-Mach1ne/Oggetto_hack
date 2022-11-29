package com.example.oggettoonboarding.fragments.checklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.oggettoonboarding.R
import com.example.oggettoonboarding.databinding.FragmentChecklistBinding
import com.example.oggettoonboarding.fragments.models.CheckList
import com.example.oggettoonboarding.utils.ItemClickListener

class CheckListFragment : Fragment(R.layout.fragment_checklist) {

    private lateinit var binding: FragmentChecklistBinding
    private val viewModel by viewModels<CheckListViewModel> { CheckListViewModelFactory() }
    private val checkListAdapter by lazy {
        CheckListAdapter(object : ItemClickListener<CheckList> {
            override fun onClickItem(value: CheckList) {

            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentChecklistBinding.inflate(layoutInflater, container, false)
//        viewModel.generateCheckList()
        viewModel.generateUserCheckList()
        observeViewModel()
        initRecycler()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCheck.setOnClickListener {
            // Todo: летит какой-то запрос, который проверяет выполнил ли пользователь действия
        }
    }

    private fun observeViewModel() {
        viewModel.checkList.observe(viewLifecycleOwner) {
            checkListAdapter.submitList(it)
            checkListAdapter.notifyDataSetChanged()
        }
    }

    private fun initRecycler() = with(binding){
        recycler.adapter = checkListAdapter
    }
}