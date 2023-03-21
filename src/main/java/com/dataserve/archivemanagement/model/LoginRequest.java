package com.dataserve.archivemanagement.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest implements Serializable {
	private static final long serialVersionUID = 5926468583005150707L;

	private String username;

	private String password;

}