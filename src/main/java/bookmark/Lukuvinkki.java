package bookmark;

import java.util.ArrayList;
import java.util.List;

public abstract class Lukuvinkki {
    private String type="";
    private String title="";
    private List<String> tags=new ArrayList<>();
    private List<String> prequisites=new ArrayList<>();
    private List<String> liittyvatKurssit=new ArrayList<>();
    private String kommentti="";
    private boolean luettu=false;

    public void setType(String tyyppi) {
        this.type = tyyppi;
    }

    public void setTitle(String otsikko) {
        this.title = otsikko;
    }

    public void setTagit(List<String> tagit) {
        this.tags = tagit;
    }

    public void setEsitietokurssit(List<String> esitietokurssit) {
        this.prequisites = esitietokurssit;
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
        return type;
    }

    public String getOtsikko() {
        return title;
    }
    
    public List<String> getTagit() {
        return tags;
    }

    public List<String> getEsitietokurssit() {
        return prequisites;
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
            if (i!=list.size()-1) {
                res+=list.get(i)+", ";
            } else {
                res+=list.get(i);
            }
        }
        return res;
    }
    
    @Override
    public String toString(){
        return attributeToString("Tyyppi", type)+
                attributeToString("Otsikko", title)+
                attributeToString("Tagit", list(tags))+
                attributeToString("Esitietokurssit", list(prequisites))+
                attributeToString("Aiheeseen liittyvät kurssit", list(liittyvatKurssit))+
                attributeToString("Kommentti", kommentti);
    }
}
