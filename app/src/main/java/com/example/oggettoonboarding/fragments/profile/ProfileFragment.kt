package com.example.oggettoonboarding.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.navOptions
import com.example.oggettoonboarding.R
import com.example.oggettoonboarding.auth.models.UserPersonalInfo
import com.example.oggettoonboarding.databinding.FragmentProfileBinding
import com.example.oggettoonboarding.fragments.tabs.TabsFragmentDirections
import com.example.oggettoonboarding.utils.findTopNavController

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel by viewModels<ProfileViewModel> { ProfileViewModelFactory() }
    private lateinit var userPersonalInfo: UserPersonalInfo

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        observeViewModel()

        binding.btnLogOut.setOnClickListener {
            signOut()
        }

        binding.btnEditProfile.setOnClickListener {
            findTopNavController().navigate(TabsFragmentDirections.actionTabsFragmentToEditProfileFragment(
                userPersInfo = userPersonalInfo))
        }

        return binding.root
    }

    private fun signOut() {
        viewModel.singOut()
        findTopNavController().navigate(R.id.signInFragment, null, navOptions {
            popUpTo(R.id.tabsFragment) {
                inclusive = true
            }
        })
    }

    private fun updateUi(userPersonalInfo: UserPersonalInfo) {
        binding.tvName.text = userPersonalInfo.name
        binding.tvPatronymic.text = userPersonalInfo.patronymic
        binding.tvSureName.text = userPersonalInfo.sureName
        binding.tvEmail.text = userPersonalInfo.email
    }

    private fun observeViewModel() {
        viewModel.userData.observe(viewLifecycleOwner) { userPersonalInfoModel ->
            userPersonalInfo = userPersonalInfoModel
            updateUi(userPersonalInfoModel)
        }
    }
}