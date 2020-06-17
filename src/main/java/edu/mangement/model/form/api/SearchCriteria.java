package edu.mangement.model.form.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchCriteria {
    @NotBlank(message = "Field can't empty!")
    String name;
}
