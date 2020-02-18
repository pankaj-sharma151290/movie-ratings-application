package api.gateway.apigateway.resourceobject;

public class AuthenticationResponseRO {

    private String jwtAuthToken;

    public AuthenticationResponseRO() {
    }

    public AuthenticationResponseRO(String jwtAuthToken) {
        this.jwtAuthToken = jwtAuthToken;
    }

    public String getJwtAuthToken() {
        return jwtAuthToken;
    }

    public void setJwtAuthToken(String jwtAuthToken) {
        this.jwtAuthToken = jwtAuthToken;
    }
}
