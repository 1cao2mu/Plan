package com.project.cyy.plan.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.cyy.plan.R;
import com.project.cyy.plan.adapter.ThingTabAdapter;
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
public class ThingTabFragment extends Fragment implements OnItemChildViewClickListener {
    private RecyclerView rv_list;
    private View view;
    private SwipeRefreshLayout srl_refresh;
    private LinearLayoutManager linearLayoutManager;
    private ThingTabAdapter adapter;
    private boolean REFRESHABLE = true;
    private int page = 1, LAST_VISIABLE_IETM_INDEX, FIRST_VISIABLE_IETM_INDEX;
    private MySqliteHelper helper;
    private JSONArray array;
    private String tableName;

    public static ThingTabFragment newInstance(String tableName) {
        ThingTabFragment fragment = new ThingTabFragment();
        Bundle args = new Bundle();
        args.putString("key", tableName);
        fragment.setArguments(args);
        return fragment;
    }

    public ThingTabFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            return view;
        }
        tableName = getArguments().getString("key");
        view = inflater.inflate(R.layout.fragment_tab_thing, null);
        helper = new MySqliteHelper(getContext());
        srl_refresh = view.findViewById(R.id.srl_refresh);
        srl_refresh.setColorSchemeResources(R.color.colorAccent);
        srl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                REFRESHABLE = true;
                page = 1;
                initData();
            }
        });
        rv_list = view.findViewById(R.id.rv_list);
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
            array = Data.getThingListData(tableName,helper, page);
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
                adapter = new ThingTabAdapter(array, getContext());
                adapter.setOnItemChildViewClickListener(ThingTabFragment.this);
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
        Log.i("ThingTabFragment", "onActivityResult: " + resultCode);
        page = 1;
        initData();
    }

    public void onRefresh() {
//        page = 1;
//        initData();
    }

    @Override
    public void onItemChildViewClickListener(int id, final int position) {
        Intent intent;
        switch (id) {
            case -1:
                Log.i("aa", "onItemChildViewClickListener:to编辑界面 ");
                //to编辑界面
                break;
            case -2:
                new AlertDialog.Builder(getContext()).setIcon(R.mipmap.ic_launcher_1).setMessage("确认删除此条目?").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            array = Data.removeAndThingList(tableName,helper, array.getJSONObject(position));
                            adapter.refresh(array);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).show();
                break;
            case R.id.ll_finish:
                new AlertDialog.Builder(getContext()).setIcon(R.mipmap.ic_launcher_1).setMessage("确认修改状态?").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            array = Data.updateAndThingList(tableName,helper, array.getJSONObject(position));
                            adapter.refresh(array);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).show();
                break;
        }
    }

}