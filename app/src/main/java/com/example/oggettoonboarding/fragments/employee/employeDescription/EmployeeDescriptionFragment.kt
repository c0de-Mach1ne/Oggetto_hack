package com.example.oggettoonboarding.fragments.employee.employeDescription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.oggettoonboarding.R
import com.example.oggettoonboarding.databinding.FragmentEditProfleBinding
import com.example.oggettoonboarding.databinding.FragmentEmployeeDescriptionBinding

class EmployeeDescriptionFragment: Fragment(R.layout.fragment_employee_description) {

    private lateinit var binding: FragmentEmployeeDescriptionBinding
    private val args by navArgs<EmployeeDescriptionFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEmployeeDescriptionBinding.inflate(layoutInflater, container, false)
        binding.tvName.text = args.user.name
        return binding.root
    }
}