package com.example.bookmark.controller;
import com.example.bookmark.bo.BookmarkBO;
import com.example.bookmark.entity.BookmarkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/bookmarks")
@CrossOrigin
public class BookmarkController {

    @Autowired
    private BookmarkBO bookmarkBO;

    @PostMapping("/add")
    public ResponseEntity<String> addBookmark(@RequestBody BookmarkEntity bookmark) {

        try {

            boolean success = bookmarkBO.addBookmark(bookmark);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to add bookmark");
        }
        return ResponseEntity.ok("Bookmark added successfully");
    }



    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBookmark(@RequestBody BookmarkEntity bookmarkEntity) {
        boolean success = bookmarkBO.deleteBookmark(bookmarkEntity);
        if (success) {
            return ResponseEntity.ok("Bookmark deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to delete bookmark");
        }
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookmarkEntity>> getUserBookmarks(@PathVariable String userId) {
        List<BookmarkEntity> userBookmarks = bookmarkBO.getUserBookmarks(userId);
        return ResponseEntity.ok(userBookmarks);
    }
}
