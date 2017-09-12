package de.bbqesports.wahltool.db;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;

import de.bbqesports.wahltool.service.AbstimmungService;
import de.bbqesports.wahltool.service.UserService;

@SpringComponent
public class TestDaten {

	@Autowired
	private UserService userService;

	@Autowired
	private AbstimmungService abstimmungService;

	public void setupTestData() {

		setupBenutzer();
		setupAbstimmungen();

	}

	private void setupBenutzer() {
		if (!userService.findByUserName("1").isPresent()) {
			userService.save(new User("1", true));
		}

		if (!userService.findByUserName("2").isPresent()) {
			userService.save(new User("2", false));
		}
	}

	private void setupAbstimmungen() {
		if (!abstimmungService.findById(1).isPresent()) {
			abstimmungService.save(new Abstimmung("Hier geht es um die Abstimmung bla bla bla...", false));
		}

		if (!abstimmungService.findById(2).isPresent()) {
			abstimmungService.save(new Abstimmung("Hier geht es um eine andere Abstimmung bla bla bla...", true));
		}

	}

	// private void setupWartungen() {
	// User userAdmin = userService.findOne(1L).get();
	// User userLesen = userService.findOne(2L).get();
	//
	// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy
	// HH:mm");
	// // LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
	//
	// Wartung wartung = new Wartung();
	// wartung.setId(1L);
	// wartung.setUserRechte(userAdmin);
	// wartung.setAnkuendigung(LocalDateTime.parse("17.09.2017 12:00", formatter));
	// wartung.setDurchfuehrungVon(LocalDateTime.parse("20.09.2017 12:00",
	// formatter));
	// wartung.setPlausibel(true);
	// wartung.setGenehmigt(true);
	// wartung.setZurueckgewiesen(false);
	//
	// if (!wartungService.findOne(1L).isPresent()) {
	// wartungService.save(wartung);
	// }
	//
	// wartung = new Wartung();
	// wartung.setId(2L);
	// wartung.setUserRechte(userAdmin);
	// wartung.setAnkuendigung(LocalDateTime.parse("17.07.2017 12:00", formatter));
	// wartung.setDurchfuehrungVon(LocalDateTime.parse("20.07.2017 12:00",
	// formatter));
	// wartung.setPlausibel(true);
	// wartung.setGenehmigt(true);
	// wartung.setZurueckgewiesen(false);
	//
	// if (!wartungService.findOne(2L).isPresent()) {
	// wartungService.save(wartung);
	// }
	//
	// wartung = new Wartung(userLesen, "Freitag", LocalDateTime.parse("17.10.2017
	// 12:00", formatter), "Stefan",
	// "Mail", "IMP", "Montag", "Montag", LocalDateTime.parse("20.10.2017 12:00",
	// formatter),
	// LocalDateTime.parse("20.10.2017 14:00", formatter), "Simon", "Remote",
	// "Markus", "vor Ort", "Lea",
	// "Remote", "Bad", "8.8.8.8", "filebasierte Produktion", null,
	// "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy
	// eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam
	// voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita
	// kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem
	// ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod
	// tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.
	// At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd
	// gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem
	// ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod
	// tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.
	// At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd
	// gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. \r\n"
	// + "\r\n"
	// + "Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse
	// molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros
	// et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril
	// delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit
	// amet,",
	// "Redundantes System", "ca. paar stunden", "keine redundanz", "00:00 bis 01:30
	// Uhr",
	// "Es \ngibt \nnichts \nweiters \nzu \nbeachten! (hier kann ein langer Text
	// stehen)",
	// "sehr unwahrscheinlich", "länger als 4 Stunden", "nichts zu machen", "System
	// neustarten",
	// "Funktion prüfen");
	// wartung.setId(3L);
	// wartung.setPlausibel(true);
	// wartung.setGenehmigt(true);
	// wartung.setZurueckgewiesen(false);
	//
	// if (!wartungService.findOne(3L).isPresent()) {
	// wartungService.save(wartung);
	// }
	//
	// wartung = new Wartung(userLesen, "Freitag", LocalDateTime.parse("05.09.2017
	// 12:00", formatter), "Stefan",
	// "Mail", "IMP", "Montag", "Montag", LocalDateTime.parse("20.09.2017 12:00",
	// formatter),
	// LocalDateTime.parse("20.09.2017 14:00", formatter), "Simon", "Remote",
	// "Markus", "vor Ort", "Lea",
	// "Remote", "Bad", "8.8.8.8", "filebasierte Produktion", null,
	// "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy
	// eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam
	// voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita
	// kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem
	// ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod
	// tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.
	// At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd
	// gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem
	// ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod
	// tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.
	// At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd
	// gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. \r\n"
	// + "\r\n"
	// + "Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse
	// molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros
	// et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril
	// delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit
	// amet,",
	// "Redundantes System", "ca. paar stunden", "keine redundanz", "00:00 bis 01:30
	// Uhr",
	// "Es \ngibt \nnichts \nweiters \nzu \nbeachten! (hier kann ein langer Text
	// stehen)",
	// "sehr unwahrscheinlich", "länger als 4 Stunden", "nichts zu machen", "System
	// neustarten",
	// "Funktion prüfen");
	// wartung.setId(4L);
	// wartung.setPlausibel(false);
	// wartung.setGenehmigt(false);
	// wartung.setZurueckgewiesen(false);
	// wartung.setSendToBS(false);
	//
	// if (!wartungService.findOne(4L).isPresent()) {
	// wartungService.save(wartung);
	// }
	// }

	// private void setupWartungsgruppe() {
	// if (new WartungsgruppeService().getWartungsgruppeByName("Abteilung
	// Infrastruktur").isPresent()) {
	// return;
	// }
	//
	// Wartungsgruppe infrastruktur = new
	// Wartungsgruppe();infrastruktur.setName("Abteilung Infrastruktur");
	//
	// new WartungsgruppeService().saveOrUpate(infrastruktur);
	// }

	// private void setupWartungsrolle() {
	//
	// if (new
	// WartungsrolleService().getWartungsgrolleByName("RolleIS").isPresent()) {
	// return;
	// }
	//
	// WartungsRolle rolle = new WartungsRolle();
	// rolle.setName("RolleIS");
	// Optional<User> userRechte = new
	// BenutzerService().getBenutzerByName("Mustermann");
	//
	// if (!userRechte.isPresent()) {
	// return;
	// }
	// rolle.setBenutzer(Arrays.asList(userRechte.get()));
	//
	// new WartungsrolleService().saveOrUpate(rolle);
	// }
	//
	// private WorkflowStep setupWorflowStep() {
	// WorkflowStep workflowStep = new WorkflowStep();
	//
	// workflowStep.setOrderNr(1);
	// workflowStep.setTaetigkeit(Taetigkeit.GENEHMIGUNG);
	//
	// Optional<WartungsRolle> wartungsrolle = new
	// WartungsrolleService().getWartungsgrolleByName("RolleIS");
	//
	// workflowStep.setWartungsRolle(wartungsrolle.get());
	//
	// return workflowStep;
	//
	// }
	//
	// private void setupWartungskategorie() {
	// Wartungskategorie kategorie = new Wartungskategorie();
	//
	// kategorie.setName("KategorieIS");
	// kategorie.setBeschreibung("Testbeschreibung");
	//
	// kategorie.setWorkflowSteps(Arrays.asList(setupWorflowStep()));
	//
	// new WartungskategorieService().saveOrUpate(kategorie);
	//
	// }

}
