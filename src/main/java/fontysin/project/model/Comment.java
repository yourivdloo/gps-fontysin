package fontysin.project.model;

import fontysin.project.model.user.AppUser;

import javax.persistence.*;

@Entity
public class Comment {
    public Comment(){

    }

    public Comment(Post post, AppUser appUser, String content) {
        this.post = post;
        this.appUser = appUser;
        this.content = content;
    }

    @Id
    @GeneratedValue
    private int commentId;

    @ManyToOne
    @JoinColumn(name = "postId", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "pcn", nullable = false)
    private AppUser appUser;

    private String content;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
