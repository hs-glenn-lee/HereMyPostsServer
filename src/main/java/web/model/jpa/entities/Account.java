package web.model.jpa.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

//when jackson convert this object as JSON, this annotation prevent cyclic call like a.b and also b.a
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")	
@Entity
@Table(name="accounts")
public class Account implements Serializable{
	
	private static final long serialVersionUID = 4615336338139177596L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="email")
	private String email;
	
	@Column(name="pass_word")
	private String password;
	
	@Column(name="create_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTimestamp;
	
	@Column(name="update_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatesTimestamp;
	
	
	@OneToOne(mappedBy="account", fetch = FetchType.LAZY)
	private AccountSetting accountSetting;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="account")
	private Set<Category> categories = new HashSet<Category>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="author")
	private Set<Article> articles = new HashSet<Article>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="owner")
	private Set<Tag> tags = new HashSet<Tag>();

	public Account(String username, String plainPassword) {}
	
	public Account() {}
	
	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}


	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public Date getUpdatesTimestamp() {
		return updatesTimestamp;
	}

	public void setUpdatesTimestamp(Date updatesTimestamp) {
		this.updatesTimestamp = updatesTimestamp;
	}
	
	
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	
	public AccountSetting getAccountSetting() {
		return accountSetting;
	}

	public void setAccountSetting(AccountSetting accountSetting) {
		this.accountSetting = accountSetting;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		
		if(this.id==null)
			id=-9999l;
		
		sb.append(String.format("%s : %s", "id", Long.toString(this.id)));
		sb.append(", ");
		sb.append(String.format("%s : %s", "username", this.username));
		sb.append(", ");
		sb.append(String.format("%s : %s", "password", this.password));
		sb.append(", ");
		sb.append(String.format("%s : %s", "email", this.email));
		sb.append(", ");
		if(this.createTimestamp != null && this.updatesTimestamp != null) {
			sb.append(String.format("%s : %s", "createTimestamp", this.createTimestamp.toString() ));
			sb.append(", ");
			sb.append(String.format("%s : %s", "updatesTimestamp", this.updatesTimestamp.toString() ));
		}
		sb.append("}");
		
		return sb.toString();
		
	}

}
