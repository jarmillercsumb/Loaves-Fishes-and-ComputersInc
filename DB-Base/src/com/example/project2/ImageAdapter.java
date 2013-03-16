package com.example.project2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
//import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
	 
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
 
    // Keep all Images in array
    public String[] mThumbIds = {
    		"award", "bell",
            "cloud", "clock",
            "engine", "grid"
    };
    public String[] mTextInfo = {
    		"Remove Viruses", "Is Your Computer Slow? Like REALLY slow.",
    		"Guard Your Children", "Find Best Internet",
    		"Search Google", "View More Options"
    };
 
    // Constructor
    public ImageAdapter(Context c){
        mContext = c;
    }
 
    @Override
    public int getCount() {
        return mThumbIds.length;
    }
 
    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }
    
    public Object getString(int position) {
        return mTextInfo[position];
    }
 
    @Override
    public long getItemId(int position) {
        return 0;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(70, 70));
        return imageView;*/
        String[][] how2info = HowTos.getHow2info();
    	
        View v;
        if(convertView==null){
        	LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.icon_text, null);
            TextView tv = (TextView)v.findViewById(R.id.icon_text);
            tv.setText(how2info[position][2]);
            ImageView iv = (ImageView)v.findViewById(R.id.icon_image);
            iv.setImageResource(mContext.getResources().getIdentifier(how2info[position][1], "drawable", mContext.getPackageName()));

        }
        else
        {
            v = convertView;
        }
        return v;
    }
 
}
