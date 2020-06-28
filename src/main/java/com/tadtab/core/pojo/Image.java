package com.tadtab.core.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long imageId;
	
	private String imageURL;
	
	@ManyToOne
	@JoinColumn(name = "postId")
	private Post post;

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	
}
