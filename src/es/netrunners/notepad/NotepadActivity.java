package es.netrunners.notepad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NotepadActivity extends Activity {
	EditText FILENAME;
	EditText BODY;

	private String PATH = Environment.getExternalStorageDirectory()
			.getAbsolutePath() + "/NotePad/";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		FILENAME = (EditText) findViewById(R.id.key);
		BODY = (EditText) findViewById(R.id.value);
		
		
		
	}

	public void retrieveData(View v) {
		String filename = FILENAME.getText().toString();
		if (!(filename.length() > 0))
			filename = "filename.txt";
		String bodytext = new String();
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(PATH + filename);
			br = new BufferedReader(fr);

			while (br.ready()) {
				bodytext += "\n" + br.readLine();
			}
			br.close();
		} catch (IOException e) {
			Toast.makeText(getApplicationContext(), e.getMessage(),
					Toast.LENGTH_LONG).show();
		}

		BODY.setText(bodytext);
		
	}

	public void storeData(View v) {
		File dir = new File(PATH);
		dir.mkdirs();
		FileWriter fw;
		String filename = FILENAME.getText().toString();
		if (!(filename.length() > 0))
			filename = "filename.txt";
		try {
			fw = new FileWriter(PATH + filename);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter write = new PrintWriter(bw);
			write.println(BODY.getText());
			write.close();
		} catch (IOException e) {
			Toast.makeText(getApplicationContext(), e.getMessage(),
					Toast.LENGTH_LONG).show();
		}
		Toast.makeText(getApplicationContext(), "Save Successfull !!",
				Toast.LENGTH_LONG).show();

	}
}