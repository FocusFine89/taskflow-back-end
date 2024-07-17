package nikita.ivanov.taskflow_back_end.projects;

import jakarta.validation.constraints.NotEmpty;

public record ProjectsCreateDTO(@NotEmpty String name) {
}
