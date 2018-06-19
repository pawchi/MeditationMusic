package org.chilon.meditationmusic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity {
    ListView lst;
    String[] musicType = {"Lake","Monk","Flower"};
    String[] musicDescription = {"This is Lake","This is Monk","this is Flower"};
    Integer[] image = {R.drawable.lake_small_squere,R.drawable.monk_smal_squerel,R.drawable.flower_small_squere};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lst = (ListView) findViewById(R.id.listview);
        CustomListView customListView=new CustomListView(this,musicType,musicDescription,image);
        lst.setAdapter(customListView);
        lst.setBackgroundResource(R.drawable.customshape);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    Intent intent = new Intent(view.getContext(), Play_music.class);
                    startActivityForResult(intent,0);
                }
                if(position==1){
                    Intent intent = new Intent(view.getContext(), Play_music.class);
                    startActivityForResult(intent,0);
                }
            }
        });
    }
}
