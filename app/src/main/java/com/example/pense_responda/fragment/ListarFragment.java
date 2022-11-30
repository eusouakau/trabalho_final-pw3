package com.example.pense_responda.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pense_responda.R;
import com.example.pense_responda.adapter.Adapter;
import com.example.pense_responda.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ListarFragment extends Fragment {
    List<User> listaUsers = new ArrayList<>();
    Adapter adapter;
    RecyclerView recyclerView;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_listar, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        carregaPessoas();

        //configurar o gerenciador de layout
        // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager layoutManager3 = new GridLayoutManager(getContext(), 2);
        /*StaggeredGridLayoutManager layoutManager2 =
                new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);*/

        //adiciona um separador entre os elementos da lista
        //recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayout.VERTICAL));

        //definindo o layout do recycler
        recyclerView.setLayoutManager(layoutManager3);
        return root;
    }
    private void carregaPessoas(){
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        listaUsers = new ArrayList<>();

        //associar os eventos ao nó pessoas para poder buscar os dados
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            //é chamado sempre que consegue recuperar algum dado
            //DataSnapshot é o retorno do Firebase => resultado da consulta
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    //para buscar todos os nós filhos de pessoa
                    User user = ds.getValue(User.class);
                    user.setId(ds.getKey());
                    listaUsers.add(user);
                }
                //configurar o adapter - que formata que o layout de cada item do recycler
                adapter = new Adapter(root.getContext(), listaUsers);
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
                reference.removeEventListener(this);
            }
            @Override
            //chamado quando a requisição é cancelada
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}