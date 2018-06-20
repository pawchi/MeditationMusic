package org.chilon.meditationmusic;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;

public class CustomListView extends ArrayAdapter<String> {

    private String[] musicType;
    //private String[] musicDescription;
    private Integer[] image;
    private Activity context;


    public CustomListView(Activity context,String[] musicType, Integer[] image) {
        super(context,R.layout.listview_layout,musicType);
        this.musicType = musicType;
        //this.musicDescription = musicDescription;
        this.image = image;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View r=convertView;
        ViewHolder viewHolder=null;
        if(r==null){


            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.listview_layout,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder) r.getTag();

        }
        viewHolder.img.setImageResource(image[position]);
        viewHolder.tx1.setText(musicType[position]);
        //viewHolder.tx2.setText(musicDescription[position]);

        return r;
    }
    class ViewHolder{
        TextView tx1;
        TextView tx2;
        ImageView img;

        ViewHolder(View v){
            tx1=(TextView) v.findViewById(R.id.musictype);
            //tx2=(TextView) v.findViewById(R.id.description);
            img=(ImageView) v.findViewById(R.id.imageView);
        }
    }
}
