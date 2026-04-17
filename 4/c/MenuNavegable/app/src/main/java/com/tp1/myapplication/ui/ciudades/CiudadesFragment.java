package com.tp1.myapplication.ui.ciudades;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tp1.myapplication.R;
import com.tp1.myapplication.databinding.FragmentCiudadesBinding;
import com.tp1.myapplication.modelo.Ciudad;

import java.util.List;

public class CiudadesFragment extends Fragment {

    private CiudadesViewModel mViewModel;
    private FragmentCiudadesBinding binding;

    public static CiudadesFragment newInstance() {
        return new CiudadesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(CiudadesViewModel.class);
        binding = FragmentCiudadesBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        mViewModel.getMLista().observe(getViewLifecycleOwner(), new Observer<List<Ciudad>>() {
            @Override
            public void onChanged(List<Ciudad> ciudads) {
                CiudadAdapter adapter = new CiudadAdapter(ciudads, inflater);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
                binding.lista.setLayoutManager(gridLayoutManager);
                binding.lista.setAdapter(adapter);
            }
        });
        mViewModel.cargarCiudades();

        //return inflater.inflate(R.layout.fragment_ciudades, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CiudadesViewModel.class);
        // TODO: Use the ViewModel
    }

}