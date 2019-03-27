package comp3717.bcit.ca.ilovenwest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ScheduleActivities extends AppCompatActivity {
    private static String selectedUrl = "";
    private ArrayList<Event> eventList;
    private ArrayList<Event> eventSelectedList;
    private ListView lv;
    private String TAG = ScheduleActivities.class.getSimpleName();
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_activities);
        lv = findViewById(R.id.eventList);

        String category = getIntent().getExtras().getString("category");
        switch (category) {
            case "Culture":
                selectedUrl = "http://opendata.newwestcity.ca/downloads/cultural-events/EVENTS.json";
                break;
            case "History":
                selectedUrl = "http://opendata.newwestcity.ca/downloads/heritage-interest/HERITAGE_INTEREST.json";
                break;
            case "Art":
                selectedUrl = "http://opendata.newwestcity.ca/downloads/public-art/PUBLIC_ART.json";
                break;
            case "Music":
                selectedUrl = "http://opendata.newwestcity.ca/downloads/cultural-venues/VENUES.json";
                break;
            default:
                break;
        }

        new ScheduleActivities.GetContacts().execute();
    }

    public void onAddClick(View view) {
        Intent i = new Intent(this, EditPlan.class);
        i.putParcelableArrayListExtra("newEventsList", eventSelectedList);
        startActivity(i);
        finish();
    }

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
            String jsonStr = sh.makeServiceCall(selectedUrl);

            if (jsonStr != null) {
                try {
                    // Getting JSON Array node
                    JSONObject obj = new JSONObject(jsonStr);
                    JSONArray test = obj.getJSONArray("features");

                    eventList = new ArrayList<>();

                    for (int i = 0; i < test.length(); i++) {
                        JSONObject item = test.getJSONObject(i).getJSONObject("properties");
                        Event currentEvent = new Event();

                        // Map json properties to event properties
                        currentEvent.setName(item.getString("Name"));
                        currentEvent.setCategory(item.getString("catname"));
                        currentEvent.setSummary(item.getString("summary"));
                        currentEvent.setDescription(item.getString("Descriptn"));
                        currentEvent.setWebsite(item.getString("website"));
                        currentEvent.setAddress(item.getString("Address"));
                        currentEvent.setEmail(item.getString("email"));
                        currentEvent.setX(Double.parseDouble(item.getString("X")));
                        currentEvent.setY(Double.parseDouble(item.getString("Y")));

                        eventList.add(currentEvent);
                    }
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
            lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            eventSelectedList = new ArrayList<Event>();

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Event selectedEvent = eventList.get(position);
                    if (!eventSelectedList.contains(selectedEvent)) {
                        eventSelectedList.add(selectedEvent);
                        view.setBackgroundResource(R.color.colorListItem1);
                    } else {
                        eventSelectedList.remove(selectedEvent);
                        view.setBackgroundResource(R.color.offWhite);
                    }
                }
            });
        }
    }
}
