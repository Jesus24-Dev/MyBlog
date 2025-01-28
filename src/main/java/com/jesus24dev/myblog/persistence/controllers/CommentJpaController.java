/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jesus24dev.myblog.persistence.controllers;

import com.jesus24dev.myblog.persistence.controllers.exceptions.NonexistentEntityException;
import com.jesus24dev.myblog.persistence.models.Comment;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import com.jesus24dev.myblog.persistence.models.Profile;
import com.jesus24dev.myblog.persistence.models.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 *
 * @author Jesus24-Dev
 */
public class CommentJpaController implements Serializable {

    public CommentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public CommentJpaController(){
        emf = Persistence.createEntityManagerFactory("MyBlogJPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Comment comment) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Profile profile = comment.getProfile();
            if (profile != null) {
                profile = em.getReference(profile.getClass(), profile.getId());
                comment.setProfile(profile);
            }
            Post post = comment.getPost();
            if (post != null) {
                post = em.getReference(post.getClass(), post.getId());
                comment.setPost(post);
            }
            em.persist(comment);
            if (profile != null) {
                profile.getUserComments().add(comment);
                profile = em.merge(profile);
            }
            if (post != null) {
                post.getUserComments().add(comment);
                post = em.merge(post);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comment comment) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comment persistentComment = em.find(Comment.class, comment.getId());
            Profile profileOld = persistentComment.getProfile();
            Profile profileNew = comment.getProfile();
            Post postOld = persistentComment.getPost();
            Post postNew = comment.getPost();
            if (profileNew != null) {
                profileNew = em.getReference(profileNew.getClass(), profileNew.getId());
                comment.setProfile(profileNew);
            }
            if (postNew != null) {
                postNew = em.getReference(postNew.getClass(), postNew.getId());
                comment.setPost(postNew);
            }
            comment = em.merge(comment);
            if (profileOld != null && !profileOld.equals(profileNew)) {
                profileOld.getUserComments().remove(comment);
                profileOld = em.merge(profileOld);
            }
            if (profileNew != null && !profileNew.equals(profileOld)) {
                profileNew.getUserComments().add(comment);
                profileNew = em.merge(profileNew);
            }
            if (postOld != null && !postOld.equals(postNew)) {
                postOld.getUserComments().remove(comment);
                postOld = em.merge(postOld);
            }
            if (postNew != null && !postNew.equals(postOld)) {
                postNew.getUserComments().add(comment);
                postNew = em.merge(postNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = comment.getId();
                if (findComment(id) == null) {
                    throw new NonexistentEntityException("The comment with id " + id + " no longer exists.");
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
            Comment comment;
            try {
                comment = em.getReference(Comment.class, id);
                comment.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comment with id " + id + " no longer exists.", enfe);
            }
            Profile profile = comment.getProfile();
            if (profile != null) {
                profile.getUserComments().remove(comment);
                profile = em.merge(profile);
            }
            Post post = comment.getPost();
            if (post != null) {
                post.getUserComments().remove(comment);
                post = em.merge(post);
            }
            em.remove(comment);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Comment> findCommentEntities() {
        return findCommentEntities(true, -1, -1);
    }

    public List<Comment> findCommentEntities(int maxResults, int firstResult) {
        return findCommentEntities(false, maxResults, firstResult);
    }

    private List<Comment> findCommentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comment.class));
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

    public Comment findComment(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comment.class, id);
        } finally {
            em.close();
        }
    }

    public int getCommentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comment> rt = cq.from(Comment.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
