package pt.ulisboa.tecnico.socialsoftware.humanaethica.enrollment;

import static pt.ulisboa.tecnico.socialsoftware.humanaethica.exceptions.ErrorMessage.ACTIVITY_NOT_FOUND;
import static pt.ulisboa.tecnico.socialsoftware.humanaethica.exceptions.ErrorMessage.ENROLLMENT_DOES_NOT_EXIST;
import static pt.ulisboa.tecnico.socialsoftware.humanaethica.exceptions.ErrorMessage.USER_NOT_FOUND;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import pt.ulisboa.tecnico.socialsoftware.humanaethica.activity.domain.Activity;
import pt.ulisboa.tecnico.socialsoftware.humanaethica.activity.dto.ActivityDto;
import pt.ulisboa.tecnico.socialsoftware.humanaethica.activity.repository.ActivityRepository;
import pt.ulisboa.tecnico.socialsoftware.humanaethica.enrollment.domain.Enrollment;
import pt.ulisboa.tecnico.socialsoftware.humanaethica.enrollment.dto.EnrollmentDto;
import pt.ulisboa.tecnico.socialsoftware.humanaethica.enrollment.repository.EnrollmentRepository;
import pt.ulisboa.tecnico.socialsoftware.humanaethica.exceptions.ErrorMessage;
import pt.ulisboa.tecnico.socialsoftware.humanaethica.exceptions.HEException;
import pt.ulisboa.tecnico.socialsoftware.humanaethica.user.domain.Volunteer;
import pt.ulisboa.tecnico.socialsoftware.humanaethica.user.repository.UserRepository;


@Service
public class EnrollmentService {

    @Autowired
    EnrollmentRepository enrollmentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ActivityRepository activityRepository;



    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<EnrollmentDto> getEnrollmentsByActivity(Integer activityId) {
        if(activityId == null)throw new HEException(ACTIVITY_NOT_FOUND);

        return enrollmentRepository.getEnrollmentsByActivityId(activityId).stream()
            .map(EnrollmentDto::new)  
            .sorted(Comparator.comparing(EnrollmentDto::getId, Comparator.naturalOrder()))
            .toList();

            //.sorted(Comparator.comparing(EnrollmentDto::getEnrollmentDateTime)).toList();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public EnrollmentDto createEnrollment(Integer  userId, Integer activityId, EnrollmentDto enrollmentDto) {
        if(activityId == null)throw new HEException(ACTIVITY_NOT_FOUND);

        if(userId == null)throw new HEException(USER_NOT_FOUND);

        if(enrollmentDto == null)throw new HEException(ENROLLMENT_DOES_NOT_EXIST);

        Volunteer volunteer = (Volunteer) userRepository.findById(userId).orElseThrow(() -> new HEException(ErrorMessage.USER_NOT_FOUND, userId));
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new HEException(ErrorMessage.ACTIVITY_NOT_FOUND, activityId));


        Enrollment enrollment = new Enrollment(activity, volunteer, enrollmentDto);

        enrollmentRepository.save(enrollment);

        return new EnrollmentDto(enrollment);
    }
}
