package org.chilon.meditationmusic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends Activity {
    ListView lst;
    String[] musicType = {"Lake","Monk","Flower"};
    Integer[] image = {R.drawable.lake_small_squere,R.drawable.monk_smal_squerel,R.drawable.flower_small_squere};
    String musicTypeItem;
    public static final String MAIN_RESPONSE = "main_response";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lst = (ListView) findViewById(R.id.listview);
        CustomListView customListView=new CustomListView(this,musicType,image);
        lst.setAdapter(customListView);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    Intent intent = new Intent(getApplicationContext(), PlayMusicOne.class);
                    intent.putExtra(MAIN_RESPONSE,0);
                    setResult(RESULT_OK,intent);
                    startActivity(intent);
                }
                if(position==1){
                    Intent intent = new Intent(getApplicationContext(), PlayMusicOne.class);
                    intent.putExtra(MAIN_RESPONSE,1);
                    setResult(RESULT_OK,intent);
                    startActivity(intent);
                }
                if(position==2){
                    Intent intent = new Intent(getApplicationContext(), PlayMusicOne.class);
                    intent.putExtra(MAIN_RESPONSE,2);
                    setResult(RESULT_OK,intent);
                    startActivity(intent);
                }
            }
        });

        //Settings on main View
        Button settings = (Button) findViewById(R.id.settingsbutton);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this,Popup_test.class));
            }
        });
    }

    public String getMusicTypeItem(int position) {
        musicTypeItem = musicType[position];
        return musicTypeItem;
    }
}
