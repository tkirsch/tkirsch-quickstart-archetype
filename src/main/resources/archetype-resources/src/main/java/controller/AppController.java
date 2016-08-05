#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	public String show(@PathVariable final Integer id, final Model model) {
		LOG.debug("show");
		App app = appRepository.findById(id);
		if (app == null) {
			return "redirect:/404.html";
		}
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
	public String edit(@PathVariable final Integer id, final Model model) {
		LOG.debug("edit");
		App app = appRepository.findById(id);
		if (app == null) {
			return "redirect:/404.html";
		}
		model.addAttribute("app", app);
		return "app/edit";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(final App newApp, final BindingResult result) {
		LOG.debug("save");
		if (appRepository.findByName(newApp.getName()) != null) {
			result.rejectValue("name", "app.error.alreadyexits.name");
		}
		if (result.hasErrors()) {
			return "app/new";
		}
		appRepository.save(newApp);
		return "redirect:/apps";
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public String update(@PathVariable final Integer id, final App updatedApp, final BindingResult result) {
		LOG.debug("update");
		App existingApp = appRepository.findById(id);
		if (existingApp == null) {
			return "redirect:/404.html";
		}
		App byName = appRepository.findByName(updatedApp.getName());
		if ((byName != null) && !byName.getId().equals(id)) {
			result.rejectValue("name", "app.error.alreadyexits.name");
		}
		if (result.hasErrors()) {
			return "app/edit";
		}
		existingApp.setName(updatedApp.getName());
		appRepository.save(existingApp);
		return "redirect:/apps";
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public String delete(@PathVariable final Integer id) {
		LOG.debug("delete");
		App existingApp = appRepository.findById(id);
		if (existingApp == null) {
			return "redirect:/404.html";
		}
		appRepository.remove(id);
		return "redirect:/apps";
	}
}
