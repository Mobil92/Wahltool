package de.bbqesports.wahltool.views;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Binder;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.bbqesports.wahltool.db.User;
import de.bbqesports.wahltool.service.UserService;

@SpringComponent
public class BenutzerverwaltungsView extends VerticalLayout {

	@Autowired
	private UserService userService;

	private Binder<User> binder = new Binder<>(User.class);

	private static final long serialVersionUID = 6262108962247877283L;

	private Button newUserButton = newUserButton();
	private Button saveUserButton;
	private Button deleteUserButton;
	private Label labelEditUser;
	private Grid<User> gridUserRechte = new Grid<>();
	private VerticalLayout layoutEditUser;

	@PostConstruct
	public void init() {

		setSizeFull();
		setSpacing(false);
		setMargin(new MarginInfo(false, true, true, true));

		VerticalLayout layout = new VerticalLayout();
		HorizontalLayout layoutGridButtons = new HorizontalLayout();
		HorizontalLayout layoutContent = new HorizontalLayout();

		layout.setSpacing(true);

		layoutGridButtons.addComponent(newUserButton);

		layoutContent.addComponent(gridUserRechte);

		createLayoutEditUser();

		layoutContent.addComponent(layoutEditUser);

		gridUserRechte.addColumn(User::getUserName).setCaption("B-User").setWidth(95);
		gridUserRechte.addColumn(User::isRechtAdmin).setCaption("Admin");

		gridUserRechte.setWidth("700px");
		gridUserRechte.setSelectionMode(SelectionMode.SINGLE);

		gridUserRechte.addSelectionListener(event -> {
			Optional<User> userRecht = event.getFirstSelectedItem();

			if (userRecht.isPresent()) {
				binder.setBean(userRecht.get());
				labelEditUser.setValue("Bearbeiten");
				saveUserButton.setCaption("Benutzer bearbeiten!");
				layoutEditUser.setVisible(true);
				deleteUserButton.setVisible(true);
			} else {
				layoutEditUser.setVisible(false);
			}

		});

		layout.addComponent(layoutGridButtons);
		layout.addComponent(layoutContent);

		layout.setComponentAlignment(layoutGridButtons, Alignment.MIDDLE_CENTER);
		layout.setComponentAlignment(layoutContent, Alignment.MIDDLE_CENTER);

		addComponent(layout);

	}

	private void createLayoutEditUser() {
		labelEditUser = new Label();
		labelEditUser.addStyleName("h2");

		deleteUserButton = deleteUserButton();
		deleteUserButton.setStyleName("danger");

		saveUserButton = saveUserButton();
		saveUserButton.setStyleName("friendly");

		layoutEditUser = new VerticalLayout();

		layoutEditUser.addComponent(labelEditUser);
		layoutEditUser.addComponent(createTextField("User-ID:", "userName", true));
		layoutEditUser.addComponent(new Label("Berechtigung:"));
		layoutEditUser.addComponent(createCheckBox("Admin", "rechtAdmin"));
		layoutEditUser.addComponent(saveUserButton);
		layoutEditUser.addComponent(deleteUserButton);
	}

	private TextField createTextField(String caption, String propertyName, boolean required) {
		TextField textfield = new TextField(caption);

		if (required) {
			binder.forField(textfield).asRequired("Feld darf nicht leer sein!").bind(propertyName);
		} else {
			binder.forField(textfield).bind(propertyName);
		}

		return textfield;
	}

	private CheckBox createCheckBox(String caption, String propertyName) {
		CheckBox checkbox = new CheckBox(caption);
		binder.forField(checkbox).bind(propertyName);
		return checkbox;
	}

	private Button saveUserButton() {
		Button button = new Button();
		button.addClickListener(event -> {
			if (validateUserData()) {
				saveUser(binder.getBean());
			}
		});
		return button;
	}

	private void saveUser(User userRecht) {
		userService.save(userRecht);
		showData();
	}

	private boolean validateUserData() {
		return binder.validate().isOk();
	}

	private Button newUserButton() {
		Button button = new Button("Neuer Benutzer");
		button.addClickListener(event -> {
			layoutEditUser.setVisible(true);
			labelEditUser.setValue("Erstellen");
			binder.setBean(new User());
			saveUserButton.setCaption("Benuter erstellen!");
			deleteUserButton.setVisible(false);

		});

		return button;
	}

	private Button deleteUserButton() {
		Button button = new Button("Benutzer löschen!");
		button.addClickListener(event -> {
			if (binder.getBean() != null) {
				confirm(binder.getBean());
			} else {
				Notification.show("Es gibt keinen Benutzer zum löschen!");
			}
		});

		return button;

	}

	public void confirm(User userRecht) {
		ConfirmDialog.show(UI.getCurrent(), "Bitte bestätigen:",
				"Möchten Sie wirklich den Benutzer " + userRecht.getUserName() + " löschen?", "Ja", "Abbrechen",
				new ConfirmDialog.Listener() {

					private static final long serialVersionUID = -6961205435514925228L;

					public void onClose(ConfirmDialog dialog) {
						if (dialog.isConfirmed()) {
							deleteUser(userRecht);
						}
					}
				});
	}

	public void showData() {
		layoutEditUser.setVisible(false);
		gridUserRechte.setItems(userService.findAll());
	}

	private void deleteUser(User userRecht) {
		userService.delete(userRecht);
		showData();
	}

}
