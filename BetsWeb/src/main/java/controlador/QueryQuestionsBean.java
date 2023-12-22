package controlador;

import java.util.Date;
import java.util.EventObject;
import java.util.List;

import domain.Event;
import domain.Question;
import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

public class QueryQuestionsBean {
	
	private BLFacade facade;
	private Date fecha;
	private Event evento;
	private List<Event> eventos;
	private List<Question> preguntas;
	public QueryQuestionsBean() {
		this.facade = new BLFacadeImplementation();
		this.fecha = new Date();
		this.evento = null;
		this.eventos = this.facade.getEvents(this.fecha);
		this.preguntas = null;
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
		if(this.evento != null) {
			System.out.println("Halladas preguntas "+this.evento.getQuestions().toString());
			this.preguntas = this.evento.getQuestions();
		}
	}
	public List<Question> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(List<Question> preguntas) {
		this.preguntas = preguntas;
	}
}
