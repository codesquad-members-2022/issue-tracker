package com.example.issu_tracker.ui.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MenuItem
import android.view.MenuItem.SHOW_AS_ACTION_ALWAYS
import android.widget.Toolbar
import androidx.appcompat.view.ContextThemeWrapper
import com.example.issu_tracker.R

class CustomToolbar(
    context: Context,
    attrs: AttributeSet?,
) : Toolbar(context, attrs) {

    lateinit var firstActionItem: MenuItem
    lateinit var secondActionItem: MenuItem

    private var toolbarTitle: Int
    private var toolbarNavigationIcon: Drawable? = null
    private var firstActionText: Int
    private var secondActionText: Int

    init {
        setMenu()
        initToolbarUI()

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomToolbar,
            0,
            0
        ).apply {
            try {
                toolbarTitle = getResourceId(R.styleable.CustomToolbar_toolbarTitle, 0)
                toolbarNavigationIcon = getDrawable(R.styleable.CustomToolbar_toolbarNavigationIcon)
                firstActionText = getResourceId(R.styleable.CustomToolbar_firstActionText, 0)
                secondActionText = getResourceId(R.styleable.CustomToolbar_secondActionText, 0)
                setToolbarTitle(toolbarTitle)
                setToolbarNavigationIcon(toolbarNavigationIcon)
                setFirstActionTitle(firstActionText)
                setSecondActionTitle(secondActionText)
            } finally {
                recycle()
            }
        }
    }

    private fun setMenu() {
        inflateMenu(R.menu.toolbar_menu)
        firstActionItem = menu.getItem(0)
        secondActionItem = menu.getItem(1)
        firstActionItem.setShowAsAction(SHOW_AS_ACTION_ALWAYS)
        secondActionItem.setShowAsAction(SHOW_AS_ACTION_ALWAYS)
        firstActionItem.isEnabled = false
        secondActionItem.isEnabled = false
    }

    private fun initToolbarUI() {
        this.setBackgroundColor(resources.getColor(R.color.issue_tracker_blue))
        this.setTitleTextAppearance(context, R.style.HeadLine6)
        ContextThemeWrapper()
    }

    private fun setToolbarTitle(textRes: Int) {
        if (textRes != 0) this.setTitle(textRes)
    }

    private fun setToolbarNavigationIcon(icon: Drawable?) {
        this.navigationIcon = icon
    }

    private fun setFirstActionTitle(textRes: Int) {
        if (textRes != 0) firstActionItem.setTitle(textRes)
        else firstActionItem.isVisible = false
    }

    private fun setSecondActionTitle(textRes: Int) {
        if (textRes != 0) secondActionItem.setTitle(textRes)
        else secondActionItem.isVisible = false
    }

}