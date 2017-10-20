package com.example.admin.pollycodechallenge.view.mainView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.pollycodechallenge.R;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.admin.pollycodechallenge.util.Functions.DateConversionToSorHorMorDate;


public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.ViewHolder> {
    private static final String TAG = "FirstAdapter";
    List<Tweet> tweetList;
    Context context;

    public FirstAdapter(List<Tweet> tweetList) {
        this.tweetList = tweetList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Tweet item = tweetList.get(position);

        Picasso.with(context).load(item.user.profileImageUrl).into(holder.picture);
        holder.name.setText(item.user.name);
        boolean url_check = item.user.verified;
        if (!url_check)
            holder.check.setVisibility(holder.check.getRootView().GONE);
        holder.screenName.setText("@"+item.user.screenName);
        holder.time.setText(DateConversionToSorHorMorDate(item.createdAt));
        holder.description.setText(item.text);
        if(item.entities.media != null && item.entities.media.size()>0) {
            String url_img = item.entities.media.get(0).mediaUrl;
            Picasso.with(context).load(url_img).into(holder.image);
        }
        else
            holder.image.setVisibility(holder.image.getRootView().GONE);
        if(item.entities.userMentions != null)
            holder.comments.setText(String.valueOf(item.entities.userMentions.size()));
        holder.retweet.setText(String.valueOf(item.retweetCount));
        holder.likes.setText(String.valueOf(item.favoriteCount));

        holder.expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Future implementation", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return tweetList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.picture)
        CircleImageView picture;
        @Nullable
        @BindView(R.id.name)
        TextView name;
        @Nullable
        @BindView(R.id.check)
        ImageView check;
        @Nullable
        @BindView(R.id.screenName)
        TextView screenName;
        @Nullable
        @BindView(R.id.time)
        TextView time;
        @Nullable
        @BindView(R.id.expand)
        ImageView expand;
        @Nullable
        @BindView(R.id.description)
        TextView description;
        @Nullable
        @BindView(R.id.image)
        ImageView image;
        @Nullable
        @BindView(R.id.retweet)
        TextView retweet;
        @Nullable
        @BindView(R.id.likes)
        TextView likes;
        @Nullable
        @BindView(R.id.comments)
        TextView comments;
        @Nullable
        @BindView(R.id.message)
        ImageButton message;

        public ViewHolder(View ResultView) {
            super(ResultView);
            ButterKnife.bind(this, ResultView);
        }
    }
}
