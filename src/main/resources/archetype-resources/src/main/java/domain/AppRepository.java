#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

	public App findById(final Long id) {
		LOG.debug("findById");
		return entityManager.find(App.class, id);
	}
	
	@Transactional
	public App save(final App story) {
		LOG.debug("save");
		return entityManager.merge(story);
	}
	
	@Transactional
	public void remove(final Long id) {
		LOG.debug("remove");
		entityManager.remove(findById(id));
	}
}
