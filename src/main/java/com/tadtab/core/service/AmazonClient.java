package com.tadtab.core.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.tadtab.core.authentication.AutoUser;
import com.tadtab.core.pojo.Attachment;
import com.tadtab.core.pojo.Image;
import com.tadtab.core.pojo.Post;
import com.tadtab.core.utility.HibernateUtilities;
import com.tadtab.dao.PrincipalHustler;

@Service
public class AmazonClient {
	
	static final Logger logger = Logger.getLogger("AmazonClient"); 

    private AmazonS3 s3client;
    
    @Autowired
    private Attachment attachment;
    
    @Autowired
    private Image image;
    
    @Autowired
    private PrincipalHustler principalHustler;

    @Value("${awsEndpointUrl}")
    private String endpointUrl;
    @Value("${awsBucketName}")
    private String bucketName;
    @Value("${awsAccessKey}")
    private String accessKey;
    @Value("${aswSecretKey}")
    private String secretKey;
    
    /**
     * create session for persisting url for the attachment
     */
    SessionFactory sessionFactory = HibernateUtilities.getSessionFactory();
    Session session = null;
    
    
    private void initializeAmazon() {
       AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
       this.s3client = AmazonS3ClientBuilder.standard().build();
    }
    
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
    	try {
	        File convFile = new File(file.getOriginalFilename());
	        FileOutputStream fos = new FileOutputStream(convFile);
	        fos.write(file.getBytes());
	        fos.close();
	        return convFile;
    	} catch (Exception e) {
    		logger.info("File not found " + e);
    	}
		return null;
    	
    }
    
    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }
    
    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }
    
    public String uploadFile(MultipartFile multipartFile) {
        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
            uploadFileTos3bucket(fileName, file);
            
            /**
             * Add a session to help the hibernate persist the url
             */
            
            session = sessionFactory.openSession();
	    	    session.beginTransaction();
	    	    
	    		    attachment.setAttachmentName(fileName);
	    		    attachment.setAttachmentURL(fileUrl);
	    		    
	    	    session.save(attachment);
	    	    
	    	    session.getTransaction().commit();
	    	    session.close();
            
            file.delete();
        } catch (Exception e) {
           e.printStackTrace();
        }
        
        
        return fileUrl;
    }
    public Post uploadFileWithPost(List<MultipartFile> multipartFileList, Post post) {
    	Post newPost = null;
    	if(!multipartFileList.isEmpty()) {
	        
	        try {
	        	
	        	session = sessionFactory.openSession();
	    	    session.beginTransaction();
	    	    
	    	    long postId = post.getPostId();
			     System.out.println("Post id id " + postId);
			    Post posttobe = (Post)session.get(Post.class, postId);
			    session.getTransaction().commit();
			    
			    session.beginTransaction();
			    if(posttobe != null) {
			    	posttobe.setTitle(post.getTitle());
			    	posttobe.setBody(post.getBody());
			    	posttobe.setDate(new Date());
			    	persistMultipart(multipartFileList, posttobe);
			    	newPost = posttobe;
			    }else {
	    	    
		    	    AutoUser autoUser = (AutoUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    		    post.setAutoUser(autoUser); 
	    		    post.setDate(new Date());
	    		    
		    	    session.save(post);
		    	    
		    	    persistMultipart(multipartFileList, post);
		    	    
		    	    newPost = post;
		        	
			    }
			    
			    
			    
	        	session.getTransaction().commit();
	    	    session.close();
	        
	        } catch (Exception e) {
	           e.printStackTrace();
	        }
    	
    	}
    	
    	return newPost;
        
    }
    
    private void persistMultipart(List<MultipartFile> multipartFileList, Post post) {
    	for(MultipartFile multipartFile : multipartFileList) {
        	
    		
    		if(!multipartFile.isEmpty()) {
    			Attachment attachmentmultiple= new Attachment();
	            File file;
				try {
					file = convertMultiPartToFile(multipartFile);
				
	            String fileName = generateFileName(multipartFile);
	            String fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
	            uploadFileTos3bucket(fileName, file);
	          
	            attachmentmultiple.setAttachmentName(fileName);
	            attachmentmultiple.setAttachmentURL(fileUrl);
		    		    
		    		  
	    		      
		    		post.getAttachmentlist().add(attachmentmultiple);  
		    		
		    		session.save(attachmentmultiple);
		    		
	            file.delete();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	
    	}
    }
    /**
     * Another method for persisting post with Image
     * @param multipartFile
     * @return
     */
    public String uploadFileandPost(MultipartFile multipartFile, Post post) {
        String fileUrl = "";
        List<Image> listedImages = new ArrayList<>();
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
            uploadFileTos3bucket(fileName, file);
            
            /**
             * Add a session to help the hibernate persist the url
             */
            
            session = sessionFactory.openSession();
	    	    session.beginTransaction();

	    		    image.setImageURL(fileUrl);
	    		    
	    		    post.setAutoUser(principalHustler.getCurrentPrincipal());
	    		    
	    		    listedImages.add(image);
	    		    
	    	    session.save(image);
	    	    session.save(post);
	    	    session.getTransaction().commit();
	    	    session.close();
            
            file.delete();
        } catch (Exception e) {
           e.printStackTrace();
        }
        
        
        return fileUrl;
    }
    
    public String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
        return "Successfully deleted";
    }
    
    /**
     * This will return the list of current User attachments persisted. 
     * @return
     */
    @SuppressWarnings("cast")
    public List<Attachment> getAllAttachmentsOfThisUser(){
    	
     	List<Attachment> thisUsersAttachment = new ArrayList<>();
     	try {
	    		session = sessionFactory.openSession();
	    		session.beginTransaction();
	    		
		    	
		    		
		    		/*for(Attachment attachment : attachmentList) {
		    			if(attachment.getAutoUser() != null &&
		    					attachment.getAutoUser().getUsername() != null && 
		    					attachment.getAutoUser().getUsername().equals(principalHustler.getCurrentPrincipal().getUsername())) {
		    				thisUsersAttachment.add(attachment);
		    			}
		    		}*/
		    		
		    	session.getTransaction().commit();
		    	session.close();
     	}catch(Exception e) {
     		logger.info("Exception inside the getAllAttachmentsOfThisUser() " + e);
 	
     	}
    		
    		return thisUsersAttachment;
    }
    
 }
