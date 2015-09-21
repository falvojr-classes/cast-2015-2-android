package br.com.cast.turmaformacao.taskmanager.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Task;

public final class TaskContract {

    public static final String TABLE = "task";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";

    public static final String[] COLUMNS = {ID, NAME, DESCRIPTION};

    private TaskContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(NAME + " TEXT NOT NULL, ");
        create.append(DESCRIPTION + " TEXT ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Task task) {
        ContentValues values = new ContentValues();
        values.put(TaskContract.ID, task.getId());
        values.put(TaskContract.NAME, task.getName());
        values.put(TaskContract.DESCRIPTION, task.getDescription());
        return values;
    }

    public static Task getTask(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Task task = new Task();
            task.setId(cursor.getLong(cursor.getColumnIndex(TaskContract.ID)));
            task.setName(cursor.getString(cursor.getColumnIndex(TaskContract.NAME)));
            task.setDescription(cursor.getString(cursor.getColumnIndex(TaskContract.DESCRIPTION)));
            return task;
        }
        return null;
    }

    public static List<Task> getTasks(Cursor cursor) {
        ArrayList<Task> tasks = new ArrayList<>();
        while (cursor.moveToNext()) {
            tasks.add(getTask(cursor));
        }
        return tasks;
    }

}
