package Models;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import Auxiliaries.FileHandler;

public class Order extends BaseModel implements Serializable{
	 private String isbn;
	    private String title;
	    private float purchasedPrice;
	    private float sellingPrice;
	    private Author author;
	    private int quantity;
	    private String category;
	    private static final String FILE_PATH = "orders.ser";
	    public static final File DATA_FILE = new File(FILE_PATH);
	    @Serial
	    private static final long serialVersionUID = 1234567L;
	    
	    private static final ArrayList<Order> orders = new ArrayList<>();
	    public Order(){}

	    public Order(String isbn, String title, float purchasedPrice, float sellingPrice, Author author,int quantity,String category) {
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
	    public static ArrayList<Order> getSearchBookResults(String searchText) {
	        // don't do it this way, build a regex
	        ArrayList<Order> searchResults = new ArrayList<>();
	        for(Order order: getOrders())
	            if (order.getTitle().toLowerCase().contains(searchText.toLowerCase()))
	            	searchResults.add(order);
	        return searchResults;
	    }

	    @Override
	    public boolean saveInFile() {
	        boolean saved = super.save(Order.DATA_FILE);
	        if (saved)
	            orders.add(this);
	        return saved;
	    }
	    @Override
	    public boolean isValid() {
	    
	    	 if(this.purchasedPrice>0 && this.sellingPrice>0 && 
	    			 this.isbn.matches("\\d{3}-\\d-\\d{2}-\\d{6}-\\d")&& 
	    			 this.quantity>0){
	    		return true;}
	    
	    	return false;
	    }
	    	@Override
	    public boolean deleteFromFile() {
	        // todo take care of books as well
	        orders.remove(this);
	        try {
	            FileHandler.overwriteCurrentListToFile(DATA_FILE, getOrders());
//	            overwriteCurrentListToFile();
	        } catch (IOException exception) {
	            exception.printStackTrace();
	            return false;
	        }
	        return true;
	    }
	    	
	    public static ArrayList<Order> getOrders() {
	        if (orders.size() == 0) {
	            try {
	                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
	                while (true) {
	                    Order temp = (Order) inputStream.readObject();
	                    if (temp != null)
	                        orders.add(temp);
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
	        return orders;
	    }

}
