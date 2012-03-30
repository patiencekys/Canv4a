package net.druil.Canv4a;

import java.util.LinkedList;

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
				for(int i=0; i<posts.size(); i++) { // we go through all the posts
					for(int j=0; j<posts.get(i).replies.size(); j++) { // then go through all the replies
						// and we get the reply, which is a new CanvasPost Object,
						// Construct it, and add it to this.posts
					}
				}
			}
			else {
				// then we must request the OP
			}
		}
		
	}
	
	public void sortThread(){
		// Sorts the thread by timestamp
	}

}
