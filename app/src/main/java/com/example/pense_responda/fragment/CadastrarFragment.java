package com.example.pense_responda.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.pense_responda.R;
import com.example.pense_responda.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastrarFragment extends Fragment {
    private Button btnCadastrar;
    View root;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_responder, container, false);
        btnCadastrar = root.findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadPessoa(view);
            }
        });
        return root;
    }
    public void cadPessoa(View view){
        String nome = ((EditText)root.findViewById(R.id.txtNome)).getText().toString();
        String email = ((EditText)root.findViewById(R.id.edtEmail)).getText().toString();

        User user = new User(nome, email);
        DatabaseReference pessoas = FirebaseDatabase.getInstance().getReference().child("users");
        //gera um identificador único para cada pessoa
        //salva a pessoa na base de dados
        pessoas.push().setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Navigation.findNavController(view).navigate(R.id.action_nav_cadastrarFragment_to_nav_listarFragment2);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(view, "Erro ao cadastrar pessoa!", Snackbar.LENGTH_LONG)
                                .setTextColor(Color.RED).show();
                    }
                });
    }
}
