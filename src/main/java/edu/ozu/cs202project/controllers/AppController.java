package edu.ozu.cs202project.controllers;

import edu.ozu.cs202project.Salter;
import edu.ozu.cs202project.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Controller
@SessionAttributes({ "usernameUser","usernamePublisher","usernameManager", "level", "itemData" })
public class AppController
{
    @Autowired
    LoginService service;

    @Autowired
    JdbcTemplate conn;

    @GetMapping("/")
    public String index(ModelMap model)
    {
        return "index";
    }

    @GetMapping("/loginUser")
    public String loginPageUser(ModelMap model)
    {
        return "loginUser";
    }

    @PostMapping("/loginUser")
    public String loginUser(ModelMap model, @RequestParam String usernameUser, @RequestParam String passwordUser)
    {

        if (usernameUser == null || usernameUser.isEmpty())
        {  // User information is EMPTY! Redirect to login page
            return "index";
        }

        passwordUser = Salter.salt(passwordUser, "CS202Project");

        if (!service.validateUser(usernameUser, passwordUser))
        {
            model.put("errorMessage", "Invalid Credentials");

            return "loginUser";
        }

        model.put("usernameUser", usernameUser);

        return "loginUser";
    }

    @GetMapping("/loginPublisher")
    public String loginPagePublisher(ModelMap model)
    {
        return "loginPublisher";
    }

    @PostMapping("/loginPublisher")
    public String loginPublisher(ModelMap model, @RequestParam String usernamePublisher, @RequestParam String passwordPublisher)
    {
        passwordPublisher = Salter.salt(passwordPublisher, "CS202Project");

        if (!service.validatePublisher(usernamePublisher, passwordPublisher))
        {
            model.put("errorMessage", "Invalid Credentials");

            return "loginPublisher";
        }

        model.put("usernamePublisher", usernamePublisher);

        return "loginPublisher";
    }

    @GetMapping("/loginManager")
    public String loginPageManager(ModelMap model)
    {
        return "loginManager";
    }

    @PostMapping("/loginManager")
    public String loginManager(ModelMap model, @RequestParam String usernameManager, @RequestParam String passwordManager)
    {
        passwordManager = Salter.salt(passwordManager, "CS202Project");

        if (!service.validateManager(usernameManager, passwordManager))
        {
            model.put("errorMessage", "Invalid Credentials");

            return "loginManager";
        }

        model.put("usernameManager", usernameManager);

        return "loginManager";
    }

    @GetMapping("/SignUpManager")
    public String SignUpPageManager(ModelMap model)
    {
        return "SignUpManager";
    }

    @PostMapping("/SignUpManager")
    public String SignUpManager(ModelMap model, @RequestParam String usernameManager, @RequestParam String passwordManager)
    {

        if(passwordManager==""){
            model.put("errorMessage", "Invalid Credentials");
            return "SignUpManager";
        }else{
            service.SignUpManagerService(usernameManager, passwordManager);
            model.put("usernameManager", usernameManager);
            model.put("passwordManager", passwordManager);
            return "SignUpManager";
        }
    }

    @GetMapping("/SignUpPublisher")
    public String SignUpPagePublisher(ModelMap model)
    {
        return "SignUpPublisher";
    }

    @PostMapping("/SignUpPublisher")
    public String SignUpPublisher(ModelMap model, @RequestParam String usernamePublisher, @RequestParam String passwordPublisher)
    {
        if(passwordPublisher==""){
            model.put("errorMessage", "Invalid Credentials");
            return "SignUpPublisher";
        }else{
            service.SignUpPublisherService(usernamePublisher, passwordPublisher);
            model.put("usernamePublisher", usernamePublisher);
            model.put("passwordPublisher", passwordPublisher);
            return "SignUpPublisher";
        }
    }

    @GetMapping("/SignUp")
    public String SignUpPage(ModelMap model)
    {
        return "SignUp";
    }
    @PostMapping("/SignUp")
    public String SignUp(ModelMap model, @RequestParam String usernameUser, @RequestParam String passwordUser)
    {
        if(passwordUser==""){
            model.put("errorMessage", "Invalid Credentials");
            return "SignUp";
        }else{
            service.SignUpUserService(usernameUser, passwordUser);
            model.put("usernameUser", usernameUser);
            model.put("passwordUser", passwordUser);
            return "SignUp";
        }
    }


    @GetMapping("/logout")
    public String logout(ModelMap model, WebRequest request, SessionStatus session)
    {
        session.setComplete();
        request.removeAttribute("usernameUser", WebRequest.SCOPE_SESSION);
        request.removeAttribute("usernamePublisher", WebRequest.SCOPE_SESSION);
        request.removeAttribute("usernameManager", WebRequest.SCOPE_SESSION);
        return "index";
    }


    @GetMapping("/DisplayUsers")
    public String DisplayUsers(ModelMap model)
    {
        List<String[]> data = conn.query("SELECT * FROM users",
                (row, index) -> {
                    return new String[]{ row.getString("users_id"), row.getString("users_name"), row.getString("users_password") };
                });

        model.addAttribute("itemData", data.toArray(new String[0][3]));

        return "DisplayUsers";
    }

    @PostMapping("//DisplayUsers")
    public String DisplayUsersPage(ModelMap model) { return "/DisplayUsers"; }


    @GetMapping("/DisplayPenalty")
    public String DisplayPenalty(ModelMap model)
    {
        List<String[]> data = conn.query("SELECT * FROM penalty",
                (row, index) -> {
                    return new String[]{ row.getString("penalty_id"), row.getString("borrow_id"), row.getString("return_id") };
                });

        model.addAttribute("itemData", data.toArray(new String[0][3]));

        return "DisplayPenalty";
    }

    @PostMapping("//DisplayPenalty")
    public String DisplayPenaltyPage(ModelMap model) { return "/DisplayPenalty"; }



    @GetMapping("/DisplayBorrow")
    public String DisplayBorrow(ModelMap model)
    {
        List<String[]> data = conn.query("SELECT * FROM borrow",
                (row, index) -> {
                    return new String[]{ row.getString("borrow_id"), row.getString("users_id"), row.getString("book_id") , row.getString("borrow_date")};
                });

        model.addAttribute("itemData", data.toArray(new String[0][4]));

        return "DisplayBorrow";
    }
    @PostMapping("//DisplayBorrow")
    public String DisplayBorrowPage(ModelMap model) { return "/DisplayBorrow"; }




    @GetMapping("/DisplayReturnBook")
    public String DisplayReturnBook(ModelMap model)
    {
        List<String[]> data = conn.query("SELECT * FROM return_book",
                (row, index) -> {
                    return new String[]{ row.getString("return_id"), row.getString("borrow_id"), row.getString("return_date")};
                });

        model.addAttribute("itemData", data.toArray(new String[0][3]));

        return "DisplayReturnBook";
    }
    @PostMapping("//DisplayReturnBook")
    public String DisplayReturnBookPage(ModelMap model) { return "/DisplayReturnBook"; }




    @GetMapping("/DisplayHold")
    public String DisplayHold(ModelMap model)

    {
        List<String[]> data = conn.query("SELECT * FROM hold ",
                (row, index) -> {
                    return new String[]{ row.getString("hold_id"), row.getString("book_id"), row.getString("users_id") , row.getString("hold_date")};
                });

        model.addAttribute("itemData", data.toArray(new String[0][4]));

        return "DisplayHold";
    }
    @PostMapping("//DisplayHold")
    public String DisplayHoldPage(ModelMap model) { return "/DisplayHold"; }


    @GetMapping("/DisplayBook")
    public String DisplayBook(ModelMap model)
    {
        List<String[]> data = conn.query("SELECT * FROM book",
                (row, index) -> {
                    return new String[]{ row.getString("book_id"), row.getString("book_title"), row.getString("publication_date") , row.getString("author_id"), row.getString("topic_id"), row.getString("publisher_id"), row.getString("avaliable_id")};
                });

        model.addAttribute("itemData", data.toArray(new String[0][7]));


        return "DisplayBook";
    }
    @PostMapping("//DisplayBook")
    public String DisplayBookPage(ModelMap model) { return "/DisplayBook"; }


    @GetMapping("/MainPage")
    public String MainPageUser(ModelMap model)
    {
        return "MainPage";
    }

    @PostMapping("/MainPAge")
    public String MainUser(ModelMap model, @RequestParam String usernameUser, @RequestParam String passwordUser)
    {

        model.put("usernameUser", usernameUser);

        return "MainPage";
    }

    @GetMapping("/PublisherMainPage")
    public String MainPagePublisher(ModelMap model)
    {
        return "PublisherMainPage";
    }

    @PostMapping("/PublisherMainPAge")
    public String MainPublisher(ModelMap model, @RequestParam String usernamePublisher, @RequestParam String passwordPublisher)
    {

        model.put("usernamePublisher", usernamePublisher);

        return "PublisherMainPage";
    }

    @GetMapping("/ManagerMainPage")
    public String ManagerMainPageUser(ModelMap model)
    {
        return "ManagerMainPage";
    }

    @PostMapping("/ManagerMainPage")
    public String ManagerMain(ModelMap model, @RequestParam String usernameManager, @RequestParam String passwordManager)
    {

        model.put("usernameManager", usernameManager);

        return "ManagerMainPage";
    }
    @GetMapping("/AddBook")
    public String ManagerAddBookPage(ModelMap model)
    {
        return "AddBook";
    }

    @PostMapping("/AddBook")
    public String ManagerAddBook(ModelMap model, @RequestParam String book_title, @RequestParam String publication_date ,@RequestParam int author_id,@RequestParam int topic_id,@RequestParam int publisher_id ,@RequestParam int avaliable_id)
    {
        service.AddBook(book_title,publication_date,author_id,topic_id,publisher_id,avaliable_id);
        return "ManagerMainPage";
    }
    @GetMapping("/PublisherRequestAddBook")
    public String PublisherRequestAddBook(ModelMap model)
    {
        return "PublisherRequestAddBook";
    }

    @PostMapping("/PublisherRequestAddBook")
    public String PublisherRequestAddBookPage(ModelMap model, @RequestParam String book_title, @RequestParam String publication_date, @RequestParam int author_id, @RequestParam int topic_id, @RequestParam int publisher_id)
    {
        service.insertRequestAddPage(book_title, publication_date, author_id, topic_id, publisher_id);
        return "PublisherRequestAddBook";
    }


    @GetMapping("/PublisherRequestRemoveBook")
    public String PublisherRequestRemoveBook(ModelMap model)
    {
        return "PublisherRequestRemoveBook";
    }

    @PostMapping("/PublisherRequestRemoveBook")
    public String PublisherRequestRemoveBookPage(ModelMap model, @RequestParam int book_id)
    {
        service.insertRequestRemovePage(book_id);
        return "PublisherRequestRemoveBook";
    }


    @GetMapping("/UserBorrowBook")
    public String UserBorrowBook(ModelMap model)
    {
        return "UserBorrowBook";
    }

    @PostMapping("/UserBorrowBook")
    public String UserBorrowBookPage(ModelMap model , @RequestParam int book_id, @RequestParam String borrow_date)
    {
        String usernameUser=(String)model.get("usernameUser");
        int users_id= service.getUserId(usernameUser);
        if(!(service.insertBorrowBook(users_id, book_id, borrow_date))){
            model.put("errorMessage", "Invalid Credentials");
        }
        return "UserBorrowBook";
    }

    @GetMapping("/UserReturnBook")
    public String UserReturnBook(ModelMap model)
    {
        String usernameUser = (String) model.get("usernameUser");
        int id= service.getUserId(usernameUser);

        List<String[]> data = conn.query("SELECT * FROM return_book natural join borrow where users_id="+id+"",
                (row, index) -> {
                    return new String[]{ row.getString("return_id"), row.getString("borrow_id"), row.getString("return_date")};
                });

        model.addAttribute("itemData", data.toArray(new String[0][3]));

        return "UserReturnBook";
    }

    @PostMapping("/UserReturnBook")
    public String UserReturnBookPage(ModelMap model, @RequestParam int borrow_id, @RequestParam String return_date)
    {
        String usernameUser=(String)model.get("usernameUser");
        int users_id=service.getUserId(usernameUser);

        if(!(service.insertReturnBook(borrow_id, return_date, users_id))){
        model.put("errorMessage", "Invalid Credentials");
        }
        return "UserReturnBook";
    }

    @GetMapping("/RemoveBook")
    public String RemoveBook(ModelMap model)
    {
        return "RemoveBook";
    }

    @PostMapping("/RemoveBook")
    public String ManagerRemoveBookPage(ModelMap model, @RequestParam String book_title, @RequestParam int book_id)
    {
        service.RemoveBook(book_title, book_id);
        return "RemoveBook";
    }
    @GetMapping("/UserHoldBook")
    public String UserHoldBook(ModelMap model) {

        String usernameUser = (String) model.get("usernameUser");
        int id= service.getUserId(usernameUser);

        List<String[]> data = conn.query("SELECT * FROM hold where users_id="+id+" ",
                (row, index) -> {
                    return new String[]{ row.getString("hold_id"), row.getString("book_id"), row.getString("users_id") , row.getString("hold_date")};
                });

        model.addAttribute("itemData", data.toArray(new String[0][4]));

        return "UserHoldBook";
    }

    @PostMapping("/UserHoldBook")
    public String UserHoldBookPage(ModelMap model, @RequestParam int book_id, @RequestParam String hold_date)
    {
        String usernameUser=(String)model.get("usernameUser");
        int users_id= service.getUserId(usernameUser);
        if(!(service.insertHoldBook(book_id, users_id, hold_date))){
            model.put("errorMessage", "Invalid Credentials");
        }

        return "UserHoldBook";
    }

    @GetMapping("/ApproveAddRequest")
    public String ApproveAddRequest(ModelMap model)
    {
        List<String[]> data = conn.query("SELECT * FROM request_add",
                (row, index) -> {
                    return new String[]{ row.getString("request_add_id"), row.getString("book_title"), row.getString("publication_date"),
                            row.getString("author_id"),row.getString("topic_id"),row.getString("publisher_id"),row.getString("approve_id")};
                });

        model.addAttribute("itemData", data.toArray(new String[0][7]));
        return "ApproveAddRequest";
    }

    @PostMapping("/ApproveAddRequest")
    public String ApproveAddRequestPage(ModelMap model, @RequestParam String book_title, @RequestParam String publication_date, @RequestParam int author_id, @RequestParam int topic_id, @RequestParam int publisher_id) {

        service.approveRequestAddBook(book_title,publication_date, author_id, topic_id, publisher_id);
        return "/ApproveAddRequest";
    }

    @GetMapping("/ApproveRemoveRequest")
    public String ApproveRemoveRequest(ModelMap model)
    {
        List<String[]> data = conn.query("SELECT * FROM request_remove",
                (row, index) -> {
                    return new String[]{ row.getString("request_remove_id"), row.getString("book_id"), row.getString("approve_id")};
                });

        model.addAttribute("itemData", data.toArray(new String[0][3]));


        return "ApproveRemoveRequest";
    }

    @PostMapping("/ApproveRemoveRequest")
    public String ApproveRemoveRequestPage(ModelMap model, @RequestParam int book_id) {

        service.approveRequestRemoveBook(book_id);
        return "/ApproveRemoveRequest";
    }


    @GetMapping("/DisplayBorrowInfoUser")
    public String DisplayBorrowInfoUser(ModelMap model)
    {

        // Get user information from the session
        String usernameUser = (String) model.get("usernameUser");

        int id= service.getUserId(usernameUser);

        List<String[]> data = conn.query("SELECT * FROM borrow where users_id="+id+"",
                (row, index) -> {
                    return new String[]{ row.getString("borrow_id"), row.getString("users_id"), row.getString("book_id") , row.getString("borrow_date")};
                });

        model.addAttribute("itemData", data.toArray(new String[0][4]));
        return "DisplayBorrowInfoUser";
    }

    @PostMapping("/DisplayBorrowInfoUser")
    public String DisplayBorrowInfoUserPage(ModelMap model) {

        return "/DisplayBorrowInfoUser";
    }

    @GetMapping("/DisplayBorrowInfoPublisher")
    public String DisplayBorrowInfoPublisher(ModelMap model )
    {
        // Get user information from the session
        String usernamePublisher = (String) model.get("usernamePublisher");

        int id= service.getPublisherId(usernamePublisher);

        List<String[]> data = conn.query("select * from borrow natural join book where publisher_id="+id+"",
                (row, index) -> {
                    return new String[]{ row.getString("borrow_id"), row.getString("users_id"), row.getString("book_id") , row.getString("borrow_date")};
                });


        model.addAttribute("itemData", data.toArray(new String[0][4]));
        return "DisplayBorrowInfoPublisher";
    }
    @PostMapping("/DisplayBorrowInfoPublisher")
    public String DisplayBorrowInfoPublisherPage(ModelMap model) {

        return "/DisplayBorrowInfoPublisher";
    }




    @GetMapping("/CancelMembershipUser")
    public String CancelMembershipUser(ModelMap model)
    {
        return "CancelMembershipUser";
    }

    @PostMapping("/CancelMembershipUser")
    public String CancelMembershipUserPage(ModelMap model,WebRequest request, SessionStatus session)
    {
        String Username = (String)model.get("usernameUser");
        int users_id = service.getUserId(Username);
        service.cancelMembershipUser(users_id);
        session.setComplete();
        request.removeAttribute("usernameUser", WebRequest.SCOPE_SESSION);
        request.removeAttribute("usernamePublisher", WebRequest.SCOPE_SESSION);
        request.removeAttribute("usernameManager", WebRequest.SCOPE_SESSION);
        return "index";
    }

    @GetMapping("/CancelMembershipPublisher")
    public String CancelMembershipPublisher(ModelMap model)
    {

        return "CancelMembershipPublisher";
    }

    @PostMapping("/CancelMembershipPublisher")
    public String CancelMembershipPublisherPage(ModelMap model,WebRequest request, SessionStatus session)
    {

        String usernamePublisher = (String)model.get("usernamePublisher");
        int publisher_id = service.getPublisherId(usernamePublisher);
        service.cancelMembershipPublisher(publisher_id);
        session.setComplete();
        request.removeAttribute("usernameUser", WebRequest.SCOPE_SESSION);
        request.removeAttribute("usernamePublisher", WebRequest.SCOPE_SESSION);
        request.removeAttribute("usernameManager", WebRequest.SCOPE_SESSION);
        return "index";
    }


    @GetMapping("/ManagerCancelUserMembership")
    public String ManagerCancelUserMembership(ModelMap model)
    {

        return "ManagerCancelUserMembership";
    }

    @PostMapping("/ManagerCancelUserMembership")
    public String ManagerCancelUserMembershipPage(ModelMap model,@RequestParam int user_id)
    {
        if(user_id==0){
            model.put("errorMessage", "Invalid Credentials");
            return "ManagerCancelUserMembership";
        }else{
            service.ManagerCancelUserMembership(user_id);
            model.put("user_id",user_id);
            return "ManagerCancelUserMembership";
        }
    }

    @GetMapping("/UserChangePassword")
    public String UserChangePassword(ModelMap model)
    {

        return "UserChangePassword";
    }

    @PostMapping("/UserChangePassword")
    public String UserChangePasswordPage(ModelMap model ,@RequestParam String current_password, @RequestParam String new_password )
    {

        String new_password_  = Salter.salt(new_password, "CS202Project");
        String usernameUser = (String)model.get("usernameUser");
        int users_id = service.getUserId(usernameUser);
        service.UserChangePassword(users_id,current_password,new_password_);
        return "UserChangePassword";
    }

    @GetMapping("/PublisherChangePassword")
    public String PublisherChangePassword(ModelMap model)
    {

        return "PublisherChangePassword";
    }

    @PostMapping("/PublisherChangePassword")
    public String PublisherChangePasswordPage(ModelMap model ,@RequestParam String current_password, @RequestParam String new_password )
    {

        String new_password_  = Salter.salt(new_password, "CS202Project");
        String usernamePublisher = (String)model.get("usernamePublisher");
        int publisher_id = service.getPublisherId(usernamePublisher);
        service.PublisherChangePassword(publisher_id,current_password,new_password_);
        return "PublisherChangePassword";
    }

    @GetMapping("/ManagerChangePassword")
    public String ManagerChangePassword(ModelMap model)
    {

        return "ManagerChangePassword";
    }

    @PostMapping("/ManagerChangePassword")
    public String ManagerChangePasswordPage(ModelMap model ,@RequestParam String current_password, @RequestParam String new_password )
    {

        String new_password_  = Salter.salt(new_password, "CS202Project");
        String usernameManager = (String)model.get("usernameManager");
        int manager_id = service.getManagerId(usernameManager);
        service.ManagerChangePassword(manager_id,current_password,new_password_);
        return "ManagerChangePassword";
    }

    @GetMapping("/UserMostBorrowedBook")
    public String UserMostBorrowedBook(ModelMap model)
    {

        List<String[]> data = conn.query("select users_id , users_name, count(book_id) as borrowed_book from borrow natural join users group by users_id ORDER BY COUNT(book_id) DESC ;",
                (row, index) -> {
                    return new String[]{ row.getString("users_id"), row.getString("users_name"), row.getString("borrowed_book")};
                });

        model.addAttribute("itemData", data.toArray(new String[0][3]));
        return "UserMostBorrowedBook";
    }
    @PostMapping("/UserMostBorrowedBook")
    public String UserMostBorrowedBookPage(ModelMap model) {

        return "/UserMostBorrowedBook";
    }

    @GetMapping("/MostBorrowedBookOfPublisher")
    public String MostBorrowedBookOfPublisher(ModelMap model)
    {

        List<String[]> data = conn.query("select publisher_id,publisher_name, book_title, count(book_id) as borrowed_book from (borrow natural join book)natural join publisher  group by publisher_id, book_id ORDER BY COUNT(book_id) DESC;",
                (row, index) -> {
                    return new String[]{ row.getString("publisher_id"),row.getString("publisher_name"), row.getString("book_title"), row.getString("borrowed_book")};
                });

        model.addAttribute("itemData", data.toArray(new String[0][4]));
        return "MostBorrowedBookOfPublisher";
    }
    @PostMapping("/MostBorrowedBookOfPublisher")
    public String MostBorrowedBookOfPublisherPage(ModelMap model) {

        return "/MostBorrowedBookOfPublisher";
    }

    @GetMapping("/ThreeMonthBorrowInfo")
    public String ThreeMonthBorrowInfo(ModelMap model)
    {

        List<String[]> data = conn.query("select * from borrow where borrow_date >= CURRENT_DATE()- interval 90 day AND borrow_date < current_date() ;",
                (row, index) -> {
                    return new String[]{ row.getString("borrow_id"),row.getString("users_id"), row.getString("book_id"), row.getString("borrow_date")};
                });

        model.addAttribute("itemData", data.toArray(new String[0][4]));
        return "ThreeMonthBorrowInfo";
    }
    @PostMapping("/ThreeMonthBorrowInfo")
    public String ThreeMonthBorrowInfoPage(ModelMap model) {

        return "/ThreeMonthBorrowInfo";
    }



    @GetMapping("/AllTimeBorrowGenreInfo")
    public String AllTimeGenreInfo(ModelMap model)
    {

        List<String[]> data = conn.query("select topic_name, count(book_id) as borrowed_book from borrow natural join book natural join topic group by topic_id ORDER BY COUNT(book_id) DESC;",
                (row, index) -> {
                    return new String[]{ row.getString("topic_name"),row.getString("borrowed_book")};
                });

        model.addAttribute("itemData", data.toArray(new String[0][4]));
        return "AllTimeBorrowGenreInfo";
    }
    @PostMapping("/AllTimeBorrowGenreInfo")
    public String AllTimeBorrowGenreInfoPage(ModelMap model) {

        return "/AllTimeBorrowGenreInfo";
    }

    @GetMapping("/NumberBorrow")
    public String NumberBorrow(ModelMap model)
    {

        List<String[]> data = conn.query("select count(*) from borrow;",
                (row, index) -> {
                    return new String[]{ row.getString("count(*)")};
                });

        model.addAttribute("itemData", data.toArray(new String[0][1]));

        return "NumberBorrow";
    }
    @PostMapping("/NumberBorrow")
    public String NumberBorrowPage(ModelMap model) {

        return "/NumberBorrow";
    }

    @GetMapping("/NumberOverdue")
    public String NumberOverdue(ModelMap model)
    {

        List<String[]> data = conn.query("select count(*) from penalty;",
                (row, index) -> {
                    return new String[]{ row.getString("count(*)")};
                });

        model.addAttribute("itemData", data.toArray(new String[0][1]));


        return "NumberOverdue";
    }
    @PostMapping("/NumberOverdue")
    public String NumberOverduePage(ModelMap model) {

        return "/NumberOverdue";
    }

    @GetMapping("/DisplayOverdueBookNow")
    public String DisplayOverdueBookNow(ModelMap model)
    {
        List<String[]> data = conn.query("select book_id, book_title, borrow.borrow_id ,borrow_date\n" +
                        "from (borrow natural join book) left join return_book on borrow.borrow_id=return_book.borrow_id where return_id is null \n" +
                        "and borrow_date < CURRENT_DATE()- interval 20 day;",
                (row, index) -> {
                    return new String[]{ row.getString("book_id"), row.getString("book_title"), row.getString("borrow_id") , row.getString("borrow_date")};
                });

        model.addAttribute("itemData", data.toArray(new String[0][4]));


        return "DisplayOverdueBookNow";
    }
    @PostMapping("/DisplayOverdueBookNow")
    public String DisplayOverdueBookNowPage(ModelMap model) { return "/DisplayOverdueBookNow"; }

    @GetMapping("/DisplayOverdueBooksCountList")
    public String DisplayOverdueBooksCountList(ModelMap model)
    {

        List<String[]> data = conn.query("select book_title,count(book_id) as borrowed_book , count(penalty_id) as overdued_book from (borrow natural join book) left join penalty on borrow.borrow_id = penalty.borrow_id group by book_id;",
                (row, index) -> {
                    return new String[]{ row.getString("book_title"),row.getString("borrowed_book"),row.getString("overdued_book")};
                });

        model.addAttribute("itemData", data.toArray(new String[0][3]));
        return "DisplayOverdueBooksCountList";
    }
    @PostMapping("/DisplayOverdueBooksCountList")
    public String DisplayOverdueBooksCountListPage(ModelMap model) {

        return "/DisplayOverdueBooksCountList";
    }

    @GetMapping("/DisplayPenaltyOfUser")
    public String DisplayPenaltyOfUser(ModelMap model)
    {
        String usernameUser=(String)model.get("usernameUser");
        int users_id= service.getUserId(usernameUser);

        List<String[]> data = conn.query("select penalty_id,borrow_id from penalty natural join borrow where users_id ="+ users_id+"",
                (row, index) -> {
                    return new String[]{ row.getString("penalty_id"),row.getString("borrow_id")};
                });

        model.addAttribute("itemData", data.toArray(new String[0][2]));


        return "DisplayPenaltyOfUser";
    }
    @PostMapping("/DisplayPenaltyOfUser")
    public String DisplayPenaltyOfUserPage(ModelMap model) {

        return "/DisplayPenaltyOfUser";
    }

    @GetMapping("/SearchBook")
    public String SearchBook(ModelMap model)
    {

        return "SearchBook";
    }
    /*
    @PostMapping("/SearchBook")
    public String SearchBookPage(ModelMap model,@RequestParam String book_title,@RequestParam String topic_name,@RequestParam String author_name,@RequestParam String avaliable_info, @RequestParam String publication_date ) {
        if(!(publication_date==null)){
            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(publication_date);
            publication_date = sdf.format(dt);
        }


        if(book_title!=""||(book_title.contains(";"))){
            List<String[]> data = conn.query("Select * " +
                    "from (book natural join topic natural join author) join available on book.avaliable_id=available.available_id WHERE book_title = '"+book_title+"'",
                    (row, index) -> {
                        return new String[]{ row.getString("book_id"), row.getString("book_title"), row.getString("publication_date") , row.getString("author_id"), row.getString("topic_id"), row.getString("publisher_id"), row.getString("avaliable_id")};
            });
            model.addAttribute("itemData", data.toArray(new String[0][7]));
            return "/SearchBook";
        }else if(topic_name!=""||(topic_name.contains(";"))){
            List<String[]> data = conn.query("Select * " +
                            "from (book natural join topic natural join author) join available on book.avaliable_id=available.available_id WHERE topic_name = '"+topic_name+"'",
                    (row, index) -> {
                        return new String[]{ row.getString("book_id"), row.getString("book_title"), row.getString("publication_date") , row.getString("author_id"), row.getString("topic_id"), row.getString("publisher_id"), row.getString("avaliable_id")};
                    });
            model.addAttribute("itemData", data.toArray(new String[0][7]));
            return "/SearchBook";
        }else if(author_name!=""||(author_name.contains(";"))){
            List<String[]> data = conn.query("Select * " +
                            "from (book natural join topic natural join author) join available on book.avaliable_id=available.available_id WHERE author_name = '"+author_name+"'",
                    (row, index) -> {
                        return new String[]{ row.getString("book_id"), row.getString("book_title"), row.getString("publication_date") , row.getString("author_id"), row.getString("topic_id"), row.getString("publisher_id"), row.getString("avaliable_id")};
                    });
            model.addAttribute("itemData", data.toArray(new String[0][7]));
            return "/SearchBook";
        }else if(avaliable_info!=""||!(avaliable_info.contains(";"))){
            System.out.println("sdvsdsdv");
            List<String[]> data = conn.query("Select * " +
                            "from (book natural join topic natural join author) join available on book.avaliable_id=available.available_id WHERE available_info = '"+avaliable_info+"'",
                    (row, index) -> {
                        return new String[]{ row.getString("book_id"), row.getString("book_title"), row.getString("publication_date") , row.getString("author_id"), row.getString("topic_id"), row.getString("publisher_id"), row.getString("avaliable_id")};
                    });
            model.addAttribute("itemData", data.toArray(new String[0][7]));
            return "/SearchBook";
        }else if(publication_date!=""||!(publication_date.contains(";"))){
            List<String[]> data = conn.query("Select * " +
                            "from (book natural join topic natural join author) join available on book.avaliable_id=available.available_id WHERE publication_date = '"+publication_date+"'",
                    (row, index) -> {
                        return new String[]{ row.getString("book_id"), row.getString("book_title"), row.getString("publication_date") , row.getString("author_id"), row.getString("topic_id"), row.getString("publisher_id"), row.getString("avaliable_id")};
                    });
            model.addAttribute("itemData", data.toArray(new String[0][7]));
            return "/SearchBook";
        }

        return "/SearchBook";
    }
    */

    @PostMapping("/SearchBook")
    public String SearchBookPage(ModelMap model, @RequestParam String book_title, @RequestParam String topic_name, @RequestParam String author_name, @RequestParam String avaliable_info, @RequestParam String publication_date ) {
        if(!(publication_date=="")){
            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(publication_date);
            publication_date = sdf.format(dt);
        }

        boolean isInserted=false;
        String query="Select * from (book natural join topic natural join author) join available on book.avaliable_id=available.available_id ";

        String where = " where ";
        int cnt=0;

        if(book_title!="" &&!(book_title.contains(";"))){
            if(cnt>=1){
                where= where.concat(" AND ");
            }
            where=where.concat("book_title= '"+book_title+"'");
            isInserted=true;
            cnt++;
        }
        if(topic_name!="" &&!(topic_name.contains(";"))){
            if(cnt>=1){
                where=where.concat(" AND ");
            }
            where=where.concat("topic_name='"+topic_name+"'");
            isInserted=true;
            cnt++;
        }
        if(author_name!="" &&!(author_name.contains(";"))){
            if(cnt>=1){
                where=where.concat(" AND ");
            }
            where=where.concat("author_name='"+author_name+"'");
            isInserted=true;
            cnt++;
        }
        if(avaliable_info !="" &&!(avaliable_info.contains(";"))){
            if(cnt>=1){
                where=where.concat(" AND ");
            }
            where=where.concat("available_info='"+ avaliable_info +"'");
            isInserted=true;
            cnt++;
        }
        if(publication_date!="" &&!(publication_date.contains(";"))){
            if(cnt>=1){
                where=where.concat(" AND ");
            }
            where=where.concat("publication_date='"+publication_date+"'");
            isInserted=true;
            cnt++;
        }

        if(isInserted){
            query= query.concat(where);
        }

        System.out.println(query);

        List<String[]> data = conn.query( query,
                (row, index) -> {
                    return new String[]{ row.getString("book_id"), row.getString("book_title"), row.getString("publication_date") , row.getString("author_id"), row.getString("topic_id"), row.getString("publisher_id"), row.getString("avaliable_id")};
                });

        model.addAttribute("itemData", data.toArray(new String[0][5]));


        return "/SearchBook";
    }


    /*

    @PostMapping("/SearchBook")
    public String SearchBookPage(ModelMap model,@RequestParam String book_title,@RequestParam String topic_name,@RequestParam String author_name,@RequestParam String available_info, @RequestParam String publication_date ) {
        if(!(publication_date==null)){
            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(publication_date);
            publication_date = sdf.format(dt);
        }

        boolean isInserted=false;
        String query="Select book_title,topic_name,author_name,available_info,publication_date from (book natural join topic natural join author) join available on book.avaliable_id=available.available_id";

        String where = " where ";
        int cnt=0;

        if(book_title!=""||!(book_title.contains(";"))){
            if(cnt>=1){
                where.concat(" AND ");
            }
            where.concat("book_title="+book_title+" ");
            isInserted=true;
            cnt++;
        }else if(topic_name!=null||!(topic_name.contains(";"))){
            if(cnt>=1){
                where.concat(" AND ");
            }
            where.concat("topic_name="+topic_name+" ");
            isInserted=true;
            cnt++;
        }else if(author_name!=null||!(author_name.contains(";"))){
            if(cnt>=1){
                where.concat(" AND ");
            }
            where.concat("author_name="+author_name+" ");
            isInserted=true;
            cnt++;
        }else if(available_info!=null||!(available_info.contains(";"))){
            if(cnt>=1){
                where.concat(" AND ");
            }
            where.concat("available_info="+available_info+" ");
            isInserted=true;
            cnt++;
        }else if(publication_date!=null||!(publication_date.contains(";"))){
            if(cnt>=1){
                where.concat(" AND ");
            }
            where.concat("publication_date="+publication_date+" ");
            isInserted=true;
            cnt++;
        }

        if(isInserted){
            query.concat(where);
        }

        List<String[]> data = conn.query(query,
                (row, index) -> {
                    return new String[]{ row.getString(" book_title"), row.getString("topic_name"), row.getString("author_name") , row.getString("available_info"), row.getString("publication_date")};
                });

        model.addAttribute("itemData", data.toArray(new String[0][5]));


        return "/SearchBook";
    }


     */


}