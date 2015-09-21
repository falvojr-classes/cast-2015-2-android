package br.com.cast.turmaformacao.taskmanager.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.cast.turmaformacao.taskmanager.model.entities.Label;

public final class LabelRepository {

    private LabelRepository() {
        super();
    }

    public static void save(Label label) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = LabelContract.getContentValues(label);
        if (label.getId() == null) {
            db.insert(LabelContract.TABLE, null, values);
        } else {
            String where = LabelContract.ID + " = ? ";
            String[] params = {label.getId().toString()};
            db.update(LabelContract.TABLE, values, where, params);
        }

        db.close();
        databaseHelper.close();
    }

    public static Label getById(long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = LabelContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};

        Cursor cursor = db.query(LabelContract.TABLE, LabelContract.COLUMNS, where, params, null, null, null);
        Label label = LabelContract.getLabel(cursor);

        db.close();
        databaseHelper.close();
        return label;
    }
}
