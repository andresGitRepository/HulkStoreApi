package ar.com.todo1.entities;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

/*** @author Andres Gonzalez ***/

@SuppressWarnings("deprecation")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private Integer id;
	@NotNull
	@Column(name = "product_description")
	private String description;
	@NotNull
	@Column(name = "product_stock")
	private BigInteger stock;
	@NotNull
	@Column(name = "product_price")
	private BigInteger price;
	@NotNull
	@Wither
	@Column(name = "product_user_registry")
	private Integer userRegistry;
	@NotNull
	@Wither
	@Column(name = "product_date_registry")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateRegistry;
	@Wither
	@Column(name = "product_user_modify")
	private Integer userModify;
	@Wither
	@Column(name = "product_date_modify")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateModify;
	@Wither
	@Column(name = "product_user_unregistry")
	private Integer userUnregistry;
	@Wither
	@Column(name = "product_date_unregistry")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateUnregistry;
}
