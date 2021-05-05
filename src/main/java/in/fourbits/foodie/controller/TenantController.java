package in.fourbits.foodie.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.fourbits.foodie.model.SubscriptionModel;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TenantController {
    
    @RequestMapping(value = "/callback/v1.0/dependencies",
			method = RequestMethod.GET)
    public String getdependencies() {
        return "{}";
    }

    @RequestMapping(value = "/callback/v1.0/tenants/{tenantId}",
			method = RequestMethod.PUT)
    public String subscribe(@PathVariable("tenantId")String tenantId, @RequestBody SubscriptionModel subscriptionModel) {
        log.error("#################33 inside subscribe"+subscriptionModel.toString());
        log.error("https://"+subscriptionModel.getSubscribedSubdomain()+"-foodie-approuter-foodie.cfapps.eu10.hana.ondemand.com");
        return "https://"+subscriptionModel.getSubscribedSubdomain()+"-foodie-approuter-foodie.cfapps.eu10.hana.ondemand.com";
        //return "https://"+tenantId+"foodie-approuter-ff302193trial.cfapps.eu10.hana.ondemand.com"; 
        // https://815f3919-45d4-46fb-933c-6a1dbdd7ad7bfoodie-approuter-ff302193trial.cfapps.eu10.hana.ondemand.com/
    }
}
