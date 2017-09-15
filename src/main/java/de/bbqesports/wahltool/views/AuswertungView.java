package de.bbqesports.wahltool.views;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
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

import de.bbqesports.wahltool.db.Auswertung;
import de.bbqesports.wahltool.db.User;
import de.bbqesports.wahltool.service.AbstimmungService;
import de.bbqesports.wahltool.service.AuthenticationService;

@UIScope
@SpringView(name = AuswertungView.VIEW_NAME)
public class AuswertungView extends AbstractView implements View {

	private static final long serialVersionUID = 5398224081261741956L;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private AbstimmungService abstimmungService;

	public static final String VIEW_NAME = "auswertung";

	private Grid<Auswertung> gridAuswertung = new Grid<>();

	private Chart chart = new Chart(ChartType.COLUMN);

	private int ja;
	private int nein;
	private int enthaltung;
	private String name;

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

		gridAuswertung.addColumn(Auswertung::getAbstimmungId).setCaption("ID").setId("ID");
		gridAuswertung.addColumn(Auswertung::getJa).setCaption("Ja");
		gridAuswertung.addColumn(Auswertung::getNein).setCaption("Nein");
		gridAuswertung.addColumn(Auswertung::getEnthaltung).setCaption("Enthaltung");

		gridAuswertung.setWidth("700px");

		gridAuswertung.sort("ID", SortDirection.DESCENDING);
		gridAuswertung.setSelectionMode(SelectionMode.SINGLE);

		gridAuswertung.addSelectionListener(event -> {
			Set<Auswertung> selected = event.getAllSelectedItems();
			if (!selected.isEmpty()) {

				ja = selected.stream().findFirst().get().getJa();
				nein = selected.stream().findFirst().get().getNein();
				enthaltung = selected.stream().findFirst().get().getEnthaltung();
				name = Long.toString(selected.stream().findFirst().get().getAbstimmung().getId());
				createChart(name, ja, nein, enthaltung);
			}
		});
		layout.addComponent(labelTitel);

		layout.addComponent(layoutGridButtons);
		layout.addComponent(layoutContent);

		layout.addComponent(chart);

		layout.setSpacing(true);

		layout.setComponentAlignment(labelTitel, Alignment.MIDDLE_CENTER);
		layout.setComponentAlignment(layoutGridButtons, Alignment.MIDDLE_CENTER);
		layout.setComponentAlignment(layoutContent, Alignment.MIDDLE_CENTER);

		return layout;
	}

	private void createChart(String name, int ja, int nein, int enthaltung) {

		Configuration conf = chart.getConfiguration();

		conf.removexAxes();
		conf.removeyAxes();
		conf.removezAxes();

		ArrayList<Series> s = new ArrayList<Series>();
		chart.getConfiguration().setSeries(s);
		chart.drawChart();

		conf.setTitle("Auswertungen");

		XAxis x = new XAxis();
		x.setCategories("Ja", "Nein", "Enthaltungen");
		conf.addxAxis(x);

		YAxis y = new YAxis();
		y.setMin(0);
		y.setMinTickInterval(1);
		y.setTitle("Anzahl der Stimmen");
		conf.addyAxis(y);

		Tooltip tooltip = new Tooltip();
		tooltip.setFormatter("this.x +': '+ this.y");
		conf.setTooltip(tooltip);

		PlotOptionsColumn plot = new PlotOptionsColumn();
		plot.setPointPadding(0.2);
		plot.setBorderWidth(0);

		conf.addSeries(new ListSeries("Abstimmungs-ID: " + name, ja, nein, enthaltung));

		chart.drawChart(conf);
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
