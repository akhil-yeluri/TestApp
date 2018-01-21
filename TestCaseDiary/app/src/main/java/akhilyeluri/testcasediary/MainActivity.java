package akhilyeluri.testcasediary;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    public static final MediaType FORM_DATA_TYPE
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    EditText name,email,phone,roll_number,branch,year,section,idea_title,idea_description;
    Button send;
    public static final String NAME="entry.1585832008";
    public static final String EMAIL="entry.304399361";
    public static final String PHONE="entry.903858721";
    public static final String ROLL_NUMBER="entry.1729685493";
    public static final String BRANCH="entry.534319138";
    public static final String YEAR="entry.11315948";
    public static final String SECTION="entry.969258304";
    public static final String IDEA_TITLE="entry.652481584";
    public static final String IDEA="entry.684732879";
    public static final String URL="https://docs.google.com/forms/d/e/1FAIpQLSc8bq-OzHrvJieDVAdBPp5eO4Hd2Oq8tPbSrby_7DmIWamA7w/formResponse";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.phone);
        roll_number=(EditText)findViewById(R.id.rollno);
        branch=(EditText)findViewById(R.id.branch);
        year=(EditText)findViewById(R.id.Year);
        section=(EditText)findViewById(R.id.section);
        idea_title=(EditText)findViewById(R.id.idea_title);
        idea_description=(EditText)findViewById(R.id.idea_description);
        send=(Button)findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postdata post =new postdata();
                post.execute(URL,name.getText().toString(),email.getText().toString(),phone.getText().toString(),roll_number.getText().toString(),branch.getText().toString(),year.getText().toString(),section.getText().toString(),idea_title.getText().toString(),idea_description.getText().toString());

            }
        });


    }
    private class postdata extends AsyncTask<String, Void, Boolean>{
        String name,email,phone,roll_no,branch,year,section,idea_title,idea_description;
        @Override
        protected Boolean doInBackground(String... strings) {
            Boolean result = true;
            this.name=strings[1];
            this.email=strings[2];
            this.phone=strings[3];
            this.roll_no=strings[4];
            this.branch=strings[5];
            this.year=strings[6];
            this.section=strings[7];
            this.idea_title=strings[8];
            this.idea_description=strings[9];
            String postBody="";
            try{
                postBody = NAME +"=" + URLEncoder.encode(name,"UTF-8") +
                        "&" + EMAIL+ "=" + URLEncoder.encode(email,"UTF-8") +
                        "&" + PHONE + "=" + URLEncoder.encode(phone,"UTF-8")
                        +
                        "&" + ROLL_NUMBER + "=" + URLEncoder.encode(roll_no,"UTF-8")+
                        "&" + BRANCH + "=" + URLEncoder.encode(branch,"UTF-8")+
                        "&" + YEAR + "=" + URLEncoder.encode(year,"UTF-8")+
                        "&" + SECTION + "=" + URLEncoder.encode(section,"UTF-8")+
                        "&" + IDEA_TITLE + "=" + URLEncoder.encode(idea_title,"UTF-8")+
                        "&" + IDEA + "=" + URLEncoder.encode(idea_description,"UTF-8");
            }
            catch (UnsupportedEncodingException ex){
                result=false;
            }
            try{
                //Create OkHttpClient for sending request
                OkHttpClient client = new OkHttpClient();
                //Create the request body with the help of Media Type
                RequestBody body = RequestBody.create(FORM_DATA_TYPE, postBody);
                Request request = new Request.Builder()
                        .url(URL)
                        .post(body)
                        .build();
                //Send the request
                Response response = client.newCall(request).execute();
            }catch (IOException exception){
                result=false;
            }

            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            Toast.makeText(MainActivity.this, "Your Idea Has Been Posted!!", Toast.LENGTH_SHORT).show();
        }
    }
}
