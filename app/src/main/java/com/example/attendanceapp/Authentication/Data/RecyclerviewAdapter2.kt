package com.example.attendanceapp.Authentication.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.attendanceapp.Authentication.RecyclerViewAdapter
import com.example.attendanceapp.Authentication.models.DatabaseModels.AttebdanceModels
import com.example.attendanceapp.Authentication.models.DatabaseModels.DatabaseModel
import com.example.attendanceapp.R
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.DocumentSnapshot

class RecyclerviewAdapter2(
    options: FirestoreRecyclerOptions<AttebdanceModels>,
    var listener : OnDataItemClickListners


 ):FirestoreRecyclerAdapter<AttebdanceModels,RecyclerviewAdapter2.datesViewHolder>(options)
{
    class datesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): datesViewHolder {
        val viewHolder = datesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.daily_dates, parent, false)
        )

        viewHolder.itemView.setOnClickListener {
            listener.onItemClick(snapshots.getSnapshot(viewHolder.adapterPosition),viewHolder.adapterPosition)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: datesViewHolder, position: Int, model: AttebdanceModels) {
        val msg = holder.itemView.findViewById<TextView>(R.id.textmssg)
                val date = holder.itemView.findViewById<TextView>(R.id.heredate)

                date.setText("  Date  : "+model.dates)
                msg.setText("  Class  :  "+model.classes)
    }

    interface OnDataItemClickListners{
        fun onItemClick(documetSnapshot: DocumentSnapshot, position: Int)

    }

    public fun setOnItemClickListener(listener:OnDataItemClickListners){
        this.listener = listener

    }

}
