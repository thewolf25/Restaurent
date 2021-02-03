package org.restaurant.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.restaurant.security.utils.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.AllArgsConstructor;
import net.bytebuddy.asm.MemberSubstitution.Substitution.Chain;
@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter{
	private UserDetailsService userDetailsService;
	private JwtUtil jwtUtil;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authorizationHeader = request.getHeader("Authorization");
		String jwt = null;
		String username = null;
		System.out.println("entre");
		if(authorizationHeader != null && authorizationHeader.startsWith("mehdi ")) {
			 jwt = authorizationHeader.substring(6);
			 username = jwtUtil.extractUsername(jwt);
			 System.out.println(username);
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			System.out.println("entre2");
			if(jwtUtil.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken userPAT = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				userPAT.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(userPAT);
				System.out.println("entre3");
			} 
			
		}
		filterChain.doFilter(request, response);
		
	}

}
