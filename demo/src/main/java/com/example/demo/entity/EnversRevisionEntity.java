package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Data
@Entity(name = "revinfo")
@RevisionEntity
//@RevisionEntity(EnversRevisionListener.class)
//@FieldNameConstants(innerTypeName = "EnversRevisionFieldNames", asEnum = true)

public class EnversRevisionEntity {

    @RevisionNumber
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "revinfo_seq")
   // @SequenceGenerator(name = "revinfo_seq", sequenceName = "revinfo_seq")
    private int rev;

    @RevisionTimestamp
    private Instant timestamp;

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }


}
