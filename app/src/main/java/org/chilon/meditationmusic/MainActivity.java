package org.chilon.meditationmusic;

import android.app.Activity;
import android.os.Bundle;
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
    }
}
