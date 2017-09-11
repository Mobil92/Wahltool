package de.bbqesports.wahltool.views;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.bbqesports.wahltool.service.AuthenticationService;
import de.bbqesports.wahltool.service.UserService;

@SpringView(name = LoginView.VIEW_NAME)
public class LoginView extends VerticalLayout implements View {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationService authenticationService;

	private static final long serialVersionUID = 9036588612976412154L;

	public static final String VIEW_NAME = "login";

	private TextField username = new TextField("Benutzername:");

	@PostConstruct
	public void init() {

		setSizeFull();
		setSpacing(true);

		username.setIcon(VaadinIcons.USER);
		username.setMaxLength(6);

		VerticalLayout layout = new VerticalLayout();

		GridLayout grid = new GridLayout(3, 3);
		grid.setSpacing(true);
		grid.setWidth("700px");

		Label titel = new Label("BBQ & eSports Malsch e.V. - Wahltool");
		titel.addStyleName("h1");
		Label anmeldeInformationen = new Label("Bitte melden Sie sich mit ihrer ID an:");

		anmeldeInformationen.setWidth("400px");

		layout.addComponent(titel);

		grid.addComponent(anmeldeInformationen, 0, 1, 1, 2);

		grid.addComponent(username, 2, 0);

		grid.addComponent(loginButton(), 2, 2);

		layout.addComponent(grid);

		layout.setComponentAlignment(titel, Alignment.MIDDLE_CENTER);
		layout.setComponentAlignment(grid, Alignment.MIDDLE_CENTER);

		addComponent(layout);

	}

	@Override
	public void enter(ViewChangeEvent event) {
		username.focus();

	}

	private Button loginButton() {
		Button button = new Button("Anmelden", new Button.ClickListener() {

			private static final long serialVersionUID = 5424841269717052842L;

			@Override
			public void buttonClick(ClickEvent event) {
				checkLogin();

			}

		});
		return button;

	}

	private void checkLogin() {
		if (username.isEmpty()) {
			Notification.show("Bitte geben Sie ihre ID ein!");
		} else {

			if (authenticationService.login(username.getValue())) {

				getUI().getNavigator().navigateTo(AktuelleView.VIEW_NAME);

			} else {
				Notification.show("Falsche ID");
			}

		}
	}

}