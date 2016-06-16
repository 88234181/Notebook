package me.june.notebook;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/14.
 */
public class NoteAdapter extends ArrayAdapter<Note>{

    public static class ViewHolder {
        TextView title;
        TextView note;
        ImageView noteIcon;
    }

    public NoteAdapter(Context context, ArrayList<Note> notes){
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //Get the date item for this position
        Note note = getItem(position);

        //create a view holder
        ViewHolder viewHolder;

        //Check if an existing view is being reused, otherwise inflate a new view from custom row layout
        if(convertView == null){

            //if we don't have a view that is being used, create one, and make sure you create a view holder along with it to save our view reference too
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);

            //Set our views to our view holder so that we no longer have to go back and user find view by id every time we have a new view
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.listItemNoteTitle);
            viewHolder.note = (TextView) convertView.findViewById(R.id.listItemNoteBody);
            viewHolder.noteIcon = (ImageView) convertView.findViewById(R.id.listItemNoteImg);

            //user setTag to remember our view holder which is holding our reference to our widgets
            convertView.setTag(viewHolder);
        }else{
            //we already have a view so just go to our viewHolder and grab the widgets from it
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Fill each referenced view with data associated with note it's reference\
        viewHolder.title.setText(note.getTitle());
        viewHolder.note.setText(note.getMessage());
        viewHolder.noteIcon.setImageResource(note.getAssociateDrawable());

        //now that we modified the view to display appropriate data, return it so it will be displayed
        return convertView;
    }
}
