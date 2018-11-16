package bookmark;

import java.util.ArrayList;
import java.util.List;

public abstract class Lukuvinkki {
    private String tyyppi="";
    private String otsikko="";
    private List<String> tagit=new ArrayList<>();
    private List<String> esitietokurssit=new ArrayList<>();
    private List<String> liittyvatKurssit=new ArrayList<>();
    private String kommentti="";
    private boolean luettu=false;

    public void setTyyppi(String tyyppi) {
        this.tyyppi = tyyppi;
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public void setTagit(List<String> tagit) {
        this.tagit = tagit;
    }

    public void setEsitietokurssit(List<String> esitietokurssit) {
        this.esitietokurssit = esitietokurssit;
    }

    public void setLiittyvatKurssit(List<String> liittyvatKurssit) {
        this.liittyvatKurssit = liittyvatKurssit;
    }

    public void setKommentti(String kommentti) {
        this.kommentti = kommentti;
    }
    
    public void lue(){
        this.luettu=true;
    }
    
    public String getTyyppi() {
        return tyyppi;
    }

    public String getOtsikko() {
        return otsikko;
    }
    
    public List<String> getTagit() {
        return tagit;
    }

    public List<String> getEsitietokurssit() {
        return esitietokurssit;
    }

    public List<String> getLiittyvatKurssit() {
        return liittyvatKurssit;
    }

    public String getKommentti() {
        return kommentti;
    }

    public boolean isLuettu() {
        return luettu;
    }
    
    // Palauttaa rivin toString varten, jos se (s) ei ole tyhjä
    public static String attributeToString(String attribute, String s){
        return (s.isEmpty()?"":attribute+": "+s+"\n");
    }
    
    // Erottaa listan alkiot pilkulla
    private static String list(List<String> list){
        String res="";
        for(int i=0;i<list.size();i++){
            if(i!=list.size()-1) res+=list.get(i)+", ";
        }
        return res;
    }
    
    @Override
    public String toString(){
        return attributeToString("Tyyppi", tyyppi)+
                attributeToString("Otsikko", otsikko)+
                attributeToString("Tagit", list(tagit))+
                attributeToString("Esitietokurssit", list(esitietokurssit))+
                attributeToString("Aiheeseen liittyvät kurssit", list(liittyvatKurssit))+
                attributeToString("Kommentti: ", kommentti);
    }
}
