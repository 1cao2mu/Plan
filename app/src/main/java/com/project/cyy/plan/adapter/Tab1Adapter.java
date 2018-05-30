package com.project.cyy.plan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.cyy.plan.R;
import com.project.cyy.plan.View.RoundImageView;
import com.project.cyy.plan.listener.OnItemChildViewClickListener;
import com.project.cyy.plan.tool.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cyy
 * on 18-5-29下午5:45
 */
public class Tab1Adapter extends RecyclerView.Adapter<Tab1Adapter.ViewHolder> {
    JSONArray array;
    Context context;
    OnItemChildViewClickListener onItemChildViewClickListener;

    public void setOnItemChildViewClickListener(OnItemChildViewClickListener onItemChildViewClickListener) {
        this.onItemChildViewClickListener = onItemChildViewClickListener;
    }

    public Tab1Adapter(JSONArray array, Context context) {
        this.array = array;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tab_1, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.iv_icon = (RoundImageView) view.findViewById(R.id.iv_icon);
        viewHolder.iv_icon.setImageResource(R.mipmap.ic_launcher_1);
        viewHolder.nv_content = (ImageView) view.findViewById(R.id.nv_content);
        viewHolder.nv_content.setImageResource(R.mipmap.ic_launcher_1);
        viewHolder.nv_content.setMinimumHeight(Utils.getScreenWidth(context) - 20);
        viewHolder.nv_content.setMinimumWidth(Utils.getScreenWidth(context) - 20);
        viewHolder.tv_name = (TextView) view.findViewById(R.id.tv_name);
        viewHolder.tv_content = (TextView) view.findViewById(R.id.tv_content);
        viewHolder.tv_ding_count = (TextView) view.findViewById(R.id.tv_ding_count);
        viewHolder.tv_cai_count = (TextView) view.findViewById(R.id.tv_cai_count);
        viewHolder.tv_comment_count = (TextView) view.findViewById(R.id.tv_comment_count);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        try {
            final JSONObject object = array.getJSONObject(position);
            holder.tv_name.setText(object.has("uname") ? object.getString("uname") : "无名");
            holder.tv_content.setText(object.has("content") ? object.getString("content") : "");
            holder.tv_ding_count.setText(object.has("topCount") ? String.valueOf(object.getInt("topCount")) : "0");
            holder.tv_cai_count.setText(object.has("bottomCount") ? String.valueOf(object.getInt("bottomCount")) : "0");
            holder.tv_comment_count.setText(object.has("commitCount") ? String.valueOf(object.getInt("commitCount")) : "0");

            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemChildViewClickListener != null) {
                        onItemChildViewClickListener.onItemChildViewClickListener(-1, position);
                    }
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return array.length();
    }

    public JSONObject getItem(int position) throws JSONException {
        return array.getJSONObject(position);
    }

    public void append(JSONArray array) {
        try {
            for (int i = 0; i < array.length(); i++) {
                this.array.put(array.getJSONObject(i));
            }
            this.notifyItemRangeInserted(this.array.length() - array.length() - 1, array.length());
            this.notifyItemRangeChanged(0, this.array.length());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void refresh(JSONArray array) {
        int old_length = this.array.length();
        this.array = array;
        int new_length = this.array.length();
        this.notifyItemRangeRemoved(new_length - 1, old_length - new_length);
        this.notifyItemRangeChanged(0, new_length);
    }

    public void refresh() {
        this.notifyItemRangeChanged(0, this.array.length());
    }

    public void remove(int position) {
        JSONArray array = new JSONArray();
        try {
            for (int i = 0; i < this.array.length(); i++) {
                if (position == i) {
                    continue;
                }
                array.put(this.array.getJSONObject(i));
            }
            this.array = array;
            this.notifyItemRemoved(position);
            this.notifyItemRangeChanged(0, this.array.length());
        } catch (JSONException e) {
        }
    }

    public void top(int position, String topCount, String topId) throws JSONException {
        JSONObject object = array.getJSONObject(position);
        object.put("topCount", topCount);
        object.put("topId", topId);
        this.notifyItemChanged(position);
    }

    public void bottom(int position, String bottomCount, String bottomId) throws JSONException {
        JSONObject object = array.getJSONObject(position);
        object.put("bottomId", bottomId);
        object.put("bottomCount", bottomCount);
        this.notifyItemChanged(position);
    }

    protected static final class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
            this.item = itemView;
        }

        RoundImageView iv_icon;
        TextView tv_name, tv_content, tv_ding_count, tv_cai_count, tv_comment_count;
        ImageView nv_content;
        View item;
    }


}
