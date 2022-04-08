package com.example.thapovaninfo;


import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.thapovaninfo.adapter.Adapter_db;
import com.example.thapovaninfo.adapter.Adapter_redis;
import com.example.thapovaninfo.helper.ItemDividerDecoration;
import com.example.thapovaninfo.helper.RecyclerViewClickListener;
import com.example.thapovaninfo.helper.RecyclerViewTouchListener;
import com.example.thapovaninfo.model.Model_mdb;
import com.example.thapovaninfo.model.Model_mredis;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class Activity_Home extends AppCompatActivity {
    private RecyclerView mRecyclerView1,mRecyclerView2;
    private Adapter_db adapter1;
    private Adapter_redis adapter2;
    ArrayList<Model_mdb> list1 = new ArrayList<>();
    ArrayList<Model_mredis> list2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRecyclerView1 = findViewById(R.id.rc_db);
        mRecyclerView2 = findViewById(R.id.rc_redis);

        adapter1 = new Adapter_db(list1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView1.setLayoutManager(layoutManager);
        mRecyclerView1.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView1.addItemDecoration(new ItemDividerDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView1.setAdapter(adapter1);

        mRecyclerView1.addOnItemTouchListener(new RecyclerViewTouchListener(this, mRecyclerView1, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));

        adapter2 = new Adapter_redis(list2);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this);
        mRecyclerView2.setLayoutManager(layoutManager2);
        mRecyclerView2.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView2.addItemDecoration(new ItemDividerDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView2.setAdapter(adapter2);

        mRecyclerView2.addOnItemTouchListener(new RecyclerViewTouchListener(this, mRecyclerView2, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));

        GetHealthDetails();
    }


    private void GetHealthDetails() {
        try {
            App.getInstance().GetDetails(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Server Error
                        }
                    });
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        final String res = response.body().string();
                        if (list1.size() > 0) list1.clear();
                        if (list2.size() > 0) list2.clear();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    System.out.println("Response--"+res);
                                    JSONObject jsonObject = new JSONObject(res);
                                    JSONObject jb0 = jsonObject.getJSONObject("data");

                                    JSONArray jsonArray = jb0.getJSONArray("health");

                                    JSONObject js = jsonArray.getJSONObject(0);
                                    JSONArray jsonArray1 = js.getJSONArray("accessible");
                                    if (jsonArray1.length() > 0) {
                                        for (int i = 0; i < jsonArray1.length(); i++) {
                                            JSONObject js0 = jsonArray1.getJSONObject(i);
                                            String type = js0.getString("type");
                                            String success = js0.getString("success");

                                            list1.add(new Model_mdb(type,success));
                                            adapter1.notifyDataSetChanged();
                                        }

                                    }

                                    JSONObject js2 = jsonArray.getJSONObject(1);
                                    JSONArray jsonArray2 = js2.getJSONArray("accessible");
                                    if (jsonArray2.length() > 0) {
                                        for (int i = 0; i < jsonArray2.length(); i++) {
                                            JSONObject js3 = jsonArray2.getJSONObject(i);
                                            String type = js3.getString("type");
                                            String success = js3.getString("success");

                                            list2.add(new Model_mredis(type,success));
                                            adapter2.notifyDataSetChanged();
                                        }

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
