package com.group3.lovelacehotel.config;

import com.group3.lovelacehotel.model.User;
import com.group3.lovelacehotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Configuration
public class HandleInterceptor implements HandlerInterceptor {

    private final UserRepository userRepository;

    @Override
    public void postHandle(final HttpServletRequest request,
                           final HttpServletResponse response,
                           final Object handler,
                           final ModelAndView modelAndView) throws Exception {
        var isAuthenticated = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        var name = SecurityContextHolder.getContext().getAuthentication().getName();
        var isLoggedIn = false;
        var user =  new User();
        if (isAuthenticated && !name.equalsIgnoreCase("anonymousUser")) {
            var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

             user = userRepository.findByEmail(userDetails.getUsername());
            isLoggedIn = true;

          if(modelAndView!= null){
              modelAndView.addObject("currentUser", user);
          }
        }

        if(modelAndView != null) {
            modelAndView.addObject("isUserLoggedIn", isLoggedIn);
        }
    }
}
