package ru.xgzzs0ocfyf1.experiments.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date updated;

    @Enumerated(EnumType.STRING)
    private EntityStatus status;
}
