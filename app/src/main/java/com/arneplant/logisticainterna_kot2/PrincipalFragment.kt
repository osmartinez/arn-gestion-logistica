package com.arneplant.logisticainterna_kot2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.arneplant.logisticainterna_kot2.adapter.pager.TabPagerAdapter
import com.arneplant.logisticainterna_kot2.application.Store
import com.arneplant.logisticainterna_kot2.network.MqttCliente
import kotlinx.android.synthetic.main.principal_fragment.view.*

class PrincipalFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.principal_fragment,container,false)

        view.userName.setText(if (Store.ID_OPERARIO !=0) Store.NOMBRE_OPERARIO else "<sin operario>")

        (activity as AppCompatActivity).setSupportActionBar(view.app_bar)

        val fragmentPrimero = PrimerTabFragment()
        val fragmentSegundo = SegundoTabFragment()

        view.tab_layout.setupWithViewPager(view.view_pager)

        if(this.fragmentManager== null){
            return view
        }
        else{
            val viewPagerAdapter =
                TabPagerAdapter(
                    this.fragmentManager!!,
                    0
                )
            // páginas
            viewPagerAdapter.addFragment(fragmentPrimero, "Módulo 1")
            viewPagerAdapter.addFragment(fragmentSegundo,"Módulo 2")

            // paginas!
            view.view_pager.adapter = viewPagerAdapter

            view.tab_layout.getTabAt(0)?.orCreateBadge?.backgroundColor = resources.getColor(R.color.white)
            view.tab_layout.getTabAt(0)?.orCreateBadge?.badgeTextColor = resources.getColor(R.color.black)
            view.tab_layout.getTabAt(0)?.orCreateBadge?.number=5

            view.tab_layout.getTabAt(1)?.orCreateBadge?.backgroundColor = resources.getColor(R.color.white)
            view.tab_layout.getTabAt(1)?.orCreateBadge?.badgeTextColor = resources.getColor(R.color.black)
            view.tab_layout.getTabAt(1)?.orCreateBadge?.number=1

        }

        if(Store.ID_OPERARIO != 0){
            MqttCliente.iniciar(activity!!, Store.CODIGO_OPERARIO)
        }

        return view
    }
}