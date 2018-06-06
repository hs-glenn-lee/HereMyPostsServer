package web.model.jpa.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
@Table(name="series")
public class Series {

	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="create_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTimestamp;
	
	@Column(name="update_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTimestamp;
	
	@OneToMany(mappedBy="series")
	private List<SeriesArticle> seriesArticles = new ArrayList<SeriesArticle>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="owner_id")
	private Account owner;
	
	

	
	public List<SeriesArticle> getSeriesArticles() {
		return seriesArticles;
	}

	public void setSeriesArticles(List<SeriesArticle> seriesArticles) {
		this.seriesArticles = seriesArticles;
	}

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

	
	
	//convention
	public void addArticle(Article article) {
		SeriesArticle seriesArticle = new SeriesArticle(this, article);
		seriesArticles.add(seriesArticle);
		article.getSeriesArticle().add(seriesArticle);
	}

	public void removeArticle(Article article) {
		for (Iterator<SeriesArticle> iterator = seriesArticles.iterator(); 
	             iterator.hasNext(); ) {
				SeriesArticle seriesArticle = iterator.next();
	 
	            if (seriesArticle.getSeries().equals(this) &&
	            		seriesArticle.getArticle().equals(article)) {
	                iterator.remove();
	                seriesArticle.getArticle().getSeriesArticle().remove(seriesArticle);
	                seriesArticle.setSeries(null);
	                seriesArticle.setArticle(null);
	            }
	        }
	}
	
}
