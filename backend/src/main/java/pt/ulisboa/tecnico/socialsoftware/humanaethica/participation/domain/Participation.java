package pt.ulisboa.tecnico.socialsoftware.humanaethica.participation.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import pt.ulisboa.tecnico.socialsoftware.humanaethica.activity;
import pt.ulisboa.tecnico.socialsoftware.humanaethica.user;


@entity
@Table(name = "participation")
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Activity activity;

    @ManyToOne
    private Volunteer volunteer;

    private Integer rating;
    private LocalDateTime acceptanceDate;

    // constructors

    public Participation(){
    }

    public Participation(Integer rating, Activity activity, Integer userId){
        setRating(rating);
        setAcceptanceDate(DateHandler.now());
        setActivity(activity);
        setVolunteer(volunteer);
    }

    public Participation(Activity activity, Integer userId){
        setAcceptanceDate(DateHandler.now());
        setActivity(activity);
        setVolunteer(volunteer);
    }


    // sets and gets

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getRating() {
        return rating;
    }

    public void setAcceptanceDate(LocalDateTime acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public LocalDateTime getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

}
