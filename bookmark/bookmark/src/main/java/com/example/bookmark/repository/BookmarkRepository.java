package com.example.bookmark.repository;

import com.example.bookmark.entity.BookmarkEntity;
import com.example.bookmark.entity.BookmarkID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity, BookmarkID> {
    @Query("SELECT COUNT(b) > 0 FROM BookmarkEntity b WHERE b.id.userid = :userid AND b.id.foodid = :foodid")
    boolean existsByUseridAndFoodid(@Param("userid") String userid, @Param("foodid") String foodid);

    List<BookmarkEntity> findById_Userid(String userId);

}