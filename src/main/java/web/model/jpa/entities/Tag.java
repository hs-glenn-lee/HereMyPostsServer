package web.model.jpa.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="tags")
public class Tag implements Serializable{

	private static final long serialVersionUID = -3942498561244888210L;

	@Id
	@Column(name="name")
	private String name;
	
	@JsonBackReference(value="tag")
	@OneToMany(mappedBy="tag")
	private List<TagArticle> tagsArticles = new ArrayList<TagArticle>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TagArticle> getTagsArticles() {
		return tagsArticles;
	}

	public void setTagsArticles(List<TagArticle> tagsArticles) {
		this.tagsArticles = tagsArticles;
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

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("name: " + this.name + ", ");
		sb.append("}");
		return sb.toString();
	}
}
