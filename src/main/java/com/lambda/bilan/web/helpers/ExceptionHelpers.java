package com.lambda.bilan.web.helpers;

import java.util.ArrayList;
import java.util.List;

public class ExceptionHelpers {

	public static List<String> getErreursForException(Exception exception){

		Throwable cause = exception;
		List<String> erreurs = new ArrayList<String>();
		while (cause != null) {
			erreurs.add(cause.getMessage());
			cause = cause.getCause();
		}
		return erreurs;
	}
}
