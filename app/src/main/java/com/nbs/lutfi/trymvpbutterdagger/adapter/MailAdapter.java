package com.nbs.lutfi.trymvpbutterdagger.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nbs.lutfi.trymvpbutterdagger.R;
import com.nbs.lutfi.trymvpbutterdagger.data.Post;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lutfi on 4/2/2017.
 */

public class MailAdapter extends RecyclerView.Adapter<MailAdapter.MailViewHolder> {
    private List<Post> mailList;
    private Context mailContext;

    public static class MailViewHolder extends RecyclerView.ViewHolder{
        LinearLayout mailLayout;
        @BindView(R.id.list_iv_pict) ImageView mailPict;
        @BindView(R.id.list_iv_star) ImageView mailStar;
        @BindView(R.id.list_tv_sender) TextView mailSender;
        @BindView(R.id.list_tv_subject) TextView mailSubject;
        @BindView(R.id.list_tv_content) TextView mailContent;
        @BindView(R.id.list_tv_time) TextView mailTime;
        @BindView(R.id.list_tv_textpic) TextView mailTextPic;

        public MailViewHolder(View v) {
            super(v);
            mailLayout = (LinearLayout) v.findViewById(R.id.list_layout_mail);
            ButterKnife.bind(this, v);
        }
    }
    public MailAdapter(List<Post> mailList, Context mailContext){
        this.mailList = mailList;
        this.mailContext = mailContext;
    }

    @Override public MailAdapter.MailViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.try_mail_list, parent, false);
        return new MailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MailViewHolder holder, final int position){
        Post mailPost = mailList.get(position);
        holder.mailSender.setText(mailPost.getFrom());
        holder.mailSubject.setText(mailPost.getSubject());
        holder.mailContent.setText(mailPost.getMessage());
        holder.mailTime.setText(mailPost.getTimestamp());
        if (TextUtils.isEmpty(mailPost.getPicture())) {
            holder.mailTextPic.setText(mailPost.getFrom().substring(0,1));
        } else {
            Glide.with(mailContext).load(mailPost.getPicture()).into(holder.mailPict);
            holder.mailTextPic.setVisibility(View.GONE);
        }
        if (!mailPost.getIsImportant()) {
            holder.mailStar.setImageResource(android.R.drawable.star_off);
        } else {
            holder.mailStar.setImageResource(android.R.drawable.star_on);
        }
        if (mailPost.getIsRead()) {
            holder.mailSender.setTypeface(null, Typeface.NORMAL);
            holder.mailSubject.setTypeface(null, Typeface.NORMAL);
        } else {
            holder.mailSender.setTypeface(null, Typeface.BOLD);
            holder.mailSubject.setTypeface(null, Typeface.BOLD);
        }
    }

    @Override
    public int getItemCount() {
        return mailList.size();
    }
}
