package com.example.oggettoonboarding.fragments.checklist

import androidx.recyclerview.widget.DiffUtil
import com.example.oggettoonboarding.fragments.models.CheckList
import com.example.oggettoonboarding.fragments.models.UserCheckList

class CheckListDiffUtilCallback : DiffUtil.ItemCallback<CheckList>() {

    override fun areItemsTheSame(oldItem: CheckList, newItem: CheckList) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: CheckList, newItem: CheckList) =
        oldItem.checkDescription == newItem.checkDescription
}