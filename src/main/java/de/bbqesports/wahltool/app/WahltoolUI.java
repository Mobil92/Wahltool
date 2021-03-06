package de.bbqesports.wahltool.app;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.bbqesports.wahltool.service.AuthenticationService;
import de.bbqesports.wahltool.util.CustomViewChangeListener;
import de.bbqesports.wahltool.views.AktuelleView;
import de.bbqesports.wahltool.views.LoginView;

@Theme("mytheme")
@Title("BBQ & eSports Malsch e.V.")
@SpringUI
@UIScope
@PreserveOnRefresh
@Widgetset("AppWidgetset")
public class WahltoolUI extends UI {

	private static final long serialVersionUID = -298539345657938128L;

	@Autowired
	SpringViewProvider viewProvider;

	@Autowired
	CustomViewChangeListener listener;

	@Autowired
	AuthenticationService authenticationService;

	@Autowired
	private LoginView loginView;

	public Navigator navigator;

	private String qrcodeId;

	@Override
	protected void init(VaadinRequest request) {

		qrcodeId = request.getParameter("id");

		final VerticalLayout root = new VerticalLayout();
		root.setSizeFull();
		setContent(root);

		Panel one = new Panel();
		one.setSizeFull();

		root.addComponents(one);
		root.setExpandRatio(one, 1f);

		Navigator navigator = new Navigator(this, one);
		navigator.addProvider(viewProvider);

		navigator.addViewChangeListener(listener);
		navigator.addView("", loginView);

		if (qrcodeId != null) {
			if (authenticationService.login(qrcodeId))
				;
			getUI().getNavigator().navigateTo(AktuelleView.VIEW_NAME);
		}
	}

}
