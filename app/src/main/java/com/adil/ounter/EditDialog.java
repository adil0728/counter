package com.adil.ounter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class EditDialog extends AppCompatDialogFragment {

    public static EditDialog create(long counterId){
        Bundle args =  new Bundle();
        args.putLong(ARG_ID, counterId);
        EditDialog dialog = new EditDialog();
        dialog.setArguments(args);
        return dialog;
    }
        private  static  final String ARG_ID = "ARG_ID";
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.d_edit, null, false);
        EditText nameEt = (EditText) view.findViewById(R.id.d_edit_name);
        long counterId = getArguments().getLong(ARG_ID);
        Repo repo = Repo.getInstance(getContext());
        nameEt.setText(repo.getCounter(counterId).name);


        AlertDialog alertDialog = new AlertDialog.Builder(requireContext())
                .setTitle("Edit counter")
                .setPositiveButton("Save", (dialog, which) -> {
                    // TODO: 23.11.2022 filter illegal input
                    String name = nameEt.getText().toString();
                    repo.changeName(counterId, name);
                })
                .setView(view)
                .create();
        InputFilters.nameFilter(nameEt, alertDialog);
        return alertDialog;
    }


}
