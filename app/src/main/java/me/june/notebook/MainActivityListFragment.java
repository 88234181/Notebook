package me.june.notebook;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityListFragment extends ListFragment {

    private ArrayList<Note> notes;
    private NoteAdapter noteAdapter;

    @Override
    public void onActivityCreated(Bundle saveInstanceSate){
        super.onActivityCreated(saveInstanceSate);

        /*
        String[] values = new String[] {"A", "B", "C", "D", "E", "F", "G", "H"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
        */

        notes = new ArrayList<>();
        notes.add(new Note("This is a new note title!", "This is a note body", Note.Category.PERSONAL, 1, 1));
        notes.add(new Note("This is another note title!", "I am think of what to put in here", Note.Category.FINANCE, 2, 1));
        notes.add(new Note("This is getting bored!", "I am going to do copy and paste", Note.Category.FINANCE, 3, 1));
        notes.add(new Note("For some reasons I have to put the category", "let me try other category", Note.Category.TECHNICAL, 4, 1));
        notes.add(new Note("Not sure i have enough notes", "let me generate some more", Note.Category.QUOTE, 5, 1));
        notes.add(new Note("i am getting tired of this", "even though I am pasting everything", Note.Category.QUOTE, 6, 1));
        notes.add(new Note("OK! Enough!", "this will be the last one", Note.Category.QUOTE, 7, 1));

        /**
         * need to add a layout profile for this 'android.R.layout.simple_list_item_1'
         */
        noteAdapter = new NoteAdapter(getActivity(), notes);

        setListAdapter(noteAdapter);

        //getListView().setDivider(ContextCompat.getDrawable(getActivity(), android.R.color.black));
        //getListView().setDividerHeight(1);

        registerForContextMenu(getListView());
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);

        launchNoteDetailActivity(position);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.long_press_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int rowPosition = info.position;

        //return to us id of whatever menu item we selected
        switch(item.getItemId()){
            //if we press edit
            case R.id.edit:
                launchNoteDetailActivity(rowPosition);
                Log.d("menu clicks", "we press edit");
                return true;
        }

        return super.onContextItemSelected(item);
    }

    private void launchNoteDetailActivity(int position){

        //grab the note information associated with whatever note item we clicked on
        Note note = (Note) getListAdapter().getItem(position);

        //create a new intent that launches our noteDetailActivity
        Intent intent = new Intent(getActivity(), NoteDetailActivity.class);

        //pass along the information of the note we clicked
        intent.putExtra(MainActivity.NOTE_TITLE_EXTRA, note.getTitle());
        intent.putExtra(MainActivity.NOTE_MESSAGE_EXTRA, note.getMessage());
        intent.putExtra(MainActivity.NOTE_CATEGORY_EXTRA, note.getCategory());
        intent.putExtra(MainActivity.NOTE_ID_EXTRA, note.getId());

        startActivity(intent);
    }
}
