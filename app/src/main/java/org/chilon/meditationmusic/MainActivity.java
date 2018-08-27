package org.chilon.meditationmusic;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {
    ListView lst;
    String[] musicType = {"Birds","Water","Korg","Short Lansienka","Kalimba","Waves","Wind","Lake","Monk","Flower","Moon","Bird","Water","Wind"};
    //String[] musicType;
    Integer[] image = {R.drawable.lake_small_squere,R.drawable.monk_smal_squerel,R.drawable.flower_small_squere,R.drawable.moon_small,
                        R.drawable.lake_small_squere,R.drawable.monk_smal_squerel,R.drawable.flower_small_squere,R.drawable.lake_small_squere,
                        R.drawable.monk_smal_squerel,R.drawable.flower_small_squere,R.drawable.moon_small,
                        R.drawable.lake_small_squere,R.drawable.monk_smal_squerel,R.drawable.flower_small_squere};
    String [] itemColor = {"#d7e4f5","#b3cde0","#6497b1","#708090","#006666","#008080","#66b2b2","#b2d8d8","#b3cde0","#6497b1","#005b96","#03396c","#011f4b","#b3cde0"};
    String musicTypeItem;
    public static final String MAIN_RESPONSE = "main_response";
    TextView quotSentence;
    TextView quotSentenceAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //musicType=getResources().getStringArray(R.array.text_listView);

        lst = (ListView) findViewById(R.id.listview);
        CustomListView customListView=new CustomListView(this,musicType,image,itemColor);
        lst.setAdapter(customListView);

        quotSentence = (TextView) findViewById(R.id.wise_sentence);
        quotSentenceAuthor = (TextView) findViewById(R.id.sentence_author);

        Random random = new Random();
        int rnd = random.nextInt(40)+1;
        String resourceAsString = "quot_"+rnd;
        int resId = getResources().getIdentifier(resourceAsString,"string", getApplicationContext().getPackageName());
        quotSentence.setText(resId);

        if(rnd>=1&&rnd<=18){
            quotSentenceAuthor.setText("Anthony de Mello");
        }
        if(rnd>=19&&rnd<=28){
            quotSentenceAuthor.setText("Eckhart Tolle");
        }
        if(rnd==29){
            quotSentenceAuthor.setText("Phil Bosmans");
        }
        if(rnd==30){
            quotSentenceAuthor.setText("Howard Thurman");
        }
        if(rnd==31){
            quotSentenceAuthor.setText("Brian Tracy");
        }
        if(rnd==32){
            quotSentenceAuthor.setText("Rumi");
        }
        if(rnd>=33&&rnd<=37){
            quotSentenceAuthor.setText("Osho");
        }
        if(rnd==38){
            quotSentenceAuthor.setText("Somebody");
        }
        if(rnd==39){
            quotSentenceAuthor.setText("Buddha");
        }
        if(rnd==40){
            quotSentenceAuthor.setText("Moji");
        }



        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(getApplicationContext(), PlayMusicOne.class);
                    intent.putExtra(MAIN_RESPONSE,position);
                    setResult(RESULT_OK,intent);
                    startActivity(intent);
            }
        });

        //Settings on main View
        Button settings = (Button) findViewById(R.id.settingsbutton);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivityMainSetup.class);
                startActivity(intent);
            }
        });
    }

    public String getMusicTypeItem(int position) {
        musicTypeItem = musicType[position];
        return musicTypeItem;
    }
}
