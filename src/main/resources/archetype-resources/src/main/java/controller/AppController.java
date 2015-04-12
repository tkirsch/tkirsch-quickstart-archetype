#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ${package}.domain.AppRepository;

@Controller
@RequestMapping("/app")
public class AppController {
	
	private static final transient Logger LOG = LoggerFactory.getLogger(AppController.class);
	@Inject
	private AppRepository appRepository;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		LOG.debug("index");
		ModelAndView modelAndView = new ModelAndView("app/index");
		modelAndView.addObject("apps", appRepository.findAll());
		return modelAndView;
	}
}
