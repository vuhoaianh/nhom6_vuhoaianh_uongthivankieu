package com.example.data_masking_project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.data_masking_project.R;
import com.example.data_masking_project.click.IclickListener;
import com.example.data_masking_project.model.User;
import com.example.data_masking_project.model.UserResponce;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private final List<UserResponce> listUser;
    private IclickListener iclickListener;


    public UserAdapter(List<UserResponce> listUser, IclickListener mIclickListener) {
        this.listUser = listUser;
        this.iclickListener = mIclickListener;
    }


    public interface OnItemClickListener {
        void onItemClick(User user);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView usernameTextView;

        public TextView addressTextView;

        public RelativeLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.username_text_view);
            addressTextView = itemView.findViewById(R.id.address_text_view);
            layout = itemView.findViewById(R.id.layout_user_items);
        }
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserResponce user = listUser.get(position);
        if(user == null)
        {
            return;
        }
        holder.usernameTextView.setText(user.getUsername().toString());
        if(user.getAddress() != null)
        {
            holder.addressTextView.setText(user.getAddress().toString());
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iclickListener.onclickListener(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(null != listUser)
        {
            return listUser.size();
        }
        return 0;
    }
}
