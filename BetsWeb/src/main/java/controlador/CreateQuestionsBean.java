package controlador;

import java.util.Date;
import java.util.List;

import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

public class CreateQuestionsBean {
	private BLFacade facade;
	private Date fecha;
	private Event evento;
	private List<Event> eventos;
	private String nombreQuestion;
	float minBet;

	public CreateQuestionsBean() {
		this.facade = new BLFacadeImplementation();
		this.fecha = new Date();
		this.evento = null;
		this.eventos = null;
		this.nombreQuestion = "";
		this.minBet = .0f;	
	}
	
	public Date getfecha() {
		return fecha;
	}
	public void setfecha(Date fecha) {
		this.fecha = fecha;
		this.eventos = this.facade.getEvents(this.fecha);
	}
	public List<Event> getEventos() {
		return eventos;
	}
	public void setEventos(List<Event> eventos) {
		this.eventos = eventos;
		if(this.eventos != null) {
			this.evento = this.eventos.get(0);
		}
	}
	public Event getEvento() {
		return evento;
	}
	public void setEvento(Event evento) {
		System.out.println("Seleccionado evento "+evento.toString());
		this.evento = evento;
	}
	
	public void setnombreQuestion(String nombreQuestion) {
		this.nombreQuestion = nombreQuestion;
	}

	public float getMinBet() {
		return minBet;
	}

	public void setMinBet(float minBet) {
		this.minBet = minBet;
	}

	public void updateeventos() {
		this.eventos = facade.getEvents(fecha);
	}

	public void createQuestion() throws EventFinished, QuestionAlreadyExist {
		if(evento == null) {
			
		} else if (nombreQuestion == null || nombreQuestion.isBlank()) {
			
		} else {
			facade.createQuestion(evento, nombreQuestion, minBet);
		}
	}
}
