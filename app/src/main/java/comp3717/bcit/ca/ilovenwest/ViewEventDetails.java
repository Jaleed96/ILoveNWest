package comp3717.bcit.ca.ilovenwest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
        if (curEvent.getDescription() == null || curEvent.getDescription().equals("")) {
            eventDescriptionTv.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Purus viverra accumsan in nisl nisi scelerisque eu. Eget nunc lobortis mattis aliquam faucibus purus in massa. Donec ac odio tempor orci dapibus ultrices. Proin fermentum leo vel orci porta non pulvinar. Pharetra convallis posuere morbi leo urna. Volutpat ac tincidunt vitae semper quis lectus nulla at. Massa enim nec dui nunc mattis enim. Aliquet lectus proin nibh nisl condimentum id venenatis a. Eu facilisis sed odio morbi quis.");
        } else {
            eventDescriptionTv.setText(curEvent.getDescription());
        }
        ImageView image = findViewById(R.id.categoryImage);
        switch (curEvent.getCategory()) {
            case "Events":
                image.setImageResource(R.drawable.firecracker);
                break;
            case "Heritage":
                image.setImageResource(R.drawable.parchment);
                break;
            case "Public Art/Monuments":
                image.setImageResource(R.drawable.palette);
                break;
            case "Venues":
                image.setImageResource(R.drawable.violin);
                break;
            default:
                break;
        }
    }

    public void onClickGotoMaps(View v) {
        Intent browse = new Intent(
                Intent.ACTION_VIEW,
                //Uri.parse("https://www.google.com/maps/search/?api=1&query=" + curEvent.getY() + "," + curEvent.getX())
                Uri.parse("https://www.google.com/maps/dir/Current+Location/"+curEvent.getY() + "," + curEvent.getX())
        );
        startActivity(browse);
    }
}
