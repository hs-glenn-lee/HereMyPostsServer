package web.model.jpa.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

	
@Entity
@Table(name="articles")
public class Article implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3850929944559500257L;

	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="content_file_id")
	private String contentFileId;
	
	@Column(name="summary")
	private String summary;
	
	@Column(name="read_count", nullable = false, columnDefinition = "INT(11) default 0")
	private Integer readCount = 0;
	
	@Column(name="create_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTimestamp;
	
	@Column(name="update_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTimestamp;
	
	@Transient
	private String createDateString;
	
	@Transient
	private String updateDateString;
	
	@Column(name="is_del")
	@Type(type="org.hibernate.type.NumericBooleanType")
	private Boolean isDel = false;

	@Column(name="is_public")
	@Type(type="org.hibernate.type.NumericBooleanType")
	private Boolean isPublic = false;
	
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="author_id")
	private Account author;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="article")
	private Set<Comment> comments;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy="article")
	private List<TagArticle> tagsArticles = new ArrayList<TagArticle>();

	public List<TagArticle> getTagsArticles() {
		return tagsArticles;
	}

	public void setTagsArticles(List<TagArticle> tagsArticles) {
		this.tagsArticles = tagsArticles;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContentFileId() {
		return contentFileId;
	}

	public void setContentFileId(String contetnFileId) {
		this.contentFileId = contetnFileId;
		
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public Date getCreateTimestamp() {
		return createTimestamp;
	}
	
	public void setCreateDateString (String createDateString) {
		this.createDateString=createDateString;
	}
	
	public void setCreateDateStringAsCreateTimestamp() {
		if(createTimestamp != null) {
			DateFormat dateFormat = new SimpleDateFormat("YYYY.MM.dd");
			this.createDateString = dateFormat.format(createTimestamp);
		}
	}
	
	public String getCreateDateString () {
		return this.createDateString;
	}
	
	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
		if(createTimestamp != null) {
			DateFormat dateFormat = new SimpleDateFormat("YYYY.MM.dd");
			this.createDateString = dateFormat.format(createTimestamp);
		}
	}
	
	public String getUpdateDateString () {
		return this.updateDateString;
	}
	
	public void setUpdateDateString(String updateDateString) {
		this.updateDateString = updateDateString;
	}
	public void setUpdateDateStringAsUpdateTimestamp() {
		if(updateTimestamp != null) {
			DateFormat dateFormat = new SimpleDateFormat("YYYY.MM.dd");
			this.updateDateString = dateFormat.format(updateTimestamp);
		}
	}
	

	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
		if(updateTimestamp != null) {
			DateFormat dateFormat = new SimpleDateFormat("YYYY.MM.dd");
			this.updateDateString = dateFormat.format(updateTimestamp);
		}
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Account getAuthor() {
		return author;
	}

	public void setAuthor(Account author) {
		this.author = author;
	}



	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}
	
	public String toString() {
		String ret = "id: " + id + ", "
					+ "title: " + id + ","
					+ "contentFilePath: " + contentFileId + ","
					+ "summary: " + summary + ","
					+ "readCount : " + readCount  + ","
					+ "createTimestamp;: " + createTimestamp + ","
					+ "updateTimestamp;: " + updateTimestamp + ","
					;
		return ret;
	}
	
	
	@Transient	
	private String content;	//for convention
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/*@Override
	public boolean equals(Object obj) {
		return false;
		
	}*/

	public boolean equalsWithId(Article article) {
		return article.getId().equals(article.getId());
	}
	
	
	
}















