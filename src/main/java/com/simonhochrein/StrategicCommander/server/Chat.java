package com.simonhochrein.StrategicCommander.server;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private List<ChatListener> chatListenerList = new ArrayList<>();

    public interface ChatListener {
        void onChatMessage(String userId, String message);
    }

    public void broadcast(String userId, String message) {
        for(ChatListener listener : chatListenerList) {
            listener.onChatMessage(userId, message);
        }
    }

    public void addListener(ChatListener listener) {
        this.chatListenerList.add(listener);
    }

    private static Chat instance = new Chat();
    public static Chat getInstance() {
        return instance;
    }
}
