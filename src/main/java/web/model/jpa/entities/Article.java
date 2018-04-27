package web.model.jpa.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@Column(name="is_valid")
	@Type(type="org.hibernate.type.NumericBooleanType")
	private Boolean isValid = true;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="author_id")
	private Account author;

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

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
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


	
	public String toString() {
		String ret = "id: " + id + ", "
					+ "title: " + id + ","
					+ "contentFilePath: " + contentFilePath + ","
					+ "summary: " + summary + ","
					+ "readCount : " + readCount  + ","
					+ "createTimestamp;: " + createTimestamp + ","
					+ "updateTimestamp;: " + updateTimestamp + ","
					+ "isValid : " + isValid;
		return ret;
		
	}
}














