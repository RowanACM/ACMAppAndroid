package org.rowanacm.android.annoucement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.us.acm.R;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import org.rowanacm.android.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnnouncementListFragment extends BaseFragment {

    private static final String LOG_TAG = AnnouncementListFragment.class.getSimpleName();

    private AnnouncementAdapter adapter;

    SearchView searchView;

    @BindView(R.id.announcement_recycler_view) RecyclerView recyclerView;

    public AnnouncementListFragment() {

    }

    public static AnnouncementListFragment newInstance() {
        return new AnnouncementListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_announcement_list, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        announcementsListener();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();
        inflater.inflate(R.menu.annoucement_search_menu, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        item.setVisible(true);

        searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });
    }

    @Override
    public String getTitle() {
        return "ANNOUNCEMENTS";
    }

    public ChildEventListener announcementsListener() {
        try {
            return FirebaseDatabase.getInstance().getReference().child("announcements").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Announcement announcement = dataSnapshot.getValue(Announcement.class);
                    addAnnouncement(announcement);
                }
                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {}
                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                @Override
                public void onCancelled(DatabaseError databaseError) {}
            });
        } catch (Exception e) {
            Log.d("Firebase job list Error", e.getMessage());
            return null;
        }
    }
    public void addAnnouncement(Announcement announcement) {
        adapter.addItem(announcement);
    }

    private void setupRecyclerView() {
        if (recyclerView != null) {
            adapter = new AnnouncementAdapter(new ArrayList<Announcement>(), AnnouncementListFragment.this, 10);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
