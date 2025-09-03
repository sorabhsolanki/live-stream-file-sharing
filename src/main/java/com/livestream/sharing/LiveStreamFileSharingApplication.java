package com.livestream.sharing;

import com.livestream.sharing.socket.ChatSocketHandler;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import org.eclipse.jetty.websocket.jakarta.server.config.JakartaWebSocketServletContainerInitializer;

public class LiveStreamFileSharingApplication extends Application<LiveStreamFileSharingConfiguration> {
    @Override
    public void run(LiveStreamFileSharingConfiguration configuration, Environment env) throws Exception {
        JakartaWebSocketServletContainerInitializer.configure(env.getApplicationContext(), ((servletContext, serverContainer) -> {
            serverContainer.addEndpoint(ChatSocketHandler.class);
        }));
    }

    public static void main(String[] args) throws Exception {
        new LiveStreamFileSharingApplication().run(args);
    }
}
