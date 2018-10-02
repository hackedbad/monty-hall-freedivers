package edu.cnm.deepdive.montyhall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

  private Random rng;
  private int wins;
  private int losses;
  private int winningDoor;
  private int selectionID ;
  private ImageButton okButton;
  private ImageButton newGameButton;
  private ImageButton door1;
  private ImageButton door2;
  private ImageButton door3;
  private List<ImageButton> activeDoors = new ArrayList<>();
  private ImageButton prizeDoor;
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
    OnClickListener doorListener = (view) -> {
         // ((ImageButton) view).setImageResource();
      okButton.setEnabled(true);
      selectionID = view.getId();
    };

    door1.setOnClickListener(doorListener);
    door2.setOnClickListener(doorListener);
    door3.setOnClickListener(doorListener);
  }

  @Override
  protected void onStart() {
    super.onStart();
    winTally.setText(getString(R.string.win_tally_format, wins));
    loseTally.setText(getString(R.string.lose_tally_format, losses));
    bannerField.setText(R.string.banner_text);
    selectWinningSpace();
  }

  void selectWinningSpace() {
    winningDoor = 0;//rng.nextInt(3);
    activeDoors.add(door1);
    activeDoors.add(door2);
    activeDoors.add(door3);
    prizeDoor = activeDoors.remove(winningDoor);
    firstDoor();
  }

  void firstDoor() {
    messageField.setText(R.string.pick_a_door);
    //OK_Button set inactive if no doors are selected
    okButton.setEnabled(false);
    //When user selects a door, OK_Button is active
    //When OK_Button is pressed, proceed to revealSecondDoor
    okButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        revealSecondDoor();
      }
    });
  }

  void revealSecondDoor() {
    messageField.setText("hi");
    if (prizeDoor.getId() == selectionID) {
      int goat = rng.nextInt(activeDoors.size());
      activeDoors.get(goat).setEnabled(false);
      activeDoors.get(goat).setImageResource(R.drawable.goatpic);
    } else if (activeDoors.get(0).getId() == selectionID) {
      int goat = 1;
      activeDoors.get(goat).setEnabled(false);
      activeDoors.get(goat).setImageResource(R.drawable.goatpic);
    } else {
      int goat = 0;
      activeDoors.get(goat).setEnabled(false);
      activeDoors.get(goat).setImageResource(R.drawable.goatpic);
    }
    secondDoor();
  }
//TODO FI X RAND SELCTION
  void secondDoor() {
    messageField.setText(R.string.choose_next_door);
    //OK_Button set inactive if no doors are selected
    //When user selects a door out of the remaining doors, OK_Button is active
    //When OK_Button is pressed, proceed to revealWinningDoor
  }

  void revealWinningDoor() {
    //if users door is winningDoor, reveal winningDoor and losingDoor and display winning toast
    // add 1 to wins
    //if users door is not winningDoor, reveal winningDoor and losingDoor and display losing toast
    // add 1 to losses
    //change OK button to new game button
    //if OK button is selected, go back to select winningSpace
  }

  @Override
  protected void onStop() {
    super.onStop();
  }

}
