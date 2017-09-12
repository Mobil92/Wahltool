package de.bbqesports.wahltool.views;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.bbqesports.wahltool.db.AbstimmungUser;
import de.bbqesports.wahltool.db.User;
import de.bbqesports.wahltool.service.AbstimmungUserService;
import de.bbqesports.wahltool.service.AuthenticationService;

@UIScope
@SpringView(name = AuswertungView.VIEW_NAME)
public class AuswertungView extends AbstractView implements View {

	private static final long serialVersionUID = 5398224081261741956L;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private AbstimmungUserService abstimmungUserService;

	public static final String VIEW_NAME = "auswertung";

	private Button deleteAbstimmungUserButton;
	private Label labelEditAbstimmungUser;
	private VerticalLayout layoutEditAbstimmungUser;

	private Grid<AbstimmungUser> gridAuswertung = new Grid<>();

	private Binder<AbstimmungUser> binder = new Binder<>(AbstimmungUser.class);

	@Override
	protected Component createContent() {

		setSpacing(false);
		setMargin(new MarginInfo(false, true, true, true));

		VerticalLayout layout = new VerticalLayout();
		HorizontalLayout layoutGridButtons = new HorizontalLayout();
		HorizontalLayout layoutContent = new HorizontalLayout();

		Label labelTitel = new Label("Auswertung");

		labelTitel.addStyleName("h2");

		layoutContent.addComponent(gridAuswertung);

		createLayoutEditAbstimmungUser();

		layoutContent.addComponent(layoutEditAbstimmungUser);

		gridAuswertung.addColumn(AbstimmungUser::getAbstimmungLong).setCaption("ID").setId("ID");
		gridAuswertung.addColumn(AbstimmungUser::getAbstimmungString).setCaption("Abstimmung-Text").setId("Text")
				.setDescriptionGenerator(AbstimmungUser::getAbstimmungString);

		gridAuswertung.setWidth("700px");

		gridAuswertung.sort("ID", SortDirection.DESCENDING);
		gridAuswertung.setSelectionMode(SelectionMode.SINGLE);

		gridAuswertung.addSelectionListener(event -> {
			Optional<AbstimmungUser> abstimmungUser = event.getFirstSelectedItem();

			if (abstimmungUser.isPresent()) {
				binder.setBean(abstimmungUser.get());
				labelEditAbstimmungUser.setValue("Bearbeiten");
				layoutEditAbstimmungUser.setVisible(true);
				deleteAbstimmungUserButton.setVisible(true);
			} else {
				layoutEditAbstimmungUser.setVisible(false);
			}

		});
		layout.addComponent(labelTitel);

		layout.addComponent(layoutGridButtons);
		layout.addComponent(layoutContent);

		layout.setSpacing(true);

		layout.setComponentAlignment(labelTitel, Alignment.MIDDLE_CENTER);
		layout.setComponentAlignment(layoutGridButtons, Alignment.MIDDLE_CENTER);
		layout.setComponentAlignment(layoutContent, Alignment.MIDDLE_CENTER);

		return layout;
	}

	private void createLayoutEditAbstimmungUser() {
		labelEditAbstimmungUser = new Label();
		labelEditAbstimmungUser.addStyleName("h2");

		deleteAbstimmungUserButton = deleteAbstimmungUserButton();
		deleteAbstimmungUserButton.setStyleName("danger");

		layoutEditAbstimmungUser = new VerticalLayout();

		layoutEditAbstimmungUser.addComponent(labelEditAbstimmungUser);
		layoutEditAbstimmungUser.addComponent(createTextArea("Abstimmungstext:", "abstimmungString", true));

		layoutEditAbstimmungUser.addComponent(deleteAbstimmungUserButton);
	}

	private TextArea createTextArea(String caption, String propertyName, boolean required) {
		TextArea textArea = new TextArea(caption);

		if (required) {
			binder.forField(textArea).asRequired("Feld darf nicht leer sein!").bind(propertyName);
		} else {
			binder.forField(textArea).bind(propertyName);
		}

		return textArea;
	}

	private Button deleteAbstimmungUserButton() {
		Button button = new Button("AbstimmungUser löschen!");
		button.addClickListener(event -> {
			if (binder.getBean() != null) {
				confirm(binder.getBean());
			} else {
				Notification.show("Es gibt keine AbstimmungUser zum löschen!", Type.WARNING_MESSAGE);
			}
		});

		return button;

	}

	public void confirm(AbstimmungUser abstimmungUser) {
		ConfirmDialog.show(UI.getCurrent(), "Bitte bestätigen:",
				"Möchten Sie wirklich den AbstimmungUser " + abstimmungUser.getId() + " löschen?", "Ja", "Abbrechen",
				new ConfirmDialog.Listener() {

					private static final long serialVersionUID = -6961205435514925228L;

					public void onClose(ConfirmDialog dialog) {
						if (dialog.isConfirmed()) {
							deleteAbstimmungUser(abstimmungUser);
						}
					}
				});
	}

	@Override
	public void showData() {
		layoutEditAbstimmungUser.setVisible(false);
		gridAuswertung.setItems(abstimmungUserService.findAll());

	}

	private void deleteAbstimmungUser(AbstimmungUser abstimmungUser) {
		abstimmungUserService.delete(abstimmungUser);
		showData();
	}

	public void enter(ViewChangeEvent event) {
		User user = authenticationService.getUser();
		if (!user.isRechtAdmin()) {
			getUI().getNavigator().navigateTo(AktuelleView.VIEW_NAME);
			Notification.show("Keine Berechtigung vorhanden!", Type.WARNING_MESSAGE);
		}
	}

}
