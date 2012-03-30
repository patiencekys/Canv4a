package net.druil.Canv4a;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ImageViewActivity extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(createUI());
		// TODO: Get double tap
		// 		change image size, and move picture
	}
	
	public View createUI(){
		LinearLayout v = new LinearLayout(this);
		v.setOrientation(LinearLayout.VERTICAL);
		LinearLayout vh = new LinearLayout(this);
		vh.setOrientation(LinearLayout.HORIZONTAL);
		v.addView(vh);
		ImageView i = new ImageView(this);
		vh.addView(i);
		
		return v;
	}

}
