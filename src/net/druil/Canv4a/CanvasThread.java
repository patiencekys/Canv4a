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
	
	public CanvasThread(CanvasPost p_orig) {
		posts = new LinkedList<CanvasPost>();
		posts.add(p_orig);
	}
	
	public void getEntireThread(){
		// This will complete the tread from the first given element
	}

}
