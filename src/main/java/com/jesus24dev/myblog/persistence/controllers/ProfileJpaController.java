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
import com.jesus24dev.myblog.persistence.models.Post;
import java.util.ArrayList;
import java.util.List;
import com.jesus24dev.myblog.persistence.models.Comment;
import com.jesus24dev.myblog.persistence.models.Profile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author Jesus24-Dev
 */
public class ProfileJpaController implements Serializable {

    public ProfileJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Profile profile) {
        if (profile.getUserPosts() == null) {
            profile.setUserPosts(new ArrayList<Post>());
        }
        if (profile.getUserComments() == null) {
            profile.setUserComments(new ArrayList<Comment>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Post> attachedUserPosts = new ArrayList<Post>();
            for (Post userPostsPostToAttach : profile.getUserPosts()) {
                userPostsPostToAttach = em.getReference(userPostsPostToAttach.getClass(), userPostsPostToAttach.getId());
                attachedUserPosts.add(userPostsPostToAttach);
            }
            profile.setUserPosts(attachedUserPosts);
            List<Comment> attachedUserComments = new ArrayList<Comment>();
            for (Comment userCommentsCommentToAttach : profile.getUserComments()) {
                userCommentsCommentToAttach = em.getReference(userCommentsCommentToAttach.getClass(), userCommentsCommentToAttach.getId());
                attachedUserComments.add(userCommentsCommentToAttach);
            }
            profile.setUserComments(attachedUserComments);
            em.persist(profile);
            for (Post userPostsPost : profile.getUserPosts()) {
                Profile oldProfileOfUserPostsPost = userPostsPost.getProfile();
                userPostsPost.setProfile(profile);
                userPostsPost = em.merge(userPostsPost);
                if (oldProfileOfUserPostsPost != null) {
                    oldProfileOfUserPostsPost.getUserPosts().remove(userPostsPost);
                    oldProfileOfUserPostsPost = em.merge(oldProfileOfUserPostsPost);
                }
            }
            for (Comment userCommentsComment : profile.getUserComments()) {
                Profile oldProfileOfUserCommentsComment = userCommentsComment.getProfile();
                userCommentsComment.setProfile(profile);
                userCommentsComment = em.merge(userCommentsComment);
                if (oldProfileOfUserCommentsComment != null) {
                    oldProfileOfUserCommentsComment.getUserComments().remove(userCommentsComment);
                    oldProfileOfUserCommentsComment = em.merge(oldProfileOfUserCommentsComment);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Profile profile) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Profile persistentProfile = em.find(Profile.class, profile.getId());
            List<Post> userPostsOld = persistentProfile.getUserPosts();
            List<Post> userPostsNew = profile.getUserPosts();
            List<Comment> userCommentsOld = persistentProfile.getUserComments();
            List<Comment> userCommentsNew = profile.getUserComments();
            List<Post> attachedUserPostsNew = new ArrayList<Post>();
            for (Post userPostsNewPostToAttach : userPostsNew) {
                userPostsNewPostToAttach = em.getReference(userPostsNewPostToAttach.getClass(), userPostsNewPostToAttach.getId());
                attachedUserPostsNew.add(userPostsNewPostToAttach);
            }
            userPostsNew = attachedUserPostsNew;
            profile.setUserPosts(userPostsNew);
            List<Comment> attachedUserCommentsNew = new ArrayList<Comment>();
            for (Comment userCommentsNewCommentToAttach : userCommentsNew) {
                userCommentsNewCommentToAttach = em.getReference(userCommentsNewCommentToAttach.getClass(), userCommentsNewCommentToAttach.getId());
                attachedUserCommentsNew.add(userCommentsNewCommentToAttach);
            }
            userCommentsNew = attachedUserCommentsNew;
            profile.setUserComments(userCommentsNew);
            profile = em.merge(profile);
            for (Post userPostsOldPost : userPostsOld) {
                if (!userPostsNew.contains(userPostsOldPost)) {
                    userPostsOldPost.setProfile(null);
                    userPostsOldPost = em.merge(userPostsOldPost);
                }
            }
            for (Post userPostsNewPost : userPostsNew) {
                if (!userPostsOld.contains(userPostsNewPost)) {
                    Profile oldProfileOfUserPostsNewPost = userPostsNewPost.getProfile();
                    userPostsNewPost.setProfile(profile);
                    userPostsNewPost = em.merge(userPostsNewPost);
                    if (oldProfileOfUserPostsNewPost != null && !oldProfileOfUserPostsNewPost.equals(profile)) {
                        oldProfileOfUserPostsNewPost.getUserPosts().remove(userPostsNewPost);
                        oldProfileOfUserPostsNewPost = em.merge(oldProfileOfUserPostsNewPost);
                    }
                }
            }
            for (Comment userCommentsOldComment : userCommentsOld) {
                if (!userCommentsNew.contains(userCommentsOldComment)) {
                    userCommentsOldComment.setProfile(null);
                    userCommentsOldComment = em.merge(userCommentsOldComment);
                }
            }
            for (Comment userCommentsNewComment : userCommentsNew) {
                if (!userCommentsOld.contains(userCommentsNewComment)) {
                    Profile oldProfileOfUserCommentsNewComment = userCommentsNewComment.getProfile();
                    userCommentsNewComment.setProfile(profile);
                    userCommentsNewComment = em.merge(userCommentsNewComment);
                    if (oldProfileOfUserCommentsNewComment != null && !oldProfileOfUserCommentsNewComment.equals(profile)) {
                        oldProfileOfUserCommentsNewComment.getUserComments().remove(userCommentsNewComment);
                        oldProfileOfUserCommentsNewComment = em.merge(oldProfileOfUserCommentsNewComment);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = profile.getId();
                if (findProfile(id) == null) {
                    throw new NonexistentEntityException("The profile with id " + id + " no longer exists.");
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
            Profile profile;
            try {
                profile = em.getReference(Profile.class, id);
                profile.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The profile with id " + id + " no longer exists.", enfe);
            }
            List<Post> userPosts = profile.getUserPosts();
            for (Post userPostsPost : userPosts) {
                userPostsPost.setProfile(null);
                userPostsPost = em.merge(userPostsPost);
            }
            List<Comment> userComments = profile.getUserComments();
            for (Comment userCommentsComment : userComments) {
                userCommentsComment.setProfile(null);
                userCommentsComment = em.merge(userCommentsComment);
            }
            em.remove(profile);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Profile> findProfileEntities() {
        return findProfileEntities(true, -1, -1);
    }

    public List<Profile> findProfileEntities(int maxResults, int firstResult) {
        return findProfileEntities(false, maxResults, firstResult);
    }

    private List<Profile> findProfileEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Profile.class));
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

    public Profile findProfile(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Profile.class, id);
        } finally {
            em.close();
        }
    }

    public int getProfileCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Profile> rt = cq.from(Profile.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
