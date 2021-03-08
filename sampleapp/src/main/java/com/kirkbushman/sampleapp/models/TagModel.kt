package com.kirkbushman.sampleapp.models

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.kirkbushman.sampleapp.R

@EpoxyModelClass
abstract class TagModel : EpoxyModelWithHolder<TagHolder>() {

    @EpoxyAttribute
    lateinit var tag: String

    @EpoxyAttribute
    lateinit var listener: View.OnClickListener

    override fun getDefaultLayout(): Int {
        return R.layout.item_tag
    }

    override fun bind(holder: TagHolder) {

        holder.tags.text = tag

        holder.container.setOnClickListener(listener)
    }

    override fun unbind(holder: TagHolder) {
        holder.container.setOnClickListener(null)
    }
}

class TagHolder : KotlinHolder() {

    val container by bind<LinearLayout>(R.id.container)
    val tags by bind<TextView>(R.id.tag)
}
