import java.io.Serializable;
/**

 Authors:
 Mohammed Riaz Khan
 Clayton Petersen

 */
public class Dvd implements Serializable {

    private String title;
    private String category;
    private boolean newRelease;
    private double price;
    private boolean availableForRent;
    private String year;


    public Dvd(){

    }

    public Dvd(String title, String category, String releaseYear, boolean newRelease, boolean avail){

        this.title = title;
        this.category = category;
        this.newRelease = newRelease;
        this.availableForRent = avail;
        this.year = releaseYear;

    }

    public void setTitle(String aTitle){

        title = aTitle;

    }

    public void setCategory(String aCategory) {

        category = aCategory;

    }

    public void setRelease(boolean rel){

        newRelease = rel;

    }

    private void setPrice(){


    }

    public void setAvailable(boolean avail){

        availableForRent = avail;

    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public boolean isNewRelease() {
        return newRelease;
    }

    public boolean isAvailableForRent() {
        return availableForRent;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String yearR) {

        year = yearR;

    }

    @Override
    public String toString() {
        return " ";
    }
}
