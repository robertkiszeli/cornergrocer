package com.robertkiszelirk.cornergrocer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

//THE ADAPTER TO FILL UP LIST
class ProduceAdapter extends RecyclerView.Adapter<ProduceAdapter.ViewHolder> {

    //CREATE VIEWHOLDER
    @Override
    public ProduceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View produceView = inflater.inflate(R.layout.list_item,parent,false);
        return new ViewHolder(produceView);
    }
    //SET VIEW DATA
    @Override
    public void onBindViewHolder(ProduceAdapter.ViewHolder holder, int position) {
        //GET CURRENT PRODUCE
        BaseProduce produce = mProduces.get(position);
        //SET PRODUCE NAME
        TextView produceName = holder.produceName;
        produceName.setText(produce.getProduceName());
        //SET SELLING PRICE
        TextView sellingPrice = holder.sellingPrice;
        sellingPrice.setText(produce.getSellingPrice());
        //SET PICTURE OF PRODUCE
        ImageView picture = holder.picture;
        Glide.with(getContext()).load(getContext().getResources().getIdentifier(produce.getPicture(),"drawable", getContext().getPackageName())).into(holder.picture);
    }
    //GET NUMBER OF PRODUCES
    @Override
    public int getItemCount() {
        return mProduces.size();
    }
    //CREATE VIEW HOLDER XML ITEMS
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView produceName;
        TextView sellingPrice;
        ImageView picture;

        ViewHolder(View itemView) {
            super(itemView);

            produceName = (TextView) itemView.findViewById(R.id.fruit_name);
            sellingPrice = (TextView) itemView.findViewById(R.id.fruit_price);
            picture = (ImageView) itemView.findViewById(R.id.fruit_picture);
        }
    }
    //LIST OF PRODUCES
    private List<BaseProduce> mProduces;
    //PASSED CONTEXT
    private Context mContext;
    //SET VARIABLES
    ProduceAdapter(Context context, List<BaseProduce> produces){
        mProduces = produces;
        mContext = context;
    }
    //GET CONTEXT
    private Context getContext(){
        return mContext;
    }
}
