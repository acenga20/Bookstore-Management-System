package Models;
import java.io.*;
import java.util.ArrayList;

import Auxiliaries.FileHandler;


public class User extends BaseModel implements Serializable {
    private static  ArrayList<User> users = new ArrayList<>();
    private String name;
    private String surname;
    private String username; 
    private String password;
    private String birthday;
    private String phoneNr;
    private String email;
    private String salary;
    private Role role;
    public static final String FILE_PATH1 = "users.ser";
    public static final File DATA_FILE1 = new File(FILE_PATH1);
    @Serial
    private static final long serialVersionUID = 1234567L;
    public User() {}

    public User(String username, String password, Role role) {
        this(username, password);
        this.role = role;
    }

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
    }
    public User(String name,String surname,String username, String password, String birthday,String phoneNr,String email, String salary, Role role) {
        setName(name);
        setSurname(surname);
        setUsername(username);
        setPassword(password);
        setBirthday(birthday);
        setPhoneNr(phoneNr);
        setEmail(email);
        setSalary(salary);
        this.role = role;
    }
    @Override
    public boolean isValid() {
    	if(getName().matches("^[a-zA-Z'-]+$")
    		&& getSurname().matches("^[a-zA-Z'-]+$")
         && getUsername().length()>0 &&
    			getPassword().length()>0 && getBirthday().matches("\\d{4}-(0[1-9]|1[012])-([012][0-9]|3[01])")
    			&& getEmail().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    			&& getPhoneNr().matches("06[789]\\d{7}")&& getSalary().matches("\\d{3}"))
    		return true;//&& getBirthday().matches*//*("\\d{4}-(0[1-9]|1[012])-([012][0-9]|3[01])"))
    			/*getEmail().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")*/
    			// getSalary().matches("\\d{3}"))
    		
    	
    	return false;
    }
    
    public boolean exists() {
    	for(User b: users) {
    		if(b.getUsername().matches(this.getUsername()))
    			return true;
    	}
    	return false;
    	
    }

    public String getUsername() {
        return username;
    }
    

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhoneNr() {
		return phoneNr;
	}

	public void setPhoneNr(String phoneNr) {
		this.phoneNr = phoneNr;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "username=" + getUsername() +
                ", password=" + getPassword() +
                ", role=" + getRole() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User other = (User) obj;
            return other.getUsername().equals(getUsername()) && other.getPassword().equals(getPassword());
        }
        return false;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    public static User getIfExists(User potentialUser) {
        for(User user: getUsers())
            if (user.equals(potentialUser))
                return user;
        return null;
    }
    
    public static ArrayList<User> getUsers () {
        if (users.size() == 0){
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH1));
                while (true) {
                    User temp = (User) inputStream.readObject();
                    if (temp == null)
                        break;
                    users.add(temp);
                }
                inputStream.close();
            } catch (EOFException eofException) {
                System.out.println("End of file reached!");
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public boolean saveInFile() {
        // write the logic that saves a user in the file
        // return true if no exception happened, otherwise false
        boolean saved = super.save(User.DATA_FILE1);
        if (saved)
            users.add(this);
        return saved;
    }

  
    public static ArrayList<User> getSearchUserResults(String searchText) {
        // don't do it this way, build a regex
        ArrayList<User> searchResults = new ArrayList<>();
        for(User user: getUsers())
            if (user.getName().toLowerCase().contains(searchText.toLowerCase()) || user.getSurname().toLowerCase().contains(searchText.toLowerCase())
            		||user.getUsername().toLowerCase().contains(searchText.toLowerCase())
            		|| user.getBirthday().toLowerCase().contains(searchText.toLowerCase()))
            	searchResults.add(user);
        return searchResults;
    }
    @Override
    public boolean deleteFromFile() {
    	 users.remove(this);
         try {
             FileHandler.overwriteCurrentListToFile(DATA_FILE1, getUsers());
//             overwriteCurrentListToFile();
         } catch (IOException exception) {
             exception.printStackTrace();
             return false;
         }
         return true;
    }
}