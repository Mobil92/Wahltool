package de.bbqesports.wahltool.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;

import de.bbqesports.wahltool.db.Auswertung;
import de.bbqesports.wahltool.db.User;
import de.bbqesports.wahltool.service.AbstimmungService;
import de.bbqesports.wahltool.service.AuthenticationService;
import de.bbqesports.wahltool.util.ChartWindow;

@UIScope
@SpringView(name = AuswertungView.VIEW_NAME)
public class AuswertungView extends AbstractView implements View {

	private static final long serialVersionUID = 5398224081261741956L;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private AbstimmungService abstimmungService;

	@Autowired
	private ChartWindow chartWindow;

	public static final String VIEW_NAME = "auswertung";

	private Grid<Auswertung> gridAuswertung = new Grid<>();

	@Override
	protected Component createContent() {

		setSpacing(false);
		setMargin(new MarginInfo(false, true, true, true));

		VerticalLayout layout = new VerticalLayout();
		HorizontalLayout layoutContent = new HorizontalLayout();

		Label labelTitel = new Label("Auswertung");

		labelTitel.addStyleName("h2");

		layoutContent.addComponent(gridAuswertung);

		gridAuswertung.addColumn(auswertung -> "Chart", new ButtonRenderer<Auswertung>(clickEvent -> {
			chartWindow.showWindow(clickEvent.getItem());
		})).setCaption("Details").setWidth(95);

		gridAuswertung.addColumn(Auswertung::getAbstimmungId).setCaption("ID").setId("ID").setWidth(80);
		gridAuswertung.addColumn(Auswertung::getAbstimmungTitel).setCaption("Titel");
		gridAuswertung.addColumn(Auswertung::getJa).setCaption("Ja").setWidth(120);
		gridAuswertung.addColumn(Auswertung::getNein).setCaption("Nein").setWidth(120);
		gridAuswertung.addColumn(Auswertung::getEnthaltung).setCaption("Enthaltung").setWidth(120);

		gridAuswertung.setWidth("1000px");

		gridAuswertung.sort("ID", SortDirection.DESCENDING);
		gridAuswertung.setSelectionMode(SelectionMode.SINGLE);

		layout.addComponent(labelTitel);

		layout.addComponent(layoutContent);

		layout.setSpacing(true);

		layout.setComponentAlignment(labelTitel, Alignment.MIDDLE_CENTER);
		layout.setComponentAlignment(layoutContent, Alignment.MIDDLE_CENTER);

		return layout;
	}

	@Override
	public void showData() {
		gridAuswertung.setItems(abstimmungService.findAllAuswertungen());
	}

	public void enter(ViewChangeEvent event) {
		User user = authenticationService.getUser();
		if (!user.isRechtAdmin()) {
			getUI().getNavigator().navigateTo(AktuelleView.VIEW_NAME);
			Notification.show("Keine Berechtigung vorhanden!", Type.WARNING_MESSAGE);
		}
	}

}
