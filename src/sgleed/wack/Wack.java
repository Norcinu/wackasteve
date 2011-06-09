package sgleed.wack;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

public class Wack extends Activity implements View.OnClickListener {    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        View continueButton=findViewById(R.id.about_button);
        continueButton.setOnClickListener(this);
        View newGameButton = findViewById(R.id.new_button);
        newGameButton.setOnClickListener(this);
        View quitButton = findViewById(R.id.quit_button);
        quitButton.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.new_button:
			openNewGameDialog();
			break;
		case R.id.about_button:
			Intent i = new Intent(this, About.class);
			startActivity(i);
			break;
		case R.id.quit_button:
			finish();
			break;
		}
	}

	private static final String TAG = "Wack-a-Steve";
	private void openNewGameDialog() {
		new AlertDialog.Builder(this)
        .setTitle(R.string.new_game_title)
        .setItems(R.array.difficulty,
         new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface,int i) {
               startGame(i);
            }
         })
        .show();
	}

	private void startGame(int which) {
		Log.d(TAG, "Clicked on " + which);
		Intent intent = new Intent(this, Game.class);
		intent.putExtra(Game.KEY_DIFFICULTY, which);
		startActivity(intent);
	}
}