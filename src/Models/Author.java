package Models;
import Auxiliaries.FileHandler;

import java.io.*;
import java.util.ArrayList;

public class Author extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1234567L;
    private String firstName;

    private String lastName;

    private static final ArrayList<Author> authors = new ArrayList<>();
    public static final String FILE_PATH = "authors.ser";
    public static final File DATA_FILE = new File(FILE_PATH);

    public static ArrayList<Author> getSearchResults(String searchText) {
        // don't do it this way, build a regex
        ArrayList<Author> searchResults = new ArrayList<>();
        for(Author author: getAuthors())
            if (author.getFullName().toLowerCase().contains(searchText.toLowerCase()))
                searchResults.add(author);
        return searchResults;
    }


    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Author(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public boolean saveInFile() {
        boolean saved = super.save(Author.DATA_FILE);
        if (saved)
            authors.add(this);
        return saved;
    }
   
    public boolean exists() {
    	for(Author b: authors) {
    		if(b.getFirstName().matches(this.getFirstName())&& b.getLastName().matches(this.getLastName()))
    			return true;
    	}
    	return false;
    }

    public boolean isValid() {
    	//You can't add an author if his name contains numbers.
    	if(getFirstName().matches("^[a-zA-Z'-]+$") && getLastName().matches("^[a-zA-Z'-]+$"))
    		return true;
    	
    	return false;
     
    }

    @Override
    public boolean deleteFromFile() {
        // todo take care of books as well
        authors.remove(this);
        try {
            FileHandler.overwriteCurrentListToFile(DATA_FILE, getAuthors());
//            overwriteCurrentListToFile();
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<Author> getAuthors() {
        if (authors.size() == 0) {
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
                while (true) {
                    Author temp = (Author) inputStream.readObject();
                    if (temp != null)
                        authors.add(temp);
                    else
                        break;
                }
                inputStream.close();
            } catch (EOFException eofException) {
                System.out.println("End of author file reached!");
            }
            catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return authors;
    }
}