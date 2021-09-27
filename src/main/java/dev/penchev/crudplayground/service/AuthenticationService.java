package dev.penchev.crudplayground.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.penchev.crudplayground.core.PasswordUtilities;
import dev.penchev.crudplayground.database.UserDAO;
import dev.penchev.crudplayground.models.UserCredentialsModel;
import dev.penchev.crudplayground.models.UserJwtModel;
import dev.penchev.crudplayground.models.UserModel;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

public class AuthenticationService implements Authenticator<UserJwtModel, UserModel> {

    final private UserDAO userDAO;
    Algorithm jwtAlgorithm = Algorithm.HMAC256("secret");
    JWTVerifier jwtVerifier = JWT.require(jwtAlgorithm)
            .withIssuer("auth0")
            .build();

    public AuthenticationService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Optional<UserModel> authenticate(UserJwtModel credentials) throws AuthenticationException {
        String username;
        try {
            DecodedJWT jwt = jwtVerifier.verify(credentials.getToken());
            username = jwt.getSubject();
        } catch (JWTVerificationException exception){
            throw new AuthenticationException("Invalid JWT signature/claims.");
        }
        if (username == null) {
            throw new AuthenticationException("Invalid credentials.");
        }
        UserModel userFromDb = userDAO.getByUsername(username);
        return Optional.of(userFromDb);
    }

    public String registerUser(UserModel user) {
        // Todo: user data validation
        userDAO.insert(user);

        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(Date.from(Instant.now().plus(7, ChronoUnit.DAYS)))
                .withIssuer("auth0")
                .sign(jwtAlgorithm);
    }

    public String loginUser(UserCredentialsModel userCredentials) throws AuthenticationException {
        UserModel userFromDb = userDAO.getByUsername(userCredentials.getUsername());
        if (userFromDb == null) {
            throw new AuthenticationException("Invalid credentials.");
        }

        if (PasswordUtilities.checkPassword(userCredentials.getPassword(), userFromDb.getPassword())) {
            return JWT.create()
                    .withSubject(userFromDb.getUsername())
                    .withExpiresAt(Date.from(Instant.now().plus(7, ChronoUnit.DAYS)))
                    .withIssuer("auth0")
                    .sign(jwtAlgorithm);
        } else {
            throw new AuthenticationException("Invalid credentials.");
        }
    }
}
