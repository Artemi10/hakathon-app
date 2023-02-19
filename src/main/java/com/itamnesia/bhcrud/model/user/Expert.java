package com.itamnesia.bhcrud.model.user;

import com.itamnesia.bhcrud.model.Announcement;
import com.itamnesia.bhcrud.model.AnnouncementReport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@Table
@Entity(name = "experts")
@NoArgsConstructor
@AllArgsConstructor
public class Expert {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "code")
    private String code;

    @Column(name = "password")
    private String password;

    @Column(name = "telegram_link")
    private String telegramLink;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToOne(mappedBy = "expert")
    private Announcement latestAnnouncement;

    @OneToMany(mappedBy = "expert", fetch = FetchType.EAGER)
    private Set<AnnouncementReport> announcementsReports = new HashSet<>();

    public double getTrustRating() {
        double total = announcementsReports.stream()
                .flatMap(it -> it.getRatings().stream())
                .reduce(0, Integer::sum);
        double amount = Math.toIntExact(announcementsReports.stream()
                .mapToLong(it -> it.getRatings().size())
                .sum());
        if (total == 0 || amount == 0) {
            return 0.0;
        } else {
            return total / amount;
        }
    }

}
