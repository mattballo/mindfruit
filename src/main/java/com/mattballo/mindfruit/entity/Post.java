package com.mattballo.mindfruit.entity;

import com.mattballo.mindfruit.annotation.ValidUserId;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.id.Assigned;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    private Long id;

    @NotNull
    @ValidUserId
    @Column(name = "user_id")
    private Long userId;

    @NotBlank
    private String title;

    @NotBlank
    private String body;
}
