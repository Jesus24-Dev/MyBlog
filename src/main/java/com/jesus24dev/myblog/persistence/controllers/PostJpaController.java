/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jesus24dev.myblog.persistence.controllers;

import com.jesus24dev.myblog.persistence.controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import com.jesus24dev.myblog.persistence.models.Profile;
import com.jesus24dev.myblog.persistence.models.Comment;
import com.jesus24dev.myblog.persistence.models.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jesus24-Dev
 */
public class PostJpaController implements Serializable {

    public PostJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public PostJpaController(){
        emf = Persistence.createEntityManagerFactory("MyBlogJPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Post post) {
        if (post.getUserComments() == null) {
            post.setUserComments(new ArrayList<Comment>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Profile profile = post.getProfile();
            if (profile != null) {
                profile = em.getReference(profile.getClass(), profile.getId());
                post.setProfile(profile);
            }
            List<Comment> attachedUserComments = new ArrayList<Comment>();
            for (Comment userCommentsCommentToAttach : post.getUserComments()) {
                userCommentsCommentToAttach = em.getReference(userCommentsCommentToAttach.getClass(), userCommentsCommentToAttach.getId());
                attachedUserComments.add(userCommentsCommentToAttach);
            }
            post.setUserComments(attachedUserComments);
            em.persist(post);
            if (profile != null) {
                profile.getUserPosts().add(post);
                profile = em.merge(profile);
            }
            for (Comment userCommentsComment : post.getUserComments()) {
                Post oldPostOfUserCommentsComment = userCommentsComment.getPost();
                userCommentsComment.setPost(post);
                userCommentsComment = em.merge(userCommentsComment);
                if (oldPostOfUserCommentsComment != null) {
                    oldPostOfUserCommentsComment.getUserComments().remove(userCommentsComment);
                    oldPostOfUserCommentsComment = em.merge(oldPostOfUserCommentsComment);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Post post) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Post persistentPost = em.find(Post.class, post.getId());
            Profile profileOld = persistentPost.getProfile();
            Profile profileNew = post.getProfile();
            List<Comment> userCommentsOld = persistentPost.getUserComments();
            List<Comment> userCommentsNew = post.getUserComments();
            if (profileNew != null) {
                profileNew = em.getReference(profileNew.getClass(), profileNew.getId());
                post.setProfile(profileNew);
            }
            List<Comment> attachedUserCommentsNew = new ArrayList<Comment>();
            for (Comment userCommentsNewCommentToAttach : userCommentsNew) {
                userCommentsNewCommentToAttach = em.getReference(userCommentsNewCommentToAttach.getClass(), userCommentsNewCommentToAttach.getId());
                attachedUserCommentsNew.add(userCommentsNewCommentToAttach);
            }
            userCommentsNew = attachedUserCommentsNew;
            post.setUserComments(userCommentsNew);
            post = em.merge(post);
            if (profileOld != null && !profileOld.equals(profileNew)) {
                profileOld.getUserPosts().remove(post);
                profileOld = em.merge(profileOld);
            }
            if (profileNew != null && !profileNew.equals(profileOld)) {
                profileNew.getUserPosts().add(post);
                profileNew = em.merge(profileNew);
            }
            for (Comment userCommentsOldComment : userCommentsOld) {
                if (!userCommentsNew.contains(userCommentsOldComment)) {
                    userCommentsOldComment.setPost(null);
                    userCommentsOldComment = em.merge(userCommentsOldComment);
                }
            }
            for (Comment userCommentsNewComment : userCommentsNew) {
                if (!userCommentsOld.contains(userCommentsNewComment)) {
                    Post oldPostOfUserCommentsNewComment = userCommentsNewComment.getPost();
                    userCommentsNewComment.setPost(post);
                    userCommentsNewComment = em.merge(userCommentsNewComment);
                    if (oldPostOfUserCommentsNewComment != null && !oldPostOfUserCommentsNewComment.equals(post)) {
                        oldPostOfUserCommentsNewComment.getUserComments().remove(userCommentsNewComment);
                        oldPostOfUserCommentsNewComment = em.merge(oldPostOfUserCommentsNewComment);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = post.getId();
                if (findPost(id) == null) {
                    throw new NonexistentEntityException("The post with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Post post;
            try {
                post = em.getReference(Post.class, id);
                post.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The post with id " + id + " no longer exists.", enfe);
            }
            Profile profile = post.getProfile();
            if (profile != null) {
                profile.getUserPosts().remove(post);
                profile = em.merge(profile);
            }
            List<Comment> userComments = post.getUserComments();
            for (Comment userCommentsComment : userComments) {
                userCommentsComment.setPost(null);
                userCommentsComment = em.merge(userCommentsComment);
            }
            em.remove(post);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Post> findPostEntities() {
        return findPostEntities(true, -1, -1);
    }

    public List<Post> findPostEntities(int maxResults, int firstResult) {
        return findPostEntities(false, maxResults, firstResult);
    }

    private List<Post> findPostEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Post.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Post findPost(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Post.class, id);
        } finally {
            em.close();
        }
    }

    public int getPostCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Post> rt = cq.from(Post.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
