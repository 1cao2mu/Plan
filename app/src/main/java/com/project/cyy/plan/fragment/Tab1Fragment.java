package com.project.cyy.plan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.cyy.plan.R;
import com.project.cyy.plan.adapter.Tab1Adapter;
import com.project.cyy.plan.db.MySqliteHelper;
import com.project.cyy.plan.listener.OnItemChildViewClickListener;
import com.project.cyy.plan.tool.Constants;
import com.project.cyy.plan.tool.Data;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by cyy
 * on 18-5-29下午5:45
 */
public class Tab1Fragment extends Fragment implements View.OnClickListener, OnItemChildViewClickListener {
    RecyclerView rv_list;
    View view;
    SwipeRefreshLayout srl_refresh;
    LinearLayoutManager linearLayoutManager;
    Tab1Adapter adapter;
    boolean REFRESHABLE = true;
    int page = 1, LAST_VISIABLE_IETM_INDEX, FIRST_VISIABLE_IETM_INDEX;
    MySqliteHelper helper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            return view;
        }
        view = inflater.inflate(R.layout.fragment_tab_1, null);
        ((TextView) view.findViewById(R.id.tv_title)).setText("发现");
        ((TextView) view.findViewById(R.id.tv_no_content_note)).setText("还没有数据噢,亲");
        helper = new MySqliteHelper(getContext());
        view.findViewById(R.id.iv_action).setOnClickListener(this);
        view.findViewById(R.id.tv_go).setOnClickListener(this);
        srl_refresh = (SwipeRefreshLayout) view.findViewById(R.id.srl_refresh);
        srl_refresh.setColorSchemeResources(R.color.colorAccent);
        srl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                REFRESHABLE = true;
                page = 1;
                initData();
            }
        });
        rv_list = (RecyclerView) view.findViewById(R.id.rv_list);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_list.setLayoutManager(linearLayoutManager);
        rv_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (adapter != null) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && LAST_VISIABLE_IETM_INDEX == adapter.getItemCount() - 1 && REFRESHABLE && LAST_VISIABLE_IETM_INDEX + 1 >= Constants.PAGE_SIZE) {
                        page++;
                        initData();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LAST_VISIABLE_IETM_INDEX = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
        srl_refresh.setRefreshing(true);
        initData();
        return view;
    }

    private void initData() {
        try {
            JSONArray array = Data.getFindTabData(new MySqliteHelper(getContext()), page);
            if (adapter != null) {
                if (page == 1) {
                    if (array.length() == 0) {
                        view.findViewById(R.id.no_content).setVisibility(View.VISIBLE);
                    } else {
                        view.findViewById(R.id.no_content).setVisibility(View.GONE);
                    }
                    adapter.refresh(array);
                } else {
                    adapter.append(array);
                }
            } else {
                if (array.length() == 0) {
                    view.findViewById(R.id.no_content).setVisibility(View.VISIBLE);
                } else {
                    view.findViewById(R.id.no_content).setVisibility(View.GONE);
                }
                adapter = new Tab1Adapter(array, getContext());
                adapter.setOnItemChildViewClickListener(Tab1Fragment.this);
                rv_list.setAdapter(adapter);
            }
            if (srl_refresh.isRefreshing()) {
                srl_refresh.setRefreshing(false);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        page = 1;
        initData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        page = 1;
        initData();
    }

    @Override
    public void onClick(View v) {
//        Intent intent;
//        if (Utils.isLogin(getContext())) {
//            switch (v.getId()) {
//                case R.id.iv_action:
//                case R.id.tv_go:
//                    intent = new Intent(getContext(), addFindTab1Activity.class);
//                    startActivityForResult(intent, 0);
//                    break;
//            }
//        } else {
//            intent = new Intent(getContext(), LoginActivity.class);
//            startActivityForResult(intent, 0);
//        }
    }

    @Override
    public void onItemChildViewClickListener(int id, int position) {
//        try {
//            JSONObject object = adapter.getItem(position);
//            Intent intent;
//            SQLiteDatabase db1 = helper.getReadableDatabase();
//            ContentValues values = new ContentValues();
//            int num = 0;
//            if (Utils.isLogin(getContext())) {
//                switch (id) {
//                    case R.id.iv_ding:
//                        String topCount;
//                        String topId;
//                        if (object.has("topId") && object.getString("topId").contains("[" + Utils.getLoginId(getContext()) + "]")) {
//                            topCount = String.valueOf(object.has("topCount") ? object.getInt("topCount") - 1 : 0);
//                            topId = String.valueOf(object.has("topId") ? object.getString("topId").replace("[" + Utils.getLoginId(getContext()) + "]", "") : "[" + Utils.getLoginId(getContext()) + "]");
//                        } else {
//                            topCount = String.valueOf(object.has("topCount") ? object.getInt("topCount") + 1 : 1);
//                            topId = String.valueOf(object.has("topId") ? object.getString("topId") + "[" + Utils.getLoginId(getContext()) + "]" : "[" + Utils.getLoginId(getContext()) + "]");
//                        }
//                        values.put("topCount", topCount);
//                        values.put("topId", topId);
//                        num = db1.update("findTab", values, "fid = ?", new String[]{String.valueOf(object.getInt("fid"))});
//                        if (num < 1) {
//                            Utils.showToast(getContext(), "操作失败");
//                        } else {
//                            Utils.showToast(getContext(), "操作成功");
//                            adapter.top(position, topCount, topId);
//                        }
//                        db1.close();
//                        break;
//                    case R.id.iv_cai:
//                        String bottomCount;
//                        String bottomId;
//                        if (object.has("bottomId") && object.getString("bottomId").contains("[" + Utils.getLoginId(getContext()) + "]")) {
//                            bottomCount = String.valueOf(object.has("bottomCount") ? object.getInt("bottomCount") - 1 : 0);
//                            bottomId = String.valueOf(object.has("bottomId") ? object.getString("bottomId").replace("[" + Utils.getLoginId(getContext()) + "]", "") : "[" + Utils.getLoginId(getContext()) + "]");
//                        } else {
//                            bottomCount = String.valueOf(object.has("bottomCount") ? object.getInt("bottomCount") + 1 : 1);
//                            bottomId = String.valueOf(object.has("bottomId") ? object.getString("bottomId") + "[" + Utils.getLoginId(getContext()) + "]" : "[" + Utils.getLoginId(getContext()) + "]");
//                        }
//                        values.put("bottomCount", bottomCount);
//                        values.put("bottomId", bottomId);
//                        num = db1.update("findTab", values, "fid = ?", new String[]{String.valueOf(object.getInt("fid"))});
//                        if (num < 1) {
//                            Utils.showToast(getContext(), "操作失败");
//                        } else {
//                            Utils.showToast(getContext(), "操作成功");
//                            adapter.bottom(position, bottomCount, bottomId);
//                        }
//                        break;
//                    case R.id.iv_comment:
//                    case -1:
//                        intent = new Intent(getContext(), Tab1FindDetailActivity.class);
//                        intent.putExtra("data", object.toString());
//                        startActivityForResult(intent, 0);
//                        break;
//                }
//            } else {
//                intent = new Intent(getContext(), LoginActivity.class);
//                startActivityForResult(intent, 0);
//            }
//            db1.close();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    public void initLoginData() {
        adapter.refresh();
    }
}