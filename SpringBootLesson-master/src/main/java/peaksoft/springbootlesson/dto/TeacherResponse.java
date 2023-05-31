package peaksoft.springbootlesson.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TeacherResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String roleName;
    private LocalDate localDate;
    private String courseName;
}
