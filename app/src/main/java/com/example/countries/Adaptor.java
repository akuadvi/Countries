package com.example.countries;

import android.content.Context;
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
        holder.title.setText(cont.getName());
        holder.capital.setText(cont.getCapital());
        holder.region.setText(cont.getRegion());
        holder.subregion.setText(cont.getSubregion());
        holder.population.setText(cont.getPopulation().toString());
        holder.boundaries.setText(cont.getBorders().get(0).toString());
        holder.language.setText(cont.getLanguages().get(0).getName().toString());


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
            region=itemView.findViewById(R.id.region);
            capital=itemView.findViewById(R.id.capital);
            subregion=itemView.findViewById(R.id.subregion);
            population=itemView.findViewById(R.id.population);
            boundaries=itemView.findViewById(R.id.boundaries);
            language=itemView.findViewById(R.id.languages);



        }
    }
}
