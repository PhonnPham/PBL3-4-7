package DAO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DataInValidImplement implements DataInValidException  {

    
    @Override
    public Boolean inValidAuthorException(String _tacgia) throws InValidAuthorException{
        var regex = "^[^0-9!@#$%^&*()-=<>/;?':_]{2,40}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(_tacgia);
        if(matcher.matches()){
           return true;
        }else{
            throw  new InValidAuthorException(_tacgia, "ten tac gia kh học le");
           
        }
    }
}
