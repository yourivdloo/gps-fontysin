package fontysin.project.model;

import fontysin.project.model.user.AppUser;

import javax.persistence.*;

@Entity
public class Post {
    public Post(){

    }

    public Post(AppUser appUser, String content) {
        this.appUser = appUser;
        this.content = content;
    }

    @Id
    @GeneratedValue
    private int postId;

    @ManyToOne
    @JoinColumn(name = "pcn", nullable = false)
    private AppUser appUser;

    private String content;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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
