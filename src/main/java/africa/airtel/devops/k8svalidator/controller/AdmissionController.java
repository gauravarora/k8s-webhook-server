package africa.airtel.devops.k8svalidator.controller;

import africa.airtel.devops.k8svalidator.validator.Validator;
import africa.airtel.devops.k8svalidator.validator.ValidatorFactory;
import io.fabric8.kubernetes.api.model.StatusBuilder;
import io.fabric8.kubernetes.api.model.admission.v1.AdmissionResponse;
import io.fabric8.kubernetes.api.model.admission.v1.AdmissionReview;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AdmissionController {

	private final ValidatorFactory validatorFactory;

	public AdmissionController(ValidatorFactory validatorFactory) {
		this.validatorFactory = validatorFactory;
	}

	@PostMapping("/validate")
	public AdmissionReview validateAdmissionReview(@RequestBody String requestBody) {
		log.info("Request Body: {}", requestBody);
		return null;
	}

	@PostMapping("/v1/validate")
	public AdmissionReview validateAdmissionReview(@RequestBody AdmissionReview req) {
		log.info("Request Body: {}", req);
		AdmissionResponse response = new AdmissionResponse();
		response.setAllowed(true);
		response.setStatus(new StatusBuilder().withMessage("Passed by Admission Controller").withCode(200).build());
		response.setUid(req.getRequest().getUid());
		String kind = req.getRequest().getKind().getKind();
		Validator validator = validatorFactory.getValidator(kind);
		if (Objects.nonNull(validator)) {
			validator.validate(req, response);
		}
		req.setResponse(response);
		return req;
	}
}
