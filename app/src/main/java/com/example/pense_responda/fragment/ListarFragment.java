package com.example.pense_responda.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pense_responda.R;
import com.example.pense_responda.adapter.Adapter;
import com.example.pense_responda.model.Resposta;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ListarFragment extends Fragment {
    List<Resposta> listaRespostas = new ArrayList<>();
    Adapter adapter;

    RecyclerView recyclerView;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_listar, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        carregaRespostas();

        GridLayoutManager layoutManager3 = new GridLayoutManager(getContext(), 1);

        recyclerView.setLayoutManager(layoutManager3);
        return root;
    }
    private void carregaRespostas(){
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("respostas");
        listaRespostas = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    Resposta resposta = ds.getValue(Resposta.class);
                    resposta.setId(ds.getKey());
                    listaRespostas.add(resposta);
                }

                adapter = new Adapter(root.getContext(), listaRespostas);
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
                reference.removeEventListener(this);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}