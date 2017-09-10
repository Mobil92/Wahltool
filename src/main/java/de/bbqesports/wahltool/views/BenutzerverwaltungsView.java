// package de.bbqesports.wahltool.views;
//
// import java.util.Iterator;
// import java.util.Optional;
// import java.util.Set;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.vaadin.dialogs.ConfirmDialog;
//
// import com.vaadin.shared.ui.MarginInfo;
// import com.vaadin.spring.annotation.SpringView;
// import com.vaadin.ui.Alignment;
// import com.vaadin.ui.Button;
// import com.vaadin.ui.Button.ClickEvent;
// import com.vaadin.ui.CheckBox;
// import com.vaadin.ui.Component;
// import com.vaadin.ui.Grid;
// import com.vaadin.ui.Grid.SelectionMode;
// import com.vaadin.ui.HorizontalLayout;
// import com.vaadin.ui.Label;
// import com.vaadin.ui.Notification;
// import com.vaadin.ui.TextField;
// import com.vaadin.ui.UI;
// import com.vaadin.ui.VerticalLayout;
//
// import de.bbqesports.wahltool.db.UserRechte;
// import de.bbqesports.wahltool.service.UserRechteService;
//
// @SpringView(name = BenutzerverwaltungsView.VIEW_NAME)
// public class BenutzerverwaltungsView extends AbstractView {
//
// // TODO: - Verwendung der jeweiligen Klassen (UserRechte, usw...) statt
// // Data-Klasse
// // TODO: - Databinding an Vaadin-Fields Benutzen
//
// @Autowired
// private UserRechteService userRechteService;
//
// private static final long serialVersionUID = 6262108962247877283L;
//
// public static final String VIEW_NAME = "benutzerverwaltung";
//
// private CheckBox checkboxLesenCreate = new CheckBox("Lesen");
// private CheckBox checkboxSchreibenCreate = new CheckBox("Schreiben");
// private CheckBox checkboxPlausibilitätCreate = new CheckBox("Plausibilität");
// private CheckBox checkboxGenehmigenCreate = new CheckBox("Genehmigen");
// private CheckBox checkboxAdminCreate = new CheckBox("Admin");
// private CheckBox checkboxLesenChange = new CheckBox("Lesen");
// private CheckBox checkboxSchreibenChange = new CheckBox("Schreiben");
// private CheckBox checkboxPlausibilitätChange = new CheckBox("Plausibilität");
// private CheckBox checkboxGenehmigenChange = new CheckBox("Genehmigen");
// private CheckBox checkboxAdminChange = new CheckBox("Admin");
//
// private TextField TextFieldCreateBuser = new TextField("B-User:");
// private TextField TextFieldCreateName = new TextField("Name:");
// private TextField TextFieldCreateVorName = new TextField("Vorname:");
// private TextField TextFieldCreateEmail = new TextField("E-Mail:");
// private TextField TextFieldChangeBuser = new TextField("B-User:");
// private TextField TextFieldChangeName = new TextField("Name:");
// private TextField TextFieldChangeVorName = new TextField("Vorname:");
// private TextField TextFieldChangeEmail = new TextField("E-Mail:");
// private Data data;
// private Data dataAdmin = new Data();
// private Button changeButton = changeButton();
// private Button changeUserButton = changeUserButton();
// private Button newUserButton = newUserButton();
// private Button createUserButton = createUserButton();
// private Button deleteUserButton = deleteUserButton();
// private Label labelAktuellBearbeiten = new Label("Bearbeiten");
// private Label labelAktuellErstellen = new Label("Erstellen");
// private Label labelBerechechtigungCreate = new Label("Berechtigung:");
// private Label labelBerechechtigungChange = new Label("Berechtigung:");
// private Grid<UserRechte> grid = new Grid<>();
// // private ComboBox<String> comboBoxAdmin = createComboBoxAdmin();
//
// // public BenutzerverwaltungsView(Data data) {
// public BenutzerverwaltungsView() {
// // this.data = data;
//
// setSizeFull();
// setSpacing(false);
// setMargin(new MarginInfo(false, true, true, true));
//
// labelAktuellBearbeiten.addStyleName("h2");
// labelAktuellErstellen.addStyleName("h2");
//
// deleteUserButton.setStyleName("danger");
// changeUserButton.setStyleName("friendly");
// createUserButton.setStyleName("friendly");
//
// VerticalLayout layout = new VerticalLayout();
// HorizontalLayout horizontalLayout = new HorizontalLayout();
// HorizontalLayout horizontalLayout2 = new HorizontalLayout();
// VerticalLayout verticalLayout2 = new VerticalLayout();
//
// layout.setSpacing(true);
//
// horizontalLayout.addComponent(changeButton);
// horizontalLayout.addComponent(newUserButton);
//
// horizontalLayout2.addComponent(grid);
// verticalLayout2.addComponent(labelAktuellBearbeiten);
// verticalLayout2.addComponent(labelAktuellErstellen);
// verticalLayout2.addComponent(TextFieldCreateBuser);
// verticalLayout2.addComponent(TextFieldCreateName);
// verticalLayout2.addComponent(TextFieldCreateVorName);
// verticalLayout2.addComponent(TextFieldCreateEmail);
// verticalLayout2.addComponent(labelBerechechtigungCreate);
// verticalLayout2.addComponent(checkboxLesenCreate);
// verticalLayout2.addComponent(checkboxSchreibenCreate);
// verticalLayout2.addComponent(checkboxPlausibilitätCreate);
// verticalLayout2.addComponent(checkboxGenehmigenCreate);
// verticalLayout2.addComponent(checkboxAdminCreate);
// verticalLayout2.addComponent(createUserButton);
//
// verticalLayout2.addComponent(TextFieldChangeBuser);
// verticalLayout2.addComponent(TextFieldChangeName);
// verticalLayout2.addComponent(TextFieldChangeVorName);
// verticalLayout2.addComponent(TextFieldChangeEmail);
// verticalLayout2.addComponent(labelBerechechtigungChange);
// verticalLayout2.addComponent(checkboxLesenChange);
// verticalLayout2.addComponent(checkboxSchreibenChange);
// verticalLayout2.addComponent(checkboxPlausibilitätChange);
// verticalLayout2.addComponent(checkboxGenehmigenChange);
// verticalLayout2.addComponent(checkboxAdminChange);
// verticalLayout2.addComponent(changeUserButton);
// verticalLayout2.addComponent(deleteUserButton);
//
// horizontalLayout2.addComponent(verticalLayout2);
//
// grid.addColumn(UserRechte::getBBenutzer).setCaption("B-User").setWidth(95);
// grid.addColumn(UserRechte::getName).setCaption("Name");
// grid.addColumn(UserRechte::getVorName).setCaption("Vorname");
//
// grid.setWidth("700px");
// grid.setSelectionMode(SelectionMode.SINGLE);
//
// grid.addSelectionListener(events -> {
// Set<UserRechte> selected = events.getAllSelectedItems();
// for (Iterator<UserRechte> it = selected.iterator(); it.hasNext();) {
// UserRechte s = it.next();
// System.out.println(s.getBBenutzer());
// TextFieldChangeBuser.setValue(s.getBBenutzer());
// TextFieldChangeName.setValue(s.getName());
// TextFieldChangeVorName.setValue(s.getVorName());
// TextFieldChangeEmail.setValue(s.getEmail());
// checkboxLesenChange.setValue(s.isRechtLesen());
// checkboxSchreibenChange.setValue(s.isRechtSchreiben());
// checkboxPlausibilitätChange.setValue(s.isRechtPlausibilitaet());
// checkboxGenehmigenChange.setValue(s.isRechtGenehmigen());
// checkboxAdminChange.setValue(s.isRechtAdmin());
//
// setCreateToFalseAndChangeToTrue();
//
// }
// });
//
// layout.addComponent(horizontalLayout);
// layout.addComponent(horizontalLayout2);
//
// layout.setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);
// layout.setComponentAlignment(horizontalLayout2, Alignment.MIDDLE_CENTER);
//
// addComponent(layout);
//
// }
//
// private Button changeUserButton() {
// Button button = new Button("Benutzer bearbeiten!", new Button.ClickListener()
// {
//
// private static final long serialVersionUID = -5459757686231918786L;
//
// @Override
// public void buttonClick(ClickEvent event) {
//
// changeUser();
//
// }
// });
// return button;
// }
//
// private Button changeButton() {
// Button button = new Button("Bearbeiten", new Button.ClickListener() {
//
// private static final long serialVersionUID = -1591445715659436414L;
//
// @Override
// public void buttonClick(ClickEvent event) {
//
// setCreateToFalseAndChangeToTrue();
//
// }
// });
// return button;
// }
//
// private Button newUserButton() {
// Button button = new Button("Neuer Benutzer", new Button.ClickListener() {
//
// private static final long serialVersionUID = -6134011511364615238L;
//
// @Override
// public void buttonClick(ClickEvent event) {
// TextFieldChangeBuser.setVisible(false);
// TextFieldChangeName.setVisible(false);
// TextFieldChangeVorName.setVisible(false);
// TextFieldChangeEmail.setVisible(false);
// changeUserButton.setVisible(false);
// deleteUserButton.setVisible(false);
// labelAktuellBearbeiten.setVisible(false);
// checkboxLesenChange.setVisible(false);
// checkboxSchreibenChange.setVisible(false);
// checkboxPlausibilitätChange.setVisible(false);
// checkboxGenehmigenChange.setVisible(false);
// checkboxAdminChange.setVisible(false);
// labelBerechechtigungChange.setVisible(false);
//
// TextFieldCreateBuser.setVisible(true);
// TextFieldCreateName.setVisible(true);
// TextFieldCreateVorName.setVisible(true);
// TextFieldCreateEmail.setVisible(true);
// createUserButton.setVisible(true);
// labelAktuellErstellen.setVisible(true);
// checkboxLesenCreate.setVisible(true);
// checkboxSchreibenCreate.setVisible(true);
// checkboxPlausibilitätCreate.setVisible(true);
// checkboxGenehmigenCreate.setVisible(true);
// checkboxAdminCreate.setVisible(true);
// labelBerechechtigungCreate.setVisible(true);
// checkboxLesenCreate.setValue(true);
// checkboxLesenCreate.setReadOnly(true);
//
// }
// });
// return button;
// }
//
// private Button createUserButton() {
// Button button = new Button("Benutzer erstellen!", new Button.ClickListener()
// {
//
// private static final long serialVersionUID = 8648121619774355925L;
//
// @Override
// public void buttonClick(ClickEvent event) {
// createUser();
//
// }
// });
// return button;
//
// }
//
// private Button deleteUserButton() {
// Button button = new Button("Benutzer löschen!", new Button.ClickListener() {
//
// private static final long serialVersionUID = -3874493178930661046L;
//
// @Override
// public void buttonClick(ClickEvent event) {
//
// confirm();
//
// }
//
// });
// return button;
//
// }
//
// public void confirm() {
// ConfirmDialog.show(UI.getCurrent(), "Bitte bestätigen:",
// "Möchten Sie wirklich den Benutzer " + dataAdmin.getbBenutzerNewUser() + "
// löschen?", "Ja", "Abbrechen",
// new ConfirmDialog.Listener() {
//
// private static final long serialVersionUID = -6961205435514925228L;
//
// public void onClose(ConfirmDialog dialog) {
// if (dialog.isConfirmed()) {
// deleteUser();
// }
// }
// });
// }
//
// // @Override
// // public void enter(ViewChangeEvent event) {
// // String username = String.valueOf(getSession().getAttribute("user"));
// // if (!data.isRechtAdmin()) {
// // getUI().getNavigator().navigateTo(WtUI.MAINVIEW);
// // Notification.show("Keine Berechtigung vorhanden!");
// // } else if (username.equals("null")) {
// // getUI().getNavigator().navigateTo(WtUI.LOGINVIEW);
// // } else {
// //
// // TextFieldCreateBuser.setVisible(false);
// // TextFieldCreateName.setVisible(false);
// // TextFieldCreateVorName.setVisible(false);
// // TextFieldCreateEmail.setVisible(false);
// // createUserButton.setVisible(false);
// // labelAktuellErstellen.setVisible(false);
// // checkboxLesenCreate.setVisible(false);
// // checkboxSchreibenCreate.setVisible(false);
// // checkboxPlausibilitätCreate.setVisible(false);
// // checkboxGenehmigenCreate.setVisible(false);
// // checkboxAdminCreate.setVisible(false);
// // labelBerechechtigungCreate.setVisible(false);
// //
// // TextFieldChangeBuser.setVisible(false);
// // TextFieldChangeName.setVisible(false);
// // TextFieldChangeVorName.setVisible(false);
// // TextFieldChangeEmail.setVisible(false);
// // changeUserButton.setVisible(false);
// // deleteUserButton.setVisible(false);
// // labelAktuellBearbeiten.setVisible(false);
// // checkboxLesenChange.setVisible(false);
// // checkboxSchreibenChange.setVisible(false);
// // checkboxPlausibilitätChange.setVisible(false);
// // checkboxGenehmigenChange.setVisible(false);
// // checkboxAdminChange.setVisible(false);
// // labelBerechechtigungChange.setVisible(false);
// // grid.setItems(data.getUserRechtes());
// // }
// // }
//
// public void showData() {
// // String username = String.valueOf(getSession().getAttribute("user"));
// // if (!data.isRechtAdmin()) {
// // getUI().getNavigator().navigateTo(WtUI.MAINVIEW);
// // Notification.show("Keine Berechtigung vorhanden!");
// // } else if (username.equals("null")) {
// // getUI().getNavigator().navigateTo(WtUI.LOGINVIEW);
// // } else {
//
// TextFieldCreateBuser.setVisible(false);
// TextFieldCreateName.setVisible(false);
// TextFieldCreateVorName.setVisible(false);
// TextFieldCreateEmail.setVisible(false);
// createUserButton.setVisible(false);
// labelAktuellErstellen.setVisible(false);
// checkboxLesenCreate.setVisible(false);
// checkboxSchreibenCreate.setVisible(false);
// checkboxPlausibilitätCreate.setVisible(false);
// checkboxGenehmigenCreate.setVisible(false);
// checkboxAdminCreate.setVisible(false);
// labelBerechechtigungCreate.setVisible(false);
//
// TextFieldChangeBuser.setVisible(false);
// TextFieldChangeName.setVisible(false);
// TextFieldChangeVorName.setVisible(false);
// TextFieldChangeEmail.setVisible(false);
// changeUserButton.setVisible(false);
// deleteUserButton.setVisible(false);
// labelAktuellBearbeiten.setVisible(false);
// checkboxLesenChange.setVisible(false);
// checkboxSchreibenChange.setVisible(false);
// checkboxPlausibilitätChange.setVisible(false);
// checkboxGenehmigenChange.setVisible(false);
// checkboxAdminChange.setVisible(false);
// labelBerechechtigungChange.setVisible(false);
// // grid.setItems(data.getUserRechtes());
// grid.setItems(userRechteService.findAll());
// // }
// }
//
// private void createUser() {
//
// dataAdmin.setbBenutzerNewUser(TextFieldCreateBuser.getValue());
// dataAdmin.setName(TextFieldCreateName.getValue());
// dataAdmin.setVorName(TextFieldCreateVorName.getValue());
// dataAdmin.setEmail(TextFieldCreateEmail.getValue());
// dataAdmin.setRechtLesen(checkboxLesenCreate.getValue());
// dataAdmin.setRechtSchreiben(checkboxSchreibenCreate.getValue());
// dataAdmin.setRechtPlausibilität(checkboxPlausibilitätCreate.getValue());
// dataAdmin.setRechtGenehmigen(checkboxGenehmigenCreate.getValue());
// dataAdmin.setRechtAdmin(checkboxAdminCreate.getValue());
//
// // Session session = HibernateUtil.getSessionFactory().openSession();
// //
// // session.beginTransaction();
//
// UserRechte userRechte = new UserRechte(dataAdmin.getbBenutzerNewUser(),
// dataAdmin.getName(),
// dataAdmin.getVorName(), dataAdmin.getEmail(), dataAdmin.isRechtLesen(),
// dataAdmin.isRechtSchreiben(),
// dataAdmin.isRechtPlausibilität(), dataAdmin.isRechtGenehmigen(),
// dataAdmin.isRechtAdmin());
//
// userRechteService.save(userRechte);
//
// // session.saveOrUpdate(userRechte);
// //
// // session.getTransaction().commit();
//
// getUserData();
//
// grid.setItems(data.getUserRechtes());
// }
//
// private void deleteUser() {
//
// dataAdmin.setbBenutzerNewUser(TextFieldChangeBuser.getValue());
// dataAdmin.setName(TextFieldChangeName.getValue());
// dataAdmin.setVorName(TextFieldChangeVorName.getValue());
// dataAdmin.setEmail(TextFieldChangeEmail.getValue());
// dataAdmin.setRechtLesen(checkboxLesenChange.getValue());
// dataAdmin.setRechtSchreiben(checkboxSchreibenChange.getValue());
// dataAdmin.setRechtPlausibilität(checkboxPlausibilitätChange.getValue());
// dataAdmin.setRechtGenehmigen(checkboxGenehmigenChange.getValue());
// dataAdmin.setRechtAdmin(checkboxAdminChange.getValue());
//
// Optional<UserRechte> userRechte =
// userRechteService.findByBBenutzer(dataAdmin.getbBenutzerNewUser());
//
// userRechte.ifPresent(ur -> userRechteService.delete(ur));
//
// // Session session = HibernateUtil.getSessionFactory().openSession();
// //
// // session.beginTransaction();
// //
// // UserRechte userRechte = new UserRechte(dataAdmin.getbBenutzerNewUser(),
// // dataAdmin.getName(),
// // dataAdmin.getVorName(), dataAdmin.getEmail(), dataAdmin.isRechtLesen(),
// // dataAdmin.isRechtSchreiben(),
// // dataAdmin.isRechtPlausibilität(), dataAdmin.isRechtGenehmigen(),
// // dataAdmin.isRechtAdmin());
// //
// // session.delete(userRechte);
// //
// // session.getTransaction().commit();
//
// getUserData();
//
// grid.setItems(data.getUserRechtes());
//
// }
//
// public void getUserData() {
//
// data.setUserRechtes(userRechteService.findAll());
//
// // List<UserRechte> userRechtes = new ArrayList<UserRechte>();
// //
// // Session session = HibernateUtil.getSessionFactory().openSession();
// // Transaction tx = null;
// // try {
// // tx = session.beginTransaction();
// // @SuppressWarnings("rawtypes")
// // List userRechten = session.createQuery("FROM UserRechte").list();
// // for (@SuppressWarnings("rawtypes")
// // Iterator iterator = userRechten.iterator(); iterator.hasNext();) {
// // UserRechte userRechte = (UserRechte) iterator.next();
// //
// // userRechtes.add(new UserRechte(userRechte.getBBenutzer(),
// // userRechte.getName(), userRechte.getVorName(),
// // userRechte.getEmail(), userRechte.isRechtLesen(),
// // userRechte.isRechtSchreiben(),
// // userRechte.isRechtPlausibilitaet(), userRechte.isRechtGenehmigen(),
// // userRechte.isRechtAdmin()));
// //
// // }
// // tx.commit();
// // } catch (HibernateException e) {
// // if (tx != null)
// // tx.rollback();
// // e.printStackTrace();
// // } finally {
// // data.setUserRechtes(userRechtes);
// // session.close();
// // }
// }
//
// private void changeUser() {
// dataAdmin.setbBenutzerNewUser(TextFieldChangeBuser.getValue());
// dataAdmin.setName(TextFieldChangeName.getValue());
// dataAdmin.setVorName(TextFieldChangeVorName.getValue());
// dataAdmin.setEmail(TextFieldChangeEmail.getValue());
// dataAdmin.setRechtLesen(checkboxLesenChange.getValue());
// dataAdmin.setRechtSchreiben(checkboxSchreibenChange.getValue());
// dataAdmin.setRechtPlausibilität(checkboxPlausibilitätChange.getValue());
// dataAdmin.setRechtGenehmigen(checkboxGenehmigenChange.getValue());
// dataAdmin.setRechtAdmin(checkboxAdminChange.getValue());
//
// Optional<UserRechte> userRechte =
// userRechteService.findByBBenutzer(dataAdmin.getbBenutzerNewUser());
//
// userRechte.ifPresent(ur -> {
// ur.setBBenutzer(dataAdmin.getbBenutzerNewUser());
// ur.setName(dataAdmin.getName());
// ur.setVorName(dataAdmin.getVorName());
// ur.setEmail(dataAdmin.getEmail());
// ur.setRechtLesen(dataAdmin.isRechtLesen());
// ur.setRechtSchreiben(dataAdmin.isRechtSchreiben());
// ur.setRechtPlausibilitaet(dataAdmin.isRechtPlausibilität());
// ur.setRechtGenehmigen(dataAdmin.isRechtGenehmigen());
// ur.setRechtAdmin(dataAdmin.isRechtAdmin());
//
// userRechteService.save(ur);
// });
//
// // Session session = HibernateUtil.getSessionFactory().openSession();
// //
// // session.beginTransaction();
// //
// // UserRechte userRechte = new UserRechte(dataAdmin.getbBenutzerNewUser(),
// // dataAdmin.getName(),
// // dataAdmin.getVorName(), dataAdmin.getEmail(), dataAdmin.isRechtLesen(),
// // dataAdmin.isRechtSchreiben(),
// // dataAdmin.isRechtPlausibilität(), dataAdmin.isRechtGenehmigen(),
// // dataAdmin.isRechtAdmin());
// //
// // session.update(userRechte);
// //
// // session.getTransaction().commit();
//
// getUserData();
//
// grid.setItems(data.getUserRechtes());
//
// Notification.show("Benutzer wurde bearbeitet");
// }
//
// private void setCreateToFalseAndChangeToTrue() {
// TextFieldCreateBuser.setVisible(false);
// TextFieldCreateName.setVisible(false);
// TextFieldCreateVorName.setVisible(false);
// TextFieldCreateEmail.setVisible(false);
// createUserButton.setVisible(false);
// labelAktuellErstellen.setVisible(false);
// checkboxLesenCreate.setVisible(false);
// checkboxSchreibenCreate.setVisible(false);
// checkboxPlausibilitätCreate.setVisible(false);
// checkboxGenehmigenCreate.setVisible(false);
// checkboxAdminCreate.setVisible(false);
// labelBerechechtigungCreate.setVisible(false);
//
// TextFieldChangeBuser.setVisible(true);
// TextFieldChangeName.setVisible(true);
// TextFieldChangeVorName.setVisible(true);
// TextFieldChangeEmail.setVisible(true);
// labelAktuellBearbeiten.setVisible(true);
// changeUserButton.setVisible(true);
// deleteUserButton.setVisible(true);
// checkboxLesenChange.setVisible(true);
// checkboxSchreibenChange.setVisible(true);
// checkboxPlausibilitätChange.setVisible(true);
// checkboxGenehmigenChange.setVisible(true);
// checkboxAdminChange.setVisible(true);
// labelBerechechtigungChange.setVisible(true);
// }
//
// @Override
// protected Component createContent() {
// // TODO Auto-generated method stub
// return null;
// }
//
// }
