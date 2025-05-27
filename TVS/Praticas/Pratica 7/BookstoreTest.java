package com.bookstore;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bookstore.domain.Book;
import com.bookstore.domain.SearchCriteria;
import com.bookstore.exception.CannotProcessRequestException;
import com.bookstore.exception.ServiceUnavailableException;
import com.bookstore.service.BookstoreService;

@Test
public class BookstoreTest {
    
    private BookstoreService service;
    private Bookstore store;
    private final Book b10 = new Book("title 10", new BigDecimal("10"), "author 10", "isbn 10");
    private final Book b15 = new Book("title 15", new BigDecimal("15"), "author 15", "isbn 15");
    private final Book b20 = new Book("title 20", new BigDecimal("20"), "author 20", "isbn 20");
    
    @BeforeMethod
    private void setUp() {
        service = mock(BookstoreService.class);
        store = new Bookstore(service);
    }

    public void givenValidBookAndServiceWhenAddBookThenBookIsAdded() throws CannotProcessRequestException, ServiceUnavailableException {
        Book book = mock(Book.class);
        
        store.addBook(book);
        
        verify(service, times(1)).storeBook(book); // OR verify(service).storeBook(book);
    }
    
    public void givenNullBookWhenAddBookThenDoNothingAndThrowNullPointer() throws ServiceUnavailableException, CannotProcessRequestException {
        assertThrows(NullPointerException.class, () -> store.addBook(null));
        
        verify(service, never()).storeBook(any());
    }
    
    public void givenUnavailableServiceWhenAddBookThenThrowCannotProcessRequestException() throws ServiceUnavailableException {
        Book book = mock(Book.class);
        // To modify methods behavior: when(mock.invocation()).thenReturn(value);
        // To modify VOID methods behavior: doReturn(value).when(mock).invocation(args)
        // You can also use thenThrow and doThrow to exceptions
        doThrow(new ServiceUnavailableException()).when(service).storeBook(book);
        
        assertThrows(CannotProcessRequestException.class, () -> store.addBook(book));
        
        verify(service).storeBook(book);
    }
    
    public void givenNullPriceAndNoBooksWhenSearchThenReturnNull() throws CannotProcessRequestException {
        List<Book> res = store.search("author", "title", null);
        
        assertNull(res);
    }
    
    public void givenNullPriceAndSeveralBooksWhenSearchThenReturnAllBooks() throws CannotProcessRequestException, ServiceUnavailableException {
        List<Book> books = List.of(b10, b15, b20);
        
        when(service.findBooks(new SearchCriteria("title", "author"))).thenReturn(books);
        
        List<Book> res = store.search("author", "title", null);
        
        assertEquals(res.size(), 3);
        assertTrue(res.contains(b10));
        assertTrue(res.contains(b15));
        assertTrue(res.contains(b20));
    }
    
}
