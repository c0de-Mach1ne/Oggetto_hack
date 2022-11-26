package com.example.oggettoonboarding.fragments.employee

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.oggettoonboarding.databinding.ItemEmployeeBinding
import com.example.oggettoonboarding.fragments.models.User
import com.example.oggettoonboarding.utils.ItemClickListener

class AdapterEmployee(private val callback: ItemClickListener<User>) :
    ListAdapter<User, EmployeeViewHolder>(EmployeeDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EmployeeViewHolder(
        ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false), callback
    )

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}