package com.example.pocket_timer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import kotlin.math.roundToInt



/**
 * Fragment for the countdown timer to include the countdown itself
 * Includes:
 * button to pause / start
 * timer visual
 * button to cancel
 */
class Countdown : Fragment() {

    private lateinit var progressBar : ProgressBar
//    private lateinit var timeText: TextView

    private val countdownTime = 60 // 60 seconds, 1 minute
    private val clockTime = (countdownTime * 1000).toLong()
    private val progressTime = (clockTime / 1000).toFloat()

    private val onBackPressedCallback = object : OnBackPressedCallback(this){
        override fun handleOnBackPressed() {
            onBackPressedMethod()
        }

    }
    private lateinit var customCountdownTimer : CustomCountdownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val view = inflater.inflate(R.layout.fragment_countdown, container, false)

        onBackPressedCallback.addCallback(this,onBackPressedCallback)
        progressBar = view.findViewById(R.id.progressBar)

        var secondsLeft = 0
        customCountdownTimer.reset(clockTime, 1000)
        customCountdownTimer.onTick = {millisUntilFinished ->

            val second = (millisUntilFinished / 1000.0f).roundToInt()
            if (second != secondsLeft){
                secondsLeft = second
            }
        }
        customCountdownTimer.onFinish = {

        }
        progressBar.max = progressTime.toInt()

        progressBar.progress = progressTime.toInt()

        customCountdownTimer.startTimer()

//        val pauseButton = view.findViewById(R.id.pauseButton)
//        val resumeButton = view.findViewById(R.id.resumeButton)
//        val resetButton = view.findViewById(R.id.resetButton)


        return view
    }

    private fun onBackPressedMethod() {
        customCountdownTimer.destroyTimer()
        popBackStack()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment Countdown.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            Countdown().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}