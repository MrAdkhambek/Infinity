@file:Suppress("FunctionName")

package mr.adkhambek.infinity.ui

import com.github.terrakok.cicerone.androidx.FragmentScreen
import mr.adkhambek.infinity.ui.main.MainFragment

object Screens {

    fun Main(): FragmentScreen = FragmentScreen {
        MainFragment()
    }
}