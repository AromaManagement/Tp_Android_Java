package com.tp1.myapplication.ui.ciudades;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tp1.myapplication.modelo.Ciudad;

public class DetalleCiudadViewModel extends AndroidViewModel {

    private MutableLiveData<Ciudad> mCiudad;
    public DetalleCiudadViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Ciudad> getMCiudad(){
        if(mCiudad == null){
            mCiudad = new MutableLiveData<>();
        }
        return mCiudad;

    }

    public void recibeCiudad(Bundle bundle){
        Ciudad ciudad = (Ciudad) bundle.getSerializable("elegida");
        if(ciudad != null){
            mCiudad.setValue(ciudad);
        }

    }

}