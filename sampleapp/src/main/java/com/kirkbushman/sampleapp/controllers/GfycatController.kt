package com.kirkbushman.sampleapp.controllers

import android.view.View
import com.airbnb.epoxy.EpoxyController
import com.kirkbushman.gfycat.models.Gfycat
import com.kirkbushman.sampleapp.models.empty
import com.kirkbushman.sampleapp.models.gfycat

class GfycatController(private val callback: OnClickCallback) : EpoxyController() {

    private val items = ArrayList<Gfycat>()

    fun setItems(items: List<Gfycat>) {
        this.items.clear()
        this.items.addAll(items)
        requestModelBuild()
    }

    override fun buildModels() {

        if (items.isEmpty()) {
            empty {
                id("empty_model")
            }
        }

        items.forEachIndexed { index, it ->

            gfycat {
                id(it.gfyId)
                gfycat(it)
                listener(View.OnClickListener { callback.onClick(index) })
            }
        }
    }
}
