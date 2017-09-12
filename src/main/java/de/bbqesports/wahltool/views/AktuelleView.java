package de.bbqesports.wahltool.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.server.Responsive;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import de.bbqesports.wahltool.db.AbstimmungUser;
import de.bbqesports.wahltool.db.Stimme;
import de.bbqesports.wahltool.db.User;
import de.bbqesports.wahltool.service.AbstimmungService;
import de.bbqesports.wahltool.service.AbstimmungUserService;
import de.bbqesports.wahltool.service.AuthenticationService;

@SpringView(name = AktuelleView.VIEW_NAME)
public class AktuelleView extends AbstractView implements View {

	private static final long serialVersionUID = -2885041735539334138L;

	public static final String VIEW_NAME = "aktuell";

	@Autowired
	private AbstimmungUserService abstimmungUserService;

	@Autowired
	private AbstimmungService abstimmungService;

	@Autowired
	private AuthenticationService authenticationService;

	private AbstimmungUser abstimmungUser;

	private VerticalLayout layout;
	private HorizontalLayout buttonLayout = new HorizontalLayout();
	private Button buttonJa;
	private Button buttonNein;
	private Button buttonEnthaltung;
	private Label labelAbstimmung = new Label();

	@Override
	protected Component createContent() {

		Label labelTitel = new Label("Aktuelle Abstimmung");

		labelTitel.addStyleName("h2");

		buttonJa = buttonJa();
		buttonNein = buttonNein();
		buttonEnthaltung = buttonEnthaltung();

		layout = new VerticalLayout();
		layout.addComponent(labelTitel);
		layout.addComponent(labelAbstimmung);
		buttonLayout.addComponent(buttonJa);
		buttonLayout.addComponent(buttonNein);
		buttonLayout.addComponent(buttonEnthaltung);
		layout.addComponent(buttonLayout);

		layout.setSpacing(true);

		layout.setComponentAlignment(labelTitel, Alignment.MIDDLE_CENTER);
		layout.setComponentAlignment(labelAbstimmung, Alignment.MIDDLE_CENTER);
		layout.setComponentAlignment(buttonLayout, Alignment.MIDDLE_CENTER);
		buttonLayout.setComponentAlignment(buttonJa, Alignment.MIDDLE_CENTER);
		buttonLayout.setComponentAlignment(buttonNein, Alignment.MIDDLE_CENTER);
		buttonLayout.setComponentAlignment(buttonEnthaltung, Alignment.MIDDLE_CENTER);

		Responsive.makeResponsive(layout);

		return layout;
	}

	private Button buttonEnthaltung() {
		Button button = new Button("Enthaltung");
		button.addClickListener(event -> {
			save(Stimme.ENTHALTUNG);
		});
		return button;
	}

	private Button buttonNein() {
		Button button = new Button("Nein");
		button.addClickListener(event -> {
			save(Stimme.NEIN);
		});
		return button;
	}

	private Button buttonJa() {
		Button button = new Button("Ja");
		button.addClickListener(event -> {
			save(Stimme.JA);
		});
		return button;
	}

	private void save(Stimme stimme) {
		User user = authenticationService.getUser();
		abstimmungUser = new AbstimmungUser();

		abstimmungUser.setAbstimmungen(abstimmungService.findAktuelleAbstimmung());
		abstimmungUser.setUser(user);
		abstimmungUser.setStimme(stimme);
		abstimmungUserService.save(abstimmungUser);
	}

	@Override
	public void showData() {
		labelAbstimmung.setValue(abstimmungService.findAktuelleAbstimmungString());
	}
}
