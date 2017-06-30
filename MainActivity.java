package com.itplus.demosqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnThem, btnSua, btnXoa, btnXem;
    private DatabaseHelper db;
    private ListView listView;
    private ContactAdapter adapter;
    List<Contact> lstContact = new ArrayList<Contact>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setOnclickView();
        //Khoi tao DB
        db = new DatabaseHelper(this);
    }

    private void initView() {
        btnThem = (Button) findViewById(R.id.btnThem);
        btnSua = (Button) findViewById(R.id.btnSua);
        btnXoa = (Button) findViewById(R.id.btnXoa);
        btnXem = (Button) findViewById(R.id.btnXem);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new ContactAdapter(this,lstContact);
        listView.setAdapter(adapter);
    }

    private void setOnclickView() {
        btnThem.setOnClickListener(this);
        btnSua.setOnClickListener(this);
        btnXoa.setOnClickListener(this);
        btnXem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnThem:
                db.insertContact();
                break;
            case R.id.btnSua:
                db.updateContact();
                break;
            case R.id.btnXoa:
                db.deleteContact();
                break;
            case R.id.btnXem:
                List<Contact> lstContact = db.getContact();
                adapter.clearAll();
                adapter.addObject(lstContact);
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
