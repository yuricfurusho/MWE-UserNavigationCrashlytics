package com.yuricfurusho.temp01firebase

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.fragment_main.*


/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {

    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this.context!!)

        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "evento")
        btnEvento.setOnClickListener { mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle) }

        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}
