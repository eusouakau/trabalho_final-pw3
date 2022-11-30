package com.example.pense_responda.fragment;

import android.content.DialogInterface;
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
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;


public class EditarFragment extends Fragment {
    private TextInputEditText txtNome;
    private TextInputEditText txtEmail;
    private Button btnEditar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_editar, container, false);
        Bundle bundle = getArguments();
        String nome = bundle.getString("NOME");
        txtNome = root.findViewById(R.id.txtNome);
        txtNome.setText(nome);
        txtEmail = root.findViewById(R.id.txtEnd);
        txtEmail.setText(bundle.getString("EMAIL"));
        btnEditar = root.findViewById(R.id.btnEdit);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarItem();
            }
        });
        return root;
    }
    private void editarItem() {
        new AlertDialog.Builder(getContext())
                .setTitle("Editando pessoa")
                .setMessage("Tem certeza que deseja editar essa pessoa?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Snackbar.make(getView(), "item editado!!!", Snackbar.LENGTH_LONG).show();
                        Navigation.findNavController(getView()).navigate(R.id.action_nav_editarFragment_to_nav_listarFragment);
                    }
                }).setNegativeButton("Não", null).show();
    }
}
