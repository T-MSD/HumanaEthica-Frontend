package pt.ulisboa.tecnico.socialsoftware.humanaethica.assessment;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pt.ulisboa.tecnico.socialsoftware.humanaethica.activity.ActivityController;
import pt.ulisboa.tecnico.socialsoftware.humanaethica.assessment.dto.AssessmentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ulisboa.tecnico.socialsoftware.humanaethica.auth.domain.AuthUser;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/assessments")
public class AssessmentController {
    @Autowired
    private AssessmentService assessmentService;

    private static final Logger logger = LoggerFactory.getLogger(AssessmentController.class);

    @GetMapping()
    public List<AssessmentDto> getAssessments() {
        return assessmentService.getAssessments();
    }

    /*
    @PostMapping()
    @PreAuthorize("(hasRole('ROLE_VOLUNTEER'))")
    public AssessmentDto registerAssessment(Principal principal, @Valid @RequestBody AssessmentDto assessmentDto){
        int userId = ((AuthUser) ((Authentication) principal).getPrincipal()).getUser().getId();
        return assessmentService.registerAssessment(UserId, activityDto);
    }

    */


}