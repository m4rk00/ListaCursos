package com.example.listacursos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    final private AdapterCallback callback;

    private List<ItemList> mItems = new ArrayList<>();

    public ListAdapter(List<ItemList> _items, AdapterCallback _callback){
        mItems = _items;
        callback = _callback;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        LinearLayout contenedor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contenedor = itemView.findViewById(R.id.contenedor);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }//Close ViewHolder

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.main__item, viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }//ListAdapter.ViewHolder

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder viewHolder, int i){
        ItemList itemList = mItems.get(i);
        viewHolder.imageView.setImageResource(itemList.getImageItem());
        viewHolder.textView.setText(itemList.getNameItem());
        viewHolder.contenedor.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        if(callback != null){
                            callback.onItemClicked(v,i);
                        }
                    }
                }
        );
    }//close onBindViewHolder

    @Override
    public int getItemCount(){
        return mItems.size();
    }

    public interface AdapterCallback {
        void onItemClicked(View v, int itemPosition);
    }

}//close ListAdapter
