package com.itamnesia.bhcrud.model;

import com.itamnesia.bhcrud.model.user.Expert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
import javax.persistence.OneToOne;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@Table
@Entity(name = "announcements")
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "announcement_id", nullable = false)
    private Long announcementId;

    @Column(name = "type")
    private String type;

    @Column(name = "title")
    private String title;

    @Column(name = "address")
    private String address;

    @Column(name = "floor")
    private Long floor;

    @Column(name = "floors")
    private Long floors;

    @Column(name = "sq")
    private Float sq;

    @Column(name = "cost")
    private Long cost;

    @Column(name = "text")
    private String text;

    @Column(name = "lat")
    private String lat;

    @Column(name = "lng")
    private String lng;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "url")
    private String url;

    @Column(name = "is_agent")
    private boolean isAgent;

    @Column(name = "source")
    private String source;

    @Column(name = "created_date")
    private OffsetDateTime createdDate;

    @Column(name = "region")
    private String region;

    @Column(name = "city")
    private String city;

    @Column(name = "material")
    private String material;

    @Column(name = "is_new")
    private boolean isNew;

    @Column(name = "rooms")
    private Long rooms;

    @Column(name = "category")
    private String category;

    @Column(name = "section")
    private String section;

    @OneToOne
    @JoinColumn(name = "expert_id")
    private Expert expert;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "images", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "image")
    private Set<String> images = new HashSet<>();

    @OneToOne(mappedBy = "announcement")
    private AnnouncementReport announcementReport;
}
