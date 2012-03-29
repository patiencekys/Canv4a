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
	
	public void getEntireThread(){
		// This will complete the tread from the first given element
		if(posts.size()>=1){
			String op_id = posts.get(0).thread_op_id;
			if(posts.get(0).id == op_id) { //if the first element is really the OP
				//then get all in response
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
