package com.chuangyou.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chuangyou.sample.base.BaseActivity;
import com.chuangyou.sample.bean.Note;
import com.chuangyou.sample.greendao.DaoSession;
import com.chuangyou.sample.greendao.NoteDao;

import java.util.Date;
import java.util.List;

public class MainActivity extends BaseActivity {

    private DaoSession mDaoSession;
    private NoteDao mNoteDao;
    private Button add,delete,deleteAll,update,retrieve;
    private TextView result;

    private EditText noteText;
    private RadioGroup noteType;
    private Button custom_insert;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mDaoSession = App.getDaoSession();
        mNoteDao = mDaoSession.getNoteDao();
        add = findView(R.id.add);

        delete = findView(R.id.delete);
        deleteAll = findView(R.id.deleteAll);
        update = findView(R.id.update);
        retrieve = findView(R.id.retrieve);
        result = findViewById(R.id.result);

        noteText = findView(R.id.noteText);
        noteType = findView(R.id.noteType);
        custom_insert = findView(R.id.custom_insert);

        /*Button button = findView(R.id.change_orientation);
        button.setOnClickListener(v -> {
            if (getRequestedOrientation()== ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            } else if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        });*/
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        add.setOnClickListener(this);
        delete.setOnClickListener(this);
        deleteAll.setOnClickListener(this);
        update.setOnClickListener(this);
        retrieve.setOnClickListener(this);

        custom_insert.setOnClickListener(this);
    }

    @Override
    protected void processClick(View v) {
        switch (v.getId()) {
            case R.id.add:{
                Note note = new Note();
                note.setText("笔记");
                note.setDate(new Date());
                note.setType(Note.TEXT);
                mNoteDao.insert(note);
                Log.d("EEEE", "Inserted new note, ID: " + note.getId());
                break;
            }
            case R.id.delete: {
                List<Note> notes = mNoteDao.queryBuilder().where(NoteDao.Properties.Text.eq("笔记")).build().list();
                mNoteDao.deleteInTx(notes);
                break;
            }
            case R.id.deleteAll: {
                mNoteDao.deleteAll();
                break;
            }
            case R.id.update: {
                List<Note> notes = mNoteDao.queryBuilder().where(NoteDao.Properties.Text.eq("笔记")).build().list();
                if (notes != null) {
                    for (Note note:notes){
                        note.setText("更新后");
                    }
                    mNoteDao.updateInTx(notes);
                }else{
                    Log.d("EEEE","note为空");
                }
                break;
            }
            case R.id.retrieve:
                List<Note> notes = mNoteDao.loadAll();
                if (notes.size()==0){
                    result.setText(getResources().getText(R.string.note_null));
                }else {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < notes.size(); i++) {
                        sb.append(notes.get(i).toString()).append("\n");
                    }
                    result.setText(sb.toString());
                }
                break;
                /*if (mBuilder == null) {

                }else{
                    List<Note> notes = mBuilder.list();
                    for (int i = 0; i < notes.size(); i++) {
                        sb.append(notes.get(i).toString()).append("\n");
                    }
                }*/
            case R.id.custom_insert: {
                Note note = new Note();
                note.setText(noteText.getText().toString());
                note.setDate(new Date());
                int id = noteType.getCheckedRadioButtonId();
                if (id==R.id.typeText)
                    note.setType(Note.TEXT);
                else if (id==R.id.typeWarn)
                    note.setType(Note.WARN);
                mNoteDao.insert(note);
                Log.d("EEEE", "Inserted new note, ID: " + note.getId());
                break;
            }
            default:
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("EEEE","onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("EEEE","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("EEEE","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("EEEE","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("EEEE","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("EEEE","onDestroy");
    }
}
