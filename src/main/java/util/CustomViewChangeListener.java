package util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.UI;

import service.AuthenticationService;

@Component
public class CustomViewChangeListener implements ViewChangeListener {

	private static final long serialVersionUID = 2913785199684905594L;

	@Autowired
	AuthenticationService authenticationService;

	@Override
	public boolean beforeViewChange(ViewChangeEvent event) {
		if (!authenticationService.isUserLoggedIn() && !event.getNewView().getClass().equals(LoginView.class)) {
			UI.getCurrent().getNavigator().navigateTo(LoginView.VIEW_NAME);
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void afterViewChange(ViewChangeEvent event) {
		if (event.getNewView() instanceof AbstractView) {
			((AbstractView) event.getNewView()).showData();
		}
	}
}
