package com.project.cyy.plan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.cyy.plan.R;
import com.project.cyy.plan.listener.OnItemChildViewClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cyy
 * on 18-5-29下午5:45
 */
public class ThingTabAdapter extends RecyclerView.Adapter<ThingTabAdapter.ViewHolder> {
    private JSONArray array;
    private Context context;
    private OnItemChildViewClickListener onItemChildViewClickListener;

    public void setOnItemChildViewClickListener(OnItemChildViewClickListener onItemChildViewClickListener) {
        this.onItemChildViewClickListener = onItemChildViewClickListener;
    }

    public ThingTabAdapter(JSONArray array, Context context) {
        this.array = array;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tab_thing, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.tv_content = view.findViewById(R.id.tv_content);
        viewHolder.cb_finish = view.findViewById(R.id.cb_finish);
        viewHolder.ll_finish = view.findViewById(R.id.ll_finish);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final int p = holder.getAdapterPosition();
        try {
            final JSONObject object = array.getJSONObject(p);
            holder.tv_content.setText(object.has("content") ? object.getString("content") : "");
            holder.cb_finish.setChecked(object.getString("finish").equalsIgnoreCase("1"));

            holder.ll_finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemChildViewClickListener != null) {
                        onItemChildViewClickListener.onItemChildViewClickListener(v.getId(), p);
                    }
                }
            });
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemChildViewClickListener != null) {
                        onItemChildViewClickListener.onItemChildViewClickListener(-1, p);
                    }
                }
            });

            holder.item.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onItemChildViewClickListener != null) {
                        onItemChildViewClickListener.onItemChildViewClickListener(-2, p);
                    }
                    return true;
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

//    public JSONObject getItem(int position) throws JSONException {
//        return array.getJSONObject(position);
//    }

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
        this.notifyItemRangeRemoved(0, old_length);
        this.notifyItemRangeChanged(0, new_length);
    }


//    public void refresh() {
//        this.notifyItemRangeChanged(0, this.array.length());
//    }
//
//    public void remove(int position) {
//        JSONArray array = new JSONArray();
//        try {
//            for (int i = 0; i < this.array.length(); i++) {
//                if (position == i) {
//                    continue;
//                }
//                array.put(this.array.getJSONObject(i));
//            }
//            this.array = array;
//            this.notifyItemRemoved(position);
//            this.notifyItemRangeChanged(0, this.array.length());
//        } catch (JSONException e) {
//            //
//        }
//    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private ViewHolder(View itemView) {
            super(itemView);
            this.item = itemView;
        }

        TextView tv_content;
        CheckBox cb_finish;
        LinearLayout ll_finish;
        View item;
    }


}
