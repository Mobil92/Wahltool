package de.bbqesports.wahltool.views;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Binder;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.bbqesports.wahltool.db.Abstimmung;
import de.bbqesports.wahltool.service.AbstimmungService;

@UIScope
@SpringComponent
public class AbstimmungView extends VerticalLayout {

	@Autowired
	private AbstimmungService abstimmungService;

	private Binder<Abstimmung> binder = new Binder<>(Abstimmung.class);

	private static final long serialVersionUID = 6262108962247877283L;

	private Button newAbstimmungButton = newAbstimmungButton();
	private Button saveAbstimmungButton;
	private Button deleteAbstimmungButton;
	private Label labelEditAbstimmung;
	private Grid<Abstimmung> gridAbstimmungen = new Grid<>();
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

		gridAbstimmungen.addColumn(Abstimmung::getId).setCaption("ID").setWidth(90);
		gridAbstimmungen.addColumn(Abstimmung::getAbstimmungTitel).setCaption("Titel")
				.setDescriptionGenerator(Abstimmung::getAbstimmungTitel);
		;
		gridAbstimmungen.addColumn(Abstimmung::getAbstimmungsText).setCaption("Abstimmungstext").setWidth(600)
				.setDescriptionGenerator(Abstimmung::getAbstimmungsText);

		gridAbstimmungen.setWidth("1000px");
		gridAbstimmungen.setSelectionMode(SelectionMode.SINGLE);

		gridAbstimmungen.addSelectionListener(event -> {
			Optional<Abstimmung> abstimmung = event.getFirstSelectedItem();

			if (abstimmung.isPresent()) {
				binder.setBean(abstimmung.get());
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
		layoutAbstimmungen.addComponent(createTextArea("Titel:", "abstimmungTitel", true));
		layoutAbstimmungen.addComponent(createTextArea("Beschreibung:", "abstimmungsText", true));
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

	private void saveAbstimmung(Abstimmung abstimmung) {
		if (abstimmung.isAktuell()) {
			abstimmungService.setAllInactiv();
		}
		abstimmungService.save(abstimmung);
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
			binder.setBean(new Abstimmung());
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
				Notification.show("Es gibt keine Abstimmung zum löschen!", Type.WARNING_MESSAGE);
			}
		});

		return button;

	}

	public void confirm(Abstimmung abstimmung) {
		ConfirmDialog.show(UI.getCurrent(), "Bitte bestätigen:",
				"Möchten Sie wirklich die Abstimmung " + abstimmung.getId() + " löschen?", "Ja", "Abbrechen",
				new ConfirmDialog.Listener() {

					private static final long serialVersionUID = -6961205435514925228L;

					public void onClose(ConfirmDialog dialog) {
						if (dialog.isConfirmed()) {
							deleteAbstimmung(abstimmung);
						}
					}
				});
	}

	public void showData() {
		layoutAbstimmungen.setVisible(false);
		gridAbstimmungen.setItems(abstimmungService.findAll());
	}

	private void deleteAbstimmung(Abstimmung abstimmung) {
		abstimmungService.delete(abstimmung);
		showData();
	}

}
