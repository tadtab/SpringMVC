package com.tadtab.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.tadtab.core.authentication.AutoUser;
import com.tadtab.core.pojo.Attachment;
import com.tadtab.core.pojo.Post;
import com.tadtab.core.utility.HibernateUtilities;

public class PostDao
{

    static final Logger logger = Logger.getLogger("PostDao");

    SessionFactory sessionfactory = HibernateUtilities.getSessionFactory();
    Session session = null;

    public Post getAPost(long postId)
    {

        Post post = null;
        try
        {
            session = sessionfactory.openSession();
            session.beginTransaction();

            post = (Post) session.get(Post.class, postId);

            session.getTransaction().commit();
            session.close();

        }
        catch (Exception e)
        {
            logger.info("Exception occured ");
            e.printStackTrace();
        }

        return post;

    }

    public Post getPostById(long id)
    {
        session = sessionfactory.openSession();
        session.beginTransaction();

        Post post = (Post) session.get(Post.class, id);

        session.getTransaction().commit();
        session.close();

        return post;

    }

    public Attachment deleteAttachmentById(long id)
    {
        session = sessionfactory.openSession();
        session.beginTransaction();

        Attachment attachment = (Attachment) session.get(Attachment.class, id);

        session.delete(attachment);

        session.getTransaction().commit();
        session.close();

        return attachment;

    }

    public void deletePost(long postId)
    {
        session = sessionfactory.openSession();
        session.beginTransaction();

        Post postToDelete = (Post) session.get(Post.class, postId);

        if (postToDelete != null)
        {
            session.delete(postToDelete);
        }

        session.getTransaction().commit();
        session.close();
    }

    public void persistPost(Post post)
    {

        session = sessionfactory.openSession();
        session.beginTransaction();

        long postId = post.getPostId();

        Post posttobe = (Post) session.get(Post.class, postId);

        if (posttobe != null)
        {
            session.update(post);
        }
        else
        {
            System.out.println("Id does not exist");
            AutoUser autoUser = (AutoUser) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            post.setAutoUser(autoUser);
            post.setDate(new Date());
            session.save(post);

        }
        session.getTransaction().commit();
        session.close();

    }

    public List<?> retrieveAllUsersPosts()
    {
        List<?> postList = new ArrayList<>();
        session = sessionfactory.openSession();
        session.beginTransaction();

      
            postList = session.createQuery("from Post").list();
        

        session.getTransaction().commit();
        session.close();

        return postList;

    }

    public List<Post> retrieveAllPosts()
    {
        List<Post> postList = new ArrayList<>();
        session = sessionfactory.openSession();
        session.beginTransaction();

        /**
         * this securityContextHolder will be useful to retrieve the current principal and pull the
         * it is user name
         * 
         * Another way to get username
         * 
         */
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userName = null;

        if (principal instanceof UserDetails)
        {
            userName = ((UserDetails) principal).getUsername();
        }
        else
        {
            userName = principal.toString();
        }

        List<?> query = session.createQuery("from Post").list();

        Iterator<?> it = query.iterator();

        while (it.hasNext())
        {
            Post currentPost = (Post)it.next();

            if (currentPost.getAutoUser() != null && currentPost.getAutoUser().getUsername() != null
                    && currentPost.getAutoUser().getUsername().equals(userName))
            {
                postList.add(currentPost);
            }

        }

        session.getTransaction().commit();
        session.close();

        return postList;

    }

}
