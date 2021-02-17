package com.example.subaseshopproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String text;
    @Column
    private Date createdDate;
    @Column
    private String picUrl;
    @ManyToOne
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;
}
