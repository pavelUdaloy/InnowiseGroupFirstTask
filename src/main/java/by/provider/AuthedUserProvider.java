package by.provider;

import by.entity.base.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthedUserProvider {

    private static final ThreadLocal<User> THREAD_LOCAL = new ThreadLocal<>();

    public static void setThreadLocalUser(User user) {
        THREAD_LOCAL.set(user);
    }

    public static User getThreadLocalUser() {
        return THREAD_LOCAL.get();
    }

    public static void clearThreadLocalUser() {
        User threadLocalUser = getThreadLocalUser();
        if (threadLocalUser != null) {
            THREAD_LOCAL.remove();
        }
    }
}