package com.mashreq.validator.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TitleModel {
	@NotBlank(message = "text should not be blank")
	private String text;
	@NotBlank(message = "language should not be blank")
	private String language;
	@NotBlank(message = "entityType should not be blank")
	private String entityType;

}
