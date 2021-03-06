package me.june.notebook;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NoteDetailActivity extends AppCompatActivity {

    public static final String NEW_NOTE_EXTRA = "New Note";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        createAndAddFragment();
    }

    private void createAndAddFragment(){

        //brag intent and fragment to laucnh from our main activity list fragment
        Intent intent = getIntent();
        MainActivity.FragmentToLaunch fragmentToLaunch = (MainActivity.FragmentToLaunch) intent.getSerializableExtra(MainActivity.NOTE_FRAGMENT_TO_LOAD_EXTRAS);

        //grabbing our fragment manager and our fragment transaction so that we can add our edit or view fragment dynamically
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (fragmentToLaunch){
            case EDIT:
                //create and add note edit fragment to note detail activity if that is what we want to launch
                NoteEditFragment noteEditFragment = new NoteEditFragment();
                setTitle(R.string.edit_Fragment_Title);
                fragmentTransaction.add(R.id.note_container, noteEditFragment, "NOTE_EDIT_FRAGMENT");
                break;
            case VIEW:
                //create and add note view fragment to note detail activity if that is what we want to launch
                NoteViewFragment noteViewFragment = new NoteViewFragment();
                setTitle(R.string.view_Fragment_Title);
                fragmentTransaction.add(R.id.note_container, noteViewFragment, "NOTE_VIEW_FRAGMENT");
                break;
            case CREATE:
                //create note edit fragment to note detail activity if that is what we want to launch
                NoteEditFragment noteCreateFragment = new NoteEditFragment();
                setTitle(R.string.create_Fragment_Title);

                Bundle bundle = new Bundle();
                bundle.putBoolean(NEW_NOTE_EXTRA, true);
                noteCreateFragment.setArguments(bundle);

                fragmentTransaction.add(R.id.note_container, noteCreateFragment, "NOTE_CREATE_FRAGMENT");
                break;
        }

        //commit our changes so that everything works
        fragmentTransaction.commit();
    }
}
