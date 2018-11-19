/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bookmark.Kirja;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class KirjaTest {
    
    Kirja kirja;
    String otsikko, kirjoittaja, isbn, kommentti;
    List<String> tagit, esitietokurssit, liittyvatKurssit;
    
    public KirjaTest() {
    }
    
    @Before
    public void setUp() {
        otsikko = "Lumi";
        kirjoittaja = "Orhan Pamuk";
        isbn = "978-951-31-7583-2";
        kirja = new Kirja(otsikko, kirjoittaja, isbn);
        kommentti = "Kommentti.";
        
        kirja.setKommentti(kommentti);
        
        tagit = new ArrayList<String>();
        tagit.add("Turkki");
        tagit.add("Nobel");
        kirja.setTagit(tagit);
        
        esitietokurssit = new ArrayList<String>();
        esitietokurssit.add("Valkoinen linna");
        esitietokurssit.add("Musta kirja");
        kirja.setEsitietokurssit(esitietokurssit);
        
        liittyvatKurssit = new ArrayList<String>();
        liittyvatKurssit.add("Jee");
        kirja.setLiittyvatKurssit(liittyvatKurssit);
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriAsettaaMuuttujatOikein() {
        assertEquals(otsikko, kirja.getOtsikko());
        assertEquals(kirjoittaja, kirja.getKirjoittaja());
        assertEquals(isbn, kirja.getIsbn());
        assertEquals("Kirja", kirja.getTyyppi());
        
    }
    
    @Test
    public void kommentiAsetetaanKirjalleOikein() {
        assertEquals(kommentti, kirja.getKommentti());
    }
    
    
    @Test
    public void tagienAsetusToimii() {
        assertEquals(tagit, kirja.getTagit());
    }
    
    @Test
    public void esitietokurssienAsetusToimii() {
        assertEquals(esitietokurssit, kirja.getEsitietokurssit());
    }
    
    @Test
    public void liittyvienKurssienAsetusToimii() {
        assertEquals(liittyvatKurssit, kirja.getLiittyvatKurssit());
    }
    
    @Test
    public void kirjanLukeminenToimii() {
        assertEquals(false, kirja.isLuettu());
        kirja.lue();
        assertEquals(true, kirja.isLuettu());
    }
    
    @Test
    public void listToimii() {
        String eka = "eka";
        String toka = "toka";
        List<String> lista = new ArrayList<String>();
        lista.add(eka);
        lista.add(toka);
        String pilkullaEroteltu = "eka, toka";
        assertEquals(pilkullaEroteltu, list(lista));
    }
    
    @Test
    public void attributeToStringToimii() {
        String palautusOtsikolla = Kirja.attributeToString("Otsikko", kirja.getOtsikko());
        assertEquals("Otsikko: " + otsikko + "\n", palautusOtsikolla);
        
        String palautusIlmanOtsikkoa = Kirja.attributeToString("Otsikko", "");
        assertEquals("", palautusIlmanOtsikkoa);
    }
    
    @Test
    public void toStringToimii() {
        String kirjoittajaToString = Kirja.attributeToString("Kirjoittaja", kirjoittaja);
        String isbnToString = Kirja.attributeToString("ISBN", isbn);
        String tyyppiToString = Kirja.attributeToString("Tyyppi", kirja.getTyyppi());
        String otsikkoToString = Kirja.attributeToString("Otsikko", otsikko);
        String tagitToString = Kirja.attributeToString("Tagit", list(tagit));
        String esitietokurssitToString = Kirja.attributeToString("Esitietokurssit", list(esitietokurssit));
        String liittyvatKurssitToString = Kirja.attributeToString("Aiheeseen liittyvät kurssit", list(liittyvatKurssit));
        String kommenttiToString = Kirja.attributeToString("Kommentti", kommentti);

        String vertailu = kirjoittajaToString+isbnToString+tyyppiToString+otsikkoToString+tagitToString+esitietokurssitToString+
                liittyvatKurssitToString+kommenttiToString;
        
        assertEquals(vertailu, kirja.toString());
        
    }
    
    
    // list-metodi kopsattu tänne testejä varten bugikorjattuna..
    private String list(List<String> list){
        String res="";
        for(int i=0;i<list.size();i++){
            if (i!=list.size()-1) {
                res+=list.get(i)+", ";
            } else {
                res+=list.get(i);
            }
        }
        return res;
    }
    
}
