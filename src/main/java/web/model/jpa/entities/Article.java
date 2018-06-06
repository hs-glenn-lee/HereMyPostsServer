package web.model.jpa.entities;

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

@Entity
@Table(name="articles")
public class Article {
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="content_file_path")
	private String contentFilePath;
	
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="article")
	private List<SeriesArticle> seriesArticle = new ArrayList<SeriesArticle>();
	
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

	public String getContetnFilePath() {
		return contentFilePath;
	}

	public void setContetnFilePath(String contetnFilePath) {
		this.contentFilePath = contetnFilePath;
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

	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
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

	public String getContentFilePath() {
		return contentFilePath;
	}

	public void setContentFilePath(String contentFilePath) {
		this.contentFilePath = contentFilePath;
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
					+ "contentFilePath: " + contentFilePath + ","
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

	public List<SeriesArticle> getSeriesArticle() {
		return seriesArticle;
	}

	public void setSeriesArticle(List<SeriesArticle> seriesArticle) {
		this.seriesArticle = seriesArticle;
	}


	
	
	
}















