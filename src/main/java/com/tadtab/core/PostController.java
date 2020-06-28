package com.tadtab.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.tadtab.core.pojo.Post;
import com.tadtab.core.service.AmazonClient;
import com.tadtab.dao.PostDao;

@Controller
@RequestMapping("/postAdd")
public class PostController {
	
	@Autowired
	private Post post;
	
	private AmazonClient amazonClient;
	
	 @Autowired
	 	PostController(AmazonClient amazonClient) {
	        this.amazonClient = amazonClient;
	    }
	
	@Autowired
	private PostDao postDao;
	
	@RequestMapping("/start")
	public String postAdd(Model model) {
		model.addAttribute("postModel", post);
		return "postAdd";
		
	}
	
	@RequestMapping("/postDetail/{postId}")
	public String postDetail(@ModelAttribute("postId") long postId, Model model) {
		
		Post post = postDao.getAPost(postId);
		
		model.addAttribute("aPost", post);
		
		return "postDetail";
	}
	
	@RequestMapping("/submit")
	public String submitadd(@ModelAttribute("postId") long postIdEdit, @RequestPart(value = "file") List<MultipartFile> fileList, @ModelAttribute("postModel")Post post, Model model) {
		long postId = post.getPostId();
		if(!fileList.isEmpty()) {
					
					Post postPersisted = this.amazonClient.uploadFileWithPost(fileList, post);
				
			model.addAttribute("aPost", postPersisted);
		}
		else {
			
			postDao.persistPost(post);
			Post postAfter = postDao.getPostById(postId);
			List<Post> allPosts = postDao.retrieveAllPosts();
			model.addAttribute("aPost", postAfter);
			
		}
		return "postDetail";
	}
	
	@RequestMapping("/allUsersPosts")
	public String viewAllUsersPosts(Model model) {
		
		List<?> allUsersPosts  = postDao.retrieveAllUsersPosts();
		
		model.addAttribute("allUsersPosts", allUsersPosts);
		
		return "allUsersPosts";
	}
	
	@RequestMapping("/viewThisUsersPosts")
	public String viewCurrentUserPosts( Model model) {
		
		List<Post> allPosts = postDao.retrieveAllPosts();
		
		model.addAttribute("allpostList", allPosts);
		return "viewAddList";
	}
	
	
	@RequestMapping("/{postId}")
	public String deletePost(@ModelAttribute("postId") long postId, Model model) {
	
				postDao.deletePost(postId);
	
		List<Post> allPosts = postDao.retrieveAllPosts();
		
		model.addAttribute("allpostList", allPosts);
		
		return "viewAddList";
	}
	
	@RequestMapping("/edit/{postId}")
	public String editPost(@ModelAttribute("postId") long postId,Model model) {
		
		Post post = postDao.getPostById(postId);
		model.addAttribute("postModel", post);
		
		return "postAdd";
	}
	
	@RequestMapping("/deleteImage/{attachmentId}")
	public String removeAttachment(@ModelAttribute("attachmentId") long attachmentId, Model model) {
		
		postDao.deleteAttachmentById(attachmentId);
		
		List<Post> allPosts = postDao.retrieveAllPosts();
		
		model.addAttribute("allpostList", allPosts);
		
		return "viewAddList";
	}

}
