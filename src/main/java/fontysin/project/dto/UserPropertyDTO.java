package fontysin.project.dto;

import java.time.LocalDateTime;

public class UserPropertyDTO {
    private String type;
    private String name;
    private String email;
    private String phoneNumber;
    private String city;
    private String school;
    private String endDate;
    private String startDate;
    private Boolean finished;

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public String getSchool() {
        return school;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public Boolean getFinished() {
        return finished;
    }
}
