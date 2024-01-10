package dataAccess;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import configuration.UtilDate;
import domain.Event;
import domain.Question;
import domain.User;
import exceptions.QuestionAlreadyExist;
import exceptions.UserAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class HibernateDataAccess implements DataAccessInterface {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	public HibernateDataAccess(boolean initializeMode) {
		System.out.println("Creating DataAccess instance");
		if (initializeMode)
			initializeDB();
	}

	public HibernateDataAccess() {
		new HibernateDataAccess(false);
	}

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Fallo creando el SessionFactory." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * This is the data access method that initializes the database with some events
	 * and questions. This method is invoked by the business logic (constructor of
	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
	 * dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		try {
			Calendar today = Calendar.getInstance();
			int month = today.get(Calendar.MONTH);
			month += 1;
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 0;
				year += 1;
			}

			Event ev1 = new Event(1, "Atlético-Athletic", UtilDate.newDate(year, month, 17));
			Event ev2 = new Event(2, "Eibar-Barcelona", UtilDate.newDate(year, month, 17));
			Event ev3 = new Event(3, "Getafe-Celta", UtilDate.newDate(year, month, 17));
			Event ev4 = new Event(4, "Alavés-Deportivo", UtilDate.newDate(year, month, 17));
			Event ev5 = new Event(5, "Español-Villareal", UtilDate.newDate(year, month, 17));
			Event ev6 = new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 17));
			Event ev7 = new Event(7, "Malaga-Valencia", UtilDate.newDate(year, month, 17));
			Event ev8 = new Event(8, "Girona-Leganés", UtilDate.newDate(year, month, 17));
			Event ev9 = new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year, month, 17));
			Event ev10 = new Event(10, "Betis-Real Madrid", UtilDate.newDate(year, month, 17));

			Event ev11 = new Event(11, "Atletico-Athletic", UtilDate.newDate(year, month, 1));
			Event ev12 = new Event(12, "Eibar-Barcelona", UtilDate.newDate(year, month, 1));
			Event ev13 = new Event(13, "Getafe-Celta", UtilDate.newDate(year, month, 1));
			Event ev14 = new Event(14, "Alavés-Deportivo", UtilDate.newDate(year, month, 1));
			Event ev15 = new Event(15, "Español-Villareal", UtilDate.newDate(year, month, 1));
			Event ev16 = new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 1));
			Event ev17 = new Event(17, "Málaga-Valencia", UtilDate.newDate(year, month, 28));
			Event ev18 = new Event(18, "Girona-Leganés", UtilDate.newDate(year, month, 28));
			Event ev19 = new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year, month, 28));
			Event ev20 = new Event(20, "Betis-Real Madrid", UtilDate.newDate(year, month, 28));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
				q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
				q3 = ev11.addQuestion("¿Quién ganará el partido?", 1);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q5 = ev17.addQuestion("¿Quién ganará el partido?", 1);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);
			} else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1);
				q2 = ev1.addQuestion("Who will score first?", 2);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2);
			} else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);

			}

			session.save(ev1);
			session.save(ev2);
			session.save(ev3);
			session.save(ev4);
			session.save(ev5);
			session.save(ev6);
			session.save(ev7);
			session.save(ev8);
			session.save(ev9);
			session.save(ev10);

			session.save(ev11);
			session.save(ev12);
			session.save(ev13);
			session.save(ev14);
			session.save(ev15);
			session.save(ev16);
			session.save(ev17);
			session.save(ev18);
			session.save(ev19);
			session.save(ev20);

			session.save(q1);
			session.save(q2);
			session.save(q3);
			session.save(q4);
			session.save(q5);
			session.save(q6);
			session.getTransaction().commit();
			System.out.println("Db initialized");
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Error al crear la base de datos.");
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question + " betMinimum="
				+ betMinimum);
		if (this.existQuestion(event, question))
			throw new QuestionAlreadyExist();
		Question q = new Question(question, betMinimum, event);

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(q);
		session.getTransaction().commit();
		return q;
	}

	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= " + event + " question= " + question);
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Event> result = session.createQuery("from Event as e where e.eventNumber= :eNum")
				.setParameter("eNum", event.getEventNumber()).list();
		if (result.get(0) != null) {
			Event ev = result.get(0);
			return ev.DoesQuestionExists(question);
		} else {
			return false;
		}
	}

	/**
	 * This method retrieves from the database the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public List<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		List<Event> result = new ArrayList<Event>();

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Event> events = session.createQuery("from Event as e where e.eventDate = :date").setParameter("date", date)
				.list();
		session.getTransaction().commit();

		for (Event ev : events) {
			System.out.println(ev.toString());
			result.add(ev);
		}
		return result;
	}

	/**
	 * This method retrieves from the database the dates a month for which there are
	 * events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		List<Date> result = new ArrayList<Date>();

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Date> dates = session
				.createQuery("select distinct e.eventDate from Event as e where MONTH(e.eventDate) = MONTH(:date)")
				.setParameter("date", date).list();
		session.getTransaction().commit();

		for (Date d : dates) {
			System.out.println(d.toString());
			result.add(d);
		}
		return result;
	}

	@Override
	public void emptyDatabase() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.createQuery("drop database bets");
		session.createQuery("create database bets");
		session.getTransaction().commit();
	}

	@Override
	public User createUser(String username, String password) throws UserAlreadyExist {
		System.out.println(">> DataAccess: createUser=> username= " + username);
		
		User u = new User(username, password);
		if (this.existUser(u))
			throw new UserAlreadyExist();
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(u);
		session.getTransaction().commit();
		return u;
	}

	public boolean existUser(User u) {
		System.out.println(">> DataAccess: existUser=> user= " + u);
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Event> result = session.createQuery("from User as u where u.username= :uname")
				.setParameter("uname", u.getUsername()).list();
		return !result.isEmpty();
	}

	@Override
	public boolean checkPassword(String username, String password) {
		System.out.println(">> DataAccess: checkPassword=> username= " + username);
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		List<User> result = session.createQuery("from User as u where u.username= :uname")
				.setParameter("uname", username).list();
		session.getTransaction().commit();

		if (!result.isEmpty()) {
			User u = result.get(0);
			return u.checkPassword(password);
		} else {
			return false;
		}
	}
}
