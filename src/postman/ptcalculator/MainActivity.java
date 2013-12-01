package postman.ptcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void sendMessage(View view) {
		Intent intent = new Intent(this, DisplayScore.class);
		
		//Get the age
		EditText temp = (EditText)findViewById(R.id.age);
		int age = Integer.parseInt(temp.getText().toString());
		intent.putExtra("age", age);
		
		//Get gender
		temp = (EditText)findViewById(R.id.gender);
		String genderString = temp.getText().toString();
		boolean isMale = false;
		if (genderString.equalsIgnoreCase("male"))
				isMale = true;
		
		intent.putExtra("isMale", true);
		
		//Get pushups
		temp = (EditText)findViewById(R.id.pushups);
		int pushups = Integer.parseInt(temp.getText().toString());
		intent.putExtra("pushups", pushups);
		
		//Get situps
		temp = (EditText)findViewById(R.id.situps);
		int situps = Integer.parseInt(temp.getText().toString());
		intent.putExtra("situps", situps);
		
		//Get runtime
		temp = (EditText)findViewById(R.id.runTime);
		float runTime = Float.parseFloat(temp.getText().toString());
		intent.putExtra("runTime", runTime);
		
		//Start the display activity
		startActivity(intent);	
	}

}
