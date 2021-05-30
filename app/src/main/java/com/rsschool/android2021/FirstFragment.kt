package com.rsschool.android2021

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var minValue: EditText? = null
    private var maxValue: EditText? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        minValue = view.findViewById(R.id.min_value)
        maxValue = view.findViewById(R.id.max_value)
        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"
        val mainActivity = activity as MainActivity?
        generateButton?.setOnClickListener {
            if (checkNumber(minValue?.text.toString()) && checkNumber(maxValue?.text.toString())) {
                if (minValue?.text.toString().toInt() < maxValue?.text.toString().toInt()) {
                    mainActivity?.openSecondFragmentFromActivity(
                        minValue?.text.toString().toInt(),
                        maxValue?.text.toString().toInt()
                    )
                } else Toast.makeText(
                    mainActivity?.applicationContext,
                    "Wrong input :3",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    mainActivity?.applicationContext,
                    "Wrong input :3",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun checkNumber(numberToCompare: String): Boolean {
        var sum: Long = 0
        if (numberToCompare == "") return false
        if (numberToCompare.toLong() < Integer.MAX_VALUE) return true
        if (numberToCompare.toLong() > Integer.MAX_VALUE) return false
        else {
            for (i in numberToCompare.indices) {
                sum = sum * 10 + numberToCompare[i].toInt()
                if (sum > Integer.MAX_VALUE) return false
            }
            return true
        }
    }

    override fun onResume() {
        super.onResume()
        val mainActivity = activity as ResultHoster?
        var result: TextView? = null
        result = view?.findViewById(R.id.previous_result)
        result?.text = "Previous result: " + mainActivity?.getPreviousResult().toString()
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}