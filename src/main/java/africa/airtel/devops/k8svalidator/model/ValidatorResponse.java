package africa.airtel.devops.k8svalidator.model;

import io.fabric8.kubernetes.api.model.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidatorResponse {

  private Status status;
  private boolean allowed;

}
