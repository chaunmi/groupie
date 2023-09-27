package com.xwray.groupie.example.item

import android.view.ViewGroup.MarginLayoutParams
import androidx.annotation.ColorInt
import com.xwray.groupie.example.INSET
import com.xwray.groupie.example.INSET_TYPE_KEY
import com.xwray.groupie.example.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.item_card.*

open class CardItem (@ColorInt private val colorInt: Int, val text: CharSequence? = "", val level: Int = 0) : Item() {

    init {
        extras[INSET_TYPE_KEY] = INSET
    }

    override fun getLayout() = R.layout.item_card

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.root.setBackgroundColor(colorInt)
        if(level != 0) {
            (viewHolder.root.layoutParams as MarginLayoutParams).apply {
                leftMargin = level * 60
            }
        }
        viewHolder.text.text = text
    }
}
