import java.util.Scanner;

/**
 * Created by robvangastel on 9-5-2017.
 */
public class KwetterGo {

    public static void main(String[] args) {

        TweetSender sender = new TweetSender();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Fill in username: ");
        String username = scanner.nextLine();

        System.out.println("Set the message: ");
        String message = scanner.nextLine();

        sender.sendTweet(username + ":" + message);
    }
}
