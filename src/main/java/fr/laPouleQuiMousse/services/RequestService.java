package fr.laPouleQuiMousse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service("request")
public class RequestService {

    @Autowired
    private HttpServletRequest request;

    public String getUrl() {
        String forward = getForward();
        String url = request.getRequestURI();
        return !forward.isEmpty() ? forward : url;
    }

    private String getForward() {
        Object forward = request.getAttribute("javax.servlet.forward.request_uri");
        return forward != null ? forward.toString() : "";
    }

    public String getBaseUrl(String url){
        String baseUrl = String.format("%s://%s:%d",request.getScheme(),  request.getServerName(), request.getServerPort());
        return baseUrl +url;
    }

    public boolean isForwarded() {
        return !getForward().isEmpty();
    }

    public boolean isForwardedFrom(String url) {
        return getForward().equals(url);
    }

    public boolean isUrl(String url) {
        return getUrl().equals(url);
    }
}
