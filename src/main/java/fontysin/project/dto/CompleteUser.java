package fontysin.project.dto;

import fontysin.project.model.user.UserProperty;

public class CompleteUser {
    public CompleteUser(String firstName, String lastName, Iterable<UserProperty> userProperties) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userProperties = new PropertyContainer(userProperties);
    }

    private String firstName;

    private String lastName;

    private PropertyContainer userProperties;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public PropertyContainer getUserProperties() {
        return userProperties;
    }
}
