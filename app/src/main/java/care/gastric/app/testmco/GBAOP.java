package care.gastric.app.testmco;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class GBAOP extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView mImageView;
    private Bitmap mImageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gbaop);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }

    public void launchcam(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }

    public void upload_op_nsv(View view) {

        String url = "https://gastric.care/?dooppost=1";
        String post_data = "uname=GBA";
        Log.d("MAK","Clicked");



        CheckBox cb_almostcry = (CheckBox)findViewById(R.id.cb_almostcry);
        CheckBox cb_cry = (CheckBox)findViewById(R.id.cb_cry);
        CheckBox cb_startlovinglife = (CheckBox)findViewById(R.id.cb_startlovinglife);
        CheckBox cb_lovelife = (CheckBox)findViewById(R.id.cb_lovelife);
        CheckBox cb_shopnormalsize = (CheckBox)findViewById(R.id.cb_shopnormalsize);
        CheckBox cb_normlasizesoon = (CheckBox)findViewById(R.id.cb_normlasizesoon);
        CheckBox cb_workharder = (CheckBox)findViewById(R.id.cb_workharder);
        CheckBox cb_excitednewlife = (CheckBox)findViewById(R.id.cb_excitednewlife);

        String it_made_me = "";
        if(cb_almostcry.isChecked()){ it_made_me = it_made_me + "Almost cry,";}
        if(cb_cry.isChecked()){ it_made_me = it_made_me + "Cry,";}
        if(cb_startlovinglife.isChecked()){ it_made_me = it_made_me + "Start loving life,";}
        if(cb_lovelife.isChecked()){ it_made_me = it_made_me + "Love life,";}
        if(cb_shopnormalsize.isChecked()){ it_made_me = it_made_me + "See myself shopping normal size clothes,";}
        if(cb_normlasizesoon.isChecked()){ it_made_me = it_made_me + "Normal size clothes… SOON,";}
        if(cb_workharder.isChecked()){ it_made_me = it_made_me + "Work harder to achieve healthy weight,";}
        if(cb_excitednewlife.isChecked()){ it_made_me = it_made_me + "Excited about my new lifestyle,";}


        EditText op_nsv_title_e = (EditText) findViewById(R.id.op_nsv_title);
        EditText op_nsv_detail_e = (EditText) findViewById(R.id.op_nsv_detail);

        String op_nsv_title = op_nsv_title_e.getText().toString();
        String op_nsv_detail = op_nsv_detail_e.getText().toString();

        // Create data variable for sent values to server
        try{
            post_data = URLEncoder.encode("op_nsv_title", "UTF-8") + "=" + URLEncoder.encode(op_nsv_title, "UTF-8");
            post_data += "&" + URLEncoder.encode("op_nsv_detail", "UTF-8") + "=" + URLEncoder.encode(op_nsv_detail, "UTF-8");
            post_data += "&" + URLEncoder.encode("it_made_me", "UTF-8") + "=" + URLEncoder.encode(it_made_me, "UTF-8");
        }
        catch (Exception ex){
            Log.d("MAK", ex.toString());
        }


    /*
        CheckBox cb;
        ListView mainListView = getListView();
        for (int x = 0; x<mainListView.getChildCount();x++){
            cb = (CheckBox)mainListView.getChildAt(x).findViewById(R.id.myCheckBox);
            if(cb.isChecked()){
                //doSomething();
            }
        }

*/


        try {
            //Toast.makeText(getApplicationContext(), Utility.downloadDataFromUrl(url),Toast.LENGTH_LONG).show();
            //String resp = Utility.downloadDataFromUrl(url);
            new GetJSONTask().execute(url,post_data);
            //Log.d("MAK","Resp: " + resp);
        } catch (Exception e) {
            Log.d("MAK",e.toString());

        }
        /*
        // Show response on activity
        //content.setText( text  );
        Toast.makeText(getApplicationContext(), text,Toast.LENGTH_LONG).show();
        */
    }



    private class GetJSONTask extends AsyncTask<String, Void, String> {
        private ProgressDialog pd;

        // onPreExecute called before the doInBackgroud start for display
        // progress dialog.
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = ProgressDialog.show(GBAOP.this, "Posting", "Please wait ...", true,
                    false); // Create and show Progress dialog
        }

        @Override
        protected String doInBackground(String... urls) {

            try {
                return Utility.downloadDataFromUrl(urls[0],urls[1]);
            } catch (IOException e) {
                return "Unable to retrieve data. URL may be invalid.";
            }
        }

        // onPostExecute displays the results of the doInBackgroud and also we
        // can hide progress dialog.
        @Override
        protected void onPostExecute(String result) {
            pd.dismiss();
            //tvData.setText(result);
            Log.d("MAK","Resp: " + result);
            Toast.makeText(getApplicationContext(), result,Toast.LENGTH_LONG).show();
        }
    }




}
