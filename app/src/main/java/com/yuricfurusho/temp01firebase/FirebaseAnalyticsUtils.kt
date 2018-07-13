package com.yuricfurusho.temp01firebase

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.google.firebase.analytics.FirebaseAnalytics

object FirebaseAnalyticsUtils {

    val ON_CLICK = "onClick"
    val NO_ID = "NO_ID"
    val NULL = "null"

    fun logFirebaseEvent(mFirebaseAnalytics: FirebaseAnalytics, view: View?, itemName: String) {
        var parentClass = ""
        var parentIdName = ""
        var viewClass = ""
        var viewIdName = ""
        var viewText = ""
        var childClass = ""
        var childIdName = ""
        var childText = ""

        if (view != null) {
            val viewParent = view.parent as View
            if (viewParent != null) {
                parentClass = viewParent.javaClass.simpleName
                val parentId = viewParent.id
                if (parentId != -1) {
                    parentIdName = view.context.resources.getResourceEntryName(parentId)
                } else {
                    parentIdName = NO_ID
                }
            } else {
                parentIdName = NULL
            }
        } else {
            parentIdName = NULL
        }


        if (view != null) {
            viewClass = view.javaClass.simpleName
            val viewId = view.id
            if (viewId != -1) {
                viewIdName = view.context.resources.getResourceEntryName(viewId)
            } else {
                viewIdName = NO_ID
            }
            if (view is TextView) {
                viewText = view.text.toString()
            }
        } else {
            viewIdName = NULL
        }

        if (view != null) {
            val viewGroup = view as ViewGroup?
            var viewChild: View? = null
            val childCount = viewGroup!!.childCount
            if (childCount > 0) {
                viewChild = viewGroup.getChildAt(0)
            }
            if (viewChild != null) {
                childClass = viewChild.javaClass.simpleName
                val childId = viewChild.id
                if (childId != -1) {
                    childIdName = view.context.resources.getResourceEntryName(childId)
                } else {
                    childIdName = NO_ID
                }
                if (viewChild is TextView) {
                    childText = viewChild.text.toString()
                }
            } else {
                childIdName = NULL
            }
        } else {
            childIdName = NULL
        }

        logFirebaseEvent(mFirebaseAnalytics, parentClass, parentIdName, viewClass, viewIdName, viewText, childClass, childIdName, childText, itemName)

    }

    /**
     * @param mFirebaseAnalytics
     * @param parentClass
     * @param parentIdName
     * @param viewClass
     * @param viewIdName
     * @param viewText
     * @param childClass
     * @param childIdName
     * @param childText
     * @param itemName           utilizar caso ainda não esteja claro onde foi clicado só com os outros parâmentros
     */
    fun logFirebaseEvent(mFirebaseAnalytics: FirebaseAnalytics,
                         parentClass: String, parentIdName: String,
                         viewClass: String, viewIdName: String, viewText: String,
                         childClass: String, childIdName: String, childText: String,
                         itemName: String) {
        val bundle = Bundle()
        bundle.putString("parent_class", parentClass)
        bundle.putString("parent_id", parentIdName)
        bundle.putString("view_class", viewClass)
        bundle.putString("view_id", viewIdName)
        bundle.putString("view_text", viewText)
        bundle.putString("child_class", childClass)
        bundle.putString("child_id", childIdName)
        bundle.putString("child_text", childText)
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, itemName)
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }
}
