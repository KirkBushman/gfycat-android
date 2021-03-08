package com.kirkbushman.sampleapp.models

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.kirkbushman.gfycat.models.Gfycat
import com.kirkbushman.sampleapp.R

@EpoxyModelClass
abstract class GfycatModel : EpoxyModelWithHolder<GfycatHolder>() {

    @EpoxyAttribute
    lateinit var gfycat: Gfycat

    @EpoxyAttribute
    lateinit var listener: View.OnClickListener

    override fun getDefaultLayout(): Int {
        return R.layout.item_gfycat
    }

    override fun bind(holder: GfycatHolder) {

        holder.gfyId.text = gfycat.gfyId
        holder.gfyTitle.text = gfycat.title
        holder.gfyAuthor.text = gfycat.userName

        holder.container.setOnClickListener(listener)
    }

    override fun unbind(holder: GfycatHolder) {
        holder.container.setOnClickListener(null)
    }
}

class GfycatHolder : KotlinHolder() {

    val container by bind<LinearLayout>(R.id.container)
    val gfyId by bind<TextView>(R.id.gfycat_id)
    val gfyTitle by bind<TextView>(R.id.gfycat_title)
    val gfyAuthor by bind<TextView>(R.id.gfycat_author)
}
