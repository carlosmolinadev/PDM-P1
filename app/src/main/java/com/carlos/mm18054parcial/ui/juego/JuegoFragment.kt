package com.carlos.mm18054parcial.ui.juego

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
import com.carlos.mm18054parcial.db.JuegoEntity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class JuegoFragment : Fragment(), JuegoAdapter.AdapterClickListeners {

    companion object {
        fun newInstance() = JuegoFragment()
    }

    private lateinit var viewModel: JuegoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = activity?.application as Application
        viewModel = ViewModelProvider(requireActivity(),
            JuegoViewModelFactory(application.repository)).get(JuegoViewModel::class.java)
        return inflater.inflate(R.layout.juego_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val juegos = viewModel.juegos
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            viewModel.juegoActual = null
            findNavController().navigate(R.id.action_nav_juego_to_nav_guardar_juego)
        }

        //Recycler View
        val adapter = JuegoAdapter(this)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(context)

        // Databinding
        viewModel.juegos.observe(viewLifecycleOwner, Observer { juegos ->
            juegos?.let { adapter.submitList(it) }
        })

    }


    override fun onEditJuego(juego: JuegoEntity) {
        viewModel.juegoActual = juego
        findNavController().navigate(R.id.action_nav_juego_to_nav_guardar_juego)
    }

    override fun onDeleteJuego(juego: JuegoEntity) {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage("Estas seguro que deseas borrar el juego ${juego.nombre}?").setCancelable(false).setPositiveButton("Si")
        { dialog, id ->
            viewModel.delete(juego)
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