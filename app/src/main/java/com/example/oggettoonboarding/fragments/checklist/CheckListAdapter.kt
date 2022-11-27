package com.example.oggettoonboarding.fragments.checklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.oggettoonboarding.databinding.ItemChecklistBinding
import com.example.oggettoonboarding.fragments.models.CheckList
import com.example.oggettoonboarding.fragments.models.UserCheckList
import com.example.oggettoonboarding.utils.ItemClickListener

class CheckListAdapter(private val callback: ItemClickListener<CheckList>) :
    ListAdapter<CheckList, CheckListViewHolder>(CheckListDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CheckListViewHolder(
        ItemChecklistBinding.inflate(LayoutInflater.from(parent.context), parent, false), callback
    )

    override fun onBindViewHolder(holder: CheckListViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}