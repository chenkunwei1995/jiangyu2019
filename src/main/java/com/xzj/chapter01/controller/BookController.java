package com.xzj.chapter01.controller;

import com.xzj.chap1.model.Book;
import com.xzj.chap1.model.BookThyeleaf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    Book book;
    @GetMapping("/book")
    public String book(){
        return book.toString();
    }

    @GetMapping("/bookThref")
    public ModelAndView bookThref(){
        List<BookThyeleaf> bookList=new ArrayList<BookThyeleaf>();
        BookThyeleaf book=new BookThyeleaf();
        book.setName("三国演义");
        book.setAuthor("罗贯中");
        book.setId(1L);
        bookList.add(book);
        book=new BookThyeleaf();
        book.setName("红楼梦");
        book.setAuthor("曹雪芹");
        book.setId(2L);
        bookList.add(book);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("books",bookList);
        modelAndView.setViewName("bookThreyView");
        return modelAndView;
    }

    @GetMapping("/bookJsonThref")
    @ResponseBody
    public BookThyeleaf bookJsonThref(){
        BookThyeleaf book=new BookThyeleaf();
        book.setName("三国演义");
        book.setAuthor("罗贯中");
        book.setPrice(135f);
        book.setPublicationDate(new Date());
        book.setId(1L);


        return book;
    }

    @GetMapping("/bookJson")
    @ResponseBody
    public Book bookJson(){
        Book book=new Book();
        book.setName("三国演义");
        book.setAuthor("罗贯中");
        book.setPrice(135f);
        book.setPubliccationDate(new Date());

        return book;
    }
}
