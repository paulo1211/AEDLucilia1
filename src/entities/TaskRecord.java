package entities;
import java.util.Date;

import entitiesenum.TaskPriority;
import entitiesenum.TaskStatus;

public class TaskRecord {
	private String nome;
	private String descricao;
	private Date finalDate;
	private Date initialDate;
	private TaskPriority priority;
	private TaskStatus status;

	public TaskRecord(String nome, String descricao, Date finalDate, Date inicialDate, TaskPriority priority,
			TaskStatus status) {
		this.nome = nome;
		this.descricao = descricao;
		this.finalDate = finalDate;
		this.initialDate = inicialDate;
		this.priority = priority;
		this.status = status;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public TaskPriority getPriority() {
		return priority;
	}

	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

}