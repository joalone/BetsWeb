package businessLogic;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.List;

import dataAccess.DataAccessInterface;
import dataAccess.HibernateDataAccess;
import domain.Question;
import domain.User;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import exceptions.UserAlreadyExists;

/**
 * It implements the business logic as a web service.
 */
public class BLFacadeImplementation implements BLFacade {
	DataAccessInterface dbManager;

	public BLFacadeImplementation() {
		System.out.println("Creating BLFacadeImplementation instance");
		this.dbManager = new HibernateDataAccess();
	}

	public BLFacadeImplementation(DataAccessInterface da) {
		System.out.println("Creating BLFacadeImplementation instance");
		this.dbManager = da;
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished        if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	public Question createQuestion(Event event, String question, float betMinimum)
			throws EventFinished, QuestionAlreadyExist {
		// The minimum bed must be greater than 0
		Question qry = null;

		if (new Date().compareTo(event.getEventDate()) > 0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));

		qry = this.dbManager.createQuestion(event, question, betMinimum);
		return qry;
	};

	/**
	 * This method invokes the data access to retrieve the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public List<Event> getEvents(Date date) {
		List<Event> events = this.dbManager.getEvents(date);
		return events;
	}

	/**
	 * This method invokes the data access to retrieve the dates a month for which
	 * there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date) {
		List<Date> dates = this.dbManager.getEventsMonth(date);
		return dates;
	}

	/**
	 * This method invokes the data access to initialize the database with some
	 * events and questions. It is invoked only when the option "initialize" is
	 * declared in the tag dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeBD() {
		this.dbManager.initializeDB();
	}

	@Override
	public User createUser(String name, String password) throws UserAlreadyExists {
		// TODO Auto-generated method stub
		return this.dbManager.createUser(name,password);
	}

	@Override
	public boolean checkPassword(String username, String password) {
		// TODO Auto-generated method stub
		return this.dbManager.checkPassword(username, password);
	}

}
