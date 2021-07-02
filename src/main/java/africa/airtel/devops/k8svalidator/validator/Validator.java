package africa.airtel.devops.k8svalidator.validator;

import io.fabric8.kubernetes.api.model.admission.v1.AdmissionResponse;
import io.fabric8.kubernetes.api.model.admission.v1.AdmissionReview;

public interface Validator {

  void validate(AdmissionReview admissionReview, AdmissionResponse response);

}
