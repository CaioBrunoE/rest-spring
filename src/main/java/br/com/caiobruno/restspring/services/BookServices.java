package br.com.caiobruno.restspring.services;

import br.com.caiobruno.restspring.controllers.BookControllers;
import br.com.caiobruno.restspring.data.vo.v1.BookVO;
import br.com.caiobruno.restspring.exceptions.RequiredObjectIsNullException;
import br.com.caiobruno.restspring.exceptions.ResourceNotFoundException;
import br.com.caiobruno.restspring.mapper.DozerMapper;
import br.com.caiobruno.restspring.model.Book;
import br.com.caiobruno.restspring.reposittories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {

    @Autowired
    BookRepository repository;


    public List<BookVO> findAll() {

        List<Book> listBockEnt = repository.findAll();

        List<BookVO> books = DozerMapper.parseListObjects(listBockEnt, BookVO.class);

        books.stream()
                .forEach(b -> b.add(linkTo(methodOn(BookControllers.class).findById(b.getKey())).withSelfRel()));

        return books;

    }

    public BookVO findById(Long id) {

        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        BookVO bookVO = DozerMapper.parseObject(book, BookVO.class);

        bookVO.add(linkTo(methodOn(BookControllers.class).findById(id)).withSelfRel());


        return bookVO;
    }


    public BookVO create(BookVO bookVO) {

        if (bookVO == null) throw new RequiredObjectIsNullException();

        Book entity = DozerMapper.parseObject(bookVO, Book.class);

        repository.save(entity);

        BookVO rBookVo = DozerMapper.parseObject(entity, BookVO.class);

        rBookVo.add(linkTo(methodOn(BookControllers.class).findById(bookVO.getKey())).withSelfRel());


        return rBookVo;

    }

    @PutMapping
    public BookVO update(BookVO bookVO) {


        if (bookVO == null) throw new RequiredObjectIsNullException();

        Book entity = DozerMapper.parseObject(bookVO, Book.class);

        entity = repository.findById(bookVO.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setAuthor(bookVO.getAuthor());
        entity.setLauchDate(bookVO.getLauchDate());
        entity.setPrice(bookVO.getPrice());
        entity.setTitle(bookVO.getTitle());

        repository.save(entity);

        BookVO rBookVo = DozerMapper.parseObject(entity, BookVO.class);

        rBookVo.add(linkTo(methodOn(BookControllers.class).findById(bookVO.getKey())).withSelfRel());


        return rBookVo;
    }

    public void delete(Long id) {

        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }

}
