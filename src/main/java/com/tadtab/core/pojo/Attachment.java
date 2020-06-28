package com.tadtab.core.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tadtab.core.authentication.AutoUser;

@Entity
public class Attachment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long attachmentId;
	
	private String attachmentName;
	
	private String attachmentURL;
	
	public String getAttachmentURL() {
		return attachmentURL;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public void setAttachmentURL(String attachmentURL) {
		this.attachmentURL = attachmentURL;
	}

	public long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(long attachmentId) {
		this.attachmentId = attachmentId;
	}


}
