package org.innovativeapps.mongohoteldemo.config;

import org.innovativeapps.mongohoteldemo.service.AddressService;
import org.innovativeapps.mongohoteldemo.service.HotelService;
import org.innovativeapps.mongohoteldemo.service.ReviewService;
import org.innovativeapps.mongohoteldemo.service.UserService;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@javax.ws.rs.ApplicationPath("/api")

public class ApplicationConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();

        s.add(UserService.class);
        s.add(HotelService.class);
        s.add(ReviewService.class);
        s.add(AddressService.class);

        return s;
    }
}
