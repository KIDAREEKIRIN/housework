package com.personal.housework.CheckList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.personal.housework.R;
import com.personal.housework.Retrofit.GetDataService;
import com.thebluealliance.spectrum.SpectrumPalette;

public class Editor extends AppCompatActivity implements EditorView {
    // Create.
    EditText et_checkListTitle, et_checkListNote;
    ProgressDialog progressDialog;

    // Spectrum Palette.
    SpectrumPalette palette;

    EditorPresenter presenter;

    // Retrofit Http 통신.
//    GetDataService apiInterface;

    int color, number;
    String title, note;

    Menu actionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // 제목/메모.
        et_checkListTitle = findViewById(R.id.et_checkListTitle);
        et_checkListNote = findViewById(R.id.et_checkListNote);

        // 팔레트.
        palette = findViewById(R.id.palette);
        palette.setOnColorSelectedListener(
                clr -> color = clr // 클릭 -> 컬러 = 클릭.
        );

        // ProgressDialog.
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("잠시만 기다려주세요");

        presenter = new EditorPresenter(this);

        Intent intent = getIntent();
        number = intent.getIntExtra("number", 0);
        title = intent.getStringExtra("title");
        note = intent.getStringExtra("note");
        color = intent.getIntExtra("color", 0);

        setDataFormIntentExtra();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor, menu);

        actionMenu = menu;

        if(number != 0) {
            actionMenu.findItem(R.id.edit).setVisible(true);
            actionMenu.findItem(R.id.delete).setVisible(true);
            actionMenu.findItem(R.id.save).setVisible(false);
            actionMenu.findItem(R.id.update).setVisible(false);
        } else {
            actionMenu.findItem(R.id.edit).setVisible(false);
            actionMenu.findItem(R.id.delete).setVisible(false);
            actionMenu.findItem(R.id.save).setVisible(true);
            actionMenu.findItem(R.id.update).setVisible(false);

        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String title = et_checkListTitle.getText().toString().trim();
        String note = et_checkListNote.getText().toString().trim();
        int color = this.color;

        switch (item.getItemId()) {
            case R.id.save:
                // save
                if(title.isEmpty()) {
                    et_checkListTitle.setError("제목을 입력해주세요.");
                } else if(note.isEmpty()) {
                    et_checkListNote.setError("내용을 입력해주세요.");
                } else {
                    presenter.saveNote(title, note, color);
                }
                return true;
            case R.id.edit:

                editMode();
                actionMenu.findItem(R.id.edit).setVisible(false);
                actionMenu.findItem(R.id.delete).setVisible(false);
                actionMenu.findItem(R.id.save).setVisible(false);
                actionMenu.findItem(R.id.update).setVisible(true);

                return true;
            case R.id.update:
                // update

                if(title.isEmpty()) {
                    et_checkListTitle.setError("제목을 입력해주세요.");
                } else if(note.isEmpty()) {
                    et_checkListNote.setError("내용을 입력해주세요.");
                } else {
                    presenter.updateNote(number, title, note, color);
                }
                return true;
            case R.id.delete:

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("삭제");
                alertDialog.setMessage("정말인가요?");
                alertDialog.setNegativeButton("네",
                        (dialog, which) -> {
                            dialog.dismiss();
                            presenter.deleteNote(number);
                        });
                alertDialog.setPositiveButton("아니요",
                        (dialog, which) ->  dialog.dismiss());

                alertDialog.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.show();
    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(Editor.this,
                "성공",
                Toast.LENGTH_SHORT).show();
        finish(); // back to checkList activity.
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(Editor.this,
                "다시 시도해주세요",
                Toast.LENGTH_SHORT).show();
    }

    private void setDataFormIntentExtra() {

        if (number != 0) {
            et_checkListTitle.setText(title);
            et_checkListNote.setText(note);
            palette.setSelectedColor(color);

            getSupportActionBar().setTitle("가정일 메모");
            readMode();
        } else {
            // 색상 선택 안하면 기본설정
            palette.setSelectedColor(getResources().getColor(R.color.white));
            color = getResources().getColor(R.color.white);
            editMode();
        }

    }

    private void editMode() {
        et_checkListTitle.setFocusableInTouchMode(true);
        et_checkListNote.setFocusableInTouchMode(true);
        palette.setEnabled(true);
    }

    private void readMode() {

        et_checkListTitle.setFocusableInTouchMode(true);
        et_checkListNote.setFocusableInTouchMode(true);
        et_checkListTitle.setFocusable(false);
        et_checkListNote.setFocusable(false);
        palette.setEnabled(true);
    }
}
