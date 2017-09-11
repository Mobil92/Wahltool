package de.bbqesports.wahltool.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import de.bbqesports.wahltool.db.Abstimmungen;
import de.bbqesports.wahltool.service.AbstimmungenService;

@SpringView(name = AktuelleView.VIEW_NAME)
public class AktuelleView extends AbstractView implements View {

	private static final long serialVersionUID = -2885041735539334138L;

	public static final String VIEW_NAME = "aktuell";

	@Autowired
	private AbstimmungenService AbstimmungenService;

	private Abstimmungen abstimmungen;

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

		return layout;
	}

	private Button buttonEnthaltung() {
		Button button = new Button("Enthaltung");
		button.addClickListener(event -> {

		});
		return button;
	}

	private Button buttonNein() {
		Button button = new Button("Nein");
		button.addClickListener(event -> {

		});
		return button;
	}

	private Button buttonJa() {
		Button button = new Button("Ja");
		button.addClickListener(event -> {
			AbstimmungenService.save(abstimmungen);
		});
		return button;
	}

	@Override
	public void showData() {
		labelAbstimmung.setValue(AbstimmungenService.findAktuelleAbstimmung());
	}
}
