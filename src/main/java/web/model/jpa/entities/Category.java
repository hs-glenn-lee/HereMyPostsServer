package web.model.jpa.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="categories")
public class Category implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5244875034007596637L;

	@Id
	@Column(name="id")
	String id;
	
	@Column(name="parent_id")
	String parentId;
	
	@Column(name="name")
	String name;
	
	@Column(name="seq")
	Integer seq;
	
	@Column(name="is_del")
	@Type(type="org.hibernate.type.NumericBooleanType")
	Boolean isDel = false;
	
	@Column(name="is_public")
	@Type(type="org.hibernate.type.NumericBooleanType")
	Boolean isPublic = false;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="owner_id")
	Account account;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="category")
	private Set<Article> articles;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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
		return this.id + ", " + this.name + ", " + this.parentId + ", " + this.seq + ", " + this.isDel;
	}
	
	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	
	
	
}
