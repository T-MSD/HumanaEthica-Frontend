package pt.ulisboa.tecnico.socialsoftware.humanaethica.enrollment.dto;

import pt.ulisboa.tecnico.socialsoftware.humanaethica.enrollment.domain.Enrollment;
import pt.ulisboa.tecnico.socialsoftware.humanaethica.utils.DateHandler;

public class EnrollmentDto {
    private Integer id;
    private String motivation;

    private String enrollmentDateTime;

    private Boolean participation;
    private String volunteerName;

    public EnrollmentDto() {}

    public EnrollmentDto(Enrollment enrollment) {
        this.id = enrollment.getId();
        this.motivation = enrollment.getMotivation();
        this.enrollmentDateTime = DateHandler.toISOString(enrollment.getEnrollmentDateTime());
        this.volunteerName = enrollment.getVolunteer().getName();
        this.participation = enrollment.getVolunteer().getParticipations().stream().anyMatch(p -> p.getActivity().getId() == enrollment.getActivity().getId());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getEnrollmentDateTime() {
        return enrollmentDateTime;
    }

    public void setEnrollmentDateTime(String enrollmentDateTime) {
        this.enrollmentDateTime = enrollmentDateTime;
    }

    public Boolean getParticipation() {
        return this.participation;
    }

    public void setParticipation(final Boolean participation) {
        this.participation = participation;
    }

    public String getVolunteerName() {
        return this.volunteerName;
    }

    public void setVolunteerName(final String volunteerName) {
        this.volunteerName = volunteerName;
    }
}
