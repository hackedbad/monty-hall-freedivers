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
  private ImageButton selectedDoor;
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
    newGameButton = findViewById(R.id.new_game_button);
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
    bannerField.setText(R.string.banner_text);
    startGame();
  }

  void startGame() {
    winTally.setText(getString(R.string.win_tally_format, wins));
    loseTally.setText(getString(R.string.lose_tally_format, losses));
    selectionID = 0;
    newGameButton.setVisibility(View.INVISIBLE);
    okButton.setVisibility(View.VISIBLE);
    door1.setEnabled(true);
    door1.setVisibility(View.VISIBLE);
    //door1.setImageResource(R.drawable.reddoor);
    door2.setEnabled(true);
    door2.setVisibility(View.VISIBLE);
    //door2.setImageResource(R.drawable.reddoor);
    door3.setEnabled(true);
    door3.setVisibility(View.VISIBLE);
    //door3.setImageResource(R.drawable.reddoor);
    selectWinningSpace();
  }

  void selectWinningSpace() {
    activeDoors.add(door1);
    activeDoors.add(door2);
    activeDoors.add(door3);
    winningDoor = rng.nextInt(activeDoors.size());
    prizeDoor = activeDoors.remove(winningDoor);
    firstDoor();
  }

  void firstDoor() {
    messageField.setText(R.string.pick_a_door);
    okButton.setEnabled(false);
    okButton.setOnClickListener(v -> revealSecondDoor());
  }

  void revealSecondDoor() {
    if (prizeDoor.getId() == selectionID) {
      int goat = rng.nextInt(activeDoors.size());
      activeDoors.get(goat).setEnabled(false);
      activeDoors.get(goat).setVisibility(View.INVISIBLE);
      activeDoors.remove(goat);
      //activeDoors.get(goat).setImageResource(R.drawable.goatpic);
    } else if (activeDoors.get(0).getId() == selectionID) {
      int goat = 1;
      activeDoors.get(goat).setEnabled(false);
      activeDoors.get(goat).setVisibility(View.INVISIBLE);
      activeDoors.remove(1);
      //activeDoors.get(goat).setImageResource(R.drawable.goatpic);
    } else {
      int goat = 0;
      activeDoors.get(goat).setEnabled(false);
      activeDoors.get(goat).setVisibility(View.INVISIBLE);
      activeDoors.remove(0);
      //activeDoors.get(goat).setImageResource(R.drawable.goatpic);
    }
    secondDoor();
  }

  void secondDoor() {
    messageField.setText(R.string.choose_next_door);
    okButton.setOnClickListener(v -> revealWinningDoor());  }

  void revealWinningDoor() {
    messageField.setText(String.valueOf(prizeDoor.getId()));
    if (prizeDoor.getId() == selectionID) {
      messageField.setText(R.string.win_text);
      wins++;
    }
    else {
      messageField.setText(R.string.lost_text);
      losses++;
    }
    activeDoors.get(0).setVisibility(View.INVISIBLE);
    activeDoors.remove(0);
    okButton.setVisibility(View.INVISIBLE);
    newGameButton.setVisibility(View.VISIBLE);
    newGameButton.setOnClickListener(v -> startGame());
  }

  @Override
  protected void onStop() {
    super.onStop();
  }

}
