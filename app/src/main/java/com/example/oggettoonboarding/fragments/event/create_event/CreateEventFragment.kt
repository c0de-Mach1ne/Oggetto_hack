package com.example.oggettoonboarding.fragments.event.create_event

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.oggettoonboarding.R
import com.example.oggettoonboarding.auth.models.AuthState
import com.example.oggettoonboarding.databinding.FragmentCreateEventBinding
import com.example.oggettoonboarding.fragments.event.EventViewModel
import com.example.oggettoonboarding.fragments.event.EventViewModelFactory
import com.example.oggettoonboarding.fragments.models.Event
import com.example.oggettoonboarding.fragments.profile.edit_profile.EditProfileFragment
import com.example.oggettoonboarding.utils.findTopNavController

class CreateEventFragment() : Fragment(R.layout.fragment_create_event) {

    private lateinit var binding: FragmentCreateEventBinding
    private val viewModel by viewModels<EventViewModel> { EventViewModelFactory() }
    private var fileUri: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCreateEventBinding.inflate(layoutInflater, container, false)
        observeViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCreateEvent.setOnClickListener {

            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()
            val date = binding.etDate.text.toString()
            val timeStart = binding.etTimeStart.text.toString()
            val timeEnd = binding.etTimeEnd.text.toString()
            val address = binding.etAddress.text.toString()
            val photoUrl = fileUri ?: ""
            val owner = viewModel.getCurrentUser()
            val members = listOf("asd", "asd", "asdasd")

            viewModel.createEvent(Event(
                title = title,
                description = description,
                date = date,
                time = "$timeStart-$timeEnd",
                address = address,
                photoUrl = photoUrl,
                owner = owner,
                members = members,
            ))
        }

        binding.ivUserAvatar.setOnClickListener {
            selectImageFromGallery()
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
            EditProfileFragment.GALLERY_REQUEST_CODE
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

        if (requestCode == EditProfileFragment.GALLERY_REQUEST_CODE
            && resultCode == Activity.RESULT_OK
            && data != null
            && data.data != null
        ) {
            fileUri = data.data.toString()
            viewModel.uploadImage(fileUri!!)
            Glide.with(binding.btnCreateEvent.context).load(data.data).into(binding.ivUserAvatar)
        }
    }

    private fun observeViewModel() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is AuthState.Success -> {
                    Toast.makeText(binding.btnCreateEvent.context, "Success", Toast.LENGTH_SHORT).show()
                }
                is AuthState.Error -> {
                    Toast.makeText(binding.btnCreateEvent.context, "${it.mes}", Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.imageUri.observe(viewLifecycleOwner) {
            fileUri = it.toString()
        }
    }
}