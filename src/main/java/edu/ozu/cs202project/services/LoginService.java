package edu.ozu.cs202project.services;

import edu.ozu.cs202project.Salter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class LoginService
{
    @Autowired
    JdbcTemplate conn;


    public boolean validateUser(String username, String password)
    {

        List<Map<String, Object>> response = conn.queryForList(
                "SELECT * FROM users WHERE users_name = ? AND users_password = ?", new Object[]{ username, password }
        );

        return response.size() == 1;
    }

    public int getUserId(String username)
    {
        List<Map<String, Object>> response = conn.queryForList(
                "SELECT users_id FROM users WHERE users_name = ? ", new Object[]{ username}
        );

        int id=(int)response.get(0).get("users_id");
        return id;
    }


    public boolean validateManager(String username, String password)
    {
        List<Map<String, Object>> response = conn.queryForList(
                "SELECT * FROM library_manager WHERE manager_name = ? AND manager_password = ?", new Object[]{ username, password }
        );

        return response.size() == 1;
    }

    public boolean validatePublisher(String username, String password)
    {
        List<Map<String, Object>> response = conn.queryForList(
                "SELECT * FROM publisher WHERE publisher_name = ? AND publisher_password = ?", new Object[]{ username, password }
        );

        return response.size() == 1;
    }


    public void SignUpPublisherService(String username, String password)
    {
        password = Salter.salt(password, "CS202Project");
        conn.update(
                "INSERT INTO publisher (publisher_name, publisher_password) VALUES ( ?, ?)", new Object[]{ username, password }
        );

    }

    public void SignUpUserService(String username, String password)
    {
        password = Salter.salt(password, "CS202Project");
        conn.update("INSERT INTO users (users_name,users_password) VALUES (?,  ?)",  new Object[]{ username, password });

    }

    public void SignUpManagerService(String username, String password)
    {
        //todo signUpManagerı silmemiz lazım
        password = Salter.salt(password, "CS202Project");
        conn.update(
                "INSERT INTO library_manager (manager_name,manager_password) VALUES (?, ?)", new Object[]{ username, password }
        );

    }

    public void AddBook(String book_title,String publication_date ,int author_id,int topic_id ,int publisher_id ,int avaliable_id)
    {
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf =   new java.text.SimpleDateFormat(publication_date);
        String currentTime = sdf.format(dt);

        conn.update(
                "INSERT INTO book (book_title,publication_date,author_id,topic_id,publisher_id,avaliable_id) VALUES (?,?,?,?,?,?)", new Object[]{ book_title, currentTime,author_id,topic_id,publisher_id,avaliable_id }
        );

    }

    public void insertRequestAddPage(String book_title, String publication_date, int author_id, int topic_id, int publisher_id)
    {
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf =   new java.text.SimpleDateFormat(publication_date);
        String currentTime = sdf.format(dt);
        int approve_initial = 0;
        conn.update(
                "INSERT INTO request_add (book_title,publication_date, author_id, topic_id, publisher_id,approve_id) " +
                        "VALUES (?,?,?,?,?,?)",
                new Object[]{ book_title, currentTime, author_id, topic_id, publisher_id,approve_initial }
        );
    }

    public void insertRequestRemovePage(int book_id)
    {
        int approve_initial = 0;
        conn.update(
                "INSERT INTO request_remove (book_id,approve_id) VALUES (?,?)", new Object[]{ book_id,approve_initial }
        );
    }

    public boolean insertBorrowBook(int users_id, int book_id, String borrow_date)
    {
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf =   new java.text.SimpleDateFormat(borrow_date);
        String currentTime = sdf.format(dt);

        List<Map<String, Object>> response = conn.queryForList(
                "SELECT avaliable_id FROM book WHERE book_id = ? ", new Object[]{ book_id}
        );

        int isAvaliable = (int)response.get(0).get("avaliable_id");

        if(isAvaliable==1){
            conn.update(
                    "INSERT INTO borrow (users_id, book_id, borrow_date) VALUES (?,?,?)", new Object[]{ users_id, book_id, currentTime}
            );
            conn.update(
                    "Update book set avaliable_id=0 where book_id=?", new Object[]{book_id}
            );
            return true;
        }

        return false;
    }

    public boolean insertReturnBook(int borrow_id, String return_date, int users_id)
    {

        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf =   new java.text.SimpleDateFormat(return_date);
        String currentTime = sdf.format(dt);

        List<Map<String, Object>> response = conn.queryForList(
                "SELECT book_id FROM borrow WHERE borrow_id = ? AND users_id=? ", new Object[]{borrow_id,users_id}
        );

        int book_id=(int)response.get(0).get("book_id");

        if(response.size()==1){
            conn.update(
                    "INSERT INTO return_book (borrow_id, return_date) VALUES (?, ?)", new Object[]{ borrow_id, currentTime}
            );
            conn.update(
                    "Update book set avaliable_id=1 where book_id=?", new Object[]{book_id}
            );

            /*List<Map<String, Object>> borrow_date = conn.queryForList(
                    "SELECT borrow_date FROM borrow WHERE borrow_id = ? AND users_id=? ", new Object[]{borrow_id,users_id}
            );
            String borrow_dateString= (String)borrow_date.get(0).get("borrow_date");
            java.text.SimpleDateFormat sdf1 =   new java.text.SimpleDateFormat(borrow_dateString);
            String borrowDateFormated = sdf1.format(dt);

            List<Map<String, Object>> dif = conn.queryForList(
                    "SELECT DATEDIFF(?,?) ", new Object[]{currentTime,borrowDateFormated}
            );

            if(dif.size()>=1) {
                List<Map<String, Object>> return_id = conn.queryForList(
                        "SELECT return_id FROM return_book WHERE borrow_id = ? ", new Object[]{borrow_id}
                );

                int return_id_int=(int)return_id.get(0).get("return_id");

                conn.update(
                        "insert into penalty (borrow_id, return_id) values ()", new Object[]{book_id,return_id_int}
                );
            }*/

            return true;
        }

        return false;
    }

    public void RemoveBook(String book_title,int book_id)
    {

        conn.update(
                "Delete from  book where book_id = ?", new Object[]{ book_id }
        );

    }

    public boolean insertHoldBook(int book_id, int users_id, String hold_date)
    {
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf =   new java.text.SimpleDateFormat(hold_date);
        String currentTime = sdf.format(dt);

        List<Map<String, Object>> response = conn.queryForList(
                "SELECT avaliable_id FROM book WHERE book_id = ? ", new Object[]{ book_id}
        );

        int isAvaliable=(int)response.get(0).get("avaliable_id");

        if(isAvaliable==0){
            conn.update(
                    "INSERT INTO hold (book_id, users_id, hold_date) VALUES (?,?,?)", new Object[]{ book_id, users_id,currentTime}
            );
            return true;
        }

        return false;


    }

    public void approveRequestAddBook(String book_title, String publication_date, int author_id, int topic_id, int publisher_id)
    {
        int approve_id=1;
        int avaliable_id=1;
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf =   new java.text.SimpleDateFormat(publication_date);
        String currentTime = sdf.format(dt);
        conn.update(
                "UPDATE request_add SET approve_id=? WHERE book_title=?", new Object[]{ approve_id,book_title}
        );
        conn.update(
                "INSERT INTO book (book_title,publication_date,author_id,topic_id, publisher_id,avaliable_id) VALUES (?,?,?,?,?,?)", new Object[]{ book_title,currentTime ,author_id, topic_id,publisher_id,avaliable_id }
        );

    }

    public void approveRequestRemoveBook(int book_id)
    {
        int approve_id=1;
        conn.update(
                "UPDATE request_remove SET approve_id=? WHERE book_id=?", new Object[]{ approve_id,book_id}
        );
        conn.update(
                "DELETE FROM book where book_id= ?", new Object[]{ book_id}
        );

    }





    public void cancelMembershipUser(int users_id){

        conn.update(
                "Delete from  users where users_id = ?", new Object[]{ users_id }
        );
    }
    public void cancelMembershipPublisher(int publisher_id){

        conn.update(
                "Delete from  publisher where publisher_id = ?", new Object[]{ publisher_id }
        );
    }

    public int getPublisherId(String username)
    {
        List<Map<String, Object>> response = conn.queryForList(
                "SELECT publisher_id FROM publisher WHERE publisher_name = ? ", new Object[]{ username}
        );

        int id=(int)response.get(0).get("publisher_id");
        return id;
    }

    public int getManagerId(String username)
    {
        List<Map<String, Object>> response = conn.queryForList(
                "SELECT manager_id FROM library_manager WHERE manager_name = ? ", new Object[]{ username}
        );

        int id=(int)response.get(0).get("manager_id");
        return id;
    }

    public void UserChangePassword(int users_id , String current_password , String new_password)
    {

        conn.update(
                "UPDATE users SET users_password=? WHERE users_id=?", new Object[]{ new_password,users_id}
        );


    }

    public void PublisherChangePassword(int users_id , String current_password , String new_password)
    {

        conn.update(
                "UPDATE publisher SET publisher_password=? WHERE publisher_id=?", new Object[]{ new_password,users_id}
        );


    }
    public void ManagerChangePassword(int users_id , String current_password , String new_password)
    {

        conn.update(
                "UPDATE library_manager SET manager_password=? WHERE manager_id=?", new Object[]{ new_password,users_id}
        );


    }

    public void ManagerCancelUserMembership(int user_id){

        conn.update(
                "Delete from  users where users_id = ?", new Object[]{ user_id }
        );
    }


}