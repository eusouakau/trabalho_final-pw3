package com.example.pense_responda.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pense_responda.R;
import com.example.pense_responda.model.Resposta;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    List<Resposta> listaRespostas;
    Context context;
    public Adapter(Context context, List<Resposta> respostas) {
        this.context = context;
        this.listaRespostas = respostas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemList = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_card_resposta, viewGroup, false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, @SuppressLint("RecyclerView") int position) {
        Resposta r = listaRespostas.get(position);
        myViewHolder.resposta.setText(r.getResposta());
        myViewHolder.btnDelete.setOnClickListener(v -> removerItem(position));
        Bundle bundle = new Bundle();
        bundle.putString("RESPOSTA", listaRespostas.get(position).getResposta());
        bundle.putString("KEY", listaRespostas.get(position).getId());
        myViewHolder.btnEdit.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_editarFragment, bundle));
    }

    @Override
    public int getItemCount() {
        return listaRespostas.size();
    }

    public void removerItem(final int position) {
        new AlertDialog.Builder(context)
                .setTitle("Deletando resposta")
                .setMessage("Tem certeza que deseja deletar essa pessoa?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("respostas");
                    reference.child(listaRespostas.get(position).getId()).removeValue();
                    listaRespostas.remove(position);
                    notifyItemRemoved(position);

                }).setNegativeButton("Não", null).show();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView resposta;
        ImageButton btnDelete;
        ImageButton btnEdit;

        public MyViewHolder(View itemView){
            super(itemView);
            resposta = itemView.findViewById(R.id.textViewResp);
            btnDelete = itemView.findViewById(R.id.btnExcluir);
            btnEdit= itemView.findViewById(R.id.btnEditar);
        }
    }
}
