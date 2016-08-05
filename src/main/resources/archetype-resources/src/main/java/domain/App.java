#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "APP")
@NamedQueries({ @NamedQuery(name = App.QUERY_NAME_FIND_ALL, query = "select e from App e"),
		@NamedQuery(name = App.QUERY_NAME_FIND_BY_NAME, query = "select e from App e where e.name = :" + App.QUERYPARAM_NAME) })
public class App implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String QUERY_NAME_FIND_ALL = "App.findAll";
	public static final String QUERY_NAME_FIND_BY_NAME = "App.findByName";
	public static final String QUERYPARAM_NAME = "name";
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "NAME", length = 50, nullable = false, unique = true)
	private String name;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}
}
