package africa.airtel.devops.k8svalidator.util;

import io.fabric8.kubernetes.api.model.StatusBuilder;
import io.fabric8.kubernetes.api.model.admission.v1beta1.AdmissionRequest;
import io.fabric8.kubernetes.api.model.admission.v1beta1.AdmissionResponse;

public class Utils {

  public static AdmissionResponse getDefaultSuccessResponse(AdmissionRequest request) {
    AdmissionResponse response = new AdmissionResponse();
    response.setAllowed(true);
    response.setStatus(new StatusBuilder().withMessage("Passed by Admission Controller")
                           .withCode(200).build());
    response.setUid(request.getUid());
    return response;
  }

  public static io.fabric8.kubernetes.api.model.admission.v1.AdmissionResponse getDefaultSuccessResponse(
      io.fabric8.kubernetes.api.model.admission.v1.AdmissionRequest request) {
    io.fabric8.kubernetes.api.model.admission.v1.AdmissionResponse response = new io.fabric8.kubernetes.api.model.admission.v1.AdmissionResponse();
    response.setAllowed(true);
    response.setStatus(new StatusBuilder().withMessage("Passed by Admission Controller")
                           .withCode(200).build());
    response.setUid(request.getUid());
    return response;
  }

}
