package web.controllers.rest.responses;

import java.util.Date;

import web.model.jpa.entities.Comment;

public class CommentAsResponse {
	
	private Boolean isAnonyumous;
	private String authorName;
	private String profilePictureFileId;
	private String content;
	private Date createTimestamp;
	private Date updateTimestamp;
	private String articleId;
	private Boolean isDel;
	
	public CommentAsResponse(Comment comment) {
		this.isAnonyumous = comment.getIsAnonymous();
		if(this.isAnonyumous) {
			this.authorName = comment.getAnonymousAuthorName();
		}else {
			this.authorName = comment.getAuthor().getAccountSetting().getPenName();
			this.profilePictureFileId = comment.getAuthor().getAccountSetting().getProfilePictureFileId();
		}
		this.content = comment.getContent();
		this.createTimestamp = comment.getCreateTimestamp();
		this.updateTimestamp = comment.getUpdateTimestamp();
		this.articleId = comment.getArticle().getId();
		this.isDel = comment.getIsDel();
	}

	public Boolean getIsAnonyumous() {
		return isAnonyumous;
	}

	public void setIsAnonyumous(Boolean isAnonyumous) {
		this.isAnonyumous = isAnonyumous;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getProfilePictureFileId() {
		return profilePictureFileId;
	}

	public void setProfilePictureFileId(String profilePictureFileId) {
		this.profilePictureFileId = profilePictureFileId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}
	
	
}