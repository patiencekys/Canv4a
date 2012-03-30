package net.druil.Canv4a;

import java.util.LinkedList;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.util.Log;

/**
 * Represents a Canvas thread
 * @author Paul
 *
 */
public class CanvasThread {
	public LinkedList<CanvasPost> posts;
	
	public CanvasThread() {
		posts = new LinkedList<CanvasPost>();
	}

	/**
	 * @param a CanvasPost. OP preferably.
	 */
	public CanvasThread(CanvasPost p_orig) {
		posts = new LinkedList<CanvasPost>();
		posts.add(p_orig);
	}
	
	
	/**
	 * Gets the entire thread from one CanvasPost
	 */
	public void getEntireThread(){
		// This will complete the tread from the first given element
		if(posts.size()>=1){
			String op_id = posts.get(0).thread_op_id;
			if(posts.get(0).id == op_id) { //if the first element is really the OP
				//then get all in response
				Log.d("CanvasThread", "posts[0] is OP");
				for(int i=0; i<posts.size(); i++) { // we go through all the posts
					for(int j=0; j<posts.get(i).replies.size(); j++) { // then go through all the replies
						// and we get the reply, which is a new CanvasPost Object,
						// Construct it, and add it to this.posts
						String url = posts.get(i).replies.get(j).api_url;
						Log.v("API", "Post URL: "+url);
						try {
							Log.d("API", "Getting a post...");
							HttpResponse getResponse = Canv4aActivity.clt.execute(new HttpGet(url));
							String resp = EntityUtils.toString(getResponse.getEntity());
							JSONObject res = new JSONObject(resp);
							
							CanvasPost p = new CanvasPost(res);
							posts.add(p);
						}
						catch(Exception e){
							Log.e("API", "Exception occured: "+e.getMessage());
						}
					}
				}
			}
			else {
				// then we must request the OP
			}
		}
		else {
			Log.w("CanvasThread", "Can not get entire thread! No post given.");
		}
		
	}
	
	public void sortThread(){
		// Sorts the thread by timestamp
	}

}
