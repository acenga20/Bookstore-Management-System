package Models;

import java.io.*;
import java.util.ArrayList;

import Auxiliaries.FileHandler;

public class Book extends BaseModel implements Serializable{
    private String isbn;
    private String title;
    private float purchasedPrice;
    private float sellingPrice;
    private Author author;
    private int quantity;
    private String category;
    private static final String FILE_PATH = "books.ser";
    public static final File DATA_FILE = new File(FILE_PATH);
    @Serial
    private static final long serialVersionUID = 1234567L;
    
    private static final ArrayList<Book> books = new ArrayList<>();
    public Book(){}

    public Book(String isbn, String title, float purchasedPrice, float sellingPrice, Author author,int quantity,String category) {
        this.isbn = isbn;
        this.title = title;
        this.purchasedPrice = purchasedPrice;
        this.sellingPrice = sellingPrice;
        this.author = author;
        this.quantity= quantity;
        this.category=category;
    }

    @Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.isbn+this.title;
	}

	public float getPurchasedPrice() {
        return purchasedPrice;
    }

    public void setPurchasedPrice(float purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }

    public float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    public int getQuantity() {
    	return quantity;
    }
    public void setQuantity(int quantity) {
    	this.quantity=quantity;
    }
    public String getCategory() {
    	return category;
    }
    public void setCategory(String category) {
    	this.category=category;
    }
    public static ArrayList<Book> getSearchBookResults(String searchText) {
        // don't do it this way, build a regex
        ArrayList<Book> searchResults = new ArrayList<>();
        for(Book book: getBooks())
            if (book.getTitle().toLowerCase().contains(searchText.toLowerCase())|| book.getIsbn().toLowerCase().contains(searchText.toLowerCase())
            		|| book.getCategory().toLowerCase().contains(searchText.toLowerCase())|| book.getAuthor().getFirstName().toLowerCase().contains(searchText.toLowerCase()))
            	searchResults.add(book);
        return searchResults;
    }

    @Override
    public boolean saveInFile() {
        boolean saved = super.save(Book.DATA_FILE);
        if (saved)
            books.add(this);
        return saved;
    }
    @Override
    public boolean isValid() {
    
    	 if(this.purchasedPrice>0 && this.sellingPrice>0 && 
    			 this.isbn.matches("\\d{3}-\\d-\\d{2}-\\d{6}-\\d")&& 
    			 this.quantity>0 && this.category.matches("^[a-zA-Z'-]+$")){
    		return true;}
    
    	return false;
    }
    public boolean exists() {
    	for(Book b: books) {
    		if(b.getIsbn().matches(this.getIsbn()))
    			return true;
    	}
    	return false;
    }
    	@Override
    public boolean deleteFromFile() {
        // todo take care of books as well
        books.remove(this);
        try {
            FileHandler.overwriteCurrentListToFile(DATA_FILE, getBooks());
//            overwriteCurrentListToFile();
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }
    	
    public static ArrayList<Book> getBooks() {
        if (books.size() == 0) {
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
                while (true) {
                    Book temp = (Book) inputStream.readObject();
                    if (temp != null)
                        books.add(temp);
                    else
                        break;
                }
                inputStream.close();
            } catch (EOFException eofException) {
                System.out.println("End of book file reached!");
            }
            catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return books;
    }
}
