package africa.airtel.devops.k8svalidator.validator;

import africa.airtel.devops.k8svalidator.model.ValidatorResponse;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.admission.v1.AdmissionResponse;
import io.fabric8.kubernetes.api.model.admission.v1.AdmissionReview;

public interface Validator {

  ValidatorResponse validate(KubernetesResource kubernetesResource);

}
