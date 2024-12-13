import org.springframework.web.bind.annotation.*;
import java.sql.*;

@RestController
public class LoginController {

    // Security Bug: SQL Injection vulnerability due to unsanitized user input in query.
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "password");
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                return "Login successful";
            } else {
                return "Invalid credentials";
            }
        } catch (SQLException e) {
            return "Database error";
        }
    }
}

class User {
    private String username;
    private String password;

    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
