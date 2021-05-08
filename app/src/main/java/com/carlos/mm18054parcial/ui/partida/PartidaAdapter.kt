package com.carlos.mm18054parcial.ui.partida

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carlos.mm18054parcial.R
import com.carlos.mm18054parcial.db.PartidaEntity

class PartidaAdapter(adapterClickListener: AdapterClickListeners) : ListAdapter<PartidaEntity, PartidaAdapter.MyViewHolder>(UserComparator()) {

    private val mAdapterClickListener = adapterClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.create(parent, mAdapterClickListener)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class MyViewHolder(itemView: View, adapterClickListener: AdapterClickListeners) : RecyclerView.ViewHolder(itemView) {
        private val adapterClickListener = adapterClickListener
        private val cantParticipante: TextView = itemView.findViewById(R.id.item_cant_participantes)
        private val ganador: TextView = itemView.findViewById(R.id.item_ganador)
        private val updateButton: ImageButton = itemView.findViewById(R.id.update_button_partida)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.delete_button_partida)

        fun bind(partida: PartidaEntity) {
            System.out.println(partida)

            cantParticipante.text = partida.cantParticipantes.toString()
            ganador.text = partida.ganador


            updateButton.setOnClickListener {
                adapterClickListener.onEditJuego(partida)
            }

            deleteButton.setOnClickListener {
                adapterClickListener.onDeleteJuego(partida)
            }
        }

        companion object {
            fun create(parent: ViewGroup, AdapterClickListeners: AdapterClickListeners):
                    MyViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_partida_item, parent, false)
                return MyViewHolder(view, AdapterClickListeners)
            }
        }
    }

    class UserComparator : DiffUtil.ItemCallback<PartidaEntity>() {
        override fun areItemsTheSame(oldItem: PartidaEntity, newItem: PartidaEntity): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: PartidaEntity, newItem: PartidaEntity):
                Boolean {
            return oldItem.identificador == newItem.identificador
        }
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    interface AdapterClickListeners {
        fun onEditJuego(partida: PartidaEntity)
        fun onDeleteJuego(partida: PartidaEntity)
    }
}