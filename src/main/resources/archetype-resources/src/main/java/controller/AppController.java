#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ${package}.domain.App;
import ${package}.domain.AppRepository;

@Controller
@RequestMapping("/apps")
public class AppController {
	
	private static final transient Logger LOG = LoggerFactory.getLogger(AppController.class);
	@Inject
	private AppRepository appRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String index(final Model model) {
		LOG.debug("index");
		model.addAttribute("apps", appRepository.findAll());
		return "app/index";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public String show(@PathVariable final Long id, final Model model) {
		LOG.debug("show");
		App app = appRepository.findById(id);
		model.addAttribute("app", app);
		return "app/show";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/create")
	public String create(final Model model) {
		LOG.debug("create");
		App app = new App();
		app.setName("n");
		model.addAttribute("app", app);
		return "app/new";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/edit")
	public String edit(@PathVariable final Long id, final Model model) {
		LOG.debug("edit");
		App app = appRepository.findById(id);
		model.addAttribute("app", app);
		return "app/edit";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(final App app) {
		LOG.debug("save");
		appRepository.save(app);
		return "redirect:/apps";
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public String update(@PathVariable final Long id, final App updatedApp) {
		LOG.debug("update");
		App app = appRepository.findById(id);
		app.setName(updatedApp.getName());
		appRepository.save(app);
		return "redirect:/apps";
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public String delete(@PathVariable final Long id) {
		LOG.debug("delete");
		appRepository.remove(id);
		return "redirect:/apps";
	}
}
