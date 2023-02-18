package com.itamnesia.bhcrud.model;

import com.itamnesia.bhcrud.model.user.Expert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Table
@Entity(name = "announcement_reports")
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementReport {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "rating", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "rating")
    private List<Integer> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "announcementReport", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private List<Risk> risks = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "annoucement_id")
    @EqualsAndHashCode.Exclude
    private Announcement announcement;

    @ManyToOne
    @JoinColumn(name = "expert_id")
    @EqualsAndHashCode.Exclude
    private Expert expert;
}
