package com.ex.subscribe.global.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class ApiGlobalExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getRequestURI().startsWith("/api/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try{
            filterChain.doFilter(request, response);
        }catch(Exception e){

            if (response.isCommitted()) throw e;

            BusinessErrorCode code = BusinessErrorCode.INTERNAL_SERVER_ERROR;
            ErrorResponse body = ErrorResponse.of(code, request.getRequestURI());

            // todo : HandlerExceptionResolver 재사용하는 방식으로 변경 예정
            response.resetBuffer();
            response.setStatus(code.getHttpStatus().value());
            response.setCharacterEncoding("UTF-8");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            // todo : getOutputStream() 으로 바꿔야 하는지 확인
            objectMapper.writeValue(response.getWriter(), body);
        }
    }
}
