package com.example.admin.litebulb.Adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.litebulb.ItemClickFragment;
import com.example.admin.litebulb.R;
import com.example.admin.litebulb.Models.Users_get;

import java.util.List;

public class AdapterAuthorsAll extends RecyclerView.Adapter<AdapterAuthorsAll.MyViewHolder> {

    private Context mContext;
    private List<Users_get> albumList;
    private AlbumsAdapterListener listener;
    public ImageView thumbnail;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, sales_items;
        public ImageView thumbnail;
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            title = (TextView) view.findViewById(R.id.title);
            sales_items = (TextView) view.findViewById(R.id.sales_items);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            cardView = (CardView) view.findViewById(R.id.card_view);
            thumbnail.setOnClickListener(this);
        }
        public void onClick(View view) {
            Log.e("ADAPTERFEATURED", "this has been clicked");
            ItemClickFragment fragment1 = new ItemClickFragment();
            FragmentManager fragmentManager =((AppCompatActivity)view.getContext()).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contentContainer, fragment1);
            fragmentTransaction.commit();


        }
    }


    public AdapterAuthorsAll(Context mContext, List<Users_get> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_featured_author_all, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Users_get Users_get = albumList.get(position);
        holder.title.setText(Users_get.getUsername());
        holder.sales_items.setText("Author has "+Users_get.getItems()+" items for sale and has racked up "+Users_get.getSales()+" sales!");
        /*loading Users_get cover using Glide library*/
        Glide.with(mContext)
                .load(Users_get.getThumbnail())
                .placeholder(R.drawable.studio)
                .error(R.drawable.studio)
                .into(holder.thumbnail);


    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public interface AlbumsAdapterListener {

       /* void onCardSelected(int position, ImageView thumbnail);  */
    }
}





