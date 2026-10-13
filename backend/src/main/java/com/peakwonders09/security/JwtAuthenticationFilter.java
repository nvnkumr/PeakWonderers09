package com.peakwonders09.security;

import com.peakwonders09.auth.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import lombok.*;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	private final JwtService jwtService;
	private final UserRepository userRepository;
	
	@Override
	protected void doFilterInternal(
		HttpServletRequest req, 
		HttpServletResponse res,
		FilterChain filterChain) throws ServletException, IOException{
	
		String header=req.getHeader("Authorization");
		if(header==null || !header.startsWith("Bearer")){
			filterChain.doFilter(req, res);
			return;
		}
		
		String token= header.substring(7);
		try{
			String email=jwtService.extractEmail(token);
			
			if(SecurityContextHolder
				.getContext()
				.getAuthentication()==null)
				{
					userRepository.findByEmail(email)
						.filter(user->user.isEnabled())
						
						.ifPresent(user->
						{
							var authority=new SimpleGrantedAuthority(
								"ROLE_"+
								user.getRole().name());
							
							var authentication=new UsernamePasswordAuthenticationToken(
								user.getEmail(),
								null,
								java.util.List.of(authority));
								
							SecurityContextHolder
								.getContext()
								.setAuthentication(authentication);
						});
				}
			}catch(Exception ignored){
				//invalid token->req remains unauthenticated
			}
			filterChain.doFilter(req,res);
		}
	}
	
	/* update SecurityConfig:
	.addFilterBefore(
		jwtAuthenticationFilter;
		UsernamePasswordAuthenticationFilter.class)
	*/
	
