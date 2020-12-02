package com.arneplant.logisticainterna_kot2.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.arneplant.logisticainterna_kot2.R
import com.arneplant.logisticainterna_kot2.delegate.BuscadorFragmentDelegate
import kotlinx.android.synthetic.main.fragment_buscador.view.*

class BuscadorFragment: Fragment() {

    private lateinit var viewOfLayout: View
    private var tbCodigo: EditText? = null
    private var delegate: BuscadorFragmentDelegate? =null
    private var modoTeclado: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewOfLayout = inflater.inflate(R.layout.fragment_buscador, container, false)
        getControles()
        setManejadores()
        return viewOfLayout;
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is BuscadorFragmentDelegate){
            delegate = context
        }
    }

    fun getControles() {
        tbCodigo = viewOfLayout.tbCodigo
    }

    fun setManejadores() {
        tbCodigo?.showSoftInputOnFocus = false
        tbCodigo?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var text = tbCodigo?.text.toString()
                if (text.length == 13) {
                    //Toast.makeText(activity,text,Toast.LENGTH_LONG).show()
                    delegate?.buscadorFragmentCodigoEscaneado(text)

                    tbCodigo?.setText("")
                }
            }
        })

        tbCodigo?.setOnLongClickListener(object: View.OnLongClickListener{
            override fun onLongClick(v: View?): Boolean {
                if(!modoTeclado){
                    tbCodigo?.setCompoundDrawablesWithIntrinsicBounds(R.drawable.kb_50x50,0,0,0)
                    tbCodigo?.showSoftInputOnFocus=true
                    modoTeclado = true
                }
                else{
                    tbCodigo?.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cb_50x50,0,0,0)
                    tbCodigo?.showSoftInputOnFocus=false
                    modoTeclado = false
                }

                tbCodigo?.setText("")
                tbCodigo?.clearFocus()
                return false
            }
        })
    }


    companion object {
        fun newInstance(): BuscadorFragment {
            return BuscadorFragment();
        }
    }


}
