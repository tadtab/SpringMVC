package com.tadtab.core;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.tadtab.core.pojo.Attachment;
import com.tadtab.core.pojo.Post;
import com.tadtab.core.service.AmazonClient;
import com.tadtab.dao.PostDao;

@Controller
@RequestMapping("/storage")
public class AWSBucketController {
	
	static final Logger logger = Logger.getLogger("AWSBucketController"); 
	
	private AmazonClient amazonClient;
	
	@Autowired
	private PostDao postDao;
	
	@RequestMapping("/startuploading")
	public String initiateUpload() {
		return "UploadImage";
	}

    @Autowired
    AWSBucketController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFile(@RequestPart(value = "file") MultipartFile file, Model model) {
    	System.out.println("Adding something for test&Name");
    	
        model.addAttribute("UploadedImage", this.amazonClient.uploadFile(file));
        List<Attachment> allAttachments = amazonClient.getAllAttachmentsOfThisUser();
		logger.info("Attachment size = " + allAttachments.size());
		model.addAttribute("attachments4user", allAttachments);
        return "UploadImage";
    }

    	/**
    	 * This handler method is invoked when sending post with attachment
    	 * @param file
    	 * @param post
    	 * @param model
    	 * @return
    	 */
    @RequestMapping(value = "/uploadFileWithPost", method = RequestMethod.POST)
    public String uploadFileAndPost(@RequestPart(value = "file") MultipartFile file, @ModelAttribute("postModel")Post post, Model model) {
    	logger.info("invoking this with Image");
    		
        model.addAttribute("UploadedImage", this.amazonClient.uploadFileandPost(file, post));
        List<Attachment> allAttachments = amazonClient.getAllAttachmentsOfThisUser();
		logger.info("Attachment size = " + allAttachments.size());
		model.addAttribute("attachments4user", allAttachments);
		
		List<Post> allPosts = postDao.retrieveAllPosts();
		
		model.addAttribute("allpostList", allPosts);
		
        return "viewAddList";
    }
    
    @RequestMapping("/getAttachmentsThisUser")
    public String getAttachments(Model model){
    		List<Attachment> allAttachments = amazonClient.getAllAttachmentsOfThisUser();
    		logger.info("Attachment size = " + allAttachments.size());
    		model.addAttribute("attachments4user", allAttachments);
    		return "UploadImage";
    }

    @RequestMapping(value="/deleteFile", method = RequestMethod.POST)
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
    	logger.info("Need to add more stuff here " + fileUrl);
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    }
    
    @RequestMapping(value="/ayine")
    public String deleteFile05(@RequestPart(value = "url") String fileUrl) {
    		logger.info("Need to add more stuff here " + fileUrl);
    		
    		return "UploadImage";
    }
    
    @RequestMapping(value="/moreStuff")
    public String deletemoreStuff() {
    		logger.info("Need to add more stuff here");
    		return "signUp";
    }

}
