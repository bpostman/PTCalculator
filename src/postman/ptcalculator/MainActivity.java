package postman.ptcalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private int age;
	private boolean isMale;
	private int pushups;
	private int situps;
	private Double runTime;
	private boolean [] errors;
	
	public MainActivity() {
		errors = new boolean[4];
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final TextView age = (TextView)findViewById(R.id.age);
	    age.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
				if (age.getText().toString().length() < 2) {
					age.setError("Enter a real age");
					errors[0] = true;
				}
				else if (Integer.parseInt(age.getText().toString()) < 17) {
					age.setError("Gotta be at least 17");
					errors[0] = true;
				}
				else
					errors[0] = false;
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    }); 
	    
	    final TextView pushups = (TextView)findViewById(R.id.pushups);
	    pushups.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
				if (pushups.getText().toString().length() < 1) {
					pushups.setError("Didn't do any?");
					errors[1] = true;
				}
				else if (Integer.parseInt(pushups.getText().toString()) < 1) {
					pushups.setError("Did zero?");
					errors[1] = true;
				}
				else
					errors[1] = false;
					
				
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    });
	    
	    final TextView situps = (TextView)findViewById(R.id.situps);
	    situps.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
				if (situps.getText().toString().length() < 1) {
					situps.setError("Didn't do any?");
					errors[2] = true;
				}
				else if (Integer.parseInt(situps.getText().toString()) < 1) {
					situps.setError("Did zero?");
					errors[2] = true;
				}
				else
					errors[2] = false;
				
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    });
	    
	    final TextView runTime1 = (TextView)findViewById(R.id.runTime1);
	    runTime1.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
				if (runTime1.getText().toString().length() < 1) {
					runTime1.setError("Skipped the run?");
					errors[3] = true;
				}
				else if (Integer.parseInt(runTime1.getText().toString()) < 1) {
					runTime1.setError("Zero minute run?");
					errors[3] = true;
				}
				else
					errors[3] = false;
				
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    });
		
	}

		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//Called when button pressed
	public void submit(View view) {
		Intent intent = new Intent(this, DisplayScore.class);
		boolean error = false;
		for (boolean entry : errors)
			if (entry)
				error = true;
		
		if (error) {
			Context context = getApplicationContext();
			CharSequence text = "Fix those errors first";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			
		}
		
		else {		
			//Get the age
			EditText temp = (EditText)findViewById(R.id.age);
			age = Integer.parseInt(temp.getText().toString());
			intent.putExtra("age", age);

			
			//Get gender
			Spinner spinner = (Spinner)findViewById(R.id.gender_select);
			String genderText = spinner.getSelectedItem().toString();
			isMale = true;

			if (genderText.compareTo("Female") == 0)
				isMale = false;
			intent.putExtra("isMale", isMale);

			
			//Get pushups
			temp = (EditText)findViewById(R.id.pushups);
			pushups = Integer.parseInt(temp.getText().toString());
			intent.putExtra("pushups", pushups);

			
			//Get situps
			temp = (EditText)findViewById(R.id.situps);
			situps = Integer.parseInt(temp.getText().toString());
			intent.putExtra("situps", situps);

			
			//Get runtime
			temp = (EditText)findViewById(R.id.runTime1);
			int runMinutes = Integer.parseInt(temp.getText().toString());

			temp = (EditText)findViewById(R.id.runTime2);
			int runSeconds;
			if (temp.getText().toString().length() == 0)
				runSeconds = 0;
			else
				runSeconds = Integer.parseInt(temp.getText().toString());

			runTime = runMinutes + (runSeconds * .01);
			intent.putExtra("runTime", runTime);

			
			//Start the display activity
			startActivity(intent);
		}
	}

}
