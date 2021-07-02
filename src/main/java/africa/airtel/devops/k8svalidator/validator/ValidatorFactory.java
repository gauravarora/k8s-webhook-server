package africa.airtel.devops.k8svalidator.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ValidatorFactory {

  private final DeploymentValidator deploymentValidator;

  public ValidatorFactory(DeploymentValidator deploymentValidator) {
    this.deploymentValidator = deploymentValidator;
  }

  public Validator getValidator(String kind) {

    if(StringUtils.equalsIgnoreCase(kind, "deployment")) {
      return deploymentValidator;
    }
    return null;
  }

}
