package com.example.pense_responda.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.pense_responda.R;
import com.example.pense_responda.activities.CadUserActivity;
import com.example.pense_responda.activities.LoginActivity;
import com.example.pense_responda.model.Resposta;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResponderFragment extends Fragment {
    private Button btnEnviar;
    View root;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_responder, container, false);
        btnEnviar = root.findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                responder(view);
            }
        });
        return root;
    }
    public void responder(View view){
        String resposta = ((EditText)root.findViewById(R.id.edtResposta)).getText().toString();

        Resposta resp = new Resposta(resposta);
        DatabaseReference respostas = FirebaseDatabase.getInstance().getReference().child("respostas");

        respostas.push().setValue(resp).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Navigation.findNavController(view).navigate(R.id.action_nav_responderFragment_to_nav_listarFragment);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(view, "Erro ao enviar resposta!", Snackbar.LENGTH_LONG)
                                .setTextColor(Color.RED).show();
                    }
                });
    }

}
