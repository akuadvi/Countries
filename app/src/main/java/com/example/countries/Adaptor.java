package com.example.countries;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.List;

public class Adaptor extends RecyclerView.Adapter<Adaptor.ViewHolder> {
    Context con;
    List<countries> c;

    public Adaptor(Context con,List<countries> c) {
        this.con = con;
        this.c=c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(con);
        View view=inflater.inflate(R.layout.card_layout,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        countries cont=c.get(position);
        Intent myIntent = new Intent(con, country.class);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("simbu",cont.getName());
                myIntent.putExtra("title",cont.getName());
                myIntent.putExtra("capital",cont.getCapital()); myIntent.putExtra("region",cont.getRegion());
                myIntent.putExtra("SubRegion",cont.getSubregion());
                myIntent.putExtra("Population",cont.getPopulation().toString());
                myIntent.putExtra("Borders",cont.getBorders().get(0).toString());
                myIntent.putExtra("Languages",cont.getLanguages().get(0).getName().toString());
                myIntent.putExtra("Flag",cont.getFlag());
                con.startActivity(myIntent);



            }
        });

        holder.title.setText(cont.getName());



        GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder = Glide.with(con
        )
                .using(Glide.buildStreamModelLoader(Uri.class, con), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .listener(new SvgSoftwareLayerSetter<Uri>());

        requestBuilder.diskCacheStrategy(DiskCacheStrategy.NONE)
                .load(Uri.parse(cont.getFlag()))
                .into(holder.img);


    }

    @Override
    public int getItemCount() {
        return c.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView title,region,capital,subregion,population,boundaries,language;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            img=itemView.findViewById(R.id.imageView);
            title=itemView.findViewById(R.id.Name);




        }
    }
}
