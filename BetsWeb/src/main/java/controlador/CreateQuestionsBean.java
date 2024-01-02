package controlador;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

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
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
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

	public String getNombreQuestion() {
		return nombreQuestion;
	}

	public void setNombreQuestion(String nombreQuestion) {
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

	public void createQuestion() {
		if(evento == null) {
			System.out.println("Error, el evento no ha sido seleccionado.");
		} else if (nombreQuestion == null || nombreQuestion.isBlank()) {
			System.out.println("Error, el nombre de la pregunta está vacío.");
		} else {
			try {
				facade.createQuestion(this.evento, this.nombreQuestion, this.minBet);
			} catch (EventFinished e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Error: el evento ya terminó."));
			} catch (QuestionAlreadyExist e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Error: la pregunta ya existe."));
			}
		}
	}
	
}
