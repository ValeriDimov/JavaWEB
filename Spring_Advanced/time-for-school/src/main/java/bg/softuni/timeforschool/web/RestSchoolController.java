package bg.softuni.timeforschool.web;

import bg.softuni.timeforschool.model.dto.SchoolDetailDTO;
import bg.softuni.timeforschool.service.SchoolService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/schools")
public class RestSchoolController {

    private final SchoolService schoolService;

    public RestSchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public ResponseEntity<List<SchoolDetailDTO>> getAllBooks() {
        return ResponseEntity.
                ok(schoolService.getAllSchoolsRest());
    }

    @Tag(name = "Get school by ID", description = "Returns the school details by its id")
    @Parameter(
            name = "id",
            description = "The ID of the school",
            required = true
    )
    @ApiResponse(
            responseCode = "200",
            description = "If the school was retrieved successfully"
    )
    @ApiResponse(
            responseCode = "404",
            description = "If the school was not found"
    )
    @GetMapping("/{id}")
    public ResponseEntity<SchoolDetailDTO> getSchoolById(@PathVariable("id") Long schoolId) {
        Optional<SchoolDetailDTO> schoolOpt = schoolService.findSchoolByID(schoolId);
        if (schoolOpt.isEmpty()) {
            return ResponseEntity.
                    notFound().
                    build();
        } else {
            return ResponseEntity.
                    ok(schoolOpt.get());
        }
    }
}
