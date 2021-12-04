package entities;

public class TaskRecord {
	private String nome;
	private String descricao;
	private String finalDate;
	private String initialDate;
	private String priority;
	private String status;

	public TaskRecord(String nome, String descricao, String finalDate, String inicialDate, String priority,
			String status) {
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

	public String getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(String finalDate) {
		this.finalDate = finalDate;
	}

	public String getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(String initialDate) {
		this.initialDate = initialDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}