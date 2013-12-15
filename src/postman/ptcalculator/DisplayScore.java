package postman.ptcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayScore extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_score);
		// Show the Up button in the action bar.
		setupActionBar();
		Intent intent = getIntent();
		
		int age = intent.getIntExtra("age", 0);
		boolean isMale = intent.getBooleanExtra("isMale", true);
		int pushups = intent.getIntExtra("pushups", 0);
		int situps = intent.getIntExtra("situps", 0);
		double runTime = intent.getDoubleExtra("runTime", 00.00);
		
		System.out.println(isMale);
		
		CalcScore calculator = new CalcScore();
		Integer pushupsScore = Integer.valueOf(calculator.getPushupsScore(age, pushups, isMale));
		Integer situpsScore = Integer.valueOf(calculator.getSitupsScore(age, situps, isMale));
		Integer runScore = Integer.valueOf(calculator.getRunScore(age, runTime, isMale));
		Integer totalScore = pushupsScore + situpsScore + runScore;
		
	    TextView pushupsDisplay = (TextView)findViewById(R.id.pushupsScoreDisplay);
	    pushupsDisplay.setText(pushupsScore.toString());
	    
	    TextView situpsDisplay = (TextView)findViewById(R.id.situpsScoreDisplay);
	    situpsDisplay.setText(situpsScore.toString());
	    
	    TextView runDisplay = (TextView)findViewById(R.id.runScoreDisplay);
	    runDisplay.setText(runScore.toString());
	    
	    TextView totalDisplay = (TextView)findViewById(R.id.totalScoreDisplay);
	    totalDisplay.setText(totalScore.toString());
		
		
	}

	
	
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_score, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
