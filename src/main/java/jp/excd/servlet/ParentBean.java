package jp.excd.servlet;

import java.util.ArrayList;
import java.util.List;

public class ParentBean extends CommentBean{
	
	List<CommentBean> replyCommment;
	
	ParentBean(){
		replyCommment = new ArrayList<CommentBean>();
	}
	
	public void setreplyComment(CommentBean input) {
		this.replyCommment.add(input);
	}
	
	public List<CommentBean> getreplyComment(){
		return this.replyCommment;
	}

}
