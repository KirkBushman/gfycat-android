package com.kirkbushman.sampleapp.models

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.kirkbushman.gfycat.models.Gfycat
import com.kirkbushman.sampleapp.R

@EpoxyModelClass(layout = R.layout.item_gfycat)
abstract class GfycatModel : EpoxyModelWithHolder<GfycatHolder>() {

    @EpoxyAttribute
    lateinit var gfycat: Gfycat

    @EpoxyAttribute
    lateinit var listener: View.OnClickListener

    override fun bind(holder: GfycatHolder) {

        holder.gfyId.text = gfycat.gfyId
        holder.gfyName.text = gfycat.gfyName
        holder.gfyNumber.text = gfycat.gfyNumber

        holder.container.setOnClickListener(listener)
    }

    override fun unbind(holder: GfycatHolder) {
        holder.container.setOnClickListener(null)
    }
}

class GfycatHolder : KotlinHolder() {

    val container by bind<LinearLayout>(R.id.container)
    val gfyId by bind<TextView>(R.id.gfycat_id)
    val gfyName by bind<TextView>(R.id.gfycat_name)
    val gfyNumber by bind<TextView>(R.id.gfycat_number)
}

