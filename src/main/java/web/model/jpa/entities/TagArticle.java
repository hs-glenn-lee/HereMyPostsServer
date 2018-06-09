package web.model.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tags_articles")
public class TagArticle {
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="seq")
	private Integer seq;
	
	@ManyToOne
	@JoinColumn(name="article_id")
	private Article article;
	
	@ManyToOne
	@JoinColumn(name="tag_id")
	private Tag tag;
	

	public TagArticle() {}
	
	public TagArticle(Tag tag, Article article) {
		this.article = article;
		this.tag = tag;
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

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
		if(!tag.getTagArticles().contains(this)) {
			tag.getTagArticles().add(this);
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
