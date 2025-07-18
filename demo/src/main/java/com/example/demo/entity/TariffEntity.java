package com.example.demo.entity;

import jakarta.persistence.*;


import lombok.*;

//import javax.persistence.Version;
import org.hibernate.envers.Audited;

import java.util.Date;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tariff")
//@Audited
public class TariffEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(unique = true, nullable = false)
        private Integer id;
        private String name;
        private String type;
        private String conditions;
        @Version
        private int version;

        public TariffEntity() {
        }

        public TariffEntity(Integer id, String name, String type, String conditions) {
                this.id = id;
                this.name = name;
                this.type = type;
                this.conditions = conditions;

        }

        public Integer getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public String getType() {
                return type;
        }

        public String getConditions() {
                return conditions;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public void setName(String name) {
                this.name = name;
        }

        public void setType(String type) {
                this.type = type;
        }

        public void setConditions(String conditions) {
                this.conditions = conditions;
        }

}
