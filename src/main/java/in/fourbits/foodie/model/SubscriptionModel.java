package in.fourbits.foodie.model;

import lombok.Data;

@Data
public class SubscriptionModel {
   
    private String subscriptionAppId;
    private String subscriptionAppName;
    private String subscribedTenantId;
    private String subscribedSubdomain;
    private String globalAccountGUID;
    private String subscribedLicenseType;
    public String getSubscriptionAppId() {
        return subscriptionAppId;
    }
    public void setSubscriptionAppId(String subscriptionAppId) {
        this.subscriptionAppId = subscriptionAppId;
    }
    public String getSubscriptionAppName() {
        return subscriptionAppName;
    }
    public void setSubscriptionAppName(String subscriptionAppName) {
        this.subscriptionAppName = subscriptionAppName;
    }
    public String getSubscribedTenantId() {
        return subscribedTenantId;
    }
    public void setSubscribedTenantId(String subscribedTenantId) {
        this.subscribedTenantId = subscribedTenantId;
    }
    public String getSubscribedSubdomain() {
        return subscribedSubdomain;
    }
    public void setSubscribedSubdomain(String subscribedSubdomain) {
        this.subscribedSubdomain = subscribedSubdomain;
    }
    public String getGlobalAccountGUID() {
        return globalAccountGUID;
    }
    public void setGlobalAccountGUID(String globalAccountGUID) {
        this.globalAccountGUID = globalAccountGUID;
    }
    public String getSubscribedLicenseType() {
        return subscribedLicenseType;
    }
    public void setSubscribedLicenseType(String subscribedLicenseType) {
        this.subscribedLicenseType = subscribedLicenseType;
    }
    
}
