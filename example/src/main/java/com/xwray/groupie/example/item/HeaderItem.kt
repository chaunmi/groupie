package com.xwray.groupie.example.item

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import android.view.View
import android.view.ViewGroup
import com.xwray.groupie.example.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.item_header.*

open class HeaderItem(
        @StringRes private val titleStringResId: Int,
        @StringRes private val subtitleResId: Int? = null,
        @DrawableRes private val iconResId: Int? = null,
        private val onIconClickListener: View.OnClickListener? = null,
        protected val level: Int = 0
        ) : Item() {

    override fun getLayout(): Int {
        return R.layout.item_header
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        if(level > 0) {
            (viewHolder.root.layoutParams as ViewGroup.MarginLayoutParams).apply {
                leftMargin = level * 60
            }
            viewHolder.title.text = viewHolder.root.context.getString(titleStringResId, level)
        }else {
            viewHolder.title.setText(titleStringResId)
        }

        viewHolder.subtitle.apply {
            visibility = View.GONE
            subtitleResId?.let {
                visibility = View.VISIBLE
                setText(it)
            }
        }

        viewHolder.icon.apply {
            visibility = View.GONE
            iconResId?.let {
                visibility = View.VISIBLE
                setImageResource(it)
                setOnClickListener(onIconClickListener)
            }
        }
    }
}
