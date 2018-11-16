package bookmark;


import bookmark.Lukuvinkki;

public class Kirja extends Lukuvinkki{
    private String kirjoittaja;
    private String isbn;
    
    public Kirja(String otsikko, String kirjoittaja, String isbn){
        super.setOtsikko(otsikko);
        super.setTyyppi("Kirja");
        this.kirjoittaja=kirjoittaja;
        this.isbn=isbn;
    }

    public String getKirjoittaja() {
        return kirjoittaja;
    }

    public String getIsbn() {
        return isbn;
    }
    
    @Override
    public String toString(){
        return Lukuvinkki.attributeToString("Kirjoittaja", kirjoittaja)+
                Lukuvinkki.attributeToString("ISBN", isbn)+
                super.toString();
    }
}
