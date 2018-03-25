package com.example.dimasdwicahya.dimasdwicahya_1202152166_modul5;

/**
 * Created by asus a456 on 25/03/2018.
 */

public class Data {
    //deklarasi variable
    String todo, deskripsi, prioritas;

    //konstruktor
    public Data(String mTodo, String mDeskripsi, String mPrioritas) {
        this.todo = mTodo;
        this.deskripsi = mDeskripsi;
        this.prioritas = mPrioritas;
    }


    //Setter Getter

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getPrioritas() {
        return prioritas;
    }

    public void setPrioritas(String prioritas) {
        this.prioritas = prioritas;
    }
}
