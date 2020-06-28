package com.tadtab.core;

import java.io.InputStream;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

@Controller
@RequestMapping("/more")
public class FileUploadController {
	
	@RequestMapping("/attachFile")
	  public String returnAttachmentfield() {
		  
		  return "AttachmentPage";
	  }
	
	@RequestMapping(method=RequestMethod.POST, value="/saveAttachment")
	  public String handleAttachment(@RequestParam(required=false,value="name") String name,
			  @RequestParam(required=false,value="file") MultipartFile file, 
			  RedirectAttributes redirectAttributes
			  /*@ModelAttribute("uploadForm") AttachmentFile attachmentFile, Model map*/) {
		
	    AmazonS3 s3Client = AmazonS3ClientBuilder.standard().build();
		
		String bucketName = "tadtab-" + UUID.randomUUID();
		s3Client.createBucket(bucketName);
		
		try {
			
			InputStream is = file.getInputStream();
			s3Client.putObject(new PutObjectRequest(bucketName, name, is, new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead));
			
			S3Object s3Object = s3Client.getObject(new GetObjectRequest(bucketName, name));
			
			redirectAttributes.addAttribute("picUrl", s3Object.getObjectContent().getHttpRequest().getURI().toString());
			
		}catch(Exception e) {
			e.getStackTrace();
		}
		
		
		return "AttachmentPage";
		
	}
}
