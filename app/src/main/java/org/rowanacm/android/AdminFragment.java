package org.rowanacm.android;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import javax.inject.Inject;


public class AdminFragment extends BaseFragment {

    @Inject AdminManager adminManager;

    private Button enableAttendanceButton;
    private Button disableAttendanceButton;

    public AdminFragment() {

    }

    public static AdminFragment newInstance() {
        AdminFragment fragment = new AdminFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ((AcmApplication)getActivity().getApplication()).getAcmComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        enableAttendanceButton = (Button) rootView.findViewById(R.id.enable_attendance_button);
        disableAttendanceButton = (Button) rootView.findViewById(R.id.disable_attendance_button);

        enableAttendanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableAttendance();
            }
        });

        disableAttendanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableAttendance();
            }
        });

        return rootView;
    }

    @Override
    public @LayoutRes int getLayout() {
        return R.layout.fragment_admin;
    }

    @Override
    public String getTitle() {
        return "ADMIN";
    }

    //@OnClick(R.id.enable_attendance_button)
    public void enableAttendance() {
        adminManager.setAttendanceEnabled(true);
    }

    //@OnClick(R.id.disable_attendance_button)
    public void disableAttendance() {
        adminManager.setAttendanceEnabled(false);
    }


}
