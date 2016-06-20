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

        NotebookDbAdapter notebookDbAdapter = new NotebookDbAdapter(getActivity().getBaseContext());
        notebookDbAdapter.open();
        notes = notebookDbAdapter.getAllNotes();
        notebookDbAdapter.close();

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

        launchNoteDetailActivity(MainActivity.FragmentToLaunch.VIEW, position);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.long_press_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //give me the position of whatever note i long pressed on
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int rowPosition = info.position;

        //return to us id of whatever menu item we selected
        switch(item.getItemId()){
            //if we press edit
            case R.id.edit:
                launchNoteDetailActivity(MainActivity.FragmentToLaunch.EDIT, rowPosition);
                Log.d("menu clicks", "we press edit");
                return true;
        }

        return super.onContextItemSelected(item);
    }

    private void launchNoteDetailActivity(MainActivity.FragmentToLaunch ftl,int position){

        //grab the note information associated with whatever note item we clicked on
        Note note = (Note) getListAdapter().getItem(position);

        //create a new intent that launches our noteDetailActivity
        Intent intent = new Intent(getActivity(), NoteDetailActivity.class);

        //pass along the information of the note we clicked
        intent.putExtra(MainActivity.NOTE_TITLE_EXTRA, note.getTitle());
        intent.putExtra(MainActivity.NOTE_MESSAGE_EXTRA, note.getMessage());
        intent.putExtra(MainActivity.NOTE_CATEGORY_EXTRA, note.getCategory());
        intent.putExtra(MainActivity.NOTE_ID_EXTRA, note.getId());

        switch(ftl){
            case VIEW:
                intent.putExtra(MainActivity.NOTE_FRAGMENT_TO_LOAD_EXTRAS, MainActivity.FragmentToLaunch.VIEW);
                break;
            case EDIT:
                intent.putExtra(MainActivity.NOTE_FRAGMENT_TO_LOAD_EXTRAS, MainActivity.FragmentToLaunch.EDIT);
                break;
        }
        startActivity(intent);
    }
}
