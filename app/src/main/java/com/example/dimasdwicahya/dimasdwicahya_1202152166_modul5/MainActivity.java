package com.example.dimasdwicahya.dimasdwicahya_1202152166_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //deklarasi variabel
    private Db mDb;
    private RecyclerView mRecyclerView;
    private DataAdapter adapter;
    private ArrayList<Data> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("To Do List App - Dimas"); // untuk judul aplikasi yang ditambilkan pada actionbar


        //Reference
        mRecyclerView = findViewById(R.id.recyclerview); //untuk recyclerview
        mArrayList = new ArrayList<>(); //bikin araylist baru
        mDb = new Db(this); //bikin database baru
        mDb.readData(mArrayList);//panggil method readData()

        //inisiasi shared preference
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        //bikin adapter baru
        adapter = new DataAdapter(this, mArrayList, color);

        //settingan untuk perubahan ukuran
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //inisiasi adapter untuk recycler view
        mRecyclerView.setAdapter(adapter);


        swipeDelete(); //akses method swipeDelete yang akan dipakai ketika kita menggeser todolistnya
    }

    //method swipeDelete digunakanakn untuk menghapus todolist yang kita geser
    public void swipeDelete() {

        //bikin touch helper baru untuk recycler view
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            //Method ini digunaakna untuk ketika kita mengeser todolist
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Data current = adapter.getData(position);
                //jika sitodolist kita geser maka akan dihapus
                if (direction == ItemTouchHelper.LEFT) {
                    if (mDb.removeData(current.getTodo())) {
                        //menghapus data
                        adapter.deleteData(position);
                        //membuat informasi degan snackbar pemberitahuan bahwa todolist yang digeserberhasil terhapus
                        Snackbar.make(findViewById(R.id.Activity_Main_Layout), "Todo List berhasil dihapus", 500).show();
                    }
                }
            }
        };


        //inisiasi itemtouchhelper untuk recycler view
        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall);
        touchhelp.attachToRecyclerView(mRecyclerView);
    }

    //ketika menu pada activity di buat
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //method untuk item yang dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //inisiasi si id
        int id = item.getItemId();
        //apabila item yang dipilih adalah setting
        if (id == R.id.action_settings) {
            //membuat intent ke menu Settings
            Intent intent = new Intent(MainActivity.this, AppSettingActivity.class);
            //pindah ke activity lain
            startActivity(intent);
            //menghentikan aktivitas yg tadi dijalankan
            finish();
        }
        return true;
    }

    //method untuk tombol tambah todolist baru
    public void addList(View view) {
        //intent ke class add to do
        Intent intent = new Intent(MainActivity.this, NewTodo.class);
        //pindah ke activity lain
        startActivity(intent);
    }
}
