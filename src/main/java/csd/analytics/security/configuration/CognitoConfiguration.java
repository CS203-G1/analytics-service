package csd.analytics.security.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;

@Configuration
public class CognitoConfiguration {
    @Value(value = "${aws.access-key}")
    private String accessKey;
    @Value(value = "${aws.access-secret}")
    private String secretKey;
    @Value(value = "${aws.default-region}")
    private String defaultRegion;

    @Bean
    public AWSCognitoIdentityProvider cognitoClient() {

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);

        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(defaultRegion)
                .build();
    }
}
