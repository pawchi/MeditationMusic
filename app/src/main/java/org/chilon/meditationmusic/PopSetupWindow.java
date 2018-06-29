package org.chilon.meditationmusic;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class PopSetupWindow extends Activity {
    RadioGroup rgMinutes;
    RadioGroup rgMinTenth;
    RadioButton rbMunutes;
    RadioButton rbMinTenth;
    TextView timerText1;
    TextView timerText2;
    int timerTime;
    String rb_1_result;
    String rb_2_result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popsetup);
        rgMinutes=(RadioGroup) findViewById(R.id.radioGroup);
        rgMinTenth=(RadioGroup) findViewById(R.id.radioGroup2);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //(int)(width*.9) = 90%
        getWindow().setLayout((int)(width*.9),(int)(height*.7));

    }

    //RadioButton method
    public String rbClick1(View view){
        int radioButtonId_1 = rgMinutes.getCheckedRadioButtonId();
        rbMunutes = (RadioButton) findViewById(radioButtonId_1);
        rb_1_result = rbMunutes.getText().toString();

        timerText1 = (TextView) findViewById(R.id.popup_text_time);
        String sumOfRadioButtons = rb_1_result+" + "+ rb_2_result;
        timerText1.setText(sumOfRadioButtons);

        return rb_1_result;
    }

    public String rbClick2(View view){
        int radioButtonId_2 = rgMinTenth.getCheckedRadioButtonId();
        rbMinTenth = (RadioButton) findViewById(radioButtonId_2);
        rb_2_result = rbMinTenth.getText().toString();
        return rb_2_result;
    }

    public void sumOfRadioButtons(View view){

    }
}
