package de.bbqesports.wahltool.views;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = AuswertungView.VIEW_NAME)
public class AuswertungView extends AbstractView implements View {

	private static final long serialVersionUID = 5398224081261741956L;

	public static final String VIEW_NAME = "auswertung";

	@Override
	protected Component createContent() {
		Label labelTitel = new Label("Auswertung");

		labelTitel.addStyleName("h2");

		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(labelTitel);

		layout.setSpacing(true);

		layout.setComponentAlignment(labelTitel, Alignment.MIDDLE_CENTER);

		return layout;
	}

	@Override
	public void showData() {
		// TODO Auto-generated method stub

	}

}
