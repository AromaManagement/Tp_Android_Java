package com.tp1.myapplication.ui.ciudades;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tp1.myapplication.R;
import com.tp1.myapplication.modelo.Ciudad;

import java.util.ArrayList;
import java.util.List;

public class CiudadesViewModel extends AndroidViewModel {

    private MutableLiveData<List<Ciudad>> mLista;

    public CiudadesViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Ciudad>> getMLista() {
        if (mLista == null) {
            mLista = new MutableLiveData<>();
            cargarCiudades();
        }
        return mLista;
    }

    public void cargarCiudades() {
        ArrayList<Ciudad> lista = new ArrayList<>();
        // Nota: Asegúrate de tener estas imágenes en drawable o cámbialas por R.drawable.ic_menu_camera etc.
        lista.add(new Ciudad("San Luis", 500000, 0, R.drawable.ic_menu_camera));
        lista.add(new Ciudad("Mendoza", 1000000, 260, R.drawable.ic_menu_gallery));
        lista.add(new Ciudad("Córdoba", 1500000, 400, R.drawable.ic_menu_slideshow));

        mLista.setValue(lista);
    }
}