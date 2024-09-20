package com.example.bookmark.bo;

import com.example.bookmark.entity.BookmarkEntity;
import com.example.bookmark.entity.BookmarkID;
import com.example.bookmark.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookmarkBO {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    public boolean addBookmark(BookmarkEntity bookmark) throws Exception {

        Optional<BookmarkEntity> existingBookmark = bookmarkRepository.findById(bookmark.getId());
        if (existingBookmark.isPresent()) {
            throw new Exception("Bookmark already exists");
        }
        BookmarkEntity savedBookmark = bookmarkRepository.save(bookmark);
        if (savedBookmark == null) {
            throw new Exception("Failed to add bookmark");
        }

        return true;
    }

    public boolean deleteBookmark(BookmarkEntity bookmark) {

        Optional<BookmarkEntity> existingBookmark = bookmarkRepository.findById(bookmark.getId());
        if (existingBookmark.isEmpty()) {
            return false; // Bookmark not found
        }

        bookmarkRepository.deleteById(bookmark.getId());
        return true;
    }

//    public boolean checkIfBookmarkExists(String userid, String foodid) {
//        return bookmarkRepository.existsByUseridAndFoodid(userid, foodid);
//    }



//    public Optional<BookmarkEntity> getBookmarkById(String userId, String foodId) {
//        return bookmarkRepository.findById(new BookmarkID(userId, foodId));
//    }

    public List<BookmarkEntity> getUserBookmarks(String userId) {
       return bookmarkRepository.findById_Userid(userId);

    }
}
