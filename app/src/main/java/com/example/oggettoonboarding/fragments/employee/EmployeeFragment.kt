package com.example.oggettoonboarding.fragments.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.navOptions
import com.example.oggettoonboarding.R
import com.example.oggettoonboarding.databinding.FragmentEmployeeBinding
import com.example.oggettoonboarding.fragments.models.User
import com.example.oggettoonboarding.fragments.tabs.TabsFragmentDirections
import com.example.oggettoonboarding.utils.ItemClickListener
import com.example.oggettoonboarding.utils.findTopNavController

class EmployeeFragment : Fragment(R.layout.fragment_employee) {

    private lateinit var binding: FragmentEmployeeBinding
    private val viewModel by viewModels<EmployeeViewModel> { EmployeeViewModelFactory() }

    private val employeeAdapterEmployee by lazy {
        AdapterEmployee(object : ItemClickListener<User> {
            override fun onClickItem(value: User) {
                findTopNavController().navigate(TabsFragmentDirections.actionTabsFragmentToEmployeeDescriptionFragment(
                    value), navOptions {
                    popUpTo(R.id.employeeFragment) {
                        inclusive = true
                    }
                })
            }
        }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEmployeeBinding.inflate(layoutInflater, container, false)
        setUpRecycler()
        viewModel.getUsers()
        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        viewModel.userList.observe(viewLifecycleOwner) {
            employeeAdapterEmployee.submitList(it)
            employeeAdapterEmployee.notifyDataSetChanged()
        }
    }

    private fun setUpRecycler() = with(binding) {
        recycler.adapter = employeeAdapterEmployee
    }
}