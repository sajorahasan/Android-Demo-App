package com.sajorahasan.demoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sajorahasan.demoapp.adapter.DemoAdapter;
import com.sajorahasan.demoapp.api.RestAdapter;
import com.sajorahasan.demoapp.api.RestApi;
import com.sajorahasan.demoapp.model.DemoItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ProgressBar progressBar;
    private DemoAdapter adapter;
    private RecyclerView rvDemo;
    private List<DemoItem> demoItemList;

    private RestApi api;
    private CompositeDisposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing views
        initViews();
    }

    private void initViews() {
        progressBar = findViewById(R.id.progressBar);
        rvDemo = findViewById(R.id.rvDemo);

        disposable = new CompositeDisposable();
        api = RestAdapter.getInstance();

        demoItemList = new ArrayList<>();

        rvDemo.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DemoAdapter(demoItemList);
        rvDemo.setAdapter(adapter);

        adapter.setOnItemClickListener((view, p) -> {
            DemoItem item = adapter.getItem(p);
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        });
    }

    private void getChipHistory() {
        progressBar.setVisibility(View.VISIBLE);
        disposable.add(api.getList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleError(Throwable t) {
        progressBar.setVisibility(View.GONE);
        Log.e(TAG, "handleError: " + t.getLocalizedMessage());
    }

    private void handleResponse(List<DemoItem> data) {
        progressBar.setVisibility(View.GONE);
        if (data != null) {
            demoItemList.clear();
            demoItemList.addAll(data);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getChipHistory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
