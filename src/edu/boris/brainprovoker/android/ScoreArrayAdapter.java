package edu.boris.brainprovoker.android;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ScoreArrayAdapter extends ArrayAdapter<igralec> { 
	LayoutInflater mInflater;
	public ScoreArrayAdapter(Context context, int textViewResourceId, List<igralec> objects) {
		super(context, textViewResourceId,objects);
	    mInflater = LayoutInflater.from(context);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		igralec tmp = getItem(position);
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.score_layout, null);
			holder = new ViewHolder();
			holder.one = (TextView) convertView.findViewById(R.id.tvOne); 
			holder.two = (TextView) convertView.findViewById(R.id.tvTwo); 
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.one.setText(""+tmp.ime); 
		holder.two.setText(""+tmp.score); 
		return convertView;
	}
	static class ViewHolder {
		TextView one; 
		TextView two; 
	}
}
