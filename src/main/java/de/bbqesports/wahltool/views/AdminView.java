package de.bbqesports.wahltool.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import de.bbqesports.wahltool.db.User;
import de.bbqesports.wahltool.service.AuthenticationService;

@SpringView(name = AdminView.VIEW_NAME)
public class AdminView extends AbstractView {

	private static final long serialVersionUID = 3682796284271369930L;

	public static final String VIEW_NAME = "admin";

	private Panel panelContent;

	@Autowired
	private BenutzerverwaltungsView benutzerverwaltungsView;

	@Autowired
	private AbstimmungsView abstimmungsView;

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	protected Component createContent() {

		setSpacing(true);

		Label labelTitel = new Label("Administration");
		labelTitel.addStyleName("h2");

		Label labelAufgabe = new Label("Aufgabe wählen:");
		HorizontalLayout layoutSelection = new HorizontalLayout(labelAufgabe, createComboBoxAdmin());

		panelContent = new Panel();
		panelContent.setSizeFull();

		VerticalLayout mainLayout = new VerticalLayout(labelTitel, layoutSelection, panelContent);
		mainLayout.setSpacing(true);

		mainLayout.setComponentAlignment(labelTitel, Alignment.MIDDLE_CENTER);
		mainLayout.setComponentAlignment(layoutSelection, Alignment.MIDDLE_CENTER);
		mainLayout.setComponentAlignment(panelContent, Alignment.MIDDLE_CENTER);

		return mainLayout;
	}

	@Override
	public void showData() {

	}

	public ComboBox<String> createComboBoxAdmin() {
		ComboBox<String> adminMenu = new ComboBox<>();
		adminMenu.setItems("Benutzerverwaltung", "Abstimmungen verwalten");
		adminMenu.setWidth("300px");
		adminMenu.addSelectionListener(event -> {

			if (event.getSelectedItem().get().equals("Benutzerverwaltung")) {
				panelContent.setContent(benutzerverwaltungsView);
				benutzerverwaltungsView.showData();
				return;
			} else if (event.getSelectedItem().get().equals("Abstimmungen verwalten")) {
				panelContent.setContent(abstimmungsView);
				abstimmungsView.showData();
				return;
			} else {
				return;
			}

		});
		return adminMenu;
	}

	public void enter(ViewChangeEvent event) {
		User user = authenticationService.getUser();
		if (!user.isRechtAdmin()) {
			getUI().getNavigator().navigateTo(AktuelleView.VIEW_NAME);
			Notification.show("Keine Berechtigung vorhanden!");
		}
	}

}