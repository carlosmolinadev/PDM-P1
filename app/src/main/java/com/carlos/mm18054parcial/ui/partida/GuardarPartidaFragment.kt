package com.carlos.mm18054parcial.ui.partida

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.carlos.mm18054parcial.Application
import com.carlos.mm18054parcial.R

import com.carlos.mm18054parcial.db.PartidaEntity


class GuardarPartidaFragment : Fragment() {
    private lateinit var viewModel: PartidaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = activity?.application as Application
        viewModel = ViewModelProvider(requireActivity(),
            PartidaViewModelFactory(application.repository)
        ).get(PartidaViewModel::class.java)
        return inflater.inflate(R.layout.guardar_partida_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val cantidadPartida: EditText = view.findViewById(R.id.partida_cant_participantes)
        val partidaGanadores: EditText = view.findViewById(R.id.partida_ganadores)
        val guardarButton: Button = view.findViewById(R.id.juego_guardar_partida)
        val partida = viewModel.partidaActual
        if (partida != null) {
            cantidadPartida.setText(partida.cantParticipantes.toString())
            partidaGanadores.setText(partida.ganador)
        }
        guardarButton.setOnClickListener{

            if (partida != null) {
                viewModel.update(
                    PartidaEntity(
                        1,
                        cantidadPartida.text.toString(),
                        partidaGanadores.text.toString().toInt(),
                    )
                )
            } else {
                viewModel.insert(
                    PartidaEntity(
                        1,
                        cantidadPartida.text.toString(),
                        partidaGanadores.text.toString().toInt(),
                    )
                )
            }
            findNavController().navigateUp()
        }
    }
}