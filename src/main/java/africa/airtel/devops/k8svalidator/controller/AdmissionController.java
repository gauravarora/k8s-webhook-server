package africa.airtel.devops.k8svalidator.controller;

import io.fabric8.kubernetes.api.model.admission.v1.AdmissionReview;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AdmissionController {

  @PostMapping("/validate")
  public AdmissionReview validateAdmissionReview(@RequestBody String requestBody) {
    log.info("Request Body: {}", requestBody);
    return null;
  }
}
