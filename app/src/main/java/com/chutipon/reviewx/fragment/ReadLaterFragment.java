package com.chutipon.reviewx.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.activity.HomeActivity;
import com.chutipon.reviewx.adapter.MovieListAdapter;
import com.chutipon.reviewx.adapter.ReadLaterAdapter;
import com.chutipon.reviewx.manager.ReadLaterManager;

/**
 * Created by admin on 12/9/2017 AD.
 */

public class ReadLaterFragment extends Fragment implements View.OnClickListener, ReadLaterManager.onLoad {
    private static final String TAg = "ReadLaterFragment";
    private static ReadLaterFragment instance;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static ReadLaterFragment getInstance() {
        if (instance == null) {
            instance = new ReadLaterFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movielist, container, false);
        initInstance(rootView);
        return rootView;
    }

    private void initInstance(View rootView) {
        RecyclerView readLaterRecycler = rootView.findViewById(R.id.movielistRecycler);
        ReadLaterAdapter.getInstance().init(getActivity().getBaseContext(),this);
        readLaterRecycler.setAdapter(ReadLaterAdapter.getInstance());
        readLaterRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        FloatingActionButton btnWrite = rootView.findViewById(R.id.btn_write);
        btnWrite.setOnClickListener(this);

        swipeRefreshLayout = rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ReadLaterAdapter.getInstance().refresh(new ReadLaterManager.onLoad() {
                    @Override
                    public void onLoadComplete() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_write:
                HomeActivity.getInstance().triggerSearch();
                break;
            default:
                break;
        }
    }

    @Override
    public void onLoadComplete() {
        swipeRefreshLayout.setRefreshing(false);
    }
}
