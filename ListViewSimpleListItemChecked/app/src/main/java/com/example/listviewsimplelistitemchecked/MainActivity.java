package com.example.listviewsimplelistitemchecked;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "ListViewExample";

    private ListView listView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listView = (ListView)findViewById(R.id.listView);
        this.button = (Button)findViewById(R.id.button);

        // CHOICE_MODE_NONE: (Default)
        // (listView.setItemChecked(..) doest not work with CHOICE_MODE_NONE).
        // CHOICE_MODE_SINGLE:
        // CHOICE_MODE_MULTIPLE:
        // CHOICE_MODE_MULTIPLE_MODAL:
        this.listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemClick: " +position);
                CheckedTextView v = (CheckedTextView) view;
                boolean currentCheck = v.isChecked();
                UserAccount user = (UserAccount) listView.getItemAtPosition(position);
                user.setActive(!currentCheck);
            }
        });
        //

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printSelectedItems();
            }
        });

        this.initListViewData();
    }

    private void initListViewData()  {
        UserAccount tom = new UserAccount("Tom","admin");
        UserAccount jerry = new UserAccount("Jerry","user");
        UserAccount donald = new UserAccount("Donald","guest", false);

        UserAccount[] users = new UserAccount[]{tom,jerry, donald};

        // android.R.layout.simple_list_item_checked:
        // ListItem is very simple (Only one CheckedTextView).
        ArrayAdapter<UserAccount> arrayAdapter
                = new ArrayAdapter<UserAccount>(this, android.R.layout.simple_list_item_checked , users);

        this.listView.setAdapter(arrayAdapter);

        for(int i=0;i< users.length; i++ )  {
            this.listView.setItemChecked(i,users[i].isActive());
        }
    }

    // When user click "Print Selected Items".
    public void printSelectedItems()  {

        SparseBooleanArray sp = listView.getCheckedItemPositions();

        StringBuilder sb= new StringBuilder();

        for(int i=0;i<sp.size();i++){
            if(sp.valueAt(i)==true){
                UserAccount user= (UserAccount) listView.getItemAtPosition(i);
                // Or:
                // String s = ((CheckedTextView) listView.getChildAt(i)).getText().toString();
                //
                String s= user.getUserName();
                sb = sb.append(" "+s);
            }
        }
        Toast.makeText(this, "Selected items are: "+sb.toString(), Toast.LENGTH_LONG).show();
    }

}