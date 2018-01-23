package com.example.sapir.shapeit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * this class creates an adpeter whose function is to insert the information into the object called recycerview, which displays the names of their classes and times.
 */
public class AdapterCv extends RecyclerView.Adapter<AdapterCv.MyViewHolder> {


    //attributes
    ArrayList<Calendar> ad;

    //constructor
    public AdapterCv(ArrayList<Calendar> ad) {
        this.ad = ad;
    }

    /**
     * inner class that provide a reference to the views for each data item
     * Complex data items may need more than one view per item, and
     * provide access to all the views for a data item in a view holder.
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        /**
         * attributes
         */
        TextView lesson_name, hour;

        /**
         * provide a suitable constructor (depends on the kind of dataset).
         * @param view
         */
        public MyViewHolder(View view) {
            super(view);
            lesson_name = (TextView) view.findViewById(R.id.lesson_name);
            hour = (TextView) view.findViewById(R.id.lesson_hour);

        }
    }

    /**
     *create new views (invoked by the layout manager).
     * @param parent
     * @param viewType
     * @return view
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layoutcalendar, parent, false);

        return new MyViewHolder(itemView);
    }

    /**
     * replace the contents of a view (invoked by the layout manager).
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.lesson_name.setText(ad.get(position).lesson_name);
        holder.hour.setText(ad.get(position).hour);

    }

    /**
     * return the size of dataset (invoked by the layout manager).
     * @return size.
     */
    @Override
    public int getItemCount() {
        return ad.size();
    }
}

