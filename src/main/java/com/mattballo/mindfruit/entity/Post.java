package com.mattballo.mindfruit.entity;

import com.mattballo.mindfruit.annotation.ValidUserId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.sql.Insert;
import org.hibernate.sql.Update;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {
    @Null(groups = {Insert.class})
    @NotNull(groups = {Update.class})
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
