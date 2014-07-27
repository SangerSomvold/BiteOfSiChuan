package dao.pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Shop entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "shop", catalog = "foodmap")
public class Shop implements java.io.Serializable {

	// Fields

	private Integer shopId;
	private Type type;
	private User user;
	private String shopName;
	private String iconUrl;
	private Double lng;
	private Double lat;
	private String address;
	private String feature;
	private Double gradeAvg;
	private Double costAvg;
	private Set<Collection> collections = new HashSet<Collection>(0);
	private Set<Comment> comments = new HashSet<Comment>(0);

	// Constructors

	/** default constructor */
	public Shop() {
	}
	

	/** default constructor */
	public Shop(Integer shopId) {
		this.shopId = shopId;
	}

	/** full constructor */
	public Shop(Type type, User user, String shopName, String iconUrl,
			Double lng, Double lat, String address, String feature,
			Double gradeAvg, Double costAvg, Set<Collection> collections,
			Set<Comment> comments) {
		this.type = type;
		this.user = user;
		this.shopName = shopName;
		this.iconUrl = iconUrl;
		this.lng = lng;
		this.lat = lat;
		this.address = address;
		this.feature = feature;
		this.gradeAvg = gradeAvg;
		this.costAvg = costAvg;
		this.collections = collections;
		this.comments = comments;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "shop_id", unique = true, nullable = false)
	public Integer getShopId() {
		return this.shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id")
	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "shop_name", length = 50)
	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@Column(name = "icon_url", length = 200)
	public String getIconUrl() {
		return this.iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	@Column(name = "lng", precision = 22, scale = 0)
	public Double getLng() {
		return this.lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	@Column(name = "lat", precision = 22, scale = 0)
	public Double getLat() {
		return this.lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	@Column(name = "address", length = 50)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "feature", length = 65535)
	public String getFeature() {
		return this.feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	@Column(name = "grade_avg", precision = 22, scale = 0)
	public Double getGradeAvg() {
		return this.gradeAvg;
	}

	public void setGradeAvg(Double gradeAvg) {
		this.gradeAvg = gradeAvg;
	}

	@Column(name = "cost_avg", precision = 22, scale = 0)
	public Double getCostAvg() {
		return this.costAvg;
	}

	public void setCostAvg(Double costAvg) {
		this.costAvg = costAvg;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "shop")
	public Set<Collection> getCollections() {
		return this.collections;
	}

	public void setCollections(Set<Collection> collections) {
		this.collections = collections;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "shop")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

}