package com.oncb.service;

import com.oncb.pojo.Page;
import com.oncb.pojo.Textbook;
import com.oncb.dao.TextbookDao;

public class TextbookService {
    private TextbookDao textbookDao = new TextbookDao();

    public void add(Textbook textbook) {
        textbookDao.add(textbook);
    }

    public void delete(String isbn) {
        textbookDao.delete(isbn);
    }

    public void edit(Textbook textbook) {
        textbookDao.edit(textbook);
    }

    public Textbook find(String isbn) {
        return textbookDao.find(isbn);
    }

    public Page<Textbook> query(Textbook textbook, int currPage, int pageRecord) {
        return textbookDao.query(textbook, currPage, pageRecord);
    }
}
