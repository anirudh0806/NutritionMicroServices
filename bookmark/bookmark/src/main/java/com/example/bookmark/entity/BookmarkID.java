package com.example.bookmark.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookmarkID implements Serializable {
    private String userid;
    private String foodid;
}