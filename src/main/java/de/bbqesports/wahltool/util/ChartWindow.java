package de.bbqesports.wahltool.util;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import de.bbqesports.wahltool.db.Auswertung;

@UIScope
@SpringComponent
public class ChartWindow extends Window {

	private static final long serialVersionUID = 3067178605048507756L;

	private VerticalLayout mainLayout;

	private Panel panel = new Panel();

	private Chart chart = new Chart(ChartType.COLUMN);

	private int ja;
	private int nein;
	private int enthaltung;
	private String titel;

	@PostConstruct
	private void init() {

		setCaption("Chart");
		setSizeFull();

		mainLayout = new VerticalLayout(panel);
		mainLayout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
		mainLayout.setSizeFull();

		mainLayout.setExpandRatio(panel, 1f);

		this.setContent(mainLayout);

		setResizable(false);
		setModal(true);
		center();
	}

	private void createChart(String titel, int ja, int nein, int enthaltung) {
		Configuration conf = chart.getConfiguration();

		conf.removexAxes();
		conf.removeyAxes();
		conf.removezAxes();

		ArrayList<Series> s = new ArrayList<Series>();
		chart.getConfiguration().setSeries(s);
		chart.drawChart();

		conf.setTitle(titel);

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

		conf.addSeries(new ListSeries(titel, ja, nein, enthaltung));

		chart.drawChart(conf);
		chart.setHeight("800px");
		chart.setSizeFull();

		panel.setContent(chart);
		panel.setSizeFull();
		panel.setStyleName(ValoTheme.PANEL_BORDERLESS);

	}

	public void showWindow(Auswertung auswertung) {
		panel.setScrollTop(0);
		UI.getCurrent().addWindow(this);

		ja = auswertung.getJa();
		nein = auswertung.getNein();
		enthaltung = auswertung.getEnthaltung();
		titel = auswertung.getAbstimmungTitel();
		createChart(titel, ja, nein, enthaltung);
	}

}
