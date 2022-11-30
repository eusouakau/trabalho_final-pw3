package com.example.pense_responda.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.pense_responda.R;
import com.example.pense_responda.activities.CadUserActivity;
import com.example.pense_responda.activities.LoginActivity;
import com.example.pense_responda.model.Resposta;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class EditarFragment extends Fragment {
    private TextInputEditText edtResposta;
    private Button btnEditar;
    private Button btnCancelar;
    private String key;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_editar, container, false);
        Bundle bundle = getArguments();
        String resposta = bundle.getString("RESPOSTA");
        edtResposta = root.findViewById(R.id.edtResposta);
        edtResposta.setText(resposta);
        btnCancelar = root.findViewById(R.id.btnCancelar);
        btnEditar = root.findViewById(R.id.btnEdit);
        key = bundle.getString("KEY");
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarItem();
            }
        });


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getView()).navigate(R.id.nav_listarFragment);
            }
        });
        return root;
    }
    private void editarItem() {
        new AlertDialog.Builder(getContext())
                .setTitle("Editando resposta")
                .setMessage("Tem certeza que deseja editar essa pessoa?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("respostas").child(key);
                        Resposta r = new Resposta(edtResposta.getText().toString());
                        reference.setValue(r);
                        Snackbar.make(getView(), "Resposta editada.", Snackbar.LENGTH_LONG).show();
                        Navigation.findNavController(getView()).navigate(R.id.action_nav_editarFragment_to_nav_listarFragment);
                    }
                }).setNegativeButton("Não", null).show();
    }

}
