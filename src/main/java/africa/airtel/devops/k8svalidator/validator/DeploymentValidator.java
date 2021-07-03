package africa.airtel.devops.k8svalidator.validator;

import africa.airtel.devops.k8svalidator.model.ValidatorResponse;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.Quantity;
import io.fabric8.kubernetes.api.model.ResourceRequirements;
import io.fabric8.kubernetes.api.model.StatusBuilder;
import io.fabric8.kubernetes.api.model.admission.v1.AdmissionResponse;
import io.fabric8.kubernetes.api.model.admission.v1.AdmissionReview;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeploymentValidator implements Validator{

  @Override
  public ValidatorResponse validate(KubernetesResource kubernetesResource) {
    Deployment deployment = (Deployment) kubernetesResource;
    ValidatorResponse response = ValidatorResponse.builder().allowed(false).build();
    ResourceRequirements resources = deployment.getSpec().getTemplate().getSpec().getContainers().get(0)
        .getResources();
    if(Objects.isNull(resources)) {
      response.setStatus(new StatusBuilder().withMessage("Resources section is required").withCode(400).build());
      return response;
    } else if(Objects.isNull(resources.getRequests())) {
      response.setStatus(new StatusBuilder().withMessage("Resource request section is required").withCode(400).build());
      return response;
    } else if(Objects.isNull(resources.getRequests().get("cpu"))) {
      response.setStatus(new StatusBuilder().withMessage("CPU request is required").withCode(400).build());
      return response;
    }
    Quantity quantity = resources.getRequests().get("cpu");
    log.info("CPU request: {}, {}", quantity.getAmount(), quantity.getFormat());
    if(StringUtils.isEmpty(quantity.getAmount())) {
      response.setStatus(new StatusBuilder().withMessage("Request CPU can not be empty").withCode(400).build());
    } else if(StringUtils.isEmpty(quantity.getFormat()) || !StringUtils.equals(quantity.getFormat(), "m")) {
      response.setStatus(new StatusBuilder().withMessage("The CPU request value must be in millicores").withCode(400).build());
    } else if(!quantity.getAmount().matches("\\b(0*(?:[1-9][0-9]?|100))\\b")) {
      response.setStatus(new StatusBuilder().withMessage("The CPU request value must not be more than 100m").withCode(400).build());
    } else {
      response.setAllowed(true);
    }
    return response;
  }
}
