package com.arneplant.logisticainterna_kot2.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.core.view.marginBottom
import com.arneplant.logisticainterna_kot2.R
import com.arneplant.logisticainterna_kot2.adapter.TareaAcabadaAdapter
import com.arneplant.logisticainterna_kot2.model.OrdenFabricacionOperacion
import com.arneplant.logisticainterna_kot2.model.dto.Consumo
import kotlinx.android.synthetic.main.dialog.*
import kotlinx.android.synthetic.main.dialog.btAceptar
import kotlinx.android.synthetic.main.dialog.texto
import kotlinx.android.synthetic.main.dialog_tareas_acabadas.*
import kotlinx.android.synthetic.main.dialogo_multioperacion.*
import kotlinx.android.synthetic.main.no_programada_dialog.*

import android.widget.LinearLayout
import com.arneplant.logisticainterna_kot2.model.Maquina
import com.arneplant.logisticainterna_kot2.model.dto.PrepaqueteSeccionDTO
import com.arneplant.logisticainterna_kot2.network_implementation.OrdenFabricacionService


object Dialogos {

    fun mostrarDialogoMultiOperacion(descripciones: List<String>, operaciones:List<OrdenFabricacionOperacion>, servicio: OrdenFabricacionService,idMaquina:Int, choiceFunction: (servicio: OrdenFabricacionService,operaciones: List<OrdenFabricacionOperacion>, idMaquina: Int, descripcion:ArrayList<String>)->Unit, ctx:Context){
        var dialog: Dialog = Dialog(ctx)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        if(dialog.window != null){
            var colorDrawable : ColorDrawable = ColorDrawable(Color.TRANSPARENT)
            dialog.window.setBackgroundDrawable(colorDrawable)
        }
        dialog.setContentView(R.layout.dialogo_multioperacion)
        dialog.setCancelable(true)
        dialog.show()

        var colores = ArrayList<Int>()
        colores.add(Color.BLUE)
        colores.add(Color.RED)
        colores.add(Color.GREEN)
        colores.add(Color.MAGENTA)

        var i = 0
        for(operacion in descripciones){
            var btn :Button = Button(ctx)
            btn.setTextColor(Color.WHITE)
            btn.setBackgroundColor(colores[i%colores.size])
            btn.setText(operacion)

            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.bottomMargin =60
            btn.setLayoutParams(params)


            btn.setOnClickListener {
                val descripciones = ArrayList<String>()
                descripciones.add(operacion)
                choiceFunction(servicio,operaciones,idMaquina,descripciones);
                dialog.dismiss()  }
            dialog.panelBotones.addView(btn)
            i++
        }

        var btn= Button(ctx);
        btn.setTextColor(Color.WHITE)
        btn.setBackgroundColor(Color.BLACK)
        btn.setText("- TODO -")
        val params =  LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.bottomMargin =60
        btn.setLayoutParams(params)

        btn.setOnClickListener {
            val descripciones = ArrayList( operaciones.map { it.descripcion })
            choiceFunction(servicio,operaciones,idMaquina,descripciones);
            dialog.dismiss()  }
        dialog.panelBotones.addView(btn)

    }

    fun mostrarDialogoMultiOperacion(operaciones: List<OrdenFabricacionOperacion> ,choiceFunction: (operacion:OrdenFabricacionOperacion, codigoEtiqueta: String)->Unit,codigoEtiqueta:String, ctx:Context){
        var dialog: Dialog = Dialog(ctx)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        if(dialog.window != null){
            var colorDrawable : ColorDrawable = ColorDrawable(Color.TRANSPARENT)
            dialog.window.setBackgroundDrawable(colorDrawable)
        }
        dialog.setContentView(R.layout.dialogo_multioperacion)
        dialog.setCancelable(true)
        dialog.show()

        var colores = ArrayList<Int>()
        colores.add(Color.BLUE)
        colores.add(Color.RED)
        colores.add(Color.GREEN)
        colores.add(Color.MAGENTA)

        var i = 0
        for(operacion in operaciones){
            var btn :Button = Button(ctx)
            btn.setTextColor(Color.WHITE)
            btn.setBackgroundColor(colores[i%colores.size])
            btn.setText(operacion.descripcion)

            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.bottomMargin =60
            btn.setLayoutParams(params)

            btn.setOnClickListener { choiceFunction(operacion,codigoEtiqueta); dialog.dismiss()  }
            dialog.panelBotones.addView(btn)
            i++
        }
    }

    fun mostrarDialogoMultiOperacionAsociar(operaciones: List<PrepaqueteSeccionDTO> ,maquina: Maquina,choiceFunction: (prepaquete:List<PrepaqueteSeccionDTO>, maquina:Maquina, descripcion:String)->Unit, ctx:Context){
        var dialog: Dialog = Dialog(ctx)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        if(dialog.window != null){
            var colorDrawable : ColorDrawable = ColorDrawable(Color.TRANSPARENT)
            dialog.window.setBackgroundDrawable(colorDrawable)
        }
        dialog.setContentView(R.layout.dialogo_multioperacion)
        dialog.setCancelable(true)
        dialog.show()

        var colores = ArrayList<Int>()
        colores.add(Color.BLUE)
        colores.add(Color.RED)
        colores.add(Color.GREEN)
        colores.add(Color.MAGENTA)

        var i = 0
        for(operacion in operaciones){
            var btn :Button = Button(ctx)
            btn.setTextColor(Color.WHITE)
            btn.setBackgroundColor(colores[i%colores.size])
            btn.setText(operacion.descripcion)

            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.bottomMargin =60
            btn.setLayoutParams(params)

            btn.setOnClickListener { choiceFunction(operaciones,maquina, operacion.descripcion); dialog.dismiss()  }
            dialog.panelBotones.addView(btn)
            i++
        }
    }

    fun mostrarDialogoMultiOperacionAsociar(operaciones: List<PrepaqueteSeccionDTO> ,maquina: Maquina,choiceFunction: (prepaquete:PrepaqueteSeccionDTO, maquina:Maquina)->Unit, ctx:Context){
        var dialog: Dialog = Dialog(ctx)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        if(dialog.window != null){
            var colorDrawable : ColorDrawable = ColorDrawable(Color.TRANSPARENT)
            dialog.window.setBackgroundDrawable(colorDrawable)
        }
        dialog.setContentView(R.layout.dialogo_multioperacion)
        dialog.setCancelable(true)
        dialog.show()

        var colores = ArrayList<Int>()
        colores.add(Color.BLUE)
        colores.add(Color.RED)
        colores.add(Color.GREEN)
        colores.add(Color.MAGENTA)

        var i = 0
        for(operacion in operaciones){
            var btn :Button = Button(ctx)
            btn.setTextColor(Color.WHITE)
            btn.setBackgroundColor(colores[i%colores.size])
            btn.setText(operacion.descripcion)

            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.bottomMargin =60
            btn.setLayoutParams(params)

            btn.setOnClickListener { choiceFunction(operacion,maquina); dialog.dismiss()  }
            dialog.panelBotones.addView(btn)
            i++
        }
    }

     fun mostrarDialogoInformacion(msg: String, ctx: Context){
         var dialog: Dialog = Dialog(ctx)
         dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
         if(dialog.window != null){
             var colorDrawable : ColorDrawable = ColorDrawable(Color.TRANSPARENT)
             dialog.window.setBackgroundDrawable(colorDrawable)
         }
         dialog.setContentView(R.layout.dialog)
         dialog.setCancelable(false)
         dialog.show()
         dialog.texto.text = msg
         dialog.btAceptar.setOnClickListener { dialog.dismiss() }

     }

    fun mostrarDialogoProgramar(ctx: Context, yesFuncion: ()->Unit):Boolean{
        var dialog: Dialog = Dialog(ctx)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        if(dialog.window != null){
            var colorDrawable : ColorDrawable = ColorDrawable(Color.TRANSPARENT)
            dialog.window.setBackgroundDrawable(colorDrawable)
        }
        dialog.setContentView(R.layout.no_programada_dialog)
        dialog.setCancelable(false)
        dialog.show()
        var result: Boolean = false
        dialog.btCancelar.setOnClickListener { dialog.dismiss();}
        dialog.btProgramar.setOnClickListener { dialog.dismiss(); yesFuncion(); }

        return result
    }

    fun mostrarDialogoTareasAcabadas(ctx:Context, consumos: List<Consumo>){
        var dialog: Dialog = Dialog(ctx)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        if(dialog.window != null){
            var colorDrawable : ColorDrawable = ColorDrawable(Color.TRANSPARENT)
            dialog.window.setBackgroundDrawable(colorDrawable)
        }
        dialog.setContentView(R.layout.dialog_tareas_acabadas)
        dialog.setCancelable(false)
        dialog.show()
        dialog.lista.adapter = TareaAcabadaAdapter(ctx, consumos)
        dialog.btAceptar.setOnClickListener { dialog.dismiss() }
    }
}