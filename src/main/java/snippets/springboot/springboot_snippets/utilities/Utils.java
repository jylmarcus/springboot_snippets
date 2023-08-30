package snippets.springboot.springboot_snippets.utilities;

import java.util.UUID;

public class Utils {
    
    public static String generateID() {
        return UUID.randomUUID().toString().substring(0, 8);
    }


}
