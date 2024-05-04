package DAO;

public class InValidAuthorException extends Exception{
    private String _tacgia;

    public InValidAuthorException(String _tacgia, String message) {
        super(message);
        this._tacgia = _tacgia;
    }

    public String getAuthor() {
        return _tacgia;
    }

    public void setAuthor(String _tacgia) {
        this._tacgia = _tacgia;
    }
    
    
}
