package africa.airtel.devops.k8svalidator.controller;


import africa.airtel.devops.k8svalidator.model.ValidatorResponse;
import africa.airtel.devops.k8svalidator.util.Utils;
import africa.airtel.devops.k8svalidator.validator.Validator;
import africa.airtel.devops.k8svalidator.validator.ValidatorFactory;
import io.fabric8.kubernetes.api.model.admission.v1beta1.AdmissionResponse;
import io.fabric8.kubernetes.api.model.admission.v1beta1.AdmissionReview;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1")
public class V1Controller {

  private final ValidatorFactory validatorFactory;

  public V1Controller(ValidatorFactory validatorFactory) {
    this.validatorFactory = validatorFactory;
  }

  @PostMapping("/validate")
  public AdmissionReview validate(@RequestBody AdmissionReview req) {
    log.info("Request Body: {}", req);
    AdmissionResponse response = Utils.getDefaultSuccessResponse(req.getRequest());
    String kind = req.getRequest().getKind().getKind();
    Validator validator = validatorFactory.getValidator(kind);
    if (Objects.nonNull(validator)) {
      ValidatorResponse validatorResponse = validator.validate(req.getRequest().getObject());
      response.setAllowed(validatorResponse.isAllowed());
      response.setStatus(validatorResponse.getStatus());
    }
    req.setResponse(response);
    return req;
  }

}
