package org.harukitty.chokin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class MyActivity extends ActionBarActivity {
    private Activity activity;
    private SharedPreferences prefGoal;
    private EditText editGoal;
    private AlertDialog goalDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // 目標を保存するための変数
        prefGoal = getSharedPreferences("activity", Context.MODE_PRIVATE);

        // Viewの名前をつける
        editGoal = (EditText) findViewById(R.id.activity_edit_goal);
        final EditText editSum = (EditText) findViewById(R.id.activity_edit_sum);

        editGoal.setFocusable(false);
        editSum.setFocusable(false);
        Button buttonSet = (Button) findViewById(R.id.activity_button_set);
        Button buttonChokin = (Button) findViewById(R.id.activity_button_chokin);

        // カスタムビューを設定



        // 目標を変数goalに代入する　もし何もなければ-1が入る
        int goal = prefGoal.getInt("goal", -1);
        // goalに値が入っていたらeditGoalにセットする
        if (goal != -1) {
            editGoal.setText("" + goal);
            editGoal.setFocusable(false);
        }

        activity = this;

        buttonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editGoal.setFocusable(true);
                editGoal.setFocusableInTouchMode(true);

                Log.d("buttonSet", "true");
                // アラートダイアログを生成
                showGoalDialog();

                Log.d("action_setting", "true");
            }
        });

        buttonChokin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    public void showGoalDialog(){

        if(goalDialog == null) {
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            final View layout = inflater.inflate(R.layout.dialog_set, (ViewGroup) findViewById(R.id.dialog));
            goalDialog = new AlertDialog.Builder(this)
                    .setTitle("目標設定")
                    .setView(layout)
                    .setPositiveButton("完了", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final EditText editDialogText = (EditText) layout.findViewById(R.id.dialog_edit_goal);
                            Integer goal = Integer.valueOf(editDialogText.getText().toString());

                            SharedPreferences.Editor editor = prefGoal.edit();
                            editor.putInt("goal", goal).apply();
                            editGoal.setText(goal.toString());

                        }
                    })
                    .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).create();
        }
            goalDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
