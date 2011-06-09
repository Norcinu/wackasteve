package sgleed.wack;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {
	private static final String TAG = "Wack-a-Steve";
	private float tileWidth;
	private float tileHeight;
	private int selectionX;
	private int selectionY;
	private Rect selectionRect = new Rect();
	private final Game game;
	private int x = 0;
	private int y = 0;
	private Bitmap moley;
	private Resources resource;
	private ArrayList bitmaps = new ArrayList();
	//private ShapeDrawable circle;
	private enum gameType { TIMED, FLTL }
	
	public GameView(Context context) {
		super(context);
		this.game = (Game)context;
		setFocusable(true);
		setFocusableInTouchMode(true);
		
		resource = context.getResources();
		moley = BitmapFactory.decodeResource(resource, R.drawable.mole);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		tileWidth = w / 9;
		tileHeight = h / 9;
		getRect(selectionX, selectionY, selectionRect);
		
		Log.d(TAG, "onSizeChanged: width " + tileWidth + " :height " + tileHeight);
		super.onSizeChanged(w, h, oldw, oldh);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		Paint background = new Paint();
		background.setColor(getResources().getColor(R.color.game_background));
		canvas.drawRect(0,0, getWidth(), getHeight(), background);;
		
		Paint divider = new Paint();
		divider.setColor(getResources().getColor(R.color.divider));
		
		for (int i = 0; i < 9; i++) {
			canvas.drawLine(0, i*tileHeight, getWidth(), i*tileHeight, divider);
			canvas.drawLine(0, i*tileHeight+1, getWidth(), i*tileHeight+1, divider);
			canvas.drawLine(i*tileWidth, 0, i*tileWidth, getHeight(), divider);
			canvas.drawLine(i*tileWidth+1, 0, i*tileWidth+1, getHeight(), divider);
		}
		
		canvas.drawBitmap(moley, selectionX, selectionY, null);
	}
	
	private void getRect(int x, int y, Rect rect) {
		rect.set((int)(x*tileWidth), (int)(y*tileHeight), (int)(x*tileWidth+tileWidth), 
				(int)(y*tileHeight+tileHeight));
	}
	
	private void select(int x, int y) {
		invalidate(selectionRect);
		selectionX = Math.min(Math.max(x, 0), 8);
		selectionY = Math.min(Math.max(y, 0), 8);
		getRect(selectionX, selectionY, selectionRect);
		invalidate(selectionRect);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() != MotionEvent.ACTION_DOWN)
			return super.onTouchEvent(event);
		
		select((int)(event.getX() / tileWidth), (int)(event.getY() / tileHeight));
		//circle.setBounds(x, y, x+32, y+32);
		Log.d("onTouchEvent [x: ", Float.toString(event.getX()) + " y: " + event.getY() + "]");
		//game.showKeypadOnError(selectionX, selectionY);
		
		return true;
	}	
	
	private boolean isValidTile(int x, int y) {
		//if (x == )
		return true;
	}
}
