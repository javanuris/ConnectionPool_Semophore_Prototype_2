package nuris.epam.service;

import nuris.epam.dao.BookInfoDao;
import nuris.epam.dao.TransactionDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.entity.BookInfo;
import nuris.epam.entity.Customer;
import nuris.epam.entity.Transaction;
import nuris.epam.service.exception.ServiceException;
import nuris.epam.service.util.SqlDate;


/**
 * Created by User on 27.03.2017.
 */
public class TransactionService {

    public Transaction takeBook(Transaction transaction) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                BookService bookService = new BookService();
                BookInfo bookInfo = bookService.findById(transaction.getBookInfo().getId());
                TransactionDao transactionDao = (TransactionDao) daoFactory.getDao(daoFactory.typeDao().getTransactionDao());

                if (bookInfo.getAmount() > 0) {
                    bookInfo.setAmount(bookInfo.getAmount() - 1);
                    daoFactory.startTransaction();
                    bookService.updateBookInfo(bookInfo);
                    transaction.setStartDate(SqlDate.currentDateAndTime());
                    transaction = transactionDao.insert(transaction);
                    daoFactory.commitTransaction();

                } else {
                    System.out.println("Книг нет");
                }
                return transaction;
            } catch (DaoException e) {
                try {
                    daoFactory.rollbackTransaction();
                } catch (DaoException e1) {
                    e1.printStackTrace();
                }
                throw new ServiceException("can't find bu login customer", e);
            }
        }

    }

    public Transaction returnBook(Transaction transaction, Customer customer) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                BookService bookService = new BookService();
                TransactionDao transactionDao = (TransactionDao) daoFactory.getDao(daoFactory.typeDao().getTransactionDao());
                BookInfoDao bookInfoDao = (BookInfoDao) daoFactory.getDao(daoFactory.typeDao().getBookInfoDao());
                transaction = transactionDao.findById(transaction.getId());
                BookInfo bookInfo = bookInfoDao.findByTransaction(transaction);
                bookInfo.setAmount(bookInfo.getAmount() + 1);
                transaction.setBookInfo(bookInfo);
                transaction.setCustomer(customer);
                transaction.setEndDate(SqlDate.currentDateAndTime());

                daoFactory.startTransaction();
                transactionDao.update(transaction);
                bookService.updateBookInfo(bookInfo);
                daoFactory.commitTransaction();
                return transaction;

            } catch (DaoException e) {
                try {
                    daoFactory.rollbackTransaction();
                } catch (DaoException e1) {
                    e1.printStackTrace();
                }
                throw new ServiceException("can't find bu login customer", e);
            }
        }

    }
}
