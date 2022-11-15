package com.bagas.laporankecelakaan.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bagas.laporankecelakaan.R

class BeritaAdapter(private val list : ArrayList<BeritaResponse>) : RecyclerView.Adapter<BeritaAdapter.ViewHolder> (){

    lateinit var contextAdapter : Context

    class  ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvDate :TextView  = view.findViewById(R.id.tv_date_berita)
        private val tvTitle :TextView  = view.findViewById(R.id.tv_title_berita)

        fun bindItem(beritaResponse: BeritaResponse) {
            with(itemView){
                val textDesc = beritaResponse.desc
                val textDate = beritaResponse.created_at
                tvDate.text = textDate
                tvTitle.text = textDesc
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInfalter =LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflateView = layoutInfalter.inflate(R.layout.row_item_berita, parent, false)
        return ViewHolder(inflateView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])

    }

    override fun getItemCount(): Int = list.size
}