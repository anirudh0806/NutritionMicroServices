package com.example.bookmark.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="bookmarks2")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BookmarkEntity {
    @EmbeddedId
    private BookmarkID id;


}
