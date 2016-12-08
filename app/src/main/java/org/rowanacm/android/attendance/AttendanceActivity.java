package org.rowanacm.android.attendance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.us.acm.R;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.rowanacm.android.nfc.NfcManager;

import java.io.IOException;

/**
 * Sign a member in to the meeting
 */
public class AttendanceActivity extends AppCompatActivity {

    private static final String TAG = "AttendanceActivity";

    private final static String defaultAttendanceFormUrl = "https://docs.google.com/forms/d/e/1FAIpQLScgL5EttHTj4HblJrkIoSRo560gseCQFoypADL7qEd5UdJlnA/viewform";

    // Handles reading and writing from NFC
    NfcManager nfcManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the NFC Manager
        createNfcManager();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button attendanceButton = (Button) findViewById(R.id.attendance_button);
        attendanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = getUser();

                String attendanceUrl = generateAttendanceUrl(user);

                openUrl(attendanceUrl);
            }
        });

        EditText nameEditText = (EditText) findViewById(R.id.name_exit_text);
        nameEditText.setText(readFromSharedPreferenceString("name"));
        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SharedPreferences prefs = AttendanceActivity.this.getSharedPreferences(
                        "org.rowanacm.android", Context.MODE_PRIVATE);

                prefs.edit().putString("name", charSequence.toString()).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });


        EditText emailEditText = (EditText) findViewById(R.id.email_edit_text);
        emailEditText.setText(readFromSharedPreferenceString("email"));
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SharedPreferences prefs = AttendanceActivity.this.getSharedPreferences(
                        "org.rowanacm.android", Context.MODE_PRIVATE);

                prefs.edit().putString("email", charSequence.toString()).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private User getUser() {
        String name = ((EditText) findViewById(R.id.name_exit_text)).getText().toString();
        String email = ((EditText) findViewById(R.id.email_edit_text)).getText().toString();

        User user = new User(name, email);

        return user;
    }



    @Override
    public void onNewIntent(Intent intent) {
        nfcManager.onActivityNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        nfcManager.onActivityResume();
    }

    @Override
    protected void onPause() {
        nfcManager.onActivityPause();
        super.onPause();
    }

    /**
     * Read the saved scan data
     * @throws IOException The file was unable to be read
     * @throws ClassNotFoundException The save file was unable to be converted to saveData
     */
    private void readScanData() throws IOException, ClassNotFoundException {
        //saveData = ReadWrite.readIn(getApplicationContext(), "Scans.ser");
    }

    /**
     * Save the scan data to a file
     * @throws IOException Unable to write to a file
     */
    private void writeScanData() throws IOException {
        //ReadWrite.writeOut(saveData, getApplicationContext());
    }

    /**
     * Create the NFC Manager and the NFC listener
     */
    private void createNfcManager() {
        nfcManager = new NfcManager(this);
        nfcManager.onActivityCreate();
        nfcManager.setOnTagReadListener(new NfcManager.TagReadListener() {
            @Override
            public void onTagRead(String tagId) {
                tagWasScanned(tagId);
            }
        });
    }

    /**
     * When a tag was scanned by the app
     * @param tagId The tag that was read
     */
    private void tagWasScanned(String tagId) {
        Toast.makeText(this, tagId, Toast.LENGTH_LONG).show();

        showCheckmarkToast();
        vibratePhone(200);
        //addScan(tagId);
        sendTagsToServer();
    }

    /**
     * Vibrate the phone
     * @param milliseconds The number of milliseconds to vibrate the phone
     */
    private void vibratePhone(int milliseconds) {
        ((Vibrator)getSystemService(VIBRATOR_SERVICE)).vibrate(milliseconds);
    }

    private void sendTagsToServer() {
        //if(SaveData.getServerPassword().equals("-1")) {
        //    Toast.makeText(getBaseContext(), "Please Insert Pass Key to Send Data", Toast.LENGTH_LONG).show();
       // }
       // SendToServer.send();
    }

    /**
     * Display a checkmark toast to notify the user of a successful scan
     */
    private void showCheckmarkToast(){
        /*
        // Inflate the toast layout
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_checkmark,
                (ViewGroup) findViewById(R.id.toast_layout_root));
        TextView text = (TextView) layout.findViewById(R.id.toast_text);
        text.setText(R.string.scan_successful);

        // Create the toast
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

        // Toast.makeText(this, "Scan Successful", Toast.LENGTH_SHORT).show();
        */
    }

    private String readFromSharedPreferenceString(String key) {
        SharedPreferences prefs = AttendanceActivity.this.getSharedPreferences(
                "org.rowanacm.android", Context.MODE_PRIVATE);

        return prefs.getString(key, ""); // the default value is an empty string
    }

    private String generateAttendanceUrl(User user) {
        if(!user.isValid())
            return defaultAttendanceFormUrl;

        String formattedName = user.getName().replace(" ", "+");

        return "https://docs.google.com/forms/d/e/1FAIpQLScgL5EttHTj4HblJrkIoSRo560gseCQFoypADL7qEd5UdJlnA/viewform?entry.319595206=" + formattedName + "&entry.1988864937=" + user.getRowanEmailAddress() + "&entry.1997712893&entry.717459855&entry.405789413&entry.856944836";
    }

    /**
     * Open a url in a Chrome Custom Tab
     * @param url the url to open
     */
    private void openUrl(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }

}