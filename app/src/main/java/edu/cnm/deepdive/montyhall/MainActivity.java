package edu.cnm.deepdive.montyhall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

  private Random rng;
  private int wins = 0;
  private int losses;
  private ImageButton okButton;
  private ImageButton door1;
  private ImageButton door2;
  private ImageButton door3;
  private TextView bannerField;
  private TextView messageField;
  private TextView winTally;
  private TextView loseTally;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    rng = new Random();
    door1 = findViewById(R.id.door_1);
    door2 = findViewById(R.id.door_2);
    door3 = findViewById(R.id.door_3);
    okButton = findViewById(R.id.ok_button);
    bannerField = findViewById(R.id.banner);
    messageField = findViewById(R.id.message);
    winTally = findViewById(R.id.win_tally);
    loseTally = findViewById(R.id.lose_tally);
    /*View.OnClickListener doorListener = new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //((ImageButton) v).setImageResource();
      }
    };*/
    /*View.OnClickListener doorListener = (view) -> {
          ((ImageButton) view).setImageResource();
        };
    door1.setOnClickListener(doorListener);
    door2.setOnClickListener(doorListener);
    door3.setOnClickListener(doorListener);*/
  }

  @Override
  protected void onStart() {
    super.onStart();
    winTally.setText(getString(R.string.win_tally_format, wins));
    loseTally.setText(getString(R.string.lose_tally_format, losses));
    bannerField.setText(R.string.banner_text);
    messageField.setText(R.string.pick_a_door);
  }

  @Override
  protected void onStop() {
    super.onStop();
  }

}
