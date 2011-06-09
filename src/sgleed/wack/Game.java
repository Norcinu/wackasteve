package sgleed.wack;

import android.app.Activity;
import android.view.View;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

// for difficulty just quicken new block time
// and add in extra blocks.
public class Game extends Activity {
	
	private static final String TAG = "Wack-a-Steve";
	
	public static final String KEY_DIFFICULTY = "sgleed.wack.difficulty";
	public static final int DIFFICULTY_EASY = 0;
	public static final int DIFFICULTY_MEDIUM = 1;
	public static final int DIFFICULTY_HARD = 2;
	
	private GameView gv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "Game->onCreate");
		
		//int difficulty = getIntent().getIntExtra(KEY_DIFFICULTY, DIFFICULTY_EASY);
		
		gv = new GameView(this);
		setContentView(gv);
		gv.requestFocus();
	}
}
