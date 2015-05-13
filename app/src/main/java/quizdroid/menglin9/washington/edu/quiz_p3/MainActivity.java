package quizdroid.menglin9.washington.edu.quiz_p3;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ActionBarActivity {
    public String[] topics = {"Math", "Physics", "Unix"};
    public int[] icons;
    private ListView quizList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Log.i("appa", "QuizApp has been loaded and run!!!!!!!!");

        // Calling async task to get json
        //new GetContacts().execute();

        // get your Application singleton
        QuizApp quizApp = (QuizApp) getApplication();

        if (quizApp.getSuccess()) {
            topics = quizApp.getAllTopics();
            icons = quizApp.getAllIcons();
        }
        //myApp.questions.get("blah blah idk"); // grab your repository from MyApp and get data from it

        quizList = (ListView) findViewById(R.id.listView);
       // ArrayAdapter<String> items = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, topics);
        //ArrayAdapter<String> items = new ArrayAdapter<String>(this, R.layout.mylist, R.id.Itemname, topics);
        //quizList.setAdapter(items);

        //set the custom adapter for loading the individual image
        quizList.setAdapter(new CustomAdapter(this, topics, icons));

        quizList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.i("in", "ink");
                Intent next = new Intent(MainActivity.this, Quiz_Content.class);
                next.putExtra("position", position);
                startActivity(next);

            }
        });
    }

    //Blow section is that if you want to fetch from the internet
    /**
     * Async task class to get json by making HTTP call
     * */

     /*jason http request
     private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray jsonArray = new JSONArray(jsonStr);
                    //JSONObject jsonObj = new JSONObject(jsonStr);

                    //fetch out each topic object
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Topic topicNew = new Topic();

                        JSONObject c = jsonArray.getJSONObject(i);

                        String title = c.getString(TAG_title);
                        String desc = c.getString(TAG_desc);
                        String sd = title;

                        topicNew.setTitle(title);
                        topicNew.setShortD(sd);
                        topicNew.setLongD(desc);


                        //Question node is Json Array
                        JSONArray question = c.getJSONArray(TAG_questions);
                        ArrayList<Quiz> quizList = new ArrayList<Quiz>();

                        //fetch out each quiz object
                        for (int j = 0; j < question.length(); j++) {
                            //implement each quiz object
                            ArrayList<String> answersList = new ArrayList<String>();
                            Quiz quizNew = new Quiz();

                            JSONObject eachQ = jsonArray.getJSONObject(j);
                            String text = eachQ.getString(TAG_text);
                            String answer = eachQ.getString(TAG_answer);
                            JSONArray answers = eachQ.getJSONArray(TAG_answers);

                            //deal with each quiz object
                            quizNew.setText(text);
                            quizNew.setCorrect(Integer.parseInt(answer));

                            //put into answers arraylist
                            int len = answers.length();
                            for (int k=0;k<len;k++){
                                answersList.add(jsonArray.get(k).toString());
                            }
                            //adding to quizArrayList
                            quizList.add(quizNew);

                        }
                        //adding to topicArrayList
                        topicList.add(topicNew);
                    }
                }
                    // Getting JSON Array node
                    //contacts = jsonObj.getJSONArray(TAG_CONTACTS);

                    // looping through All Contacts
                    /*
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString(TAG_ID);
                        String name = c.getString(TAG_NAME);
                        String email = c.getString(TAG_EMAIL);
                        String address = c.getString(TAG_ADDRESS);
                        String gender = c.getString(TAG_GENDER);

                        // Phone node is JSON Object
                        JSONObject phone = c.getJSONObject(TAG_PHONE);
                        String mobile = phone.getString(TAG_PHONE_MOBILE);
                        String home = phone.getString(TAG_PHONE_HOME);
                        String office = phone.getString(TAG_PHONE_OFFICE);

                        // tmp hashmap for single contact
                        HashMap<String, String> contact = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        contact.put(TAG_ID, id);
                        contact.put(TAG_NAME, name);
                        contact.put(TAG_EMAIL, email);
                        contact.put(TAG_PHONE_MOBILE, mobile);

                        // adding contact to contact list
                        contactList.add(contact);
                    }
                }

                 */

    // jason http request
    /*
                 catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss()
        */
            /**
             * Updating parsed JSON data into ListView
             * */

/*
             ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, contactList,
                    R.layout.list_item, new String[] { TAG_NAME, TAG_EMAIL,
                    TAG_PHONE_MOBILE }, new int[] { R.id.name,
                    R.id.email, R.id.mobile });

            setListAdapter(adapter);
            */
    /*
        }

    }
    */


    // reads InputStream of JSON file and returns the file in JSON String format
    public String readJSONFile(InputStream inputStream) throws IOException {

           int size = inputStream.available();
           byte[] buffer = new byte[size];
           inputStream.read(buffer);
           inputStream.close();

           return new String(buffer, "UTF-8");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
