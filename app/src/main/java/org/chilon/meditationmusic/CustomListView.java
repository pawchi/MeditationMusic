package org.chilon.meditationmusic;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

        //Set different colors for each ListView position
        if(position==0){
            viewHolder.gd.setColor(Color.parseColor("#c1d0c3"));
        }
        if(position==1){
            viewHolder.gd.setColor(Color.parseColor("#97d3d3"));
        }
        if(position==2){
            viewHolder.gd.setColor(Color.parseColor("#84b8b2"));
        }
        if(position==3){
            viewHolder.gd.setColor(Color.parseColor("#64a59e"));
        }
        if(position==4){
            viewHolder.gd.setColor(Color.parseColor("#3c6f8b"));
        }
        if(position==5){
            viewHolder.gd.setColor(Color.parseColor("#2f7399"));
        }
        if(position==6){
            viewHolder.gd.setColor(Color.parseColor("#2c4c7e"));
        }



        return r;
    }
    class ViewHolder{
        TextView tx1;
        TextView tx2;
        ImageView img;
        RelativeLayout rlt;
        GradientDrawable gd;

        ViewHolder(View v){
            tx1=(TextView) v.findViewById(R.id.musictype);
            //tx2=(TextView) v.findViewById(R.id.description);
            img=(ImageView) v.findViewById(R.id.imageView);
            rlt=(RelativeLayout) v.findViewById(R.id.relativlaoutid);
            gd=(GradientDrawable) rlt.getBackground();
        }
    }
}
