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

import de.bbqesports.wahltool.service.AuthenticationService;

@SpringView(name = AktuelleView.VIEW_NAME)
public class AktuelleView extends AbstractView implements View {

	private static final long serialVersionUID = -2885041735539334138L;

	public static final String VIEW_NAME = "aktuell";

	@Autowired
	private AuthenticationService authenticationService;

	private VerticalLayout layout;
	private HorizontalLayout buttonLayout = new HorizontalLayout();
	private Button buttonJa;
	private Button buttonNein;
	private Button buttonEnthaltung;

	@Override
	protected Component createContent() {

		Label labelTitel = new Label("Aktuelle Abstimmung");
		Label labelAbstimmung = new Label("Aktue...");

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

		});
		return button;
	}

	@Override
	public void showData() {
		// gridAktuelle.setItems(wartungService.findWartungenAktuellOrArchiv(true));
		//
		// System.out.println(wartungService.findWartungenAktuellOrArchiv(true));
		// System.out.println(wartungService.findWartungenAktuellOrArchiv(false));
		//
		// UserRechte userRecht = authenticationService.getUserRecht();
		// if (userRecht.isRechtAdmin() || userRecht.isRechtGenehmigen()) {
		// buttonMailToBS.setVisible(true);
		// } else {
		// buttonMailToBS.setVisible(false);
		// }
	}

	// private Button buttonMailToBS() {
	//
	// Button button = new Button("Wartungen an den IT-Service senden", event ->
	// mailService.sendMailToBS()
	//
	// );
	// return button;
	// }

	// public void enter(ViewChangeEvent event) {
	// UserRechte userRecht = authenticationService.getUserRecht();
	// if (!userRecht.isRechtLesen()) {
	// getUI().getNavigator().navigateTo(LoginView.VIEW_NAME);
	// Notification.show("Keine Berechtigung vorhanden!");
	// }
	// }
	// protected void changeSendStatus() {
	// function ist not used at the moment

	// TODO: Replace update query with Service
	// Session session = HibernateUtil.getSessionFactory().openSession();
	//
	// session.beginTransaction();
	//
	// @SuppressWarnings("rawtypes")
	// Query query = session.createQuery(
	// "UPDATE Wartung SET isSendToBS = 1 WHERE isGenehmigt = 1 AND isPlausibel = 1
	// AND isSendToBS = 0");
	//
	// query.executeUpdate();
	//
	// session.getTransaction().commit();
	//
	// session.close();

	// }

	// @SuppressWarnings({ "unchecked", "rawtypes" })
	// public AktuelleView(Data data) {
	// this.data = data;
	//
	// setSizeFull();
	// setSpacing(true);
	//
	// createMenuBar(this.data);
	//
	// Label labelTitel = new Label("Aktuelle Wartungen");
	//
	// labelTitel.addStyleName("h2");
	//
	// gridAktuelle.addColumn(Wartung -> "Details", new ButtonRenderer(clickEvent ->
	// {
	// UI.getCurrent().addWindow(subWindows(this.data, (Wartung)
	// clickEvent.getItem(), "AktuelleView"));
	//
	// })).setCaption("Details").setWidth(95);
	//
	// gridAktuelle.addColumn(Wartung::getId).setCaption("ID").setId("ID");
	// gridAktuelle.addColumn(Wartung::getDatumDerAnkuendigungDatum).setCaption("Ankündigung");
	// gridAktuelle.addColumn(Wartung::getDurchfuehrungDatumVon).setCaption("Durchführung");
	// gridAktuelle.addColumn(Wartung::getStandort).setCaption("Standort")
	// .setDescriptionGenerator(Wartung::getStandort);
	// gridAktuelle.addColumn(Wartung::getSystembezeichnung).setCaption("System")
	// .setDescriptionGenerator(Wartung::getSystembezeichnung);
	// gridAktuelle.addColumn(Wartung::getGrundHardware).setCaption("Grund-Hardware").setId("GrundHW")
	// .setDescriptionGenerator(Wartung::getGrundHardware);
	// gridAktuelle.addColumn(Wartung::getGrundSoftware).setCaption("Grund-Software").setId("GrundSW")
	// .setDescriptionGenerator(Wartung::getGrundSoftware);
	//
	// gridAktuelle.sort("ID", SortDirection.DESCENDING);
	// gridAktuelle.getColumn("GrundHW").setWidth(300);
	// gridAktuelle.getColumn("GrundSW").setWidth(300);
	//
	// gridAktuelle.setWidth("1250px");
	// gridAktuelle.setSelectionMode(SelectionMode.SINGLE);
	//
	// layout.addComponent(labelTitel);
	// layout.addComponent(gridAktuelle);
	//
	// layout.setSpacing(true);
	//
	// layout.setComponentAlignment(labelTitel, Alignment.MIDDLE_CENTER);
	// layout.setComponentAlignment(gridAktuelle, Alignment.MIDDLE_CENTER);
	//
	// addComponent(layout);
	// }

}
