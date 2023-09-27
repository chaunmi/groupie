package com.xwray.groupie.example

import android.graphics.drawable.Animatable
import androidx.annotation.StringRes
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.Item
import com.xwray.groupie.example.item.HeaderItem
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.item_header.*

class ExpandableHeaderItem(
    @StringRes titleStringResId: Int,
    @StringRes subtitleResId: Int,
    level: Int = 0
) : HeaderItem(titleStringResId, subtitleResId, level = level), ExpandableItem {
    var clickListener: ((ExpandableHeaderItem) -> Unit)? = null

    private lateinit var expandableGroup: ExpandableGroup

    private val actualClickListener = View.OnClickListener {
        clickListener?.invoke(this)
    }

    private val iconClickListener = View.OnClickListener { icon ->
        expandableGroup.onToggleExpanded()
        (icon as ImageView).apply {
            visibility = View.VISIBLE
            setImageResource(when {
                expandableGroup.isExpanded -> R.drawable.collapse_animated
                else -> R.drawable.expand_animated
            })
            (drawable as Animatable).start()
        }
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        super.bind(viewHolder, position)

        if(level > 0) {
            (viewHolder.root.layoutParams as ViewGroup.MarginLayoutParams).apply {
                leftMargin = level * 60
            }

        }
        viewHolder.title.text = viewHolder.title.text.toString() + "(${this.expandableGroup.childLeafCount})"

                // Initial icon state -- not animated.
        viewHolder.icon.apply {
            visibility = View.VISIBLE
            setImageResource(when {
                expandableGroup.isExpanded -> R.drawable.collapse
                else -> R.drawable.expand
            })
            setOnClickListener(iconClickListener)
        }

        viewHolder.itemView.setOnClickListener(actualClickListener)
    }

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        this.expandableGroup = onToggleListener
    }
}
