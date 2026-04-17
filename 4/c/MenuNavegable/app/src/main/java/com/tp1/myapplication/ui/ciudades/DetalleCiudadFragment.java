package com.tp1.myapplication.ui.ciudades;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tp1.myapplication.R;
import com.tp1.myapplication.databinding.FragmentCiudadesBinding;
import com.tp1.myapplication.databinding.FragmentDetalleCiudadBinding;
import com.tp1.myapplication.modelo.Ciudad;

public class DetalleCiudadFragment extends Fragment {

    private DetalleCiudadViewModel mViewModel;

    private FragmentDetalleCiudadBinding binding;


    public static DetalleCiudadFragment newInstance() {
        return new DetalleCiudadFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(DetalleCiudadViewModel.class);
        binding = FragmentDetalleCiudadBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        mViewModel.getMCiudad().observe(getViewLifecycleOwner(), new Observer<Ciudad>() {
            @Override
            public void onChanged(Ciudad ciudad) {
                binding.nombre.setText(ciudad.getNombre());
                binding.foto2.setImageResource(ciudad.getFoto());
            }
        });

        mViewModel.recibeCiudad(getArguments());

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetalleCiudadViewModel.class);
        // TODO: Use the ViewModel
    }

}