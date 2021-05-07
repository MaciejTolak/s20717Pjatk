/**
 * @author Tolak Maciej S20717
 */

package zad1;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ClientTask extends FutureTask<String> {
    public ClientTask(Callable<String> callable) {
        super(callable);
    }

    public static ClientTask create(Client c, List<String> reqs, boolean showSendRes) {
        Callable<String> callable = () -> {
            c.connect();
            c.send("login " + c.id);
            for (String s : reqs) {
                String tmp = c.send(s);
                if (showSendRes) {
                    System.out.println(tmp);
                }
            }
            return c.send("bye and log transfer");
        };
        return new ClientTask(callable);
    }

}
