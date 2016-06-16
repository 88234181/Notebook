package me.june.notebook;

/**
 * Created by Junjie Zhao on 2016/6/14.
 */
public class Note {
    private String title;
    private String message;
    private Category category;

    private long noteId;
    private long dateCreatedMilli;

    public enum Category{PERSONAL, TECHNICAL, QUOTE, FINANCE}

    public Note(String title, String message, Category category, long noteId, long dateCreatedMilli){
        this.title = title;
        this.message = message;
        this.noteId = noteId;
        this.category = category;
        this.dateCreatedMilli = dateCreatedMilli;
    }

    public String getTitle(){
        return title;
    }

    public Category getCategory(){
        return category;
    }

    public String getMessage(){
        return message;
    }

    public long getDate(){
        return dateCreatedMilli;
    }

    public long getId(){
        return noteId;
    }

    public String toString(){
        return "ID: " + noteId + " Title: " + title + " Message: " + message + " IconID: " + category.name() + " Date: " + dateCreatedMilli;
    }

    public int getAssociateDrawable(){
        return categoryToDrawable(category);
    }

    public static int categoryToDrawable(Category noteCategory){
        switch(noteCategory){
            case PERSONAL:
                return R.drawable.p;
            case TECHNICAL:
                return R.drawable.t;
            case QUOTE:
                return R.drawable.q;
            case FINANCE:
                return R.drawable.f;
        }

        return R.drawable.p;
    }
}
