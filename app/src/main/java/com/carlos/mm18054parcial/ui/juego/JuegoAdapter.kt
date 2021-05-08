package com.carlos.mm18054parcial.ui.juego

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carlos.mm18054parcial.R
import com.carlos.mm18054parcial.db.JuegoEntity

class JuegoAdapter(adapterClickListener: AdapterClickListeners) : ListAdapter<JuegoEntity, JuegoAdapter.MyViewHolder>(UserComparator()) {

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
        private val nameField: TextView = itemView.findViewById(R.id.item_name)
        private val categoryField: TextView = itemView.findViewById(R.id.item_category)
        private val cantMin: TextView = itemView.findViewById(R.id.item_cant_min)
        private val cantMax: TextView = itemView.findViewById(R.id.item_cant_max)
        private val updateButton: ImageButton = itemView.findViewById(R.id.update_button)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.delete_button)

        fun bind(juego: JuegoEntity) {
            System.out.println(juego)
            nameField.text = juego.nombre
            categoryField.text = juego.categoria
            cantMin.text = juego.cantMinima.toString()
            cantMax.text = juego.cantMinima.toString()

            updateButton.setOnClickListener {
                adapterClickListener.onEditJuego(juego)
            }

            deleteButton.setOnClickListener {
                adapterClickListener.onDeleteJuego(juego)
            }
        }

        companion object {
            fun create(parent: ViewGroup, AdapterClickListeners: AdapterClickListeners):
                    MyViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return MyViewHolder(view, AdapterClickListeners)
            }
        }
    }

    class UserComparator : DiffUtil.ItemCallback<JuegoEntity>() {
        override fun areItemsTheSame(oldItem: JuegoEntity, newItem: JuegoEntity): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: JuegoEntity, newItem: JuegoEntity):
                Boolean {
            return oldItem.identificador == newItem.identificador
        }
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    interface AdapterClickListeners {
        fun onEditJuego(usuario: JuegoEntity)
        fun onDeleteJuego(usuario: JuegoEntity)
    }
}