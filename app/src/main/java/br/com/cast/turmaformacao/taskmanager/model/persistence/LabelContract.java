package br.com.cast.turmaformacao.taskmanager.model.persistence;


import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Color;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;

public final class LabelContract {

    public static final String TABLE = "label";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String COLOR = "color";

    public static final String[] COLUMNS = {ID, NAME, DESCRIPTION, COLOR};

    private LabelContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(NAME + " TEXT NOT NULL, ");
        create.append(DESCRIPTION + " TEXT, ");
        create.append(COLOR + " TEXT NOT NULL ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Label label) {
        ContentValues values = new ContentValues();
        values.put(ID, label.getId());
        values.put(NAME, label.getName());
        values.put(DESCRIPTION, label.getDescription());
        values.put(COLOR, label.getColor().getHex());
        return values;
    }

    public static Label getLabel(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Label label = new Label();
            label.setId(cursor.getLong(cursor.getColumnIndex(ID)));
            label.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            label.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
            label.setColor(Color.getInstance(cursor.getString(cursor.getColumnIndex(COLOR))));
            return label;
        }
        return null;
    }

    public static List<Label> getLabels(Cursor cursor) {
        ArrayList<Label> values = new ArrayList<>();
        while (cursor.moveToNext()) {
            values.add(getLabel(cursor));
        }
        return values;
    }


}
