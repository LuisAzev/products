package com.mercadona.products.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    // Environment properties
    private @Value("${server.servlet.contextPath}") String contextPath;

    // Properties read from config file
    private @Value("${oas.info.name}") String infoName;
    private @Value("${oas.info.description}") String infoDescription;
    private @Value("${oas.info.version}") String infoVersion;
    private @Value("${oas.info.termsOfService}") String infoTermsOfService;
    private @Value("${oas.license.name}") String licenseName;
    private @Value("${oas.license.url}") String licenseUrl;
    private @Value("${oas.contact.name}") String contactName;
    private @Value("${oas.contact.url}") String contactUrl;
    private @Value("${oas.contact.email}") String contactEmail;
    private @Value("${oas.externaldocs.description}") String externaldocsDescription;
    private @Value("${oas.externaldocs.url}") String externaldocsUrl;


    /**
     * Creates and returns an Open API configuration
     *
     * @return the Open API setup
     */
    @Bean
    public OpenAPI openApi() {

        return new OpenAPI()
                .addServersItem(getServerInfo())
                .info(getApiInfo())
                .externalDocs(getExternalDocInfo());
    }

    /**
     * Returns server details
     *
     * @return the Server object with everything needed
     */
    private Server getServerInfo() {

        return new Server()
                .url(contextPath);
    }

    /**
     * Returns the api info to be used in the API doc generation
     *
     * @return the ApiInfo object with everything needed
     */
    private Info getApiInfo() {

        return new Info()
                .title(infoName)
                .description(infoDescription)
                .version(infoVersion)
                .license(getLicenseInfo())
                .termsOfService(infoTermsOfService)
                .contact(getContactInfo());
    }

    /**
     * Returns api license details
     *
     * @return the License object with everything needed
     */
    private License getLicenseInfo() {

        return new License().name(licenseName)
                .url(licenseUrl);
    }

    /**
     * Returns api lecense details
     *
     * @return the Contact object with everything needed
     */
    private Contact getContactInfo() {

        return new Contact()
                .name(contactName)
                .email(contactEmail)
                .url(contactUrl);
    }

    /**
     * Returns api external documentation info
     *
     * @return the ExternalDocumentation object with everything needed
     */
    private ExternalDocumentation getExternalDocInfo() {

        return new ExternalDocumentation()
                .description(externaldocsDescription)
                .url(externaldocsUrl);
    }

}
