package p.lodz.pl.spjava.sdudkiewicz.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Represents an User for this web application.
 */
@Entity
public class Script {

	// ------------------------
	// PRIVATE FIELDS
	// ------------------------

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String command;

	@NotNull
	private Boolean onlyAdmin;

	@NotNull
	private Boolean toRead;

	@NotNull
	private Boolean toEdit;

	@NotNull
	private Boolean toRun;
	
	@NotNull
	private Boolean fileExist;

	public Boolean getFileExist() {
		return fileExist;
	}

	public void setFileExist(Boolean fileExist) {
		this.fileExist = fileExist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getOnlyAdmin() {
		return onlyAdmin;
	}

	public void setOnlyAdmin(Boolean onlyAdmin) {
		this.onlyAdmin = onlyAdmin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Boolean getToRead() {
		return toRead;
	}

	public void setToRead(Boolean toRead) {
		this.toRead = toRead;
	}

	public Boolean getToEdit() {
		return toEdit;
	}

	public void setToEdit(Boolean toEdit) {
		this.toEdit = toEdit;
	}

	public Boolean getToRun() {
		return toRun;
	}

	public void setToRun(Boolean toRun) {
		this.toRun = toRun;
	}

	@Override
	public String toString() {
		return "Script [id=" + id + ", name=" + name + ", command=" + command
				+ ", onlyAdmin=" + onlyAdmin + ", toRead=" + toRead
				+ ", toEdit=" + toEdit + ", toRun=" + toRun + "]";
	}

} // class Script
