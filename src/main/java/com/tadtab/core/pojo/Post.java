package com.tadtab.core.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tadtab.core.authentication.AutoUser;

@Entity
@Table(name="PostAgust0218")
public class Post {
	

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long postId;
	private String title;
	private String body;
	private Date date;
	
	/* no cascade type mentioned mean, there will not be change to the child element autoUser
		while the parent is Post is removed, updated, or pesrsisted. 
		
		*/
	@ManyToOne(fetch=FetchType.EAGER)
	/**
	 * @JoinColumn is a foreign key in child element table
	 */
	@JoinColumn(name = "postUserId")
	private AutoUser autoUser;
	
	/*
	 * Had cascade type Detatach. saw a behaviour where the post is deleted, the image stayed there with the join column being null
	 * 
	 * which mean detach makes the child element to loose its relationship from the parent when the parent is deleted.
	 */
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="postId")
	private List<Attachment> attachmentlist = new ArrayList<>(); 
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public AutoUser getAutoUser() {
		return autoUser;
	}
	public void setAutoUser(AutoUser autoUser) {
		this.autoUser = autoUser;
	}
	public List<Attachment> getAttachmentlist() {
		return attachmentlist;
	}
	public void setAttachmentlist(List<Attachment> attachmentlist) {
		this.attachmentlist = attachmentlist;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	
}
