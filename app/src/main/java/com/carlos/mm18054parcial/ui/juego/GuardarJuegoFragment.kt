package com.carlos.mm18054parcial.ui.juego


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.carlos.mm18054parcial.Application
import com.carlos.mm18054parcial.R
import com.carlos.mm18054parcial.db.JuegoEntity

class GuardarJuegoFragment : Fragment() {
    private lateinit var viewModel: JuegoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = activity?.application as Application
        viewModel = ViewModelProvider(requireActivity(),
            JuegoViewModelFactory(application.repository)).get(JuegoViewModel::class.java)
        return inflater.inflate(R.layout.guardar_juego_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val nombre: EditText = view.findViewById(R.id.juego_nombre)
        val descripcion: EditText = view.findViewById(R.id.juego_descripcion)
        val cantidadMin: EditText = view.findViewById(R.id.juego_cant_minima)
        val cantidadMax: EditText = view.findViewById(R.id.juego_cant_maxima)
        val categoria: EditText = view.findViewById(R.id.juego_categoria)
        val guardarButton: Button = view.findViewById(R.id.juego_guardar)
        val juego = viewModel.juegoActual
        if (juego != null) {
            nombre.setText(juego.nombre)
            descripcion.setText(juego.descripcion)
            cantidadMin.setText(juego.cantMinima.toString())
            cantidadMax.setText(juego.cantMaxima.toString())
            categoria.setText(juego.categoria)
        }
        guardarButton.setOnClickListener{
            if (cantidadMin.text.isNullOrEmpty() ||
                nombre.text.isNullOrEmpty() ||
                cantidadMax.text.isNullOrEmpty() || cantidadMin.text.isNullOrEmpty()) {
                Toast.makeText(context, "Todos los campos son requeridos",
                    Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (juego != null) {
                viewModel.update(
                    JuegoEntity(
                        0,
                        nombre.text.toString(),
                        descripcion.text.toString(),
                        cantidadMin.text.toString().toInt(),
                        cantidadMax.text.toString().toInt(),
                        categoria.text.toString(),
                    )
                )
            } else {
                viewModel.insert(
                    JuegoEntity(
                    0,
                        nombre.text.toString(),
                        descripcion.text.toString(),
                        cantidadMin.text.toString().toInt(),
                        cantidadMax.text.toString().toInt(),
                        categoria.text.toString(),
                )
                )
            }
            findNavController().navigateUp()
        }
    }
}