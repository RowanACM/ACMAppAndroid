package org.rowanacm.android.announcement;


import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.View;

import org.rowanacm.android.R;

//import butterknife.ButterKnife;

public class CreateAnnouncementDialog extends AlertDialog.Builder {

    //@BindView(R.id.author_edit_text) EditText authorEditText;
    //@BindView(R.id.subject_edit_text) EditText subjectEditText;
    //@BindView(R.id.message_edit_text) EditText messageEditText;
    //@BindView(R.id.committee_spinner) Spinner committeeSpinner;

    public CreateAnnouncementDialog(Activity activity) {
        super(activity);
        setTitle(R.string.create_announcement);
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.create_announcement_view, null);
        setView(dialogView);
        //ButterKnife.bind(this, dialogView);

        /*
        setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            // When Cancel is pressed, close the dialog and do nothing
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
        });

        setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String author = authorEditText.getText().toString();
                String subject = subjectEditText.getText().toString();
                String message = messageEditText.getText().toString();
                String committee = committeeSpinner.getSelectedItem().toString();

                // Date uses a timestamp with milliseconds. Dividing makes it match the system
                long timestamp = new Date().getTime() / 1000;
                String date = DateFormat.getDateTimeInstance().format(new Date());

                Announcement announcement = new Announcement(author, committee, date, subject, message, subject, timestamp, null, null);

                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                DatabaseReference newRef = database.child("announcements").push();
                newRef.setValue(announcement);
            }
        });

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            authorEditText.setText(currentUser.getDisplayName());
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity,
                R.array.committee_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        committeeSpinner.setAdapter(adapter);
        */
    }
}
