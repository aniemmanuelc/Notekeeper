package com.emmanuelani.notekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    private ArrayAdapter<NoteInfo> mAdapterNotes;

    @Override
    // starts activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // new note
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteListActivity.this, NoteActivity.class));
            }
        });
        
        initializeDisplayContent();
    }

    // update note list
    @Override
    protected void onResume() {
        super.onResume();
        mAdapterNotes.notifyDataSetChanged();
    }

    // displays note list
    private void initializeDisplayContent() {
        // make listNotes constant to use in abstract class
        final ListView listNotes = findViewById(R.id.list_notes);

        // get notes
        List<NoteInfo> notes = DataManager.getInstance().getNotes();

        // create note adapter
        mAdapterNotes = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        listNotes.setAdapter(mAdapterNotes);

        // anonymous class for onItemClick
        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // qualify anonymous class
                Intent intent = new Intent(NoteListActivity.this,
                        NoteActivity.class);

//                NoteInfo note = (NoteInfo) listNotes.getItemAtPosition(position);
                intent.putExtra(NoteActivity.NOTE_POSITION, position);

                // starts NoteActivity
                startActivity(intent);
            }
        });
    }

}
