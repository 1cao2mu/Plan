package com.project.cyy.plan.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.project.cyy.plan.R;
import com.project.cyy.plan.db.MySqliteHelper;
import com.project.cyy.plan.tool.Data;

import org.json.JSONException;

/**
 * Created by cyy
 * on 18-5-31下午4:47
 */
public class AddThingActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_content;
    private Button bt_ok;
    private String tableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_thing);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setTitle("加一" + indexToName(getIntent().getIntExtra("index", 0)) + "事");
        et_content = findViewById(R.id.et_content);
        bt_ok = findViewById(R.id.bt_ok);

        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bt_ok.setClickable(!TextUtils.isEmpty(s));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        bt_ok.setOnClickListener(this);
        bt_ok.setClickable(false);
    }


    private String indexToName(int index) {
        String name = "";
        switch (index) {
            case 0:
                name = "学习";
                tableName="learnTab";
                break;
            case 1:
                name = "工作";
                tableName="workTab";
                break;
            case 2:
                name = "生活";
                tableName="lifeTab";
                break;
            case 3:
                name = "娱乐";
                tableName="enterTab";
                break;
        }
        return name;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View v) {
        try {
            Data.addThing(tableName,new MySqliteHelper(getApplicationContext()),et_content.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setResult(10002);
        finish();
    }
}

