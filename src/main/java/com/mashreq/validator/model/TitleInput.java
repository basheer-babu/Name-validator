package com.mashreq.validator.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TitleInput {
		@Valid
		@NotNull(message = "paymentcopy should not be null")
	 	private TitleModel paymentcopy;
		@Valid
		@NotNull(message = "watchlist should not be null")
	    private TitleModel watchlist;

}
