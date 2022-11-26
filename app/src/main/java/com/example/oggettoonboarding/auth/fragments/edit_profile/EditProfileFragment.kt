package com.example.oggettoonboarding.auth.fragments.edit_profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.oggettoonboarding.R
import com.example.oggettoonboarding.auth.fragments.edit_profile.recycler_hobby.HobbyListAdapter
import com.example.oggettoonboarding.auth.fragments.edit_profile.recycler_skils.TechStackAdapter
import com.example.oggettoonboarding.databinding.FragmentEditProfleBinding
import com.example.oggettoonboarding.fragments.models.Hobby
import com.example.oggettoonboarding.fragments.models.TechStack
import com.example.oggettoonboarding.utils.ItemClickListener

class EditProfileFragment : Fragment(R.layout.fragment_edit_profle) {

    private lateinit var binding: FragmentEditProfleBinding
    private val args by navArgs<EditProfileFragmentArgs>()
    private val hobby = mutableListOf<Hobby>()
    private val techStack = mutableListOf<TechStack>()
    private val viewModel by viewModels<EditProfileViewModel>()

    private val hobbyAdapter by lazy {
        HobbyListAdapter(object : ItemClickListener<Hobby> {
            override fun onClickItem(value: Hobby) {
                hobby.remove(value)
                viewModel.updateHobbyList(hobby)
            }
        })
    }

    private val techStackAdapter by lazy {
        TechStackAdapter(object : ItemClickListener<TechStack> {
            override fun onClickItem(value: TechStack) {
                techStack.remove(value)
                viewModel.updateTechStack(techStack)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEditProfleBinding.inflate(layoutInflater, container, false)
        observeViewModel()
        setUpUi()
        setUpRecycler()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            getUiValue()
        }
        binding.btnAddHobby.setOnClickListener {
            hobby.add(Hobby(binding.etLayoutHobby.text.toString()))
            binding.etLayoutHobby.text?.clear()
            viewModel.updateHobbyList(hobby)
            Log.d("TAG", "hobbyList $hobby")
        }

        binding.btnAddTechStack.setOnClickListener {
            techStack.add(TechStack(binding.etLayoutTechStack.text.toString()))
            binding.etLayoutTechStack.text?.clear()
            viewModel.updateTechStack(techStack)
            Log.d("TAG", "techList $techStack")
        }
    }

    private fun setUpUi() {

        // Todo: установить все пришедшие аргументы
        //  Установить recycler, adapter и передать туда значения

        val grade = resources.getStringArray(R.array.graid)
        val arrayAdapterGrade = ArrayAdapter(binding.btnSave.context, R.layout.dropdown_item, grade)
        binding.autoCompleteTextViewGrade.setAdapter(arrayAdapterGrade)

        val specialization = resources.getStringArray(R.array.specialization)
        val arrayAdapter =
            ArrayAdapter(binding.btnSave.context, R.layout.dropdown_item, specialization)
        binding.autoCompleteTextViewSpecialization.setAdapter(arrayAdapter)
    }

    private fun getUiValue() {
        val photoUrl = ""
        val name = binding.etName.text.toString()
        val sureName = binding.etSureName.text.toString()
        val patronymic = binding.etPatronymic.text.toString()
        val specialization = binding.autoCompleteTextViewSpecialization.text.toString()
        val graid = binding.autoCompleteTextViewGrade.text.toString()
        val hobby = ""
        val techStack = ""
    }

    private fun observeViewModel() {
        viewModel.hobbyList.observe(viewLifecycleOwner) {
            hobbyAdapter.submitList(it)
            hobbyAdapter.notifyDataSetChanged()
        }

        viewModel.techStack.observe(viewLifecycleOwner) {
            techStackAdapter.submitList(it)
            techStackAdapter.notifyDataSetChanged()
        }
    }

    private fun setUpRecycler() {
        with(binding) {
            recyclerHobby.adapter = hobbyAdapter
        }

        with(binding) {
            recyclerTechStack.adapter = techStackAdapter
        }
    }
}