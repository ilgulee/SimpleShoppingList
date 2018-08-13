package ilgulee.com.simpleshoppinglistanddialogfragmentmvp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShoppingListActivity extends AppCompatActivity {
    private static final String TAG = "ShoppingListActivity";

    List<String> mItemList = null;
    ArrayAdapter<String> mArrayAdapter = null;
    ListView mListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        mItemList = new ArrayList<>();
        mItemList.add("Apple");
        mItemList.add("1Orange");
        mItemList.addAll(Arrays.asList("Bread", "juice", "milk", "Toilet paper"));
        mListView = findViewById(R.id.listView);
        mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mItemList);
        mListView.setAdapter(mArrayAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = ((TextView) view).getText().toString();
                Toast.makeText(getApplicationContext(), "you clicekd " + item, Toast.LENGTH_SHORT).show();
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shopping_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_sorting) {
            Collections.sort(mItemList);
            mListView.setAdapter(mArrayAdapter);
            return true;
        }
        if (id == R.id.action_adding) {
            EditText input = new EditText(this);
            //Alert Dialog to get input item
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Add Shopping Item")
                    .setView(input)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mItemList.add(firstLetterToCapital(input.getText().toString()));
                            Collections.sort(mItemList);
                            mListView.setAdapter(mArrayAdapter);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private String firstLetterToCapital(String item){
        return item.substring(0,1).toUpperCase()+item.substring(1).toLowerCase();
    }
}
