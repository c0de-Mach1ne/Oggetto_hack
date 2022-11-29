package com.example.oggettoonboarding.fragments.checklist

import androidx.recyclerview.widget.RecyclerView
import com.example.oggettoonboarding.R
import com.example.oggettoonboarding.databinding.ItemChecklistBinding
import com.example.oggettoonboarding.fragments.models.CheckList
import com.example.oggettoonboarding.fragments.models.UserCheckList
import com.example.oggettoonboarding.utils.ItemClickListener

class CheckListViewHolder(
    private val binding: ItemChecklistBinding,
    private val callback: ItemClickListener<CheckList>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(checkList: CheckList) {
        binding.tvCheckTitle.text = checkList.checkTitle
        binding.tvCheckDescription.text = checkList.checkDescription
        binding.ivCheckState.setOnClickListener {
            callback.onClickItem(checkList)
            // Todo: тестовая хрень. Будет работать автоматически и засчитывать
            //  без прямого нажатия самого пользователя
            val flag = !(checkList.flagCheck ?: true)
            if(flag) binding.ivCheckState.setImageResource(R.drawable.task_24px)
            else binding.ivCheckState.setImageResource(R.drawable.radio_button_24px)
        }
    }
}