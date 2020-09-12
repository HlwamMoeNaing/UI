package com.hmn.ui

import com.hmn.ui.room.CategiryEntity

interface RecyclerViewClickInterface {
    fun onItemClick(position:Int,categiryEntity: CategiryEntity)

}