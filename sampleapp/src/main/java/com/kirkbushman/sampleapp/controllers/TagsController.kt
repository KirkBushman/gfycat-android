package com.kirkbushman.sampleapp.controllers

import android.view.View
import com.airbnb.epoxy.EpoxyController
import com.kirkbushman.sampleapp.models.empty
import com.kirkbushman.sampleapp.models.tag

class TagsController(private val callback: OnClickCallback) : EpoxyController() {

    private val items = ArrayList<String>()

    fun setItems(items: List<String>) {
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

            tag {
                id(it)
                tag(it)
                listener(View.OnClickListener { this@TagsController.callback.onClick(index) })
            }
        }
    }
}
