package api.gateway.apigateway.resource;

import api.gateway.apigateway.resourceobject.AuthenticationRequestRO;
import api.gateway.apigateway.resourceobject.AuthenticationResponseRO;
import api.gateway.apigateway.utils.AuthorizationTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class DefaultResource {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthorizationTokenUtils authorizationTokenUtils;

    @Value("${config.server.test.data:Welcome}")
    private String configServerTestData;


    @RequestMapping("/")
    public String getStatus(){
        return "Zuul-API-Gateway-Service is up and running - " + configServerTestData;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequestRO authenticationRequest) throws Exception {
        this.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwtAuthToken = authorizationTokenUtils.generateToken(userDetails);
        return  ResponseEntity.ok(new AuthenticationResponseRO(jwtAuthToken));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
