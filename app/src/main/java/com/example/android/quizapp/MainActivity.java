package com.example.android.quizapp;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.app.AlertDialog;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mp;
    int scoreA = 0;
    int scoreB = 0;
    int scoreC = 0;
    int scoreD = 0;
    int scoreE = 0;
    int scoreK = 0;
    int scoreName = 0;
    int scoreAge = 0;
    int scoreCute = 0;
    int totalScore;
    CheckBox chAge, chGender, chDelhi;
    EditText nameEntry;
    Button playA, playB, playC, playD, playE, playK;
    RadioGroup cuteness;
    RadioButton cute, totallyCute, wayTooCute;
    int currentUri;
    int answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chAge = (CheckBox) findViewById(R.id.i_am_2);
        chGender = (CheckBox) findViewById(R.id.i_am_female);
        chDelhi = (CheckBox) findViewById(R.id.i_live_in_delhi);
        nameEntry = (EditText) findViewById(R.id.name_input);
        playA = (Button) findViewById(R.id.playA);
        playB = (Button) findViewById(R.id.playB);
        playC = (Button) findViewById(R.id.playC);
        playD = (Button) findViewById(R.id.playD);
        playE = (Button) findViewById(R.id.playE);
        playK = (Button) findViewById(R.id.playK);
        cuteness = (RadioGroup) findViewById(R.id.radioCuteness);
        cute = (RadioButton) findViewById(R.id.radioCute);
        totallyCute = (RadioButton) findViewById(R.id.radioTotallyCute);
        wayTooCute = (RadioButton) findViewById(R.id.radioWayTooCute);

    }

    /**
     *
     * @param view this is called from all of the 6 "Play" buttons
     */
    public void assign(View view) {
        if (view == playA) {
            currentUri = R.raw.a;
        } else if (view == playB) {
            currentUri = R.raw.b;
        } else if (view == playC) {
            currentUri = R.raw.c;
        } else if (view == playD) {
            currentUri = R.raw.d;
        } else if (view == playE) {
            currentUri = R.raw.e;
        } else {
            currentUri = R.raw.k;
        }

        playFile(currentUri);
        popUp(currentUri);
    }

    /**
     * On question button push, the following will play a voice prompt
     * Thanks to this link for getting me going on this:  http://www.geeks.gallery/how-to-play-mp3-file-from-raw-folder-in-a-listview-android/
     * @param uri the uri is the mp3 file resource located in the raw folder - just a handy way to reference which question is being worked on.
     */
    public void playFile(int uri) {
        mp = new MediaPlayer();
        mp.reset(); // stops any current playing song
        mp = MediaPlayer.create(getApplicationContext(), uri); // creates new mediaplayer with song.
        mp.start(); // starting mediaplayer
    }

    /**
     * This method pops up what will ultimately be pictures for Maira to choose from.
     * Thanks to:  https://stackoverflow.com/questions/16389581/android-create-a-popup-that-has-multiple-selection-options#16389626
     * @param uri the uri is the mp3 file resource located in the raw folder - just a handy way to reference which question is being worked on.
     */
    public void popUp(int uri) {

        // First set the potential choices for the pop-up questions.
        CharSequence choicesA[] = new CharSequence[]{getString(R.string.cow), getString(R.string.kangaroo), getString(R.string.elephant), getString(R.string.apple)};
        CharSequence choicesB[] = new CharSequence[]{getString(R.string.kangaroo), getString(R.string.banana), getString(R.string.elephant), getString(R.string.apple)};
        CharSequence choicesC[] = new CharSequence[]{getString(R.string.cow), getString(R.string.banana), getString(R.string.kangaroo), getString(R.string.apple)};
        CharSequence choicesD[] = new CharSequence[]{getString(R.string.banana), getString(R.string.apple), getString(R.string.dog), getString(R.string.kangaroo)};
        CharSequence choicesE[] = new CharSequence[]{getString(R.string.kangaroo), getString(R.string.dog), getString(R.string.elephant), getString(R.string.banana)};
        CharSequence choicesK[] = new CharSequence[]{getString(R.string.dog), getString(R.string.banana), getString(R.string.apple), getString(R.string.kangaroo)};
        CharSequence questions[] = new CharSequence[]{"", "", "", ""};

        switch (uri) { // then set the question variable for the correct question, and provide the code the answer key
            case R.raw.a:
                questions = choicesA.clone();
                answer = 3;
                break;
            case R.raw.b:
                questions = choicesB.clone();
                answer = 1;
                break;
            case R.raw.c:
                questions = choicesC.clone();
                answer = 0;
                break;
            case R.raw.d:
                questions = choicesD.clone();
                answer = 2;
                break;
            case R.raw.e:
                questions = choicesE.clone();
                answer = 2;
                break;
            case R.raw.k:
                questions = choicesK.clone();
                answer = 3;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.title);
        builder.setItems(questions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // the dialog box is built and displayed; the onClick listener will track the response for us.
                int userInput = 0;
                switch (which) {
                    case 0:
                        userInput = 0;
                        break;
                    case 1:
                        userInput = 1;
                        break;
                    case 2:
                        userInput = 2;
                        break;
                    case 3:
                        userInput = 3;
                }

                // Now check the question set via the current URI, then score if the answer matches the user input.
                if (currentUri == R.raw.a) {
                    if (userInput == answer) {
                        scoreA = 1;
                    }
                    playA.setBackgroundResource(R.drawable.checkgreensmall);
                } else if (currentUri == R.raw.b) {
                    if (userInput == answer) {
                        scoreB = 1;
                    }
                    playB.setBackgroundResource(R.drawable.checkgreensmall);
                } else if (currentUri == R.raw.c) {
                    if (userInput == answer) {
                        scoreC = 1;
                    }
                    playC.setBackgroundResource(R.drawable.checkgreensmall);
                } else if (currentUri == R.raw.d) {
                    if (userInput == answer) {
                        scoreD = 1;
                    }
                    playD.setBackgroundResource(R.drawable.checkgreensmall);
                } else if (currentUri == R.raw.e) {
                    if (userInput == answer) {
                        scoreE = 1;
                    }
                    playE.setBackgroundResource(R.drawable.checkgreensmall);
                } else if (currentUri == R.raw.k) {
                    if (userInput == answer) {
                        scoreK = 1;
                    }
                    playK.setBackgroundResource(R.drawable.checkgreensmall);
                }
            }
        });
        builder.show();
    }

    /**
     *
     * @param view This is executed on the "Score" button press; it will take in the final user inputs, then tally scores, and finally display the total in a Toast.
     */
    public void score(View view) {
        String name_in_text_field = nameEntry.getText().toString().toLowerCase();
        String mairah = getString(R.string.mairah);

        if (name_in_text_field.equals(mairah)) {
            scoreName = 1;
            Log.i("ScoreName", "is " + scoreName);
        }

        if (chAge.isChecked() && chDelhi.isChecked() && chGender.isChecked()) {
            scoreAge = 1;
            Log.i("ScoreAge", "is " + scoreAge);
        }

        if (wayTooCute.isChecked()) {
            scoreCute = 1;
            Log.i("ScoreCute", "is " + scoreCute);
        }

        totalScore = scoreA + scoreB + scoreC + scoreD + scoreE + scoreK + scoreName + scoreAge + scoreCute;
        Toast.makeText(getApplicationContext(), getString(R.string.you_did_it) + totalScore + getString(R.string.out_of_total), Toast.LENGTH_LONG).show();
        mp.release();

        // reset all!
        scoreA = 0;
        scoreB = 0;
        scoreC = 0;
        scoreD = 0;
        scoreE = 0;
        scoreK = 0;
        scoreName = 0;
        scoreAge = 0;
        scoreCute = 0;
        playA.setBackgroundResource(0);
        playB.setBackgroundResource(0);
        playC.setBackgroundResource(0);
        playD.setBackgroundResource(0);
        playE.setBackgroundResource(0);
        playK.setBackgroundResource(0);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.release();
    }

}
