package africa.airtel.devops.k8svalidator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.fabric8.kubernetes.api.model.ResourceRequirements;
import io.fabric8.kubernetes.api.model.StatusBuilder;
import io.fabric8.kubernetes.api.model.admission.v1.AdmissionResponseBuilder;
import io.fabric8.kubernetes.api.model.admission.v1.AdmissionReview;
import io.fabric8.kubernetes.api.model.admission.v1.AdmissionReviewBuilder;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AdmissionController {

	@PostMapping("/validate")
	public AdmissionReview validateAdmissionReview(@RequestBody String requestBody) {
		log.info("Request Body: {}", requestBody);
		return null;
	}

	@PostMapping("/validate2")
	public AdmissionReview validateAdmissionReview(@RequestBody AdmissionReview req) {
		log.info("Request Body: {}", req);
		if (req.getRequest().getResource().getResource().equalsIgnoreCase("deployment")) {
			Deployment deployment = (Deployment) req.getRequest().getObject();
			ResourceRequirements resources = deployment.getSpec().getTemplate().getSpec().getContainers().get(0)
					.getResources();
			log.info("{}", resources);
		}
		return new AdmissionReviewBuilder()
				.withResponse(new AdmissionResponseBuilder().withAllowed(false).withUid(req.getRequest().getUid())
						.withStatus(new StatusBuilder().withMessage("I won't allow this!").build()).build())
				.build();
	}
}
