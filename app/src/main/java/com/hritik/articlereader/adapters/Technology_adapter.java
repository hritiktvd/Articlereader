package com.hritik.articlereader.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hritik.articlereader.Article_main;
import com.hritik.articlereader.R;
import com.hritik.articlereader.model.Article;
import com.hritik.articlereader.model.Entertainment;
import com.hritik.articlereader.model.Source;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Technology_adapter extends RecyclerView.Adapter<Technology_adapter.TechnologyViewHolder> {

    //we are storing all the products in a list
    private List<Article> articleList;
    private List<Entertainment> entertainmentList;
    private List<Source> sourceList;
    //this context we will use to inflate the layout
    private Context context;
    //onclick to next page
    CardView cardView;


    //getting the context and product list with constructor
    public Technology_adapter(Context context, List<Article> articleList) {
        this.context = context;
        this.articleList = articleList;
        this.entertainmentList = entertainmentList;
        this.sourceList = sourceList;
    }

    @NonNull
    @Override
    public TechnologyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_news, parent, false);
        return new TechnologyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TechnologyViewHolder holder, int position) {

        holder.headline.setText(articleList.get(position).getTitle());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(articleList.get(position).getUrlToImage())
                .placeholder((R.drawable.ic_launcher_foreground))
                .error(R.drawable.technology_error)
                .into(holder.thumbnail);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = articleList.get(position).getTitle();
                String desc = articleList.get(position).getDescription();
                String article_url = articleList.get(position).getUrl();

                Bundle bundle = new Bundle();
                bundle.putString("img_url", articleList.get(position).getUrlToImage());
                bundle.putString("title", title);
                bundle.putString("description", desc);
                bundle.putString("url", article_url);
                Intent i_main = new Intent(context.getApplicationContext(), Article_main.class);
                i_main.putExtras(bundle);
                context.startActivity(i_main);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class TechnologyViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView headline;
        ImageView thumbnail;

        TechnologyViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            headline = mView.findViewById(R.id.headline);
            thumbnail = mView.findViewById(R.id.thumbnail);
            cardView = mView.findViewById(R.id.news_card);
        }
    }
}
