package com.tienda;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {
    /* Los siguientes métodos son para incorporar el tema de internacionalización en el proyecto */
    
    /* localeResolver se utiliza para crear una sesión de cambio de idioma*/
    @Bean
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");
        return slr;
    }

    /* localeChangeInterceptor se utiliza para crear un interceptor de cambio de idioma*/
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registro) {
        registro.addInterceptor(localeChangeInterceptor());
    }

    //Bean para poder acceder a los Messages.properties en código...
    @Bean("messageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource= new ResourceBundleMessageSource();
        messageSource.setBasenames("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    /* Los siguientes métodos son para implementar el tema de seguridad dentro del proyecto */
    @Anular
    public void addViewControllers(ViewControllerRegistry registro) {
        registro.addViewController("/").setViewName("index");
        registro.addViewController("/index").setViewName("index");
        registro.addViewController("/iniciar sesión").setViewName("iniciar sesión");
        registro.addViewController("/registro/nuevo").setViewName("/registro/nuevo");
 }

@Frijol
    Public SecurityFilterChain securityFilterChain (HttpSecurity http) arroja una excepción {
        http
                .authorizeHttpRequests((solicitud) -> solicitud
                .requestMatchers("/","/index","/errores/**",
                        "/carrito/**","/pruebas/**","/reportes/**",
                        "/registro/**","/js/**","/webjars/**")
                        .permitAll()
                .requestMatchers(
                        "/producto/nuevo","/producto/guardar",
                        "/producto/modificar/**","/producto/eliminar/**",
                        "/categoria/nuevo","/categoria/guardar",
                        "/categoria/modificar/**","/categoria/eliminar/**",
                        "/usuario/nuevo","/usuario/guardar",
                        "/usuario/modificar/**","/usuario/eliminar/**",
                        "/informes/**"
                ).hasRole("ADMINISTRADOR")
                .requestMatchers(
                        "/producto/listado",
                        "/categoria/listado",
                        "/usuario/listado"
                ).hasAnyRole("ADMINISTRADOR", "PROVEEDOR")
                .requestMatchers("/facturar/carrito")
                .hasRole("USUARIO")
                )
                .formLogin((formulario) -> formulario
                .loginPage("/login").permitAll())
                .logout((cerrar sesión) -> cerrar sesión.permitAll());
        volver http.construir();
    }

/* El siguiente metodo se utiliza para completar la clase no es
    realmente funcional, la próxima semana se reemplazará con los usuarios de BD */    
    @Frijol
    Usuarios públicos de UserDetailsService () {
        UserDetails admin = Usuario.constructor()
                .nombre de usuario("juan")
                .contraseña("{noop}123")
                .roles("USUARIO", "PROVEEDOR", "ADMINISTRADOR")
                .construir();
        Ventas UserDetails = User.builder()
                .nombre de usuario("rebeca")
                .contraseña("{noop}456")
                .roles("USUARIO", "PROVEEDOR")
                .construir();
        UserDetails usuario = Usuario.constructor()
                .nombre de usuario("pedro")
                .contraseña("{noop}789")
                .roles("USUARIO")
                .construir();
        devolver nuevo InMemoryUserDetailsManager (usuario, ventas, administrador);
    }
}