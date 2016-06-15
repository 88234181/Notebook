package me.june.notebook;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/14.
 */
public class NoteAdapter extends ArrayAdapter<Note>{
    public NoteAdapter(Context context, ArrayList<Note> notes){
        super(context, 0, notes);
    }
}
