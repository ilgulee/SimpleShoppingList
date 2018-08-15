package ilgulee.com.simpleshoppinglistanddialogfragmentmvp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.widget.EditText;

import java.util.Objects;

public class AddAppCompatDialogFragmentNoLayout extends AppCompatDialogFragment{

    private static final String TAG = "AddAppCompatDialogFragm";

    public interface AddItemListener{
        void addItemToListview(String input);
    }

    private AddItemListener mAddItemListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        EditText input = new EditText(getActivity());
        //Alert Dialog to get input item
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setTitle("Add Shopping Item?")
                .setView(input)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mAddItemListener.addItemToListview(input.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mAddItemListener=(AddItemListener) context;
        }catch (ClassCastException e){
            Log.d(TAG, "onAttach: "+e.getMessage());
        }
    }
}
