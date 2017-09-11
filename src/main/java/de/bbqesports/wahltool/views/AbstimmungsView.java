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
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.bbqesports.wahltool.db.Abstimmungen;
import de.bbqesports.wahltool.service.AbstimmungenService;

@SpringComponent
public class AbstimmungsView extends VerticalLayout {

	@Autowired
	private AbstimmungenService abstimmungenService;

	private Binder<Abstimmungen> binder = new Binder<>(Abstimmungen.class);

	private static final long serialVersionUID = 6262108962247877283L;

	private Button newAbstimmungButton = newAbstimmungButton();
	private Button saveAbstimmungButton;
	private Button deleteAbstimmungButton;
	private Label labelEditAbstimmung;
	private Grid<Abstimmungen> gridAbstimmungen = new Grid<>();
	private VerticalLayout layoutAbstimmungen;

	@PostConstruct
	public void init() {

		setSizeFull();
		setSpacing(false);
		setMargin(new MarginInfo(false, true, true, true));

		VerticalLayout layout = new VerticalLayout();
		HorizontalLayout layoutGridButtons = new HorizontalLayout();
		HorizontalLayout layoutContent = new HorizontalLayout();

		layout.setSpacing(true);

		layoutGridButtons.addComponent(newAbstimmungButton);

		layoutContent.addComponent(gridAbstimmungen);

		createLayoutEditUser();

		layoutContent.addComponent(layoutAbstimmungen);

		gridAbstimmungen.addColumn(Abstimmungen::getId).setCaption("ID").setWidth(90);
		gridAbstimmungen.addColumn(Abstimmungen::getAbstimmungsText).setCaption("Abstimmungstext");

		gridAbstimmungen.setWidth("700px");
		gridAbstimmungen.setSelectionMode(SelectionMode.SINGLE);

		gridAbstimmungen.addSelectionListener(event -> {
			Optional<Abstimmungen> abstimmungen = event.getFirstSelectedItem();

			if (abstimmungen.isPresent()) {
				binder.setBean(abstimmungen.get());
				labelEditAbstimmung.setValue("Bearbeiten");
				saveAbstimmungButton.setCaption("Abstimmung bearbeiten!");
				layoutAbstimmungen.setVisible(true);
				deleteAbstimmungButton.setVisible(true);
			} else {
				layoutAbstimmungen.setVisible(false);
			}

		});

		layout.addComponent(layoutGridButtons);
		layout.addComponent(layoutContent);

		layout.setComponentAlignment(layoutGridButtons, Alignment.MIDDLE_CENTER);
		layout.setComponentAlignment(layoutContent, Alignment.MIDDLE_CENTER);

		addComponent(layout);

	}

	private void createLayoutEditUser() {
		labelEditAbstimmung = new Label();
		labelEditAbstimmung.addStyleName("h2");

		deleteAbstimmungButton = deleteAbstimmungButton();
		deleteAbstimmungButton.setStyleName("danger");

		saveAbstimmungButton = saveAbstimmungButton();
		saveAbstimmungButton.setStyleName("friendly");

		layoutAbstimmungen = new VerticalLayout();

		layoutAbstimmungen.addComponent(labelEditAbstimmung);
		layoutAbstimmungen.addComponent(createTextArea("Abstimmungstext", "abstimmungsText", true));
		layoutAbstimmungen.addComponent(createCheckBox("Aktiv", "aktuell"));
		layoutAbstimmungen.addComponent(saveAbstimmungButton);
		layoutAbstimmungen.addComponent(deleteAbstimmungButton);
	}

	private CheckBox createCheckBox(String caption, String propertyName) {
		CheckBox checkbox = new CheckBox(caption);
		binder.forField(checkbox).bind(propertyName);
		return checkbox;
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

	private Button saveAbstimmungButton() {
		Button button = new Button();
		button.addClickListener(event -> {
			if (validateUserData()) {
				saveAbstimmung(binder.getBean());
			}
		});
		return button;
	}

	private void saveAbstimmung(Abstimmungen abstimmungen) {
		if (abstimmungen.isAktuell()) {
			abstimmungenService.setAllInactiv();
		}
		abstimmungenService.save(abstimmungen);
		showData();
	}

	private boolean validateUserData() {
		return binder.validate().isOk();
	}

	private Button newAbstimmungButton() {
		Button button = new Button("Neue Abstimmung");
		button.addClickListener(event -> {
			layoutAbstimmungen.setVisible(true);
			labelEditAbstimmung.setValue("Erstellen");
			binder.setBean(new Abstimmungen());
			saveAbstimmungButton.setCaption("Abstimmung erstellen!");
			deleteAbstimmungButton.setVisible(false);

		});

		return button;
	}

	private Button deleteAbstimmungButton() {
		Button button = new Button("Abstimmung löschen!");
		button.addClickListener(event -> {
			if (binder.getBean() != null) {
				confirm(binder.getBean());
			} else {
				Notification.show("Es gibt keine Abstimmung zum löschen!");
			}
		});

		return button;

	}

	public void confirm(Abstimmungen abstimmungen) {
		ConfirmDialog.show(UI.getCurrent(), "Bitte bestätigen:",
				"Möchten Sie wirklich die Abstimmung " + abstimmungen.getId() + " löschen?", "Ja", "Abbrechen",
				new ConfirmDialog.Listener() {

					private static final long serialVersionUID = -6961205435514925228L;

					public void onClose(ConfirmDialog dialog) {
						if (dialog.isConfirmed()) {
							deleteAbstimmung(abstimmungen);
						}
					}
				});
	}

	public void showData() {
		layoutAbstimmungen.setVisible(false);
		gridAbstimmungen.setItems(abstimmungenService.findAll());
	}

	private void deleteAbstimmung(Abstimmungen abstimmungen) {
		abstimmungenService.delete(abstimmungen);
		showData();
	}

}
