package com.opsmatters.newrelic.api.model.graphql;

import java.util.List;

/**
 * Represents GraphQL response data.
 *
 * @author Nikhil Unni (nikhilunni)
 */
public class AccountLookupResponse {

    private Actor actor;

    public AccountLookupResponse() {
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }


    public class Actor {

        private Organization organization;

        public Organization getOrganization() {
            return organization;
        }

        public void setOrganization(Organization organization) {
            this.organization = organization;
        }

    }

    public class Organization {

        private AccountManagement accountManagement;

        public AccountManagement getAccountManagement() {
            return accountManagement;
        }

        public void setAccountManagement(AccountManagement accountManagement) {
            this.accountManagement = accountManagement;
        }
    }

    public class AccountManagement {

        private List<ManagedAccount> managedAccounts;

        public List<ManagedAccount> getManagedAccounts() {
            return managedAccounts;
        }

        public void setManagedAccounts(List<ManagedAccount> managedAccounts) {
            this.managedAccounts = managedAccounts;
        }
    }

    public class ManagedAccount {

        private Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }
}
