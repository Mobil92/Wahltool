package de.bbqesports.wahltool.views;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import de.bbqesports.wahltool.service.AuthenticationService;

@SpringComponent
public abstract class AbstractView extends VerticalLayout implements View {

	@Autowired
	private AuthenticationService authenticationService;

	private Panel content;

	private static final long serialVersionUID = 955878707721348788L;

	@PostConstruct
	private void init() {
		final CssLayout navigationBar = new CssLayout();
		navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

		navigationBar.addComponent(createNavigationButton("Aktuelle Abstimmung", AktuelleView.VIEW_NAME));
		navigationBar.addComponent(createNavigationButton("Auswertung", AuswertungView.VIEW_NAME));
		navigationBar.addComponent(createNavigationButton("Administration", AdminView.VIEW_NAME));
		navigationBar.addComponent(createLogoutButton());

		content = new Panel();
		content.setSizeFull();

		addComponents(navigationBar, content);

		setComponentAlignment(navigationBar, Alignment.MIDDLE_CENTER);
		setComponentAlignment(content, Alignment.MIDDLE_CENTER);

		content.setContent(createContent());
	}

	abstract protected Component createContent();

	abstract public void showData();

	private Button createLogoutButton() {
		Button button = new Button("Abmelden");
		button.addStyleName(ValoTheme.BUTTON_LARGE);
		button.addClickListener(event -> {
			authenticationService.logout();
		});
		return button;
	}

	private Button createNavigationButton(String caption, final String viewName) {
		Button button = new Button(caption);
		button.addStyleName(ValoTheme.BUTTON_LARGE);
		button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
		return button;
	}

}
