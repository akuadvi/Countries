package com.example.countries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;

import java.io.InputStream;

public class country extends AppCompatActivity {
    ImageView img;
    TextView title,region,capital,subregion,population,boundaries,language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        img=findViewById(R.id.imageView);
        title=findViewById(R.id.Name);
        region= findViewById(R.id.region);
        capital= findViewById(R.id.capital);
        subregion= findViewById(R.id.subregion);
        population= findViewById(R.id.population);
        boundaries= findViewById(R.id.boundaries);
        language= findViewById(R.id.languages);

        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
        region.setText(intent.getStringExtra("region"));
        capital.setText(intent.getStringExtra("capital"));
        subregion.setText(intent.getStringExtra("SubRegion"));
        population.setText(intent.getStringExtra("Population"));
        boundaries.setText(intent.getStringExtra("Borders"));
        language.setText(intent.getStringExtra("Languages"));

        GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder = Glide.with(this
        )
                .using(Glide.buildStreamModelLoader(Uri.class, this), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .listener(new SvgSoftwareLayerSetter<Uri>());

        requestBuilder.diskCacheStrategy(DiskCacheStrategy.NONE)
                .load(Uri.parse(intent.getStringExtra("Flag")))
                .into(img);


    }
}