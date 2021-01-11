package com.example.accountmanagement5system;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class WSHandling extends AsyncTask<String,Void, ArrayList> {
    String URL_DISP = "http://10.0.2.2/ws/display.php";
    String URL_CREATE = "http://10.0.2.2/ws/create.php";
    String URL_UPDATE = "http://10.0.2.2/ws/update.php";
    String URL_DELETE = "http://10.0.2.2/ws/delete.php";
    ProgressDialog pDialog;
    ListView lvAccounts;
    static final String DISPLAY = "display";
    static final String CREATE = "create";
    static final String UPDATE = "update";
    static final String DELETE = "delete";
    String acc_user;
    String acc_pwd;
    ServiceHandling sh = new ServiceHandling();
    Context context;
    ArrayList<Accounts> al;
    public WSHandling(Context context, ListView lvAccounts){
        this.context = context;
        this.lvAccounts = lvAccounts;
    }
    public WSHandling(Context context, String acc_user, String acc_pwd) {
        this.context = context;
        this.acc_user = acc_user;
        this.acc_pwd = acc_pwd;
    }
    @Override
    protected ArrayList doInBackground(String... params) {
        switch (params[0]) {
            case DISPLAY:
                break;
            case CREATE:
                break;
            case UPDATE:
                break;
            case DELETE:
                break;
        }
        return al;
    }
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Proccesing..");
        pDialog.setCancelable(false);
        pDialog.show();
    }
    @Override
    protected void onPostExecute(ArrayList ret) {
        super.onPostExecute(ret);
        if (pDialog.isShowing())
            pDialog.dismiss();
        loadData();
    }
    private void loadData() {
        if (al == null) {
            return;
        }
        List<String> data = new ArrayList<String>();
        for (int i = 0; i < al.size(); i++) {
            Accounts acc = al.get(i);
            data.add(acc.acc_user + ": " + acc.acc_pwd);
        }
        // Tạo adapter cho listivew
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, data);
        // Gắn adapter cho listview
        lvAccounts.setAdapter(adapter);
    }
    public ArrayList<Accounts> getData() {
        return al;
    }
}
