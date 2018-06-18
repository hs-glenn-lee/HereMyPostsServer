package web.model.jpa.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

@Entity
@Table(name="tags")
public class Tag {

	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="owner_id")
	private Account owner;
	
	@OneToMany(mappedBy="tag")
	private List<TagArticle> tagArticles = new ArrayList<TagArticle>();

	@Column(name="create_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTimestamp;
	
	@Column(name="update_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTimestamp;
	
	public Account getOwner() {
		return owner;
	}

	public void setOwner(Account owner) {
		this.owner = owner;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	
	public List<TagArticle> getTagArticles() {
		return tagArticles;
	}

	public void setTagArticles(List<TagArticle> tagArticles) {
		this.tagArticles = tagArticles;
	}
	//convention
/*	public void addArticle(Article article) {
		TagArticle seriesArticle = new TagArticle(this, article);
		tagArticles.add(seriesArticle);
		article.getTagArticles().add(seriesArticle);
	}

	public void removeArticle(Article article) {
		for (Iterator<TagArticle> iterator = tagArticles.iterator(); 
	             iterator.hasNext(); ) {
				TagArticle seriesArticle = iterator.next();
	 
	            if (seriesArticle.getSeries().equals(this) &&
	            		seriesArticle.getArticle().equals(article)) {
	                iterator.remove();
	                seriesArticle.getArticle().getTagArticles().remove(seriesArticle);
	                seriesArticle.setSeries(null);
	                seriesArticle.setArticle(null);
	            }
	        }
	}*/
	
}
