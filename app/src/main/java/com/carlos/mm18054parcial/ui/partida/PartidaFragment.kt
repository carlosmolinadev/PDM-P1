package com.carlos.mm18054parcial.ui.partida

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carlos.mm18054parcial.Application
import com.carlos.mm18054parcial.R
import com.carlos.mm18054parcial.db.PartidaEntity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PartidaFragment : Fragment(), PartidaAdapter.AdapterClickListeners {

    companion object {
        fun newInstance() = PartidaFragment()
    }

    private lateinit var viewModel: PartidaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = activity?.application as Application
        viewModel = ViewModelProvider(requireActivity(),
            PartidaViewModelFactory(application.repository)
        ).get(PartidaViewModel::class.java)
        return inflater.inflate(R.layout.partida_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val partida = viewModel
        val fab = view.findViewById<FloatingActionButton>(R.id.fab_partida)
        fab.setOnClickListener {
            viewModel.partidaActual = null
            findNavController().navigate(R.id.action_nav_partida_to_nav_guardar_partida)
        }

        //Recycler View
        val adapter = PartidaAdapter(this)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerview_partida)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(context)

        // Databinding
        viewModel.partida.observe(viewLifecycleOwner, Observer { partidas ->
            partidas?.let { adapter.submitList(it) }
        })

    }


    override fun onEditJuego(partida: PartidaEntity) {
        viewModel.partidaActual = partida
        findNavController().navigate(R.id.action_nav_partida_to_nav_guardar_partida)
    }

    override fun onDeleteJuego(partida: PartidaEntity) {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage("Estas seguro que deseas borrar la partida?").setCancelable(false).setPositiveButton("Si")
        { dialog, id ->
            viewModel.delete(partida)
        }
            .setNegativeButton("No") { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}