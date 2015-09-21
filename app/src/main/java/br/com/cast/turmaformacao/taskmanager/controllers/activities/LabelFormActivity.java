package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.controllers.adapters.ColorListAdapter;
import br.com.cast.turmaformacao.taskmanager.model.entities.Color;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;
import br.com.cast.turmaformacao.taskmanager.model.services.LabelBusinessService;
import br.com.cast.turmaformacao.taskmanager.util.FormHelper;

public class LabelFormActivity extends AppCompatActivity {

    private Label label;
    private EditText editTextName;
    private EditText editTextDescription;
    private View viewColor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_form);
        initLabel();
        bindViewColor();
        bindEditTextName();
        bindEditTextDescription();
    }

    private void initLabel() {
        if (label == null) {
            label = new Label();
        }
    }

    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextName.setText(label.getName());
    }

    private void bindEditTextDescription() {
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        editTextDescription.setText(label.getDescription());
    }

    private void bindViewColor() {
        viewColor = findViewById(R.id.viewColor);
        viewColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LabelFormActivity.this);
                final ColorListAdapter adapter = new ColorListAdapter(LabelFormActivity.this);
                dialogBuilder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateColor(adapter.getItem(which));
                    }
                });
                dialogBuilder.setTitle(R.string.lbl_select_color);
                dialogBuilder.setNeutralButton(R.string.lbl_cancel, null);
                dialogBuilder.show();
            }
        });
    }

    private void bindLabel() {
        label.setName(editTextName.getText().toString());
        label.setDescription(editTextDescription.getText().toString());
    }

    private void updateColor(Color color) {
        label.setColor(color);
        int hexColor = android.graphics.Color.parseColor(label.getColor().getHex());
        viewColor.setBackgroundColor(hexColor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_label_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_save) {
            onMenuSaveClick();
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuSaveClick() {
        if (!FormHelper.checkRequireFields(getString(R.string.msg_required), editTextName)) {
            bindLabel();
            LabelBusinessService.save(label);
            Label label = LabelBusinessService.getById(1l);
            Toast.makeText(LabelFormActivity.this, label.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
