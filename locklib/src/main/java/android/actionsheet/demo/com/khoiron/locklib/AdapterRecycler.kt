package android.actionsheet.demo.com.khoiron.locklib

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView


/**
 * Created by khoiron on 23/07/18.
 */
class AdapterRecycler(contextt: Context) : RecyclerView.Adapter<AdapterRecycler.MyViewHolder>() {

    var mutableList :MutableList<modelNumber> = ArrayList<modelNumber>()
    val context = contextt

    lateinit var onclickListen :onclickListener

    fun onclik(onclickListener: onclickListener){
        this.onclickListen = onclickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler, parent, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mutableList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var modelNumber = mutableList.get(position)

        holder.number.setText(modelNumber.number)
        if(modelNumber.number.isEmpty()){
            holder.background.visibility = View.INVISIBLE
        }

        if (modelNumber.number=="10"){
            holder.number.visibility = View.INVISIBLE
            holder.img.setImageResource(R.drawable.delet)
        }

        holder.itemView.setOnClickListener {
            /*if(holder.background.background == ContextCompat.getDrawable(context, R.drawable.layout_bg)){

            }else{
                holder.background.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.layout_bg))
            }*/

            if(modelNumber.checked == true){
                modelNumber.checked = false
            }else{
                modelNumber.checked = true
            }
            onclickListen.onclik(holder.itemView.id,position)
        }

    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var number: TextView
        var background: RelativeLayout
        var img : ImageView
        init {
            number = view.findViewById(R.id.number) as TextView
            background = view.findViewById(R.id.background) as RelativeLayout
            img = view.findViewById(R.id.img)
        }
    }

    interface onclickListener{
        fun onclik(view: Int,position :Int)
    }
}