package com.example.oggettoonboarding.auth.fragments.edit_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.oggettoonboarding.R
import com.example.oggettoonboarding.databinding.FragmentEditProfleBinding

class EditProfileFragment : Fragment(R.layout.fragment_edit_profle) {

    private lateinit var binding: FragmentEditProfleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEditProfleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val grade = resources.getStringArray(R.array.graid)
        val arrayAdapterGrade = ArrayAdapter(binding.etEmail.context, R.layout.dropdown_item, grade)
        binding.autoCompleteTextViewGrade.setAdapter(arrayAdapterGrade)

        val specialization = resources.getStringArray(R.array.specialization)
        val arrayAdapter =
            ArrayAdapter(binding.etEmail.context, R.layout.dropdown_item, specialization)
        binding.autoCompleteTextViewSpecialization.setAdapter(arrayAdapter)
    }
}