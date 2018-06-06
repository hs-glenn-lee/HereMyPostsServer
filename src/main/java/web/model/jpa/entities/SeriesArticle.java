package web.model.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="series_articles")
public class SeriesArticle {
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="seq")
	private Integer seq;
	
	@ManyToOne
	@JoinColumn(name="article_id")
	private Article article;
	
	@ManyToOne
	@JoinColumn(name="series_id")
	private Series series;
	

	public SeriesArticle() {}
	
	public SeriesArticle(Series series, Article article) {
		this.article = article;
		this.series = series;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
		if(!series.getSeriesArticles().contains(this)) {
			series.getSeriesArticles().add(this);
		}
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	/*public boolean equals(Series series) {
		if(series.getId() == this.getId()) {
			return true;
		}else {
			return false;
		}
	}*/
	
}
