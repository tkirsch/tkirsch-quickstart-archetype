#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Named
public class AppRepository {

	private static final transient Logger LOG = LoggerFactory.getLogger(AppRepository.class);
	@PersistenceContext
	private EntityManager entityManager;

	public List<App> findAll() {
		LOG.debug("findAll");
		return entityManager.createNamedQuery(App.QUERY_NAME_FIND_ALL, App.class).getResultList();
	}

	public App findById(final Integer id) {
		LOG.debug("findById");
		return entityManager.find(App.class, id);
	}

	@Transactional
	public App save(final App story) {
		LOG.debug("save");
		return entityManager.merge(story);
	}

	@Transactional
	public void remove(final Integer id) {
		LOG.debug("remove");
		entityManager.remove(findById(id));
	}

	public App findByName(final String name) {
		LOG.debug("findByName");
		TypedQuery<App> query = entityManager.createNamedQuery(App.QUERY_NAME_FIND_BY_NAME, App.class);
		query = query.setParameter(App.QUERYPARAM_NAME, name);
		return getSingleResult(query);
	}

	private <T> T getSingleResult(final TypedQuery<T> query) {
		T result = null;
		try {
			result = query.getSingleResult();
		} catch (NoResultException e) {
			// returns null
		}
		return result;
	}
}
