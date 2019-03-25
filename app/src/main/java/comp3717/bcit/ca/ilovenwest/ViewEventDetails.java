package comp3717.bcit.ca.ilovenwest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewEventDetails extends AppCompatActivity {

    Event curEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event_details);

        curEvent = (Event) getIntent().getExtras().get("eventObj");

        TextView eventNameTv = findViewById(R.id.eventName);
        eventNameTv.setText(curEvent.getName());

        TextView eventDescriptionTv = findViewById(R.id.eventDesc);
        eventDescriptionTv.setText(curEvent.getDescriptn());
    }

    protected void onClickGotoMaps(View v) {
        Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse( "https://www.google.com/maps/search/?api=1&query="+curEvent.getY()+","+curEvent.getX() ) );

        startActivity( browse );
    }
}
