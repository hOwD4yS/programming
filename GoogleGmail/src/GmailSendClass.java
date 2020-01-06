public class GmailSendClass {
    GmailClass gmc;
    String targetemail;
    String title , content;

    GmailSendClass(GmailClass gmc , String targetemail, String title , String content){
        this.gmc = gmc;
        this.targetemail = targetemail;
        this.title = title;
        this.content = content;
    }
}
