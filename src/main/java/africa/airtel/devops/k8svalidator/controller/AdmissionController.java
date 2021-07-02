package africa.airtel.devops.k8svalidator.controller;

import io.fabric8.kubernetes.api.model.admission.v1.AdmissionReview;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AdmissionController {

  @GetMapping("/validate")
  public AdmissionReview validateAdmissionReview(@RequestBody AdmissionReview requestBody) {
    log.info("Request Body: {}", requestBody);
    return requestBody;
  }
}
