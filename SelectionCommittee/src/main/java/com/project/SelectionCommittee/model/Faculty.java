package com.project.SelectionCommittee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "faculties")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "faculty_id")
    private Long id;

    @Column(name = "faculty_name")
    private String facultyName;

    @Column(name = "licensed_volume")
    private int licensedVolume;

    @Column(name = "budget_places")
    private int budgetPlaces;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "faculty_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "faculty_id"))
    private Set<User> users;

    @JoinColumn(name = "university_id", foreignKey = @ForeignKey(name = "university_id"))
    private Long university_id;
}
