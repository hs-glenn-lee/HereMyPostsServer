package web.model.jpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name="tags_articles")
public class TagArticle implements Serializable{

	private static final long serialVersionUID = 8741257002550640334L;

	@Id
	@Column(name="id")
	private String id;
	
	@ManyToOne
	@JoinColumn(name="article_id")
	private Article article;
	
	@ManyToOne
	@JoinColumn(name="tag_name")
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
		if(!article.getTagArticles().contains(this)) {
			article.getTagArticles().add(this);
		}
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

	/*public boolean equals(Series series) {
		if(series.getId() == this.getId()) {
			return true;
		}else {
			return false;
		}
	}*/
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("id:" + this.id + ", ");
		String article = (this.article ==null)? null : this.article.toString();
		sb.append("article: " + article + ", ");
		String tag = (this.tag ==null)? null : this.tag.toString();
		sb.append("tag: " + tag + "");
		sb.append("}");
		return sb.toString(); 
	}
}
