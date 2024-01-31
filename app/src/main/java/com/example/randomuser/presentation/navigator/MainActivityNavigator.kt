package com.example.randomuser.presentation.navigator

import android.app.Application
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import com.example.randomuser.R
import com.example.randomuser.presentation.MainActivity
import com.example.randomuser.presentation.base.BaseScreen

const val KEY_AGR_SCREEN = "KEY_AGR_SCREEN"

class MainActivityNavigator(application: Application) : AndroidViewModel(application), Navigator {

    var whenActivityActive = MainActivityActions()

    override fun launch(screen: BaseScreen) = whenActivityActive {
        launchFragment(it, screen)
    }

    override fun goBack() = whenActivityActive {
        it.onBackPressed()
    }

    override fun onCleared() {
        super.onCleared()
        whenActivityActive.clear()
    }

    fun launchFragment(activity: MainActivity, screen: BaseScreen, addToBackStack: Boolean = true) {
        val fragment = screen.javaClass.enclosingClass.newInstance() as Fragment
        fragment.arguments = bundleOf(KEY_AGR_SCREEN to screen)
        val transaction = activity.supportFragmentManager.beginTransaction()
        if (addToBackStack) transaction.addToBackStack(screen.javaClass.simpleName)
        transaction
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}