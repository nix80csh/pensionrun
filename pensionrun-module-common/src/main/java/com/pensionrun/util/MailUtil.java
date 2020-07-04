package com.pensionrun.util;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Component
public class MailUtil {

	@Autowired
	private VelocityEngine velocityEngine;

	public int sendMail(String fromName, String address, String subject, String htmlForm) {
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("api", "key-6024199b66d89d33d21abf9bc98225ae"));
		WebResource webResource = client.resource("https://api.mailgun.net/v3/mg.pensionday.kr" + "/messages");
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add("from", fromName + "<no-reply@mg.pensionday.kr>");
		formData.add("to", address);
		formData.add("subject", "[펜션으로 튀어라] " + subject);
		formData.add("html", htmlForm);

		ClientResponse result_data = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class,
				formData);
		return result_data.getStatusInfo().getStatusCode();

	}

	public String makeHtmlForm(Map<String, String> map, String formName) {
		return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, formName + ".vm", "UTF-8", map);

	}
}
