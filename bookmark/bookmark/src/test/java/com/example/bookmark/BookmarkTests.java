package com.example.bookmark;

import com.example.bookmark.bo.BookmarkBO;
import com.example.bookmark.config.JavaConstant;
import com.example.bookmark.config.ResourcesBo;
import com.example.bookmark.controller.BookmarkController;
import com.example.bookmark.entity.BookmarkEntity;
import com.example.bookmark.entity.BookmarkID;
import com.example.bookmark.repository.BookmarkRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class BookmarkTests {

    @Autowired
    private BookmarkController bookmarkController;

    @Autowired
    private BookmarkBO bookmarkBO;

    @Autowired
    private BookmarkRepository bookmarkRepository;



    @Test
    public void mainControllerTest(){
        BookmarkApplication.main(new String[]{ResourcesBo.getKey(JavaConstant.SAMPLE_STRING)});
    }


    @Test
    @Order(1)
    void testAddBookmark() {
        BookmarkEntity bookmark = new BookmarkEntity();
        bookmark.setId(new BookmarkID((ResourcesBo.getKey(JavaConstant.USER_ID)), ResourcesBo.getKey(JavaConstant.FOOD_ID)));

        ResponseEntity<String> responseEntity = bookmarkController.addBookmark(bookmark);
        assertEquals(ResourcesBo.getKey(JavaConstant.BOOK_ADDED), responseEntity.getBody());
    }

    @Test
    @Order(2)
    void testAddDuplicateBookmark() {
        BookmarkEntity bookmark = new BookmarkEntity();
        bookmark.setId(new BookmarkID(ResourcesBo.getKey(JavaConstant.USER_ID), ResourcesBo.getKey(JavaConstant.FOOD_ID)));
        bookmarkController.addBookmark(bookmark);
        ResponseEntity<String> responseEntity = bookmarkController.addBookmark(bookmark);

        assertEquals(ResourcesBo.getKey(JavaConstant.BOOK_ADDED_FAILED), responseEntity.getBody());
    }

    @Test
    @Order(3)
    void testDeleteBookmark() {
        BookmarkEntity bookmark = new BookmarkEntity();
        bookmark.setId(new BookmarkID(ResourcesBo.getKey(JavaConstant.USER_ID), ResourcesBo.getKey(JavaConstant.FOOD_ID)));
        ResponseEntity<String> responseEntity = bookmarkController.deleteBookmark(bookmark);
        assertEquals(ResourcesBo.getKey(JavaConstant.BOOK_DELETED), responseEntity.getBody());
    }

    @Test
    @Order(4)
    void testDeleteAbsentBookmark() {
        BookmarkEntity bookmark = new BookmarkEntity();
        bookmark.setId(new BookmarkID(ResourcesBo.getKey(JavaConstant.USER_ID), ResourcesBo.getKey(JavaConstant.FOOD_ID)));

        ResponseEntity<String> responseEntity = bookmarkController.deleteBookmark(bookmark);
        assertEquals(ResourcesBo.getKey(JavaConstant.BOOK_DELETED_FAILED), responseEntity.getBody());
    }




    @Test
    @Order(5)
    void testGetBookmarkByUserID() {
        ResponseEntity<List<BookmarkEntity>> responseEntity = bookmarkController.getUserBookmarks(ResourcesBo.getKey(JavaConstant.EXISTING_USER_ID));
        assertNotNull(responseEntity.getBody());
    }
}
