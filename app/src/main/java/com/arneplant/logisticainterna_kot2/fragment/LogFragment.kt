package com.arneplant.logisticainterna_kot2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arneplant.logisticainterna_kot2.R
import kotlinx.android.synthetic.main.fragment_log.view.*

class LogFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var viewOfLayout: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewOfLayout = inflater!!.inflate(R.layout.fragment_log, container, false)

        return viewOfLayout;
    }

    fun log(msg: String,ok: Boolean){
        viewOfLayout.tbLog?.setText(msg)
        if(!ok){
            viewOfLayout.tbLog?.setBackgroundResource(R.drawable.edittext_style_log_error);
        }
        else{
            viewOfLayout.tbLog?.setBackgroundResource(R.drawable.edittext_style);
        }
    }

    companion object {
        fun newInstance(): LogFragment{
            return LogFragment();
        }
    }
}



