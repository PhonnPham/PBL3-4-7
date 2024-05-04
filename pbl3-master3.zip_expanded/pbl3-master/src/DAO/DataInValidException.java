package DAO;


import DAO.InValidAuthorException;

/**
 *
 * @author DELL
 */
public interface DataInValidException {

    Boolean inValidAuthorException(String _tacgia)  throws InValidAuthorException;


    
}