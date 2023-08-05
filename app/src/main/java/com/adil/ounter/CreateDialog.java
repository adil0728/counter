package com.adil.ounter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class CreateDialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.d_create, null, false);
        EditText nameEt = (EditText) view.findViewById(R.id.d_create_name);
        AlertDialog alertDialog = new AlertDialog.Builder(requireContext())
                .setTitle("Create counter")
                .setPositiveButton("Create", (dialog, which) -> {
                    String name = nameEt.getText().toString();
                    Repo.getInstance(getContext()).addCounter(name);
                })
                .setView(view)
                .create();
        InputFilters.nameFilter(nameEt, alertDialog);
        return alertDialog;
    }
}
