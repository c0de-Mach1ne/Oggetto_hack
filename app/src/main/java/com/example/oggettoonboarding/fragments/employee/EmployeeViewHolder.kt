package com.example.oggettoonboarding.fragments.employee

import android.util.Log
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.oggettoonboarding.databinding.ItemEmployeeBinding
import com.example.oggettoonboarding.fragments.models.User
import com.example.oggettoonboarding.utils.ItemClickListener

class EmployeeViewHolder(
    private val binding: ItemEmployeeBinding,
    private val callback: ItemClickListener<User>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {

        binding.root.setOnClickListener {
            callback.onClickItem(user)
        }

        binding.tvCity.text = user.aboutMe?.city
        Glide.with(binding.root.context)
            .load(user.photoUrl?.toUri())
            .into(binding.ivUserPreview)
        Log.d("TAG", "photo url ${user.photoUrl}")
        val fullName = "${user.name} ${user.sureName}"
        binding.tvFullName.text = fullName
        val workRole = "${user.job?.grade} ${user.job?.jobTitle}"
        binding.tvWorkRole.text = workRole

    }
}