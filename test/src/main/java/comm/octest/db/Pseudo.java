package comm.octest.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import comm.octest.beans.Message;
import comm.octest.beans.User;

public class Pseudo {
	
	public String email;
	public static ArrayList<String> onlineFriends = new ArrayList<String>();

	public Pseudo() {
	}

	public Pseudo(String email) {
		this.email = email;
	}

	public void addOnlineFriends(){
		onlineFriends.add(this.email);
	}



	Connection connexion = null;
	Statement statement = null  ; 
	ResultSet resultat = null;

	public String getUsername(){
		String username = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
			statement = connexion.createStatement() ;

			PreparedStatement preparedStatement = connexion.prepareStatement("SELECT username FROM user WHERE email=?");
			preparedStatement.setString(1, this.email);

			ResultSet resultat = preparedStatement.executeQuery();
			resultat.next();
			username = resultat.getString("username");

		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}


		return username;
	}

	public  void updateUsername(String username) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		}

		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
			System.out.println("Connexion reussite ") ;
			PreparedStatement preparedStatement = connexion.prepareStatement("UPDATE user SET username=? WHERE email =?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, email);
			preparedStatement.executeUpdate();

		}catch (SQLException e) {
		}

	}

	public  void removeContact(String emailRemoved) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		}

		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
			System.out.println("Connexion reussite ") ;
			PreparedStatement preparedStatement = connexion.prepareStatement("DELETE FROM contact WHERE user_id=? AND id_friend=?");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, emailRemoved);
			preparedStatement.executeUpdate();

		}catch (SQLException e) {
		}

	}

	public User checkUserExist(String userEmail){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
			statement = connexion.createStatement() ;

			PreparedStatement preparedStatement = connexion.prepareStatement("SELECT * FROM user WHERE email=?");
			preparedStatement.setString(1, userEmail);

			ResultSet resultat = preparedStatement.executeQuery();

			if (resultat.next()){
				return new User(resultat.getString("username"),resultat.getString("email"));
			}
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return new User();
	}


	public ArrayList<String> getContacts(){
		ArrayList<String> contacts = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
			statement = connexion.createStatement() ;

			PreparedStatement preparedStatement = connexion.prepareStatement("SELECT id_friend FROM contact WHERE user_id=?");
			preparedStatement.setString(1, email);

			System.out.println("email : " + email);
			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				contacts.add(resultat.getString("id_friend"));
			}


		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return contacts;
	}


	public  void addContact(String emailContact) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		}

		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
			System.out.println("Connexion reussite ") ;
			PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO contact (user_id,id_friend) VALUES (?,?)");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, emailContact);
			preparedStatement.executeUpdate();

		}catch (SQLException e) {
		}

	}


	public  void insert( User user) {
		String username =user.getUsername() ;
		String email =user.getEmail() ;
		String password = user.getPassword() ;
		System.out.println(user.getEmail()) ;
		System.out.println(user.getUsername()) ;
		System.out.println(user.getPassword()) ;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		}

		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
			System.out.println("Connexion reussite ") ;
			PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO user (username, email, password) VALUES (?,?,?)");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, password);
			preparedStatement.executeUpdate();

		}catch (SQLException e) {
		}

	}


	public boolean authentification(User user) {

    String email = user.getEmail();
    String password = user.getPassword();
    boolean auth = false ; 
    
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
        PreparedStatement preparedStatement = connexion.prepareStatement("SELECT password FROM user WHERE email = ?");
        preparedStatement.setString(1, email);
        ResultSet resultat = preparedStatement.executeQuery();
        
        if (resultat.next()) {
            String passwordBb = resultat.getString("password");
            if( password.equals(passwordBb)) { 
            	auth = true ; 
            }
        } else {
               auth = false;
        }
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
       
    }
	return auth ;
}


	public void removeFriend (String emailFriend) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
			System.out.println("Connexion reussite ") ;
			PreparedStatement preparedStatement = connexion.prepareStatement("DELETE FROM contact WHERE user_id = ? AND friend_id =?");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, emailFriend);
			preparedStatement.executeUpdate();

		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertMsg (String to, String from, String msg, String type) throws Exception{

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
			System.out.println("Connexion reussite ") ;
			PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO message (id_sender,id_receiver,message,type_message) values(?,?,?,?)");
			preparedStatement.setString(1, from);
			preparedStatement.setString(2, to);
			if(type.equals("text")) {
				preparedStatement.setString(3, msg);
			}
			else {
				preparedStatement.setString(3, msg);
			}
			preparedStatement.setString(4, type);
			preparedStatement.executeUpdate();

		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}



	public ArrayList<Message> getMessagesWithUser (String withClientId) throws Exception{
		ArrayList<Message> messages = new ArrayList<Message>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
			System.out.println("Connexion reussite ") ;
			PreparedStatement preparedStatement = connexion.prepareStatement("SELECT * FROM message WHERE (id_sender=? AND id_receiver=?) OR (id_sender=? AND id_receiver=?) ");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, withClientId);
			preparedStatement.setString(3, withClientId);
			preparedStatement.setString(4, email);
			ResultSet res = preparedStatement.executeQuery();
			while (res.next()) {
				Message msg = new Message(res.getString("id_sender"),res.getString("id_receiver"),res.getString("message"),res.getString("type_message"));
				messages.add(msg);
			}

		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}

	public void removeMsg (String withClientId) throws Exception{

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
			System.out.println("Connexion reussite ") ;
			System.out.println("yes");
			PreparedStatement preparedStatement = connexion.prepareStatement("DELETE FROM message WHERE id_receiver=? AND id_sender=?");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, withClientId);
			preparedStatement.executeUpdate();

		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


	public boolean validerInput(String username,String email,String password,String passwordc) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
		System.out.println("Connexion reussite ") ;
		PreparedStatement preparedStatement = connexion.prepareStatement("SELECT * FROM user WHERE username = ?");
		preparedStatement.setString(1, username);
		ResultSet resultat = preparedStatement.executeQuery();
		if (resultat.next()){
			return false;
		}else {
			preparedStatement = connexion.prepareStatement("SELECT * FROM user WHERE email = ?");
			preparedStatement.setString(1, email);
			resultat = preparedStatement.executeQuery();
			if (resultat.next()){
				return false;
			}else {
				if (password.equals(passwordc)){
					return true;
				}else {
					return false;
				}
			}
		}
	}



}











	/*private void loadDatabase() {
        // Chargement du driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }

        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                // Fermer la connexion
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	*/






