package com.example.dimasdwicahya.dimasdwicahya_1202152166_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewTodo extends AppCompatActivity {

    //Deklarasi Variabel
    private EditText ToDo, Description, Priority;
    private Db db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo);

        //set title menjadi add to do
        setTitle("Tambah ToDo Baru"); // untuk judul aplikasi yang ditambilkan pada actionbar

        //Referensi
        ToDo = (EditText) findViewById(R.id.etTodo);
        Description = (EditText) findViewById(R.id.etDeskripsi);
        Priority = (EditText) findViewById(R.id.etPrioritas);
        db = new Db(this);
    }

    //Method untuk tombol kembali
    @Override
    public void onBackPressed() {
        //intent menuju MainActivity
        Intent intent = new Intent(NewTodo.this, MainActivity.class);
        //pindah ke intent pertama
        startActivity(intent);
        //mengakhiri activity yg berjalan
        this.finish();
    }

    //Method untuk menambah todolist baru
    public void addTodo(View view) {
        //Jika data inputan tersisi
        if (db.inputData(new Data(ToDo.getText().toString(), Description.getText().toString(), Priority.getText().toString()))) {
            //maka munculkan informasi data todolist berhasil ditambahkan
            Toast.makeText(this, "To Do baru berhasil ditambahkan", Toast.LENGTH_SHORT).show();

            //pindah ke activity awal
            startActivity(new Intent(NewTodo.this, MainActivity.class));

            //mengakhiri activity yg berjalan
            this.finish();

        } else {
            //kalau tidak ada inputannya maka tampilkan pesan tidak boleh kosong
            Toast.makeText(this, "To Do harus diisi!", Toast.LENGTH_SHORT).show();

            //reset inputan jadi null atau dikosongkan kembali
            ToDo.setText(null);
            Description.setText(null);
            Priority.setText(null);
        }
    }
}
