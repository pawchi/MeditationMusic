package org.chilon.meditationmusic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;

import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Popsetup_gong extends Activity {
    int gongTime;
    RadioGroup gongRadioGroup;
    RadioButton radioButton;
    Button gongOkButton;
    Button gongCancelButton;
    TextView gongTimeText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popsetup_gong);
        this.setFinishOnTouchOutside(false);

        gongRadioGroup = (RadioGroup) findViewById(R.id.gong_radioGroup);
        gongOkButton = (Button) findViewById(R.id.button_gong_ok);
        gongCancelButton = (Button) findViewById(R.id.button_gong_cancel);



        //Set screen in %: (int)(width*.9) = 90%

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.7),(int)(height*.7));

        gongOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preparingDataToSend();
                finish();
            }
        });

        gongCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void rbGongOnClick(View view){
        int gongRadioGroupCheckedRadioButtonId = gongRadioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(gongRadioGroupCheckedRadioButtonId);

        String radioButtonResult = radioButton.getText().toString();

        String rb2_only_digits = radioButtonResult.replaceAll("\\D+", "");
        gongTime = Integer.parseInt(rb2_only_digits);
        gongTimeText = (TextView) findViewById(R.id.gong_text_time);
        gongTimeText.setText(gongTime+"");

    }

    public void preparingDataToSend(){
        Intent intent = new Intent();
        intent.putExtra("key",gongTime);
        setResult(RESULT_OK,intent);
    }
}
