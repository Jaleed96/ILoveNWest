package comp3717.bcit.ca.ilovenwest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ScheduleActivities extends Activity {
    private static String EVENTS_URL = "http://opendata.newwestcity.ca/downloads/cultural-events/EVENTS.json";
    private String category;
    private ArrayList<Event> eventList;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_activities);

        lv = findViewById(R.id.eventList);

        category = "event"; //getIntent().getExtras().getString("category");
        String url = "";
        switch(category){
            case "event":
                url = EVENTS_URL;
                break;
            default:
                break;
        }

        new ScheduleActivities.GetContacts().execute();
    }

    private String TAG = ScheduleActivities.class.getSimpleName();
    private ProgressDialog pDialog;

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Showing progress dialog
            pDialog = new ProgressDialog(ScheduleActivities.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(EVENTS_URL);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    //JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONObject obj = new JSONObject(jsonStr);
                    JSONArray test = obj.getJSONArray("features");

                    eventList = new ArrayList<>();

                    for(int i=0; i<test.length(); i++){
                        JSONObject item = test.getJSONObject(i).getJSONObject("properties");

                        Event currentEvent = new Event();
                        currentEvent.setDescriptn(item.getString("Descriptn"));
                        currentEvent.setAddress(item.getString("Address"));
                        currentEvent.setX(Double.parseDouble(item.getString("X")));
                        currentEvent.setY(Double.parseDouble(item.getString("Y")));
                        currentEvent.setName(item.getString("Name"));

                        eventList.add(currentEvent);
                    }

                    System.out.println(eventList.size());
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            ScheduleActivitiesAdapter adapter = new ScheduleActivitiesAdapter(ScheduleActivities.this, eventList);

            // Attach the adapter to a ListView
            lv.setAdapter(adapter);
        }
    }
}
