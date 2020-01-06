import javax.mail.Store;

public class GmailClass {
    Store store;
    String email , password;

    GmailClass(Store store , String email , String password){
        this.store = store;
        this.email = email;
        this.password = password;
    }
}
