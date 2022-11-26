package com.example.oggettoonboarding.auth.fragments.edit_profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.oggettoonboarding.R
import com.example.oggettoonboarding.auth.fragments.edit_profile.hobby.HobbyListAdapter
import com.example.oggettoonboarding.auth.fragments.edit_profile.project.ProjectAdapter
import com.example.oggettoonboarding.auth.fragments.edit_profile.skills.TechStackAdapter
import com.example.oggettoonboarding.databinding.FragmentEditProfleBinding
import com.example.oggettoonboarding.fragments.models.*
import com.example.oggettoonboarding.utils.ItemClickListener
import java.util.*

class EditProfileFragment : Fragment(R.layout.fragment_edit_profle) {

    companion object {
        const val GALLERY_REQUEST_CODE = 1337
    }

    private lateinit var binding: FragmentEditProfleBinding
    private val viewModel by viewModels<EditProfileViewModel> { EditProfileViewModelFactory() }

    private val args by navArgs<EditProfileFragmentArgs>()

    private val hobby = mutableListOf<Hobby>()
    private val professionalSkills = mutableListOf<TechStack>()
    private val projectList = mutableListOf<Project>()

    private lateinit var fileUri: Uri

    private val hobbyAdapter by lazy {
        HobbyListAdapter(object : ItemClickListener<Hobby> {
            override fun onClickItem(value: Hobby) {
                hobby.remove(value)
                viewModel.updateHobbyList(hobby)
            }
        })
    }

    private val projectAdapter by lazy {
        ProjectAdapter(object : ItemClickListener<Project> {
            override fun onClickItem(value: Project) {
                projectList.remove(value)
                viewModel.updateProjectList(projectList)
            }
        })
    }

    private val techStackAdapter by lazy {
        TechStackAdapter(object : ItemClickListener<TechStack> {
            override fun onClickItem(value: TechStack) {
                professionalSkills.remove(value)
                viewModel.updateTechStack(professionalSkills)
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            // Todo: добавить проверку, что если пустая юри, то защитить от краша
            viewModel.uploadImage(fileUri)
            Log.d("TAG" ,"${getUiValue().photoUrl}")
        }

        binding.btnAddHobby.setOnClickListener {
            hobby.add(Hobby(binding.etLayoutHobby.text.toString()))
            binding.etLayoutHobby.text?.clear()
            viewModel.updateHobbyList(hobby)
            Log.d("TAG", "hobbyList $hobby")
        }

        binding.btnAddTechStack.setOnClickListener {
            professionalSkills.add(TechStack(binding.etLayoutTechStack.text.toString()))
            binding.etLayoutTechStack.text?.clear()
            viewModel.updateTechStack(professionalSkills)
            Log.d("TAG", "techList $professionalSkills")
        }

        binding.btnAddProjects.setOnClickListener {
            projectList.add(Project(binding.etLayoutProjects.text.toString()))
            binding.etLayoutProjects.text?.clear()
            viewModel.updateProjectList(projectList)
            Log.d("TAG", "projectList $projectList")
        }

        binding.ivUserAvatar.setOnClickListener {
            selectImageFromGallery()
        }
    }

    private fun setUpUi() {
        val grade = resources.getStringArray(R.array.graid)
        val arrayAdapterGrade = ArrayAdapter(binding.btnSave.context, R.layout.dropdown_item, grade)
        binding.autoCompleteTextViewGrade.setAdapter(arrayAdapterGrade)

        val specialization = resources.getStringArray(R.array.specialization)
        val arrayAdapter =
            ArrayAdapter(binding.btnSave.context, R.layout.dropdown_item, specialization)
        binding.autoCompleteTextViewSpecialization.setAdapter(arrayAdapter)

        with(binding) {
            recyclerHobby.adapter = hobbyAdapter
        }
        with(binding) {
            recyclerTechStack.adapter = techStackAdapter
        }
        with(binding) {
            recyclerProjects.adapter = projectAdapter
        }
    }

    private fun getUiValue(): User {
        val specialization = binding.autoCompleteTextViewSpecialization.text.toString()
        val workLevel = binding.autoCompleteTextViewGrade.text.toString()
        val team = binding.etTeam.text.toString()
        val city = binding.etCity.text.toString()

        return User(
            name = args.userPersInfo.name,
            sureName = args.userPersInfo.sureName,
            patronymic = args.userPersInfo.patronymic,
            photoUrl = fileUri,
            Job(
                jobTitle = specialization,
                grade = workLevel,
                projects = projectList.map { it.toString() },
                team = team,
                professionalSkills = professionalSkills.map { it.toString() },
            ),
            AboutMe(
                city = city,
                hobby = hobby.map { it.toString() },
            ),
        )
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

        viewModel.imageUri.observe(viewLifecycleOwner) {
            fileUri = it
        }

        viewModel.projectList.observe(viewLifecycleOwner) {
            projectAdapter.submitList(it)
            projectAdapter.notifyDataSetChanged()
        }
    }

    private fun selectImageFromGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(
                intent,
                "Please select..."
            ),
            GALLERY_REQUEST_CODE
        )
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
    ) {

        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )

        if (requestCode == GALLERY_REQUEST_CODE
            && resultCode == Activity.RESULT_OK
            && data != null
            && data.data != null
        ) {
            fileUri = data.data!!
            Glide.with(binding.btnSave.context).load(data.data).into(binding.ivUserAvatar)
        }
    }
}